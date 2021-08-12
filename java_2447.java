import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_2447 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static char board[][];

    final static int dydx[][] = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 2 }, { 2, 0 }, { 2, 1 }, { 2, 2 } };

    /*
     ***
     * *
     ***
     */

    // *********
    // * ** ** *
    // *********
    // *** ***
    // * * * *
    // *** ***
    // *********
    // * ** ** *
    // *********

    // ***************************
    // * ** ** ** ** ** ** ** ** *
    // ***************************
    // *** ****** ****** ***
    // * * * ** * * ** * * *
    // *** ****** ****** ***
    // ***************************
    // * ** ** ** ** ** ** ** ** *
    // ***************************
    // ********* *********
    // * ** ** * * ** ** *
    // ********* *********
    // *** *** *** ***
    // * * * * * * * *
    // *** *** *** ***
    // ********* *********
    // * ** ** * * ** ** *
    // ********* *********
    // ***************************
    // * ** ** ** ** ** ** ** ** *
    // ***************************
    // *** ****** ****** ***
    // * * * ** * * ** * * *
    // *** ****** ****** ***
    // ***************************
    // * ** ** ** ** ** ** ** ** *
    // ***************************

    // 가운데 위치
    public static void paintStar(int y, int x, int N) {

        // if (y < 0 || y >= board.length) {
        //     return;
        // }

        // if (x < 0 || x >= board.length) {
        //     return;
        // }

        if (N == 3) {
            for (int i = 0; i < dydx.length; i++) {
                board[y + dydx[i][0]][x + dydx[i][1]] = '*';
            }
            return;
        }

        // 8개의 조각 채우기
        // 한 조각의 길이
        int width = N / 3;
        for (int i = 0; i < dydx.length; i++) {
            paintStar(y + dydx[i][0] * width, x + dydx[i][1] * width, N / 3);
        }

    }

    public static void printStar() throws IOException {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                bw.write(board[i][j] + "");
            }
            bw.write("\n");
        }
    }

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = ' ';
            }
        }

        paintStar(0, 0, N);

        printStar();

        // N이 3일 때
        // 1,1
        // 한 조각의 너비 = 1

        // N이 9일 때
        // 3,3
        // 한 조각의 너비 = 3

        // N이 27일 때
        // 9,9
        // 한 조각의 너비 = 9

        // N이 81일 때
        // 27,27
        // 한 조각의 너비 = 27

        br.close();
        bw.close();
    }
}
