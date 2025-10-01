package sliding_window;

import java.util.*;
import java.util.stream.Stream;


public class SlidingWindowMaximum {
    public static int[] slidingWindowMaximum(int[] nums, int w) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        List<Integer> outputList = new ArrayList<>();
        Map<Integer, Integer> freqMap = new HashMap<>();

        //adding first w elements into maxHeap
        for (int i = 0; i < w; i++) {
            maxHeap.offer(nums[i]);
        }
        outputList.add(maxHeap.peek());

        for (int i = 1; i <= nums.length - w; i++) {
            //registering removal of leftmost window element into hashmap
            if (!freqMap.containsKey(nums[i - 1])) {
                freqMap.put(nums[i - 1], 1);
            } else {
                freqMap.put(nums[i - 1], freqMap.get(nums[i - 1]) + 1);
            }

            //addition of rightmost element of window
            maxHeap.offer(nums[i + w - 1]);

            while (!maxHeap.isEmpty() && freqMap.containsKey(maxHeap.peek()) && freqMap.get(maxHeap.peek()) > 0) {
                freqMap.put(maxHeap.peek(), freqMap.get(maxHeap.poll()) - 1);
            }

            outputList.add(maxHeap.peek());
        }

        return outputList.stream().mapToInt(Integer::intValue).toArray();
    }


    //***Your solution is correct now but find out why you had to move max element removal before the cleaning up operation while in solution code
    //they remove max element  after cleaning up operation.

    //Note that if your code maintain current window list by values and not indices, then it will fail for input {1, 3, 1, 3, 1}, w=3 because
    // it accidentally removes max element of current window [indices (2,3,4)] based on element(index=1) that just moved out of the window
    // while actually it was already removed when index=3 was compared to current window list elements at that moment.
    public static int[] slidingWindowMaximumOptimumMethod(int[] nums, int w) {
        List<Integer> outputList = new ArrayList<>();
        Deque<Integer> currentWindowQueue = new ArrayDeque<>();
        boolean condition = true;

        for (int i = 0; i < nums.length; i++) {
            if (currentWindowQueue.isEmpty()) {
                currentWindowQueue.add(i);
                if(w==1) {
                    outputList.add(nums[i]);
                }
            } else {
                //if max element i.e. front element in current window queue matches the element that moved out
                // of the window, then remove the element from current window queue
                //We do this first so that in next step, when we compare new element to current window queue elements,
                //result does not get impacted due to max element from older window.
                if ((i - w) >= 0 && currentWindowQueue.getFirst() == i - w) {
                    currentWindowQueue.removeFirst();
                }

                //check if new element is greater than all elements in current window queue
                final int k = i;
                currentWindowQueue.removeIf(index-> nums[index] <= nums[k]);
                currentWindowQueue.add(k);

                //add max element i.e. front element of current window to output list
                if (i >= w - 1 && !currentWindowQueue.isEmpty()) {
                    outputList.add(nums[currentWindowQueue.getFirst()]);
                }
            }
        }
        return outputList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int windowSizes [] = {3, 3, 3, 3, 2, 4, 3, 2, 3, 6, 3, 3};
        int [][] numLists = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
                {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                {1, 5, 8, 10, 10, 10, 12, 14, 15, 19, 19, 19, 17, 14, 13, 12, 12, 12, 14, 18, 22, 26, 26, 26, 28, 29, 30},
                {10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67},
                {4, 5, 6, 1, 2, 3},
                {9, 5, 3, 1, 6, 3},
                {2, 4, 6, 8, 10, 12, 14, 16},
                {-1, -1, -2, -4, -6, -7},
                {4, 4, 4, 4, 4, 4},
                {1, 3, 1, 2, 0, 5},
                {1, 3, 1, 3, 1}
        };

        for (int i = 0; i < numLists.length; i++) {
            System.out.println(i + 1 + ".\tInput array:\t" + Arrays.toString(numLists[i]));
            System.out.println("\tWindow size:\t" + windowSizes[i]);
            System.out.println("\n\tMaximum in each sliding window:\t" + Arrays.toString(slidingWindowMaximumOptimumMethod(numLists[i], windowSizes[i])));
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println();
        }
    }
}
