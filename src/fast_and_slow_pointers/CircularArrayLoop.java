package fast_and_slow_pointers;

import java.util.Arrays;

public class CircularArrayLoop {

    //Only works for array having only positive numbers; very inefficient
    public static boolean circularArrayLoopNaiveMethod(int[] nums) {
        for(int i = 0; i< nums.length; i++) {
            //next index
            int nextIndex = i;
            int sum = 0;
            sum = sum - i;
            while(true) {
                sum = sum + nums[nextIndex];
                int step = nums[nextIndex] % (nums.length);
                if((nextIndex + step) <= nums.length-1) {
                    nextIndex = nextIndex + step;
                } else {
                    int diff = (nextIndex + step) - (nums.length);
                    nextIndex = diff;
                }
                if(nextIndex == i) {
                    return true;
                }
                if(sum >= nums.length && nextIndex > i) {
                    break;
                }
            }
        }
        return false;
    }


    public static boolean circularArrayLoopOptimumMethod(int[] nums) {
        int size = nums.length;
        for(int i = 0; i<nums.length; i++) {
            int fast = i, slow = i;
            boolean forward = nums[i] > 0;

            while(true) {
                slow = nextStep(slow, nums[slow], size);
                if(isNotCycle(slow, nums, forward)) {
                    break;
                }
                fast = nextStep(fast, nums[fast], size);
                if(isNotCycle(fast, nums, forward)) {
                    break;
                }

                fast = nextStep(fast, nums[fast], size);
                if(isNotCycle(fast, nums, forward)) {
                    break;
                }

                if(fast == slow) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int nextStep(int pointer, int value, int size) {
        int result = (pointer + value) % size;
        if(result < 0) {
            result = result + size;
        }
        return result;
    }

    private static boolean isNotCycle(int pointer, int[] nums, boolean previousDirection) {
        boolean currentDirection = nums[pointer] > 0;
        if(currentDirection != previousDirection || nums[pointer] % nums.length == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] input = {
                {-2, -3, -9},
                {-5, -4, -3, -2, -1},
                {-1, -2, -3, -4, -5},
                {2, 1, -1, -2},
                {-1, -2, -3, -4, -5, 6},
                {1, 2, -3, 3, 4, 7, 1},
                {2, 2, 2, 7, 2, -1, 2, -1, -1}
        };

        for (int i = 0; i < input.length; i++) {
            System.out.println((i + 1) + ".\tCircular array = " + Arrays.toString(input[i]) + "\n");
            boolean result = circularArrayLoopOptimumMethod(input[i]);
            System.out.println("\tFound Loop = " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
