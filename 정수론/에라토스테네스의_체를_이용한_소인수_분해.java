package 정수론;

import java.util.ArrayList;
import java.util.List;

public class 에라토스테네스의_체를_이용한_소인수_분해 {
    static int n;
    // i의 가장 작은 소인수
    static int minFactor[];

    static void eratosthenes() {
        // 0, 1은 예외처리
        minFactor[0] = minFactor[1] = -1;

        // 자기 자신으로 일단 초기화
        for (int i = 2; i <= n; i++) {
            minFactor[i] = i;
        }

        int lastNumber = (int) Math.sqrt(n);
        for (int i = 2; i <= lastNumber; i++) {
            // 자기 자신이 가장 작은 소인수인 경우 -> 소수인 경우
            if (minFactor[i] == i) {
                // i의 배수는 모두 합성수이다.
                // i의 배수의 가장 작은 소인수는 i이다.
                for (int j = i * i; j <= lastNumber; j += i) {
                    minFactor[j] = i;
                }
            }
        }
    }

    // 2이상의 자연수 n을 소인수분해한 결과를 반환
    static List<Integer> factor(int n) {
        List<Integer> result = new ArrayList<>();

        // n이 1이 될 때 까지 가장 작은 소인수로 나누기 반복
        while (n > 1) {
            result.add(minFactor[n]);
            n /= minFactor[n];
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
