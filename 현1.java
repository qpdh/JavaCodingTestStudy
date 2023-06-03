import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 현1 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int board[][];

    public static void zip(int y, int x, int width) throws IOException {

        if (width == 1) {
            bw.write(board[y][x] + "");
            return;
        }

        // 범위 내의 요소가 모두 같으면?
        boolean isEqual = true;
        int element = board[y][x];

        for (int i = y; i < y + width; i++) {
            for (int j = x; j < x + width; j++) {
                if (board[i][j] != element) {
                    isEqual = false;
                    break;
                }
            }
            if (!isEqual)
                break;
        }

        if (isEqual) {
            bw.write(element + "");
            return;
        }

        else {
            bw.write("(");
            // 4개로 나누기
            zip(y, x, width / 2);
            zip(y, x + width / 2, width / 2);
            zip(y + width / 2, x, width / 2);
            zip(y + width / 2, x + width / 2, width / 2);
            bw.write(")");
        }

    }

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            String st = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = st.charAt(j) - '0';
            }
        }

        // // 보드 출력하기
        // for (int i = 0; i < N; i++) {
        // for (int j = 0; j < N; j++) {
        // System.out.print(board[i][j]);
        // }
        // System.out.println();
        // }

        zip(0, 0, N);

        bw.close();
        br.close();
    }
}
