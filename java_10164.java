import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_10164 {
    static int cache[][];

    // [y][x] 에서 [maxY][maxX] 까지 가는 경우의 수
    // 방문 했는지 확인 필요

    // [0][0] -> [1][2] 가는 경우의 수
    // 1. [1][0] -> [1][2] 가는 경우의 수
    // 2. [0][1] -> [1][2] 가는 경우의 수
    //...

    // [1][1] -> [1][2] 가는 경우의 수
    // [0][2] -> [1][2] 가는 경우의 수
    // [1][2] [0][3]

    // 2행 3열짜리 2차원 배열
    // [0][0] -> [1][2]
    public static int routeCount(int y, int x, int maxY, int maxX) {
        if (y == maxY && x == maxX) {
            return 1;
        }

        if (y > maxY || x > maxX) {
            return 0;
        }

        if (cache[y][x] != -1) {
            return cache[y][x];
        }

        cache[y][x] = 0;
        cache[y][x] = routeCount(y + 1, x, maxY, maxX) + routeCount(y, x + 1, maxY, maxX);

        return cache[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        cache = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cache[i][j] = -1;
            }
        }

        int ret = 0;

        // 무조건 방문해야 하는 곳이 없으면
        if (K == 0) {
            cache = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    cache[i][j] = -1;
                }
            }

            ret = routeCount(0, 0, N - 1, M - 1);
        } 
        // 무조건 방문해야 하는 곳이 있으면
        else {
            // 일단 그 지점을 방문하는 경우의 수 찾기
            int toY = (K - 1) / M;
            int toX = (K - 1) % M;

            cache = new int[toY + 1][toX + 1];

            for (int i = 0; i < toY + 1; i++) {
                for (int j = 0; j < toX + 1; j++) {
                    cache[i][j] = -1;
                }
            }

            ret = routeCount(0, 0, toY, toX);

            // 그 지점부터 마지막까지 가는 경우의 수 찾기
            cache = new int[N - toY][M - toX];

            for (int i = 0; i < N - toY; i++) {
                for (int j = 0; j < M - toX; j++) {
                    cache[i][j] = -1;
                }
            }

            // 곱하기
            ret *= routeCount(0, 0, N - toY - 1, M - toX - 1);
        }

        bw.write(ret + "\n");

        br.close();
        bw.close();

    }
}
