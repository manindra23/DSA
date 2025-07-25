package two_pointers;

import java.util.Arrays;
import java.util.List;

/*
Write a function that takes a string as input and checks whether it can be a valid palindrome by removing at most one character from it.
Constraints:
 + 1<= string.length <=10^5
 + The string only consists of English letters
 */
public class ValidPalindrome2 {

    //This method misses one scenario where in case of mismatch, both of the following are true at the same time
    //(a) next char at left matches current char at right and (b) next char at right matches current char at left
    //Hence it fails for "ABBAB"
    public static boolean validPalindrome2Method1(String str) {
        int i=0, j=str.length() - 1;
        int removedCharCount = 0;
        while(i < j) {
            if(str.charAt(i) != str.charAt(j)) {
                if(removedCharCount >=1) {
                    return false;
                }
                if(str.charAt(i+1) == str.charAt(j)) {
                    i++;
                    removedCharCount++;

                } else if(str.charAt(i) == str.charAt(j-1)) {
                    j--;
                    removedCharCount++;
                } else {
                    return false;
                }
            } else {
                i++;
                j--;
            }
        }
        return true;
    }


    //This method handles the scenario missed by method-1 where in case of mismatch, both of the following are true at the same time
    //(a) next char at left matches current char at right and (b) next char at right matches current char at left
    public static boolean validPalindrome2Method2(String str) {
        int left = 0, right = str.length() - 1;
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) {
                return isPalindrome(str, left+1, right) || isPalindrome(str, left, right-1);
            }
            left++;
            right--;
        }

        return true;
    }

    private static boolean isPalindrome(String str, int left, int right) {
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        List<String> inputList = Arrays.asList("ABCEBA", "RACEACAT", "DEEAD", "RACEACAR", "abbababa", "abcdeca", "ABBAB"
        );
        for (String s : inputList) {
            System.out.println("Is " + s + " a valid palindrome after removing at most one char:" + validPalindrome2Method2(s));
        }
    }
}
