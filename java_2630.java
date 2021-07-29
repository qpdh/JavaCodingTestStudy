import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_2630 {
    static int[][] board;
    static StringBuffer ret;

    public static void zip(int y, int x, int width) {

        // 종이 크기가 1개 라면
        // 그냥 출력해버리기
        if (width == 1) {
            ret.append(board[y][x] + "");
            return;
        }

        // 모두 같은 색종이 인지 확인
        boolean isEqual = true;
        int element = board[y][x];
        for (int i = y; i < y + width; i++) {
            for (int j = x; j < x + width; j++) {
                if (board[i][j] != element) {
                    isEqual = false;
                    break;
                }
            }
            if (!isEqual) {
                break;
            }
        }

        if (isEqual) {
            ret.append(element + "");

        } else {
            // 4등분 하기
            zip(y, x, width / 2);
            zip(y, x + width / 2, width / 2);
            zip(y + width / 2, x, width / 2);
            zip(y + width / 2, x + width / 2, width / 2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ret = new StringBuffer();

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // // 보드 출력해보기
        // for (int i = 0; i < N; i++) {
        // for (int j = 0; j < N; j++) {
        // bw.write(board[i][j]+ " ");
        // }
        // bw.write("\n");
        // }

        zip(0, 0, N);

        int whiteCount = 0;
        int blueCount = 0;
        for (int i = 0; i < ret.length(); i++) {
            if ((ret.charAt(i)) == '0') {
                whiteCount += 1;
            } else {
                blueCount += 1;
            }
        }

        bw.write(whiteCount + "\n");
        bw.write(blueCount + "\n");

        bw.close();
        br.close();
    }
}
