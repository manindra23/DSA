package heaps;

import java.util.*;

/*
An investor is looking to maximize their capital by undertaking a set of profitable projects. Due to limited time and resources, they can complete at most k distinct projects.

There are n available projects. Each project i has:

    +A profit of profits[i] earned upon completion.
    +A minimum capital requirement of capital[i] needed to start the project.

The investor starts with an initial capital of c. After completing a project, its profit is immediately added to the investor's current capital.
The goal is to choose up to k different projects in a way that maximizes the investor’s final capital. Return the maximum capital achievable after completing these projects.
It is guaranteed that the answer fits within a 32-bit signed integer.

Constraints:
    +1<=k<=10^3
    +0<=c<=10^9
    +n==profits.length
    +n==capitals.length
    +1<=n<=10^3
    +0<=profits[i]<=10^4
    +0<=capitals[i]<=10^9
 */
public class IPO {
    public static int maximumCapital(int k, int c, int[] capitals, int[] profits) {
        Comparator<Map.Entry<Integer, Integer>> keyComparator =
                Comparator.comparingInt(Map.Entry::getKey);
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(keyComparator);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i< capitals.length; i++) {
            minHeap.add(new AbstractMap.SimpleEntry<>(capitals[i], profits[i]));
        }

        for(int i=0; i< k; i++) {
            while(!minHeap.isEmpty() && c >= minHeap.peek().getKey()) {
                maxHeap.add(minHeap.poll().getValue());
            }
            if(!maxHeap.isEmpty()) {
                c = c + maxHeap.poll();
            }
        }
        return c;
    }
    public static void main(String[] args) {
        int[] c = { 0, 1, 1, 2, 1, 7, 2 };
        int[] k = { 1, 3, 2, 3, 3, 2, 4 };
        int[][] capitals = {
                { 1, 1, 2 },
                { 0, 1, 2 },
                { 1, 2, 2, 3 },
                { 1, 3, 4, 5, 6 },
                { 1, 2, 3, 4 },
                { 6, 7, 8, 10 },
                { 2, 3, 5, 6, 8, 12 }
        };
        int[][] profits = {
                { 1, 2, 3 },
                { 1, 2, 3 },
                { 2, 4, 6, 8 },
                { 1, 2, 3, 4, 5 },
                { 1, 3, 5, 7 },
                { 4, 8, 12, 14 },
                { 1, 2, 5, 6, 8, 9 }
        };

        for (int i = 0; i < k.length; i++) {
            System.out.println((i + 1) + ".\tProject capital requirements: " + Arrays.toString(capitals[i]));
            System.out.println("\tProject expected profits: " + Arrays.toString(profits[i]));
            System.out.println("\tNumber of projects: " + k[i]);
            System.out.println("\tStart-up capital: " + c[i]);
            System.out.println("\n\tMaximum Capital earned: " + maximumCapital(k[i], c[i], capitals[i], profits[i]));
            System.out.println("------------------------------------------------------------------------------------");
        }

    }
}
