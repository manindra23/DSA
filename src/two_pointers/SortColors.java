package two_pointers;

import java.util.Arrays;

/*
Given an array, colorList, which contains a combination of the following three elements: 0 (Representing red), 1 (Representing white), 2 (Representing blue).
Sort the array in place so that the elements of the same color are adjacent, and the final order is: red (0), then white (1), and then blue (2).
Note: You are not allowed to use any built-in sorting functions. The goal is to solve this efficiently without extra space

Constraints:
    (1) n == colorList.length
    (2) 1<= n <=300
    (3) colorList[i] is either 0, 1, or 2
 */

public class SortColors {

    private static int[] sortColorsWithOptimumMethod(int[] colorList) {
        int current = 0, left = 0, right = colorList.length-1;
        while(current <= right) {
            if(colorList[current] == 0) {
                int temp = colorList[left];
                colorList[left] = colorList[current];
                colorList[current] = temp;
                left++;
                current++;
                //current pointer can be incremented here since swapped elements from left have already been analyzed by current pointer
            } else if(colorList[current] == 1) {
                current++;
            } else {
                int temp = colorList[right];
                colorList[right] = colorList[current];
                colorList[current] = temp;
                right--;
                //current pointer not to be incremented because swapped element from right has to be analyzed if further swapping is required.
            }
        }

        return colorList;
    }

    public static void main(String[] args) {
        int[] outputList = sortColorsWithOptimumMethod(new int[] {1,0,2,1,2,2});
        System.out.println("Sorted Colors:" + Arrays.toString(outputList));
    }
}
