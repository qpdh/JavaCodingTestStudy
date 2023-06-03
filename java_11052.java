import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_11052 {
    // 구매하려는 카드의 개수
    static int N;

    static int P[];

    // 캐시
    static int cache[];

    // 현재 count개의 카드를 샀을 때, N개를 채웠을 때 금액의 최댓값 반환
    static int dp(int count) {
        // N개가 된 경우, 더 살 수 없다.
        if (count == N) {
            return 0;
        }

        // 이미 값이 존재한다면..이를 사용
        if (cache[count] != -1) {
            return cache[count];
        }

        cache[count] = 0;

        // 카드팩 배열을 돌면서 하나를 선택한다.
        for (int i = 0; i < P.length; i++) {
            // 해당 카드팩을 살 수 있는가?

            // 살 수 없는 경우
            if (count + (i + 1) > N) {
                continue;
            }

            // 살 수 있는 경우 -> 더 큰 값을 저장
            cache[count] = Math.max(cache[count], P[i] + dp(count + (i + 1)));
        }

        return cache[count];
    }

    static int solution() {
        // 캐시 초기화
        cache = new int[N];
        Arrays.fill(cache, -1);

        int result = dp(0);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        P = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }
}
