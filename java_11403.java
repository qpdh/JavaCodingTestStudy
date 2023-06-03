import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_11403 {
    static int N;

    static int board[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floydWarshall();

        printBoard();

        br.close();
    }

    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void floydWarshall() {
        // k를 경유하여
        for (int k = 0; k < N; k++) {
            // i에서
            for (int i = 0; i < N; i++) {
                // j를 갈 수 있는가?
                for (int j = 0; j < N; j++) {
                    // i->j 바로 갈 수 있으면 스킵
                    if (board[i][k] == 1 && board[k][j] == 1) {
                        board[i][j] = 1;
                    }

                }
            }
        }
    }
}
