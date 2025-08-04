package heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public static int[] slidingWindowMedian(int[] nums, int k) {
        //inserting all elements into either maxHeap or minHeap
        for(int element: nums) {
            if(maxHeap.isEmpty() || element <= maxHeap.peek()) {
                maxHeap.add(element);
            } else {
                minHeap.add(element);
            }
            //re-balance
            if(maxHeap.size() > minHeap.size() + 1) {
                minHeap.add((maxHeap.poll()));
            } else if(minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        int left = 0, right = k;
        double[] output = new double[nums.length];
        while(k <= nums.length-1) {
            output[left] = findMedian();
            //how to remove left element from heap
            //add right element to heap
        }

    }

    private static double findMedian() {
        if(!maxHeap.isEmpty() && maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek())/2.0;
        } else {
            return !maxHeap.isEmpty() ? maxHeap.peek(): 0.0;
        }
    }

    public static void main(String[] args) {

    }
}
