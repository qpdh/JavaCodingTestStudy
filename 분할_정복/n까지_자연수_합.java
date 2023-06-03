package 분할_정복;

public class n까지_자연수_합 {
    static int fastSum(int n) {
        // n이 1이면 1을 반환한다.
        if (n == 1) {
            return 1;
        }
        // n이 홀수인 경우
        // n을 미리 더하고 짝수로 바꾼다.
        if (n % 2 != 0) {
            return n + fastSum(n - 1);
        }

        return 2 * fastSum(n / 2) + (n / 2) * (n / 2);
    }

    public static void main(String[] args) {

    }
}
