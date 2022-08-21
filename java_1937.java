import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_1937 {
    final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    final static int INF = Integer.MIN_VALUE;
    static int board[][];

    // [i][j] 위치에서 이동할 수 있는 최대 칸
    static int cache[][];

    static int dp(int y, int x) {
        if (cache[y][x] != -1) {
            return cache[y][x];
        }

        cache[y][x] = INF;

        int toY = -1;
        int toX = -1;
        for (int i = 0; i < dydx.length; i++) {
            int tmpY = y + dydx[i][0];
            int tmpX = x + dydx[i][1];
            try {
                if (board[tmpY][tmpX] > board[y][x]) {
                    toY = tmpY;
                    toX = tmpX;
                    cache[y][x] = Math.max(cache[y][x], 1 + dp(toY, toX));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }

        // 최댓값을 찾지 못했다면... 현재 칸만 + 1
        if (toY == -1 && toX == -1) {
            cache[y][x] = 1;
        }
        return cache[y][x];
    }

    static int solve(int N) {
        cache = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp(i, j);
            }
        }

        int result = 0;
        for (int row[] : cache) {
            for (int item : row) {
                if (result < item) {
                    result = item;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(solve(N) + "\n");

        br.close();
        bw.close();
    }
}
