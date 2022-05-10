import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_17391 {
    final static int dydx[][] = { { 0, 1 }, { 1, 0 } };

    final static int INF = 987654321;
    // 보드
    static int board[][];
    // 행, 열
    static int N, M;

    public static boolean isOutOfRange(int y, int x) {
        boolean result = false;

        if (y < 0 || N <= y) {
            result = true;
        }

        if (x < 0 || M <= x) {
            result = true;
        }

        return result;
    }

    public static int dp(int cache[][], int y, int x) {
        // 이미 최적 값이 저장이 되어있다면...처리하지 않음
        if (cache[y][x] != -1) {
            return cache[y][x];
        }

        // 도착했다면
        if (y == N - 1 && x == M - 1) {
            return 0;
        }

        // 캐시 초기화
        cache[y][x] = INF;

        // 현재 위치의 연료 값
        int fuel = board[y][x];

        // 우측 or 하단 으로
        for (int i = 0; i < 2; i++) {
            // 연료 만큼 이동
            for (int j = 1; j <= fuel; j++) {
                int toY = dydx[i][0] * j + y;
                int toX = dydx[i][1] * j + x;

                // 배열의 범위를 넘어가면 체크하지 않음
                if (isOutOfRange(toY, toX)) {
                    break;
                }

                cache[y][x] = Math.min(cache[y][x], dp(cache, toY, toX) + 1);
            }
        }

        //
        return cache[y][x];
    }

    public static int solution() {

        // DP 만들기
        // cache는 무엇을 가리키는가?
        // [i][j]에서 도착지점까지 가능한 최소 격자의 수
        int cache[][] = new int[N][M];

        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }

        dp(cache, 0, 0);

        // System.out.println("===========");
        // for (int[] c : cache) {
        // for (int d : c) {
        // System.out.print(d + " ");
        // }
        // System.out.println();
        // }
        // System.out.println("===========");

        return cache[0][0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 보드 초기화
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(solution() + "\n");

        br.close();
        bw.close();
    }
}
