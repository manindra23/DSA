package top_k_elements;

import java.util.*;

public class TopKFreqElements {
    public static List<Integer> topKFrequent(int[] arr, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> e1.getValue().compareTo(e2.getValue()));

        for (int i = 0; i < arr.length; i++) {
            if (freqMap.containsKey(arr[i])) {
                freqMap.replace(arr[i], freqMap.get(arr[i]) + 1);
            } else {
                freqMap.put(arr[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else {
                if (entry.getValue() > minHeap.peek().getValue()) {
                    minHeap.poll();
                    minHeap.offer(entry);
                }
            }
        }

        List<Integer> topKFreqElements = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            topKFreqElements.add(minHeap.poll().getKey());
        }

        return topKFreqElements;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] inputs = {
                {1, 3, 5, 12, 11, 12, 11, 12, 5},
                {1, 3, 5, 14, 18, 14, 5},
                {2, 3, 4, 5, 6, 7, 7},
                {9, 8, 7, 6, 6, 5, 4, 3, 2, 1},
                {2, 4, 3, 2, 3, 4, 5, 4, 4, 4},
                {1, 1, 1, 1, 1, 1},
                {2, 3}
        };
        int[] inputK = {3, 2, 1, 1, 3, 1, 2};
        for (int i = 0; i < inputK.length; i++) {
            List<Integer> result = topKFrequent(inputs[i], inputK[i]);
            System.out.print(i + 1);
            System.out.println(".\tInput: (" + Arrays.toString(inputs[i]) + ", " + inputK[i] + ")");
            System.out.println("\n\tTop " + inputK[i] + " frequent elements: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
