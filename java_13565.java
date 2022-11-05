import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_13565 {
    final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static int N, M;
    static int board[][];

    static boolean result;

    public static void DFS(int y, int x, boolean[][] isVisited) {
        if (y == board.length - 1) {
            result = true;
            return;
        }

        for (int i = 0; i < dydx.length; i++) {
            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            try {
                // 방문하지 않았어야 하며
                if (!isVisited[toY][toX]) {
                    // 0이여야 한다.
                    if (board[toY][toX] == 0) {
                        isVisited[toY][toX] = true;
                        DFS(toY, toX, isVisited);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
    }

    public static void DFSAll() {
        result = false;

        boolean isVisited[][] = new boolean[M][N];

        // 가장 위 부터 시작
        for (int i = 0; i < N; i++) {
            // 방문하지 않았다면..
            if (!isVisited[0][i]) {
                // 전류가 통한다면..
                if (board[0][i] == 0) {
                    // DFS 시작
                    DFS(0, i, isVisited);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[M][N];

        for (int i = 0; i < M; i++) {
            String data = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = data.charAt(j) - '0';
            }
        }

        DFSAll();

        if (result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        br.close();
    }
}
