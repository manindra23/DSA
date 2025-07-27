package fast_and_slow_pointers;

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
