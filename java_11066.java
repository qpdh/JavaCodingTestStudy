import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_11066 {
    static int N;
    static int pages[];
    // 누적합
    static int sum[];

    static int cache[][];

    // cost[start] ~ cost[end] 까지 합치겠다.
    // 이 때 최소비용
    static int dp(int start, int end) {
        if (cache[start][end] != -1) {
            return cache[start][end];
        }

        if (start == end) {
            return 0;
        }

        cache[start][end] = Integer.MAX_VALUE;

        // [0][3] 을 위해서
        // [0][0] [1][3] -> [1][3] -> [1][1] [2][3]/ [1][2] [3][3]

        // [0][1] [2][3] -> [0][0] [1][1] / [2][2] [3][3] => 300
        // [0][2] [3][3]
        for (int i = start; i < end; i++) {
            int left = dp(start, i);
            int right = dp(i + 1, end);
            cache[start][end] = Math.min(cache[start][end], left + right);
        }

        return cache[start][end] += sum[end] - sum[start - 1];
    }

    static int solve() {
        cache = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(cache[i], -1);
        }

        int result = dp(1, N);
        // 1-2 2-3 3-4 4-5 5-6 6-7 7-8...
        // 1-2 3-4

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            pages = new int[N + 1];
            sum = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < pages.length; j++) {
                pages[j] = Integer.parseInt(st.nextToken());

                sum[j] += sum[j - 1] + pages[j];

            }

            bw.write(solve() + "\n");
        }

        br.close();
        bw.close();
    }
}
