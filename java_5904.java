import java.util.Arrays;
import java.util.Scanner;

public class java_5904 {

    static int N;
    static long moo[];

    static long dp(int n) {
        if (n == 0) {
            return 3;
        }

        if (moo[n] != -1) {
            return moo[n];
        }

        moo[n] = dp(n - 1) * 2 + (n + 2)+1;

        return moo[n];
    }

    static char findMoo(int k, long left) {
        if (k == 0) {
            if (left == 1) {
                return 'm';
            } else {
                return 'o';
            }
        }

        if (left < moo[k - 1] + 1) {
            return findMoo(k - 1, left);
        }

        // 첫 S(k-1) 을 전부 포함하는 경우 -> k
        if (left == moo[k - 1] + 1) {
            return 'm';
        }

        // 사이에 있는 경우
        // o
        if (moo[k - 1] + 1 < left && left <= moo[k - 1] + k + 2 + 1) {
            return 'o';
        }

        // 뒤 S(k-1) 을 전부 포함하는 경우
        return findMoo(k - 1, left - moo[k - 1] - k - 2 - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        moo = new long[25];

        Arrays.fill(moo, -1);

        moo[0] = 3;

        dp(24);

        char result = findMoo(24, N);

        System.out.println(result);

        scanner.close();
    }
}
