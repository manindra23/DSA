package two_pointers;

public class ValidPalindrome {

    //Method-1: Assuming input only contains alphabets
    private static boolean isValidPalindrome1(String str) {
        for(int i = 0; i < str.length()/2; i++) {
            if(str.charAt(i) != str.charAt(str.length()-1-i)) {
                return false;
            }
        }
        return true;
    }

    //Method-2: Input string may be alphanumeric along with special characters
    private static boolean isValidPalindrome2(String str) {
        int left = 0, right = str.length()-1;

        while(left<right) {
            while (left < right && !Character.isLetterOrDigit(str.charAt(left))) {
                left++;
            }

            while (left<right && !Character.isLetterOrDigit(str.charAt(right))) {
                right--;
            }

            if(Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))) {
                    return false;
            }

            left++;
            right--;
        }

        return true;

    }

    public static void main(String[] args) {
        System.out.println("Is Valid Palindrome-1:" + isValidPalindrome1("abndcba"));
        System.out.println("Is Valid Palindrome-2:" + isValidPalindrome2("a ,. . ...b--k#$@@@n -*&c-, *&b % .a"));
    }
}
