package two_pointers;

import java.util.*;

/*
You are given a 0-indexed integer array, nums, of length n, and an integer target. Your task is to determine the number of distinct pairs of indexes (i,j) such that:
    + 0<= i < j < n
    + The sum of the elements of the indexes (i,j) i.e. nums[i] + nums[j] is strictly less than the target

    Constraints:
        + n = nums.length
        + 1 <= n <= 50
        + -50 <= nums[i], target <=50
 */
public class PairsWithSumLessThanTarget {

    private static Set<Map.Entry<Integer, Integer>> pairsWithSumLessThanTargetNaiveMethod(int [] nums, int target) {
        int i = 0;
        Set<Map.Entry<Integer, Integer>> outputDistinctPairs = new HashSet<>();
        while(i < nums.length - 1) {
            int j = nums.length - 1;
            while(j > i) {
                if(nums[i] + nums[j] < target) {
                    outputDistinctPairs.add(new AbstractMap.SimpleEntry<>(i, j));
                }
                j--;
            }
            i++;
        }
        return outputDistinctPairs;
    }

    private static Set<Map.Entry<Integer, Integer>> pairsWithSumLessThanTargetOptimizedMethod(int [] nums, int target) {
        int i = 0;
        Arrays.sort(nums);
        Set<Map.Entry<Integer, Integer>> outputPairs = new HashSet<>();
        int  j = nums.length - 1;
        while(i < j) {
            if(nums[i] + nums[j] < target) {
                for(int k = j; k>i; k--) {
                    outputPairs.add(new AbstractMap.SimpleEntry<>(i, k));
                }
                i++;
            } else {
                j--;
            }
        }
        return outputPairs;
    }
    public static void main(String[] args) {
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(10, 1, 6, 2, 3, 8),
                Arrays.asList(1, 3, 5, 7),
                Arrays.asList(1, 2, 3, 6),
                Arrays.asList(2, 4, 6, 8, 10),
                Arrays.asList(5, 1, 9, 2)
        );

        List<Integer> targets = Arrays.asList(9, 8, 6, 12, 10);
        for(int i = 0; i< testCases.size(); i++) {
            Integer[] inputArr = testCases.get(i).stream().toArray(Integer[]::new);
            int[] inputArr2 = Arrays.stream(inputArr).mapToInt(Integer::intValue).toArray();

            //System.out.println(pairsWithSumLessThanTargetNaiveMethod(inputArr2, targets.get(i)));
            System.out.println(pairsWithSumLessThanTargetOptimizedMethod(inputArr2, targets.get(i)));

        }
    }
}
