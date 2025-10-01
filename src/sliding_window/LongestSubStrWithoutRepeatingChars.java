package sliding_window;

import java.util.HashMap;

public class LongestSubStrWithoutRepeatingChars {

    public static String findLongestSubstringBetterImplementation(String str) {
        if (str.isEmpty()) {
            return "";
        }

        int n = str.length() - 1, windowStart = 0, windowLen = 0, longestSubStrLen = 0;
        String outputSubStr = "";
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            if (!charMap.containsKey(str.charAt(i))) {
                charMap.put(str.charAt(i), i);
            } else {
                if (charMap.get(str.charAt(i)) >= windowStart) {
                    windowLen = i - windowStart;
                    if (longestSubStrLen < windowLen) {
                        longestSubStrLen = windowLen;
                        outputSubStr = str.substring(windowStart, i);
                    }
                    windowStart = charMap.get(str.charAt(i)) + 1;
                }
                charMap.replace(str.charAt(i), i);
            }
        }

        if (longestSubStrLen < (n + 1 - windowStart)) {
            longestSubStrLen = n + 1 - windowStart;
            outputSubStr = str.substring(windowStart, n + 1);
        }
        return outputSubStr;
    }

    public static String findLongestSubstringMyImplementation(String str) {
        int left = 0, right = str.length() - 1, curr = 1;
        String outputSubStr = "";
        HashMap<Character, Integer> charMap = new HashMap<>();
        while (curr <= right) {
            charMap.put(str.charAt(curr - 1), curr - 1);
            if (charMap.containsKey(str.charAt(curr))) {
                String interimOutStr = str.substring(left, curr);
                if (interimOutStr.length() > outputSubStr.length()) {
                    outputSubStr = interimOutStr;
                }
                int duplicatePos = charMap.get(str.charAt(curr));
                for (int i = charMap.get(str.charAt(curr)); i >= left; i--) {
                    charMap.remove(str.charAt(i));
                }
                left = duplicatePos + 1;
            } else {
                if (curr == right) {
                    String interimOutStr = str.substring(left, curr + 1);
                    if (interimOutStr.length() > outputSubStr.length()) {
                        outputSubStr = interimOutStr;
                    }
                }
            }

            curr++;
        }
        return outputSubStr;
    }

    public static void main(String[] args) {
        String[] stringArr = {
                "abccabcabcc"
        };

        for (String str : stringArr) {
            System.out.println("Longest substr for " + str + ":" + findLongestSubstringMyImplementation(str));
            System.out.println("Longest substr for " + str + ":" + findLongestSubstringBetterImplementation(str));
        }
    }
}
