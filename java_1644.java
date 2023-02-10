import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class java_1644 {
    static int N;
    static boolean primes[];
    static List<Integer> primeList;
    static long primeSum[];

    static void eratos() {
        primes = new boolean[N + 1];
        primeList = new ArrayList<>();

        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i <= N; i++) {
            if (primes[i]) {
                primeList.add(i);
                for (int j = i + i; j <= N; j += i) {
                    primes[j] = false;
                }
            }
        }
    }

    static int solution() {
        if (N == 1) {
            return 0;
        }
        // 소수 찾기
        eratos();

        // 소수 누적합 저장하는 배열
        primeSum = new long[primeList.size()];

        // 2 3 5 7 11 13 17 19
        // 2 5 10 ...

        // System.out.println(primeList);

        // 누적합 계산 + [i]값이 N이상인 지점 찾기
        int startIndex = 0;

        primeSum[0] = primeList.get(0);
        for (int i = 1; i < primeList.size(); i++) {
            primeSum[i] = primeSum[i - 1] + primeList.get(i);

            // [i]값이 N이상인 점 찾기
            if (startIndex == -1 && primeSum[i] >= N) {
                startIndex = i;
            }
        }

        // N이 만들어지는지 체크
        int result = 0;

        // 제거 시작 인덱스
        // primeSum[i]-primeSum[removeIndex]
        int removeIndex = -1;
        for (int i = startIndex; i < primeList.size(); i++) {
            long removeValue = removeIndex == -1 ? 0 : primeSum[removeIndex];

            // N이 만들어지는지 체크
            if (primeSum[i] - removeValue == N) {
                result++;
                continue;
            }

            // 만들어지지 않는 경우 == N이 더 큰 경우
            if (primeSum[i] - removeValue < N) {
                continue;
            }

            // 만들어지지 않는 경우 == N이 더 작은 경우
            // removeIndex을 증가시킨다.
            removeIndex += 1;
            for (; removeIndex < primeList.size() - 1; removeIndex++) {
                // N이 만들어지는지 체크
                if (primeSum[i] - primeSum[removeIndex] == N) {
                    result++;
                    break;
                }

                if (primeSum[i] - primeSum[removeIndex] < N) {
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // 1. 소수 구하기
        // 2. 소수의 누적합 구하기
        // 3. 부분 연속합이 N이 되는지 확인하기
        // 3-1. [i]값이 N이상인 지점 찾기
        // 3-2. [0] 부터 지워나가면서 해당 값이 되는지 확인하기
        // 3-3. 값보다 작아지면 빼기연산 종료
        // 3-1. [i]는 언제까지? -> 1개로 N 이상이되는 수가 될 때 까지
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        int result = solution();

        System.out.println(result);

        scanner.close();
    }
}
