import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_1303 {
    final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static int N, M;
    static char board[][];

    static int blueScore, whiteScore;
    static boolean isVisited[][];

    public static int DFS(int y, int x) {
        int result = 1;

        for (int i = 0; i < dydx.length; i++) {
            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            try {
                if (!isVisited[toY][toX]) {
                    if (board[y][x] == board[toY][toX]) {
                        isVisited[toY][toX] = true;
                        result += DFS(toY, toX);
                    }

                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }

        return result;
    }

    public static void DFSAll() {
        isVisited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!isVisited[i][j]) {
                    isVisited[i][j] = true;
                    // White, 아군의 경우
                    if (board[i][j] == 'W') {
                        whiteScore += Math.pow(DFS(i, j), 2);
                    }
                    // Blue, 적군의 경우
                    else if (board[i][j] == 'B') {
                        blueScore += Math.pow(DFS(i, j), 2);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // col
        N = Integer.parseInt(st.nextToken());
        // row
        M = Integer.parseInt(st.nextToken());

        board = new char[M][N];

        for (int i = 0; i < M; i++) {
            String data = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = data.charAt(j);
            }
        }

        DFSAll();

        System.out.printf("%d %d\n", whiteScore, blueScore);

        br.close();
    }
}
