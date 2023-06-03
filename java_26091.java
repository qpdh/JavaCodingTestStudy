import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_26091 {
    // N : 학회원의 수
    // M : 팀의 최소 능력치
    static int N, M;

    static int stats[];

    public static void main(String[] args) throws IOException {
        /*
         * 팀원 능력치 정렬
         * 가장 높은거, 가장 낮은거 : 투 포인터 이용
         * 가장 높은거 + 가장 낮은거 < M 이라면
         * -> 가장 낮은거 포인터 증가
         * >= M 이라면
         * -> 두 포인터 거리 좁히기
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        stats = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stats[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }

    // 견학 보낼 수 있는 최대 팀 수를 반환하는 메서드
    private static int solution() {
        // 결과 값
        int result = 0;

        // 능력치 정렬
        Arrays.sort(stats);

        // indexA, indexB
        int indexA = 0;
        int indexB = N - 1;

        // 투 포인터 연산 시작
        while (indexA < indexB) {
            // 두 인덱스의 합이 M이상인가?
            int twoPointerSum = stats[indexA] + stats[indexB];

            if (twoPointerSum >= M) {
                // 포인터 거리 좁히기
                ++indexA;
                --indexB;
                // 최대 팀 수 증가
                ++result;
                continue;
            }

            // 합이 M 미만인 경우
            // A 포인터만 증가시키기
            ++indexA;
        }

        return result;
    }
}
