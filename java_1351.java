import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class java_1351 {
    static long N;
    static int P, Q;

    static HashMap<Long, Long> cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        long result = solution();

        System.out.println(result);

        br.close();
    }

    private static long solution() {
        cache = new HashMap<>();

        if (N == 0) {
            return 1;
        }

        long result = dp(N / P) + dp(N / Q);

        return result;
    }

    private static long dp(long index) {
        // 기저사례: i가 0인 경우
        if (index == 0) {
            return 1;
        }

        // 기저사례: 캐시값이 존재하는 경우
        if (cache.getOrDefault(index, (long) -1) != -1) {
            return cache.get(index);
        }

        // 값 계산하기
        cache.put(index, dp(index / P) + dp(index / Q));

        return cache.get(index);
    }
}
