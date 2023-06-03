import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class java_2118 {
    // 지점의 수
    static int N;

    static int towers[];
    static int pSum[];

    public static void main(String[] args) throws IOException {
        /*
         * 인덱스 차이는 N//2 만큼
         * 시계 반시계 값 계산하기
         * 누적합 이용
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        pSum = new int[N];
        towers = new int[N];

        towers[0] = Integer.parseInt(br.readLine());
        pSum[0] = towers[0];

        for (int i = 1; i < N; ++i) {
            towers[i] = Integer.parseInt(br.readLine());
            pSum[i] = pSum[i - 1] + towers[i];
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }

    //
    private static int solution() {
        int result = 0;

        int indexA = 0, indexB = 1;

        int sumA = towers[0];
        int sumB = pSum[N - 1] - sumA;

        while (indexA < N) {
            result = Math.max(result, Math.min(sumA, sumB));

            // 이 때가 최대임
            if (sumA == sumB) {
                break;
            }

            if (sumA > sumB) {
                sumA -= towers[indexA];
                sumB += towers[indexA];
                ++indexA;
                continue;
            }

            // sumA < sumB 의 경우
            sumA += towers[indexB];
            sumB -= towers[indexB];
            indexB = (indexB + 1) % N;

        }

        return result;
    }
}
