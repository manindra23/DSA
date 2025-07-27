package fast_and_slow_pointers;

/*
Write an algorithm to determine if a number n is a happy number.

We use the following process to check if a given number is a happy number:

Starting with the given number n, replace the number with the sum of the squares of its digits.
Repeat the process until:
The number equals 1, which will depict that the given number n is a happy number.
The number enters a cycle, which will depict that the given number n is not a happy number.

Return TRUE if n is a happy number, and FALSE if not.

Constraints:
    + 1<=n<= 2^31 - 1

 */
public class HappyNumber {
    public static boolean isHappyNumber(int n) {
        int sum = 0;
        int fast = n;
        int slow = n;
        while(true) {
            slow = sumOfDigits(slow);
            fast = sumOfDigits(sumOfDigits(fast));
            if(slow == fast && slow != 1) {
                return false;
            }

            if(fast == 1) {
                return true;
            }
        }
    }

    private static int sumOfDigits(int n) {
        int sum = 0;
        while(n > 0 ) {
            sum = sum + (n%10) * (n%10);
            n = n/10;
        }
        return sum;
    }
    public static void main(String[] args) {
        int a[] = {1, 5, 19, 25, 7};
        for (int j : a) {
            System.out.println("Is " + j + " a happy number:" + isHappyNumber(j));
        }
    }
}
