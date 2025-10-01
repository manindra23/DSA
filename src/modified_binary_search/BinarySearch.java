package modified_binary_search;

import java.util.Arrays;

/*
We are given an array of integers, nums, sorted in ascending order, and an integer value, target.
If the target exists in the array, return its index. If the target does not exist, return -1.

Constraints:
    + -1 <= nums.length<=10^3
    + -10^4 <= nums[i], target <=10^4
    + All integer in nums are unique
    + nums is sorted in ascending order
 */
public class BinarySearch {
    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        while(left <= right) {
            int mid = left + (right-left)/2;
            if(target == nums[mid]) {
                return mid;
            }

            if(target < nums[mid]) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] numsLists = {
                {1},
                {0, 1},
                {1, 2, 3},
                {-1, 0, 3, 5, 9, 12},
                {-100, -67, -55, -50, -49, -40, -33, -22, -10, -5}
        };

        int[] targetList = {12, 1, 3, 9, -22};

        for (int i = 0; i < numsLists.length; i++) {
            int[] nums = numsLists[i];
            int target = targetList[i];
            int index = binarySearch(nums, target);
            System.out.println((i + 1) + ".\tArray to search: " + Arrays.toString(nums));
            System.out.println("\tTarget: " + target);
            if (index != -1) {
                System.out.println("\t" + target + " exists in the array at index " + index);
            } else {
                System.out.println("\t" + target + " does not exist in the array, so the return value is " + index);
            }
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
