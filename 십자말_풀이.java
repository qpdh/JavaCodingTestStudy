import java.util.Arrays;
import java.util.Scanner;

public class 십자말_풀이 {
    static String A, B;

    static char board[][];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        A = scanner.next();
        B = scanner.next();

        board = new char[B.length()][A.length()];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }

        int y = -1;
        int x = -1;

        boolean isDone = false;
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    x = i;
                    y = j;
                    isDone = true;
                    break;
                }
            }
            if (isDone) {
                break;
            }
        }

        board[y] = A.toCharArray();

        for (int i = 0; i < board.length; i++) {
            board[i][x] = B.charAt(i);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        scanner.close();
    }
}
