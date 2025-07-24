package two_pointers;

public class CountSubarraysWithFixedBounds {
    /*Given an integer array, nums, and two integers minK and maxK, return the number of fixed-bound subarrays.
      A subarray in nums is called a fixed-bound subarray if it satisfies the following conditions:
        (1) The smallest value in the subarray equals minK
        (2) The largest value in the subarray equals maxK.
        Note: A subarray is a contiguous sequence of elements within an array.

        Constraints:
         2<=nums.length<=10^3
         1<=nums[i], minK, maxK<=10^3
     */

    private static int countSubarraysWithFixedBounds(int[] nums, int minK, int maxK) {
        int i = 0, minPos = -1, maxPos = -1, invalidElementPos = -1;
        int count = 0;
        while (i <= nums.length - 1) {
            if (nums[i] == minK) {
                minPos = i;
            }

            if (nums[i] == maxK) {
                maxPos = i;
            }

            if (nums[i] < minK || nums[i] > maxK) {
                invalidElementPos = i;
            }

            if (nums[i] >= minK && nums[i] <= maxK && minPos != -1 && maxPos != -1) {
                count = count + Math.max(0, Math.min(maxPos, minPos) - invalidElementPos);
            }
            i++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 3, 5, 2, 7, 5},
                {1, 5},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4},
                {1, 5, 1, 5, 1, 5}
        };

        int[] minKs = {1, 1, 1, 2, 1};
        int[] maxKs = {5, 5, 1, 5, 5};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Count of fixed bound subarrays:" + countSubarraysWithFixedBounds(testCases[i], minKs[i], maxKs[i]));
        }
    }
}
