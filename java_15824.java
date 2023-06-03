import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class java_15824 {
    final static int MOD = 1_000_000_007;
    static int N;
    static int[] food;
    static long[] foodSum;

    static long combination() {
        // 차이가 1인 조합 뽑기
        // 차이가 2인 조합 뽑기
        // ...
        // 차이가 N-1인 조합 뽑기

        /*
         * N = 5
         * 0 1
         * 1 2
         * 2 3
         * 3 4
         * => [4]-[0]
         */

        // 0 2
        // 1 3
        // 2 4
        // 3 5
        // 4 6
        // => [6]+[5]-[0]-[1]

        // 0 3
        // 1 4
        // 2 5
        // 3 6
        // 4 7
        // 5 8
        // K-3 K
        // K-2 K+1
        // K-1 K+2
        // K K+3
        // => -[0]-[1]-[2]+[K+3]+[K+2]+[K+1]
        // (foodSum[K+3] - foodSum[K] - foodSum[2]) * 2^(2)

        // [0] ~ [99]
        // [0] ~ [97]
        // [0] ~ [1]

        // [0] ~ [99]
        // [0] ~ [0]
        // [0] ~ [98]
        long result = 0;
        // 차이가 1 ~ N-1 뽑기
        for (int i = 1; i <= N - 1; i++) {
            long scoville = (foodSum[N - 1] - foodSum[N - 1 - i] - foodSum[i - 1]) % MOD;

            long combCount = calcCombinationCount(i - 1) % MOD;

            scoville = scoville * combCount % MOD;

            result = (result + scoville) % MOD;
        }

        // for (int i = 0; i < N; i++) {
        // // [i] 가 최댓값이 될 때, [i]가 최솟값이 될 때
        // // 2^(i) 2^(N-i-1)
        // long tmp = food[i] * (calcCombinationCount(i) + MOD - calcCombinationCount(N
        // - i - 1)) % MOD;
        // result = (result + tmp) % MOD;
        // }

        return result;
    }

    static long calcCombinationCount(int count) {
        if (count == 0) {
            return 1;
        }
        if (count == 1) {
            return 2;
        }

        long result = 0;
        // 짝수라면...
        if (count % 2 == 0) {
            result = calcCombinationCount(count / 2);
            result *= result;
            result %= MOD;
        }
        // 홀수라면...
        else {
            result = calcCombinationCount(count / 2);
            result *= result;
            result %= MOD;
            result *= 2;
        }

        return result % MOD;

    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = scanner.nextInt();

        food = new int[N];

        for (int i = 0; i < N; i++) {
            food[i] = scanner.nextInt();
        }

        // 정렬
        Arrays.sort(food);

        // // food 누적합 저장
        foodSum = new long[N];
        foodSum[0] = food[0];
        for (int i = 1; i < N; i++) {
            foodSum[i] = (foodSum[i - 1] + food[i]);
        }

        // 조합 목록 구하기
        // 인덱스 2개 선택

        // 경우의 수 출력해보기
        // for (Pair pair : combinationList) {
        // System.out.println(pair);
        // }

        long result = combination();

        bw.write(result + "\n");

        // 조합 목록에서 값 추출하기
        // 선택된 인덱스 2개 A<B
        // food[B] - food[A] 가 스코빌 지수
        // 하지만 그 안에 숫자를 몇개 고르는가? 2^(B-A-1) 의 경우의 수 생성

        // 값이 같은 경우에는 상관이 없는가?
        // 1 2 2 3
        // 1 2 :: 1 가지
        // 1 2 :: 2 가지
        // 1 3 :: 4 가지

        // 0 1
        // 1 2
        // 2 3
        // (1 - 0) * k + (2 - 1) * k + (3 - 2) * k
        // k(3-0)

        // 0 2
        // 1 3
        // 2 4
        // (2-0)*2k + (3-1)*2k + (4-2)*2k
        // 2k(-0+3-1+4)
        scanner.close();
        bw.close();
    }
}
