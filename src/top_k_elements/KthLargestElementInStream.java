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

    private PriorityQueue<Integer> minHeap;
    private int k;
    public KthLargestElementInStream(int[] nums, int k) {
        this.k= k;
        this.minHeap = new PriorityQueue<>();
        for(int num: nums) {
            add(num);
        }
    }

    public int add(int value) {
        if(minHeap.size() < k) {
            minHeap.offer(value);
        } else if(value > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(value);
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 10};
        KthLargestElementInStream kLargest = new KthLargestElementInStream(nums, 3);
        int[] val = {4, 7, 10, 8, 15};
        for (int i = 0; i < val.length; i++) {
            System.out.println("\tAdding a new number " + val[i] + " to the stream");
            System.out.println("\n\tKth largest element in the stream: " + kLargest.add(val[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
