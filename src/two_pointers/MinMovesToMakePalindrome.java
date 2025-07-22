package two_pointers;


/*
Given a string s, return the minimum number of moves required to transform s into a palindrome. In each move, you can swap any two adjacent characters in s.
Note: The input string is guaranteed to be convertible into a palindrome.

Constraints:
    + 1<=s.length<=2000
    + s consists of only lowercase English letters.
    + s is guaranteed to be converted into a palindrome in a finite number of moves.
 */
public class MinMovesToMakePalindrome {

    private static int mimMovesToMakePalindrome(String str) {
        int lastIndex = str.length()-1;
        char[] strArray = str.toCharArray();
        int i = 0, j = lastIndex;
        int moves = 0;
        boolean found = false;
        while(i < j) {
            System.out.println("i=" + i + ", j = " + j + " strArray=" + String.valueOf(strArray));
            int k = j;
            while(k > i) {
                System.out.println("i=" + i + ", k = " + k + " strArray=" + String.valueOf(strArray));
                if(strArray[k] == strArray[i]) {
                    System.out.println("Match found..");
                    while (k < j) {
                        char temp = strArray[k+1];
                        strArray[k+1] = strArray[k];
                        strArray[k] = temp;
                        System.out.println("char at " + k + " swapped with adjacent char at " + (k+1));
                        moves++;
                        k++;
                    }
                    j--;
                    i++;
                    found = true;
                    break;
                } else {
                    k--;
                }
            }
            if(!found) {
                System.out.println("No Match found..");
                int m = i;
                while(m < lastIndex/2) {
                    char temp = strArray[m + 1];
                    strArray[m + 1] = strArray[m];
                    strArray[m] = temp;
                    System.out.println("char at " + m + " swapped with adjacent char at " + (m + 1));
                    m++;
                    moves++;
                }
            }
            found = false;
        }
        return moves;
    }
    public static void main(String[] args) {
        System.out.println("Min moves to make palindrome:" + mimMovesToMakePalindrome("eggeekgbbeg"));
    }
}
