package two_pointers;

import java.util.HashMap;
import java.util.Map;

/*
Given a string num representing an integer, determine whether it is a strobogrammatic number. Return TRUE if the number is strobogrammatic or FALSE if it is not.

Note: A strobogrammatic number appears the same when rotated 180 degrees (viewed upside down). For example, “69” is strobogrammatic because it looks
the same when flipped upside down, while “962” is not.

Constraints:
    + 1<= num.length <=50
    + num contains only digits
    + num has no leading zeroes except when the number itself is 0
 */
public class StrobogrammaticNumber {

    private static boolean isStrobogrammaticNumber(String number) {
        Map<Character, Character> charRotationMap = new HashMap<>();
        charRotationMap.put('0', '0');
        charRotationMap.put('1', '1');
        charRotationMap.put('6', '9');
        charRotationMap.put('8', '8');
        charRotationMap.put('9', '6');

        char [] charArray = number.toCharArray();
        int left = 0, right = charArray.length - 1;

        while(left <= right) {
            if(!charRotationMap.containsKey(charArray[right]) || (charArray[left] != charRotationMap.get(charArray[right]))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Given number is Strobogrammatic Number: " + isStrobogrammaticNumber("123"));
    }
}
