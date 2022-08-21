import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_16918 {
    final static int dydx[][] = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static char board[][];
    static char firstBomb[][];
    static char secondBomb[][];
    static char fullBoard[][];

    static void bomb(char[][] targetBoard, int y, int x) {
        for (int i = 0; i < dydx.length; i++) {
            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            try {
                targetBoard[toY][toX] = '.';
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        firstBomb = new char[R][C];
        secondBomb = new char[R][C];
        fullBoard = new char[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        // 다음 배열 초기화
        for (int i = 0; i < R; i++) {
            Arrays.fill(fullBoard[i], 'O');
            Arrays.fill(firstBomb[i], 'O');
            Arrays.fill(secondBomb[i], 'O');
        }

        // 폭탄이 들어가야 하는 상태
        // 폭탄이 터지는 영역

        // . : 빈 공간의 위치
        // o : 폭탄의 위치

        // 폭탄 터뜨리기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'O') {
                    bomb(firstBomb, i, j);
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (firstBomb[i][j] == 'O') {
                    bomb(secondBomb, i, j);
                }
            }
        }

        if (N == 1) {
            for (char[] row : board) {
                System.out.println(row);
            }
        } else if (N % 2 == 0) {
            for (char[] row : fullBoard) {
                System.out.println(row);
            }
        } else if (N % 4 == 1) {
            for (char[] row : secondBomb) {
                System.out.println(row);
            }
        } else if (N % 4 == 3) {

            for (char[] row : firstBomb) {
                System.out.println(row);
            }
        }

        br.close();
        bw.close();
    }
}
