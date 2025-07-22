package two_pointers;

/*
You are given a string, sentence, comprising words and leading or trailing spaces or multiple spaces between words.
Your task is to reverse the order of its words without affecting the order of letters within the given word. Return the modified sentence.

Note: A word is defined as a continuous sequence of non-space characters. Multiple words separated by single spaces form a valid English sentence.
Therefore, ensure there is only a single space between any two words in the returned string, with no leading, trailing, or extra spaces.

Constraints:
(1) The sentence contains English uppercase and lowercase letters, digits, and spaces.
(2) There is at least one word in a sentence.
(3) 1 <= sentence.length <= 10^4
 */
public class ReverseWordsInString {
    private static String reverseWordsInStringNaiveMethod(String sentence) {
        String normalizedString = sentence.trim().replaceAll("\\s+", " ");
        System.out.println("Normalized String:" + normalizedString);
        int len = normalizedString.length();
        String outputString ="";
        String token = "";
        int right = 0;
        for(char c: normalizedString.toCharArray()) {
            System.out.println("c:" + c);
            if(c == ' ' && !token.isEmpty()) {
                outputString = token + " " + outputString;
                token = "";
            } else {
                token = token + c;
                if(right == len-1) {
                    outputString = token + " " + outputString;
                }
                System.out.println("token:" + token);
            }
            right++;

        }

        return outputString;
    }

    private static String reverseWordsInStringOptimumMethod(String sentence) {
        String normalizedString = sentence.trim().replaceAll("\\s+", " ");
        System.out.println("Normalized String:" + normalizedString);
        String[] normalizedStrArr = normalizedString.split("\\s+");
        int left = 0, right = normalizedStrArr.length-1;
        while(left <= right) {
            String temp = normalizedStrArr[left];
            normalizedStrArr[left] = normalizedStrArr[right];
            normalizedStrArr[right] = temp;
            left++;
            right--;
        }

        return String.join(" ", normalizedStrArr);
    }

    public static void main(String[] args) {
        //System.out.println(reverseWordsInStringNaiveMethod("Hello World"));
        System.out.println(reverseWordsInStringOptimumMethod("case test interesting an is this"));
    }
}
