import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1010 {
    static int cache[][];
    // 왼쪽 다리 최대값
    // 오른쪽 다리 최대값

    // N번째 시작하는 다리는 M이 M->N번째 까지 놓을 수 있음
    public static int buildBridge(int N, int M) {

        // 왼쪽의 다리가 1개 뿐인 경우
        if (N == 1) {
            return M;
        }

        // 시작하는 다리의 수와 끝나는 다리의 수가 같으면
        // => ㅡ자로 이어야 함
        if (N == M) {
            return 1;
        }

        if (cache[N][M] != -1) {
            return cache[N][M];
        }

        cache[N][M] = 0;

        // 시작하는 다리의 수 N-1
        // 끝나는 다리의 수 M-1 ... N-1
        for (int i = N - 1; i < M; i++) {
            cache[N][M] += buildBridge(N - 1, i);
        }

        return cache[N][M];
    }

    public static int buildBridge2(int n, int r) {
        if (n == r) {
            return 1;
        }

        if (r == 1) {
            return n;
        }

        if (cache[n][r] != -1) {
            return cache[n][r];
        }

        cache[n][r] = buildBridge2(n - 1, r - 1) + buildBridge2(n - 1, r);

        return cache[n][r];
    }

    public static long fac(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * fac(n - 1);
    }

    // nCr = n! / ((n-r)! * r!);
    public static long calNCR(int n, int r) {
        long A = 1;
        for (int i = n; i > n-r; i--) {
            A *= i;
        }

        long B = fac(r);

        return A / B;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            cache = new int[M + 1][N + 1];

            for (int j = 0; j < M + 1; j++) {
                for (int k = 0; k < N + 1; k++) {
                    cache[j][k] = -1;
                }
            }

            // bw.write(buildBridge(N, M) + "\n");
            // bw.write(buildBridge2(M, N) + "\n");
            bw.write(calNCR(M, N) + "\n");
        }

        br.close();
        bw.close();
    }
}
