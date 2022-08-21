import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_4920 {
    static int board[][];
    // 조각/조각의 방향/dydx
    // [0] - ㅡ 모양
    // [1] - ㄹ 모양
    // [2] - ㄱ 모양
    // [3] - ㅗ 모양
    // [4] - ㅁ 모양

    // ㅡ 모양
    static int block1[][][] = {
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } } };

    // ㄹ 모양
    static int block2[][][] = {
            { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 1, 0 }, { 1, -1 }, { 2, -1 } },
    };

    // ㄱ 모양
    static int bloack3[][][] = {
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, -1 } },
            { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 2, 0 } } };

    // ㅜ 모양
    static int bloack4[][][] = {
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, -1 } },
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { -1, 1 } }, { { 0, 0, }, { 1, 0 }, { 2, 0 }, { 1, 1 } }
    };

    // ㅁ 모양
    static int bloack5[][][] = {
            { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } }
    };

    static int blockList[][][][] = { block1, block2, bloack3, bloack4, bloack5 };

    public static int solve() {
        int maxValue = Integer.MIN_VALUE;

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                // 하나의 블록 타입 선택
                for (int blocks[][][] : blockList) {
                    // 하나의 블록 타입 중 한 가지 블록 선택
                    for (int block[][] : blocks) {
                        int value = 0;
                        // 한 가지 블록 선택
                        boolean isFit = true;
                        for (int i = 0; i < block.length; i++) {
                            try {
                                value += board[y + block[i][0]][x + block[i][1]];
                            } catch (ArrayIndexOutOfBoundsException e) {
                                isFit = false;
                                break;
                            }
                        }

                        if (isFit) {
                            maxValue = Math.max(maxValue, value);
                        }
                    }
                }
            }
        }

        return maxValue;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = 1;
        while (true) {

            int N = Integer.parseInt(br.readLine().strip());

            // 0이면 종료
            if (N == 0) {
                break;
            }

            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = solve();

            bw.write(testCase + ". " + result + "\n");

            testCase++;

        }

        br.close();
        bw.close();
    }
}
