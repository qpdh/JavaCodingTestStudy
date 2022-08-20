import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_2225 {

    static int N, K;
    static long cache[][];

    static long dp(int now, int count) {

        if (cache[now][count] != -1) {
            return cache[now][count];
        }

        // K개를 쓰고 N이 만들어지는 경우
        if (count == K && now == N) {
            return 1;
        }

        // K개 이상을 쓸 경우
        // 넘어설 경우
        if (count >= K || now > N) {
            return 0;
        }

        cache[now][count] = 0;

        for (int i = 0; i <= N - now; i++) {

            cache[now][count] += dp(now + i, count + 1) % 1_000_000_000;
        }

        return cache[now][count];

    }

    static long solve() {
        cache = new long[N + 1][K + 1];

        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }

        long result = dp(0, 0) % 1_000_000_000;

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bw.write(solve() + "\n");

        br.close();
        bw.close();
    }
}
