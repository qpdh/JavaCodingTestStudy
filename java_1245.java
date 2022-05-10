import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_1245 {
    public static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { 1, 1 }, { -1, 1 },
            { 1, -1 } };
    public static boolean check;

    public static void dfs(int[][] board, boolean[][] isVisited, int y, int x) {
        // 주변의 타일보다 커야함
        // 같은 높이가 이어져 있을 수 있음
        // 하나의 산에 1개 이상의 봉우리가 있을 수 있음

        for (int i = 0; i < dydx.length; i++) {
            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            // 범위 벗어나면 체크하지 않음
            if (0 > toY || board.length <= toY || 0 > toX || board[0].length <= toX) {
                continue;
            }

            // 현재 위치의 높이와 같으면 dfs 다시시작
            // 어디까지 봉우리인지 확인하기 위함
            else if (board[y][x] == board[toY][toX] && !isVisited[toY][toX]) {
                isVisited[toY][toX] = true;
                dfs(board, isVisited, toY, toX);
            }

            // 현재 위치보다 높으면 dfs 중단 false 반환
            else if (board[y][x] < board[toY][toX]) {
                check = false;
            }
        }

    }

    public static int dfsAll(int[][] board, boolean[][] isVisited) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!isVisited[i][j]) {
                    isVisited[i][j] = true;
                    check = true;
                    dfs(board, isVisited, i, j);
                    if (check) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        boolean[][] isVisited = new boolean[N][M];

        // board 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(board, 0);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(dfsAll(board, isVisited) + "\n");

        br.close();
        bw.close();
    }
}
