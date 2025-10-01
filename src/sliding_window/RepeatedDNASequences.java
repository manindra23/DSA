package sliding_window;


import java.util.*;

/*
A DNA sequence consists of nucleotides represented by the letters ‘A’, ‘C’, ‘G’, and ‘T’ only. For example, “ACGAATTCCG” is a valid DNA sequence.

Given a string, s, that represents a DNA sequence, return all the 10-letter-long sequences (continuous substrings of exactly 10 characters)
that appear more than once in s. You can return the output in any order.

Constraints:
    + 1<=s.length<=10^3
    + s[i] is either 'A', 'C', 'G' or 'T'

 */
public class RepeatedDNASequences {
    private static List<String> repeatedDNASequencesNaiveMethod1(String str) {
        int left = 0, right = 9;
        Map<String, Integer> strFrequencyMap = new HashMap<>();
        char[] strArr = str.toCharArray();
        List<String> outputList = new ArrayList<>();
        if(str.length() <= 10) {
            return outputList;
        }

        while(right <= str.length()-1) {
            String subStr = String.valueOf(strArr, left, 10);
            strFrequencyMap.put(subStr, strFrequencyMap.containsKey(subStr) ? strFrequencyMap.get(subStr) + 1: 1);
            if(strFrequencyMap.containsKey(subStr) && strFrequencyMap.get(subStr) > 1 && !outputList.contains(subStr)) {
                outputList.add(subStr);
            }
            left++;
            right++;
        }

        return outputList;
    }

    private static List<String> repeatedDNASequencesSubOptimalMethod(String str) {
        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('A', 0);
        charMap.put('C', 1);
        charMap.put('G', 2);
        charMap.put('T', 3);

        List<Integer> encodedSequence = new ArrayList<>();
        Set<Integer> seenHashes = new HashSet<>();
        Set<String> outputSet = new HashSet<>();

        if(str.length() <=10) {
            return new ArrayList<>(outputSet);
        }

        for(char c: str.toCharArray()) {
            encodedSequence.add(charMap.get(c));
        }

        double hash = 0;
        for(int i = 0; i<=9; i++) {
            hash = hash + encodedSequence.get(i) * Math.pow(4, 9-i);
        }
        seenHashes.add((int) hash);

        for(int i = 1; i <= encodedSequence.size() - 10; i++) {
            hash = hash * 4- (encodedSequence.get(i-1) * Math.pow(4,10)) + encodedSequence.get(i+9);
            if(seenHashes.contains((int) hash)) {
                outputSet.add(String.valueOf(str.toCharArray(), i, 10));
            } else {
                seenHashes.add((int) hash);
            }
        }

        return new ArrayList<>(outputSet);
    }

    private static List<String> repeatedDNASequencesOptimumMethod(String str) {
        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('A', 0);
        charMap.put('C', 1);
        charMap.put('G', 2);
        charMap.put('T', 3);

        List<Integer> encodedSequence = new ArrayList<>();
        Set<Integer> seenHashes = new HashSet<>();
        Set<String> outputSet = new HashSet<>();

        if(str.length() <=10) {
            return new ArrayList<>(outputSet);
        }

        for(char c: str.toCharArray()) {
            encodedSequence.add(charMap.get(c));
        }

        int hash = 0;
        int baseNumber = 4;
        int subStrLen = 10;
        int baseCalc = 1;
        for(int i = 0; i < subStrLen; i++) {
            hash = hash * baseNumber + encodedSequence.get(i);
            baseCalc = baseCalc * baseNumber;
        }
        seenHashes.add(hash);

        for(int i = 1; i <= encodedSequence.size() - subStrLen; i++) {
            hash = hash * baseNumber - (encodedSequence.get(i-1) * baseCalc) + encodedSequence.get(i + subStrLen - 1);
            if(seenHashes.contains(hash)) {
                outputSet.add(str.substring(i, i + subStrLen));
            } else {
                seenHashes.add(hash);
            }
        }
        return new ArrayList<>(outputSet);
    }

    public static void main(String[] args) {
        System.out.println("All 10-letter subsequences:" + repeatedDNASequencesOptimumMethod("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));

    }
}
