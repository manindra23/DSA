package two_pointers;

import java.util.Arrays;
import java.util.List;

public class MinMovesToMakePalindrome2 {
    public static int minMovesToMakePalindromeWithoutAdjacentCharSwapRestriction(String s) {
        char[] strArr = s.toCharArray();
        int left = 0, right = strArr.length-1, curr = right;
        int count = 0;
        if(s.length() == 1) {
            return 0;
        }

        while (left < curr) {
            if (strArr[left] == strArr[curr]) {
                if((left+curr) != strArr.length-1) {
                    char temp = strArr[right];
                    strArr[right] = strArr[curr];
                    strArr[curr] = temp;
                    count++;
                }
                left++;
                right--;
                curr = right;
            } else {
                curr--;
            }
        }
        System.out.println("Converted String:" + new String(strArr));
        return count;
    }

    public static int minMovesToMakePalindrome(String s) {
        System.out.println("Input String:" + s);

        if(s.length() == 1) {
             return 0;
        }

        int left = 0, right = s.length() - 1, curr = right;
        char[] strArr = s.toCharArray();
        int count = 0;

        while(left < curr) {
            boolean matchFound = false;
            System.out.println("left:" + left + ", right:" + right + ", curr=" + curr);
            if(strArr[left] == strArr[curr]) {
                System.out.println("Equality>>left:" + left + ", right:" + right + ", curr=" + curr);
                if((left + curr) != strArr.length - 1) {
                    System.out.println("Non-Equality>>left:" + left + ", right:" + right + ", curr=" + curr);
                    while(curr < right) {
                        System.out.println("Swap-begin>>right:" + right + ", curr=" + curr);
                        char temp = strArr[curr];
                        strArr[curr] = strArr[curr+1];
                        strArr[curr+1] = temp;
                        curr++;
                        count++;
                        System.out.println("Swap-end>>count:" + count);
                    }
                }
                left++;
                right--;
                curr = right;
                matchFound = true;
            } else {
                curr--;
            }

            if(left==curr && !matchFound) {
                System.out.println("middle element case for odd length string");
                int midIndex = strArr.length/2;
                while(curr < midIndex) {
                    char temp = strArr[curr + 1];
                    strArr[curr + 1] = strArr[curr];
                    strArr[curr] = temp;
                    curr++;
                    count++;
                }
                left++;
                right--;
                curr = right;
            }
        }
        System.out.println("Converted String:" + new String(strArr));
        return count;
    }

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("ccxx", "arcacer", "w", "ooooooo", "eggeekgbbeg", "iywuupcxsnnunjoxlfexjwnctpvveeidyuujsoiwernhkqrapejwfqnjbmnuogrkilaweqoolfdajmlynmgczejwvypvzexdktpguzjibiriaitqddpcopwuruufbersytzrxubvasqacwvqqytlifxtzzyzuzlwarmffxasnommwmrxgcukqekbvgaavruirtbgbkbvzypnrnuylvqbrmrnmqkoopqbdsuwwiwpimnuwyxxlvonbeooxduewyqksyhsauamakjeosifueeonanwkbdtuiwefmhjvtpickmzukgalnvummlujfqwmmiitczjeqszkufeippspjmquckjynzegcrdiikypsowsfdfvdztkwonheylwtqnjeylhspwqcypjewwutryvhwpecxvuauqpofrjsaqvzqifrqnboefoilmqjmbluifwoeulzeznwpwmkjvulzaiqukrgaelijzoyczgimfdyfucziorcmtsnsdlejpzissdzbprxxosgjpwjrrgzfriupddcwfrkozmkcduwrobeeieyeojygxvygngoiozrwhjetztflldtdwwoewpfkiyordzanjwpgyedgiagamwqxklbdvfbmotoqlwwqnojyajvrarwzwmllpleugowgbngmcafifmstzrscesuntfjiluendbylpcblftxyorzgaqumbyuuqdkizzbwviuxwzlhqnfmhzcpiasntihuihyfljxwovvtiwkdmynasmwxxdzcvqtjjkbyazakeovaapyrzeleqzmfwfyxynmwztqtaxvvvvovkhondjwpaqrouhgcxpdccosfchyeymvilwfouwqkqbhpmwmjrmvckimtnhtiudjhonanabezgzkzvoohhuxytwsudlzyoxopnkanznwtnxwuzqprdqharxhwuuqwvddwtavfostazxskmatfchxvhozlvjzduhfghqzxkoxvewssejmnerdptvpvdutfgaeltmiagoytwbtryooqkgzzievtjreroktspmmyncnbhroskmiscaiowlew");
        for(String str: strList) {
            System.out.println("Min moves to make a palindrome:" + minMovesToMakePalindrome(str));
        }
    }
}
