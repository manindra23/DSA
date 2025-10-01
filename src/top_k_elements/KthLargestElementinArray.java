package top_k_elements;

import java.util.PriorityQueue;

public class KthLargestElementinArray {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i=0; i< nums.length; i++) {
            if(minHeap.size() < k) {
                minHeap.offer(nums[i]);
            } else {
                if(nums[i] > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(nums[i]);
                }
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {10,-5,0,-8,4};
        int k = 3;
        System.out.println("KthLargestElementinArray:" + KthLargestElementinArray.findKthLargest(nums, k));
    }
}
