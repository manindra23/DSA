package heaps;

import java.util.*;

/*
Given an integer array, nums, and an integer, k, there is a sliding window of size k, which is moving from the very left to the very right of the array.
We can only see the k numbers in the window. Each time the sliding window moves right by one position.
Given this scenario, return the median of the each window. Answers within 10^-5 of the actual value will be accepted.

Constraints:
    * 1<=k<=nums.length<=10^3
    * -2^31 <=nums[i]<=(2^31)-1
 */
public class SlidingWindowMedian {
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    private static int balance = 0;

    public static double[] slidingWindowMedian(int[] nums, int k) {
        minHeap.clear();
        maxHeap.clear();
        //inserting all elements into maxHeap
        for(int i = 0; i <k; i++) {
            maxHeap.offer(nums[i]);
        }
        //move k/2 elements to minHeap
        for(int i =0; i< k/2; i++) {
            minHeap.offer(maxHeap.poll());
        }

        int left = 0, right = k-1;
        double[] output = new double[nums.length-k+1]; //size of output array would be number of movements(size of input array - k) + one median which we find at the start(1)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        while(right <= nums.length-1) {
            //finding median
            output[left] = findMedian(k);

            if(right < nums.length-1) {
                //moving window one step to the right

                //registering to be removed element in hashmap with its frequency
                if(frequencyMap.containsKey(nums[left])) {
                    frequencyMap.put(nums[left], frequencyMap.get(nums[left]) + 1);
                } else {
                    frequencyMap.put(nums[left], 1);
                }

                //maintain balance counter to ensure that max-heap has either the same number of elements as the min-heap (for even k) or one more element (for odd k).
                if(nums[left] <= maxHeap.peek()) {
                    balance--;
                } else {
                    balance++;
                }

                //insert the new element
                insertNum(nums[right + 1]);

                // re-balancing both heaps according to value of balance
                // Note: balance can be incremented twice: once when removing an element greater than the max-heap root, and once when adding a new element
                // less than the max-heap root, so balance variable can be +2 or -2 in rare cases. In practice,the code assumes balance will only be ±1.
                // If balance becomes ±2, the single re-balancing step might not be sufficient, and additional re-balancing would be needed. The solution
                // relies on the fact that the sliding window move one element at a time, so such large imbalance is uncommon. But if you want to handle
                // this strictly, you could repeat the re-balancing step until balance is 0, ensuring the heaps are properly balanced.
                if(balance > 0) {
                    minHeap.offer(maxHeap.poll());
                }
                if(balance < 0) {
                    maxHeap.offer(minHeap.poll());
                }
                //resetting balance to zero
                balance = 0;

                //remove those elements from heap which are marked for removal in frequencyMap
                while(!maxHeap.isEmpty() && frequencyMap.containsKey(maxHeap.peek()) && frequencyMap.get(maxHeap.peek()) > 0) {
                    frequencyMap.put(maxHeap.peek(), frequencyMap.get(maxHeap.poll()) - 1); //removing from heap and updating frequency map in single line
                }

                while(!minHeap.isEmpty() && frequencyMap.containsKey(minHeap.peek()) && frequencyMap.get(minHeap.peek()) > 0) {
                    frequencyMap.put(minHeap.peek(), frequencyMap.get(minHeap.poll()) - 1); //removing from heap and updating frequency map in single line
                }
            }
            left++;
            right++;
        }

        return output;
    }

    private static void insertNum(int element) {
        if(maxHeap.isEmpty() || element <= maxHeap.peek()) {
            maxHeap.offer(element);
            balance++;
        } else {
            minHeap.offer(element);
            balance--;
        }
    }

    private static double findMedian(int windowSize) {
        //(1) Note that following logic to find median won't work for this problem because the element marked for removal from heap may not have got actually removed;
        //it will be removed only if it reached top of either heaps. So finding median based on comparison of heap sizes won't work here as actual sizes may be different
        //from expected size of maxHeap = minHeap+1
        //(2) Also, if element marked for removal is not at the top, it does not impact median calculation provided we do it based on window size.
        //If window size is even, then median is mean of maxHeap root and minHeap root, else we take root of maxHeap.
        //We use window size, because we added elements to maxHeap and minHeap in such a way that maxHeap size = minHeap size for even window and
        //maxHeap size = minHeap size + 1 for odd window. And later on we maintain this difference logically using balance (not physically because may be element marked
        //for removal is not at the root, so it might not get removed)
        /*if(!maxHeap.isEmpty() && maxHeap.size() == minHeap.size()) {
            return (double) ((maxHeap.peek() + minHeap.peek())/2.0);
        } else {
            return (double) (!maxHeap.isEmpty() ? maxHeap.peek(): 0.0);
        }*/

        //using window size to find median
        if((windowSize & 1) == 0) { //even size window
            return (double) ((maxHeap.peek() + minHeap.peek())/2.0);
        } else { //odd size window
            return (double) maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        int [][]arr = {{3, 1, 2, -1, 0, 5, 8}, {1, 2}, {4, 7, 2, 21}, {22, 23, 24, 56, 76, 43, 121 ,1 ,2 ,0 ,0 ,2 ,3 ,5}, {1, 1, 1, 1, 1}};
        int[] k = {4, 1, 2, 5, 2};
        for(int i=0; i<k.length; i++){
            System.out.print(i+1);
            System.out.println(".\tInput array = " + Arrays.toString(arr[i]) + ", k = " + k[i]);
            double[] output = slidingWindowMedian(arr[i], k[i]);
            System.out.println("\tMedians = " + Arrays.toString(output));
            System.out.println("---------------------------------------------------------------------------");
        }
    }
}
