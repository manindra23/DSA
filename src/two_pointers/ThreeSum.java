package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    /*
    -Given an integer array, nums, find and return all unique triplets [nums[i], nums[j], nums[k]], such that i!=j, i!=k, j!=k and nums[i] + nums[j] + nums[k] = 0
    -Constraints:
        3<= nums.length <=500
        -10^3 <= nums[i] <= 10^3
    */
    private static List<List<Integer>> ThreeSumSolution(int[] num) {
        //sort the array
        Arrays.sort(num);

        List<List<Integer>> outputArray = new ArrayList<>();
        if(num[0] >= 0) {
            System.out.println("No triplets possible");
            return outputArray;
        }
        int i = 0;
        while(i < num.length-2) {
            //skip duplicate element for i
            while(i > 0 && num[i] == num[i-1]) {
                i++;
            }
            int left = i+1, right = num.length-1;

            while(left < right) {
                List<Integer> tripletList = new ArrayList<>();
                if (num[i] + num[left] + num[right] < 0) {
                    left++;
                } else if (num[i] + num[left] + num[right] > 0) {
                    right--;
                } else {
                    if(num[i] != num[left] && num[left] != num[right] && num[i] != num[right]) {
                        tripletList.add(num[i]);
                        tripletList.add(num[left]);
                        tripletList.add(num[right]);
                    }

                    left++;
                }
                if(!tripletList.isEmpty()) {
                    outputArray.add(tripletList);
                }

                //skip duplicate element for left pointer
                while(left > 1 && left < num.length-1 && num[left] == num[left-1]) {
                    left++;
                }

                //skip duplicate element for right pointer
                while(right < num.length-1 && num[right] == num[right+1]) {
                    right--;
                }
            }
            i++;
        }
        return outputArray;

    }
    public static void main(String[] args) {
        int[][] numsArrs = {
                {-1, 0, 1, 2, -1, -4},
                {1, 2, 3, 4, 5},
                {0, 0, 0, 0},
                {-4, -1, -1, 0, 1, 2, 2},
                {-10, -7, -3, -1, 0, 3, 7, 10},
                {-3, -5, -7, -9}
        };

        for(int i = 0; i< numsArrs.length; i++) {
            System.out.println("--------------------------------");
            System.out.println("inputList:");
            for(int j = 0; j < numsArrs[i].length; j++)
            {
                System.out.print(numsArrs[i][j] + " ");
            }
            System.out.println();
            System.out.println("--------------------------------");
            List<List<Integer>> outputList = ThreeSumSolution(numsArrs[i]);

            System.out.println("--------------------------------");
            System.out.println("outputList:" + outputList);
            System.out.println("--------------------------------");
        }
    }
}
