import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 1. 2차원 배열 돌리기
// 2. DFS

public class java_20058 {
    final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    final static int twoPow[] = { 1, 2, 4, 8, 16, 32, 64 };

    static int board[][];
    static int boardTmp[][];

    static int N;
    static int Q;

    static List<Pair> fireList;

    static boolean[][] isVisited;

    static int sumOfIce = 0;

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    // fireStorm
    public static void fireStorm(int q) {
        storm(q);

        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.copyOf(boardTmp[i], board.length);
        }

        fire();

        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.copyOf(boardTmp[i], board.length);
        }

    }

    // [y][x] 부터 length 만큼 돌리기
    public static void rotate(int y, int x, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                boardTmp[y + i][x + j] = board[y + length - j - 1][x + i];
            }
        }
    }

    // storm
    // 1. 돌리기
    public static void storm(int q) {
        // 2^q * 2^q
        for (int i = 0; i < board.length; i += twoPow[q]) {
            for (int j = 0; j < board.length; j += twoPow[q]) {
                rotate(i, j, twoPow[q]);
            }
        }
    }

    public static int DFS(int y, int x) {
        int count = 0;
        sumOfIce += board[y][x];

        for (int i = 0; i < dydx.length; i++) {

            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            try {
                if (!isVisited[toY][toX]) {
                    if (board[toY][toX] > 0) {
                        count++;
                        isVisited[toY][toX] = true;
                        count += DFS(toY, toX);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }
        return count;
    }

    // fire
    // 2. 녹이기
    public static void fire() {
        for (int i = 0; i < board.length; i++) {
            boardTmp[i] = Arrays.copyOf(board[i], board.length);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int count = 0;
                for (int k = 0; k < dydx.length; k++) {
                    int toY = i + dydx[k][0];
                    int toX = j + dydx[k][1];

                    try {
                        if (board[toY][toX] > 0) {
                            count++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }
                if (count < 3) {
                    if (board[i][j] > 0) {
                        boardTmp[i][j] -= 1;
                    }
                }
            }
        }
    }

    // countIce
    public static int countIce() {
        int result = 0;

        isVisited = new boolean[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // 방문하지 않았어야 함
                if (!isVisited[i][j]) {
                    // 얼음이여야 함
                    if (board[i][j] > 0) {
                        isVisited[i][j] = true;
                        int count = DFS(i, j) + 1;
                        result = Math.max(result, count);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        Q = Integer.parseInt(st.nextToken());

        board = new int[twoPow[N]][twoPow[N]];
        boardTmp = new int[twoPow[N]][twoPow[N]];

        // 데이터 삽입
        for (int i = 0; i < board.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < board.length; i++) {
            boardTmp[i] = Arrays.copyOf(board[i], board.length);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            // 파이어스톰
            fireStorm(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] > 0) {
                    sumOfIce += board[i][j];
                }
            }
        }

        System.out.println(sumOfIce);
        // 가장 큰 얼음덩어리가 차지하는 칸의 갯수
        System.out.println(countIce());

        br.close();
    }
}
