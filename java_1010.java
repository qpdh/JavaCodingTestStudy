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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            cache = new int[N + 1][M + 1];

            for (int j = 0; j < N + 1; j++) {
                for (int k = 0; k < M + 1; k++) {
                    cache[j][k] = -1;
                }
            }

            bw.write(buildBridge(N, M) + "\n");
        }

        br.close();
        bw.close();
    }
}
