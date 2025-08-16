package top_k_elements;

/*
Given an infinite stream of integers (sorted or unsorted), nums, design a class to find the kth largest element in a stream.
Note: It is the kth largest element in the sorted order, not the kth distinct element
The class should have the following functions, inputs, and return values:
    +Init(nums, k): It takes an array of integers nums and an integer k and initializes the class object.
    +Add(value): It takes one integer value, appends it to the stream, and returns the element representing the kth largest element in the stream
Constraints:
    + 1<=k<=10^3
    + 0<=nums.length<=10^3
    + -10^3 <= nums[i] <=10^3
    + -10^3 <= value <= 10^3
    + At most, 10^3 calls will be made to add
    + It is guaranteed that there will be at least k elements in the array when you search for the kth element
 */

import java.util.PriorityQueue;

public class KthLargestElementInStream {
    /*Naive Method
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    List<Integer> removedElements;
    private int k;
    private void init(int[] nums, int k) {
            for(int num: nums) {
                maxHeap.offer(num);
            }
            this.k= k;
            removedElements = new ArrayList<>();
    }

    private int add(int value) {
        maxHeap.offer(value);
        if(k > maxHeap.size()) {
            return maxHeap.peek();
        }
        for(int i = 0; i< k-1; i++) {
            removedElements.add(maxHeap.poll());
        }
        int result = !maxHeap.isEmpty()?maxHeap.peek(): -1;
        for(int num: removedElements) {
            maxHeap.offer(num);
        }
        removedElements.clear();
        return result;
    }*/

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private int k;
    private void init(int[] nums, int k) {
        this.k= k;
        for(int num: nums) {
            add(num);
        }
    }

    private int add(int value) {
        if(minHeap.isEmpty() || minHeap.size() < k) {
            minHeap.offer(value);
            return minHeap.peek();
        }

        if(value > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(value);
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        KthLargestElementInStream kthLargest = new KthLargestElementInStream();
        kthLargest.init(new int[]{4,5,8,2}, 3);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }
}
