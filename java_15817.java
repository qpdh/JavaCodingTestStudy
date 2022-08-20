import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_15817 {

    static int pipes[][];
    static int x;
    static int cache[][];

    // pipe번째 파이프를 파이프 갯수만큼 사용할 때
    public static int dp(int length, int pipe) {
        // 목표 파이프 길이에 도달 시 경우의 수 + 1
        if (length == x) {
            return 1;
        }

        // 모든 파이프를 다 쓰면
        if (pipe >= pipes.length) {
            return 0;
        }

        // 파이프의 길이를 넘어서면
        if (length > x) {
            return 0;
        }

        // 이미 캐시값이 존재하는 경우
        if (cache[length][pipe] != -1) {
            return cache[length][pipe];
        }

        cache[length][pipe] = 0;

        for (int i = 0; i <= pipes[pipe][1]; i++) {
            int tmpLength = pipes[pipe][0] * i;
            cache[length][pipe] += dp(length + tmpLength, pipe + 1);
        }

        return cache[length][pipe];
    }

    public static int solve() {
        // 현재 [i] 길이일 때 만들 수 있는 경우의 수
        cache = new int[x + 1][pipes.length];

        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }

        return dp(0, 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        pipes = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L_i = Integer.parseInt(st.nextToken());
            int C_i = Integer.parseInt(st.nextToken());

            pipes[i][0] = L_i;
            pipes[i][1] = C_i;
        }

        int result = solve();

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}
