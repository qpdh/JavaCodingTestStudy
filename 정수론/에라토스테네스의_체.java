package 정수론;

import java.util.Arrays;

public class 에라토스테네스의_체 {
    static int n;
    static boolean isPrime[];

    static void eratosthenes() {
        isPrime = new boolean[100];
        Arrays.fill(isPrime, true);

        // 1은 항상 예외처리
        isPrime[0] = isPrime[1] = false;

        int lastNumber = (int) Math.sqrt(n);
        for (int i = 2; i <= lastNumber; i++) {
            // 이 수가 아직 지워지지 않았다면..
            if (isPrime[i]) {
                // i의 배수는 전부 합성수 처리
                // i*2, i*3 은 i가 2, 3일때 각각 처리되므로 따로 처리하지 않음
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
