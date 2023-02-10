import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class java_15684 {
    static int N, M, H;

    static int board[][];

    static List<List<Pair>> combResult;

    static int minResult = -1;

    // i번 세로선이 i번이 나오는지 체크
    static boolean checkResult() {
        boolean result = true;

        // i번 세로줄 선택
        for (int col = 0; col < board[0].length; col++) {
            int line = col;
            for (int row = 0; row < board.length; row++) {
                // 그냥 내려감
                if (board[row][line] == -1) {
                    continue;
                }

                line = board[row][line];
            }

            if (line != col) {
                result = false;
                break;
            }
        }

        return result;
    }

    static class Pair {
        int height, col;

        @Override
        public String toString() {
            return "Pair [height=" + height + ", col=" + col + "]";
        }

        public Pair(int height, int col) {
            this.height = height;
            this.col = col;
        }
    }

    // 연결 되지 않은 연속되지 않은 가로선 선택하기
    static void combination(int y, int x, int count, int maxCount) {
        if (minResult != -1) {
            return;
        }

        // 데이터 추가
        if (count == maxCount) {
            if (checkResult()) {
                minResult = maxCount;
            }
            return;
        }

        if (y >= board.length || x >= board[0].length) {
            return;
        }

        for (int i = y; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {
                if (board[i][j] == -1 && board[i][j + 1] == -1) {
                    // i높이 j와 j+1을 잇는다.
                    board[i][j] = j + 1;
                    board[i][j + 1] = j;

                    combination(i, j, count + 1, maxCount);

                    board[i][j] = -1;
                    board[i][j + 1] = -1;
                }
            }
        }
    }

    static void solution() {
        // 0개로 되는 경우
        if (checkResult()) {
            minResult = 0;
            return;
        }

        // 1개로 되는 경우
        combResult = new ArrayList<>();
        combination(0, 0, 0, 1);

        if (minResult != -1) {
            return;
        }

        // 2개로 되는 경우
        combResult = new ArrayList<>();
        combination(0, 0, 0, 2);

        if (minResult != -1) {
            return;
        }

        // 3개로 되는 경우
        combResult = new ArrayList<>();
        combination(0, 0, 0, 3);

        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 세로선의 갯수
        N = Integer.parseInt(st.nextToken());
        // 가로선의 갯수
        M = Integer.parseInt(st.nextToken());
        // 가로선을 놓을 수 있는 위치의 갯수
        H = Integer.parseInt(st.nextToken());

        board = new int[H][N];

        for (int i = 0; i < H; i++) {
            Arrays.fill(board[i], -1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            board[height][col] = col + 1;
            board[height][col + 1] = col;
        }

        solution();

        System.out.println(minResult);

        br.close();
    }
}
