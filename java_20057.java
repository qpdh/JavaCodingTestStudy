import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_20057 {
    final static int dydx[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    static int board[][];

    static int N;

    static int result;

    static int tmp = 0;

    static void printBoard() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }

    }

    // 흩날리기
    static void spread(int y, int x, int moveIndex) {
        int toY = y + dydx[moveIndex][0];
        int toX = x + dydx[moveIndex][1];

        // System.out.printf("To toY : %d, toX : %d\n", toY, toX);

        // toY, toX의 모래를 거리가 2인 위치에 전부 뿌린다.

        // 방향은 moveIndex 방향이다.
        // 수평 방향인지 수직 방향인지 판단하자.

        // 모래는 board[toY][toX] 기준
        int leftSand = board[toY][toX];

        // 수평 방향이다.
        if (moveIndex % 2 == 0) {

            // 1% 더하기 -> x 기준 y+-1
            try {
                board[toY + 1][x] += board[toY][toX] / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] / 100;
            }
            leftSand -= board[toY][toX] / 100;
            try {
                board[toY - 1][x] += board[toY][toX] / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] / 100;
            }
            leftSand -= board[toY][toX] / 100;

            // 7% 더하기 -> toX 기준 y+-1
            try {
                board[y + 1][toX] += board[toY][toX] * 7 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 7 / 100;
            }
            leftSand -= board[toY][toX] * 7 / 100;
            try {
                board[y - 1][toX] += board[toY][toX] * 7 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 7 / 100;
            }
            leftSand -= board[toY][toX] * 7 / 100;

            // 2% 더하기 -> toX 기준 y+-2
            try {
                board[y + 2][toX] += board[toY][toX] * 2 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 2 / 100;
            }
            leftSand -= board[toY][toX] * 2 / 100;
            try {
                board[y - 2][toX] += board[toY][toX] * 2 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 2 / 100;
            }
            leftSand -= board[toY][toX] * 2 / 100;

            // 10% 더하기 -> toX+dydx[moveIndex][1] 기준 y+-1
            try {
                board[y + 1][toX + dydx[moveIndex][1]] += board[toY][toX] * 10 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 10 / 100;
            }
            leftSand -= board[toY][toX] * 10 / 100;
            try {
                board[y - 1][toX + dydx[moveIndex][1]] += board[toY][toX] * 10 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 10 / 100;
            }
            leftSand -= board[toY][toX] * 10 / 100;

            // 5% 더하기 -> toX + (dydx[moveIndex][1] * 2)
            try {
                board[y][toX + (dydx[moveIndex][1] * 2)] += board[toY][toX] * 5 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 5 / 100;
            }
            leftSand -= board[toY][toX] * 5 / 100;

            // 55% 더하기 -> toX+dydx[moveIndex][1]
            try {
                board[y][toX + dydx[moveIndex][1]] += leftSand;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += leftSand;
            }
        }
        // 수직 방향이다.
        else {
            // 1% 더하기 -> y 기준 x+-1
            try {
                board[y][x + 1] += board[toY][toX] / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] / 100;
            }
            leftSand -= board[toY][toX] / 100;
            try {
                board[y][x - 1] += board[toY][toX] / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] / 100;
            }
            leftSand -= board[toY][toX] / 100;

            // 7% 더하기 -> toY 기준 x+-1
            try {
                board[toY][x + 1] += board[toY][toX] * 7 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 7 / 100;
            }
            leftSand -= board[toY][toX] * 7 / 100;
            try {
                board[toY][x - 1] += board[toY][toX] * 7 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 7 / 100;
            }
            leftSand -= board[toY][toX] * 7 / 100;

            // 2% 더하기 -> toY 기준 x+-2
            try {
                board[toY][x + 2] += board[toY][toX] * 2 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 2 / 100;
            }
            leftSand -= board[toY][toX] * 2 / 100;
            try {
                board[toY][x - 2] += board[toY][toX] * 2 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 2 / 100;
            }
            leftSand -= board[toY][toX] * 2 / 100;

            // 10% 더하기 -> toY+dydx[moveIndex][0] 기준 x+-1
            try {
                board[toY + dydx[moveIndex][0]][x + 1] += board[toY][toX] * 10 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 10 / 100;
            }
            leftSand -= board[toY][toX] * 10 / 100;
            try {
                board[toY + dydx[moveIndex][0]][x - 1] += board[toY][toX] * 10 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 10 / 100;
            }
            leftSand -= board[toY][toX] * 10 / 100;

            // 5% 더하기 -> toY + (dydx[moveIndex][0] * 2)
            try {
                board[toY + (dydx[moveIndex][0] * 2)][x] += board[toY][toX] * 5 / 100;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += board[toY][toX] * 5 / 100;
            }
            leftSand -= board[toY][toX] * 5 / 100;

            // 55% 더하기 -> toY+dydx[moveIndex][0]
            try {
                board[toY + dydx[moveIndex][0]][x] += leftSand;
            } catch (ArrayIndexOutOfBoundsException e) {
                result += leftSand;
            }
        }

        // 모래 이동했으므로 0이 된다.
        board[toY][toX] = 0;
    }

    // 이동하기
    static void solution() {
        int y = board.length / 2;
        int x = board.length / 2;

        int moveCount = 0;
        int moveIndex = 0;
        int length = 1;
        while (true) {
            // 길이 증가
            if (moveCount == 2) {
                moveCount = 0;
                length++;
            }

            for (int i = 0; i < length; i++) {
                System.out.printf("From y : %d x : %d\n", y, x);

                // 흩뿌리기
                spread(y, x, moveIndex);

                y += dydx[moveIndex][0];
                x += dydx[moveIndex][1];

                printBoard();

                if (y == 0 && x == 0) {
                    return;
                }

            }

            // 방향 변경
            moveCount++;
            moveIndex = (moveIndex + 1) % dydx.length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        //
        board = new int[N][N];
        result = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();

        System.out.println(result);

        br.close();
    }
}
