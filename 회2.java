import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 회2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int board[][] = new int[N][N * 4];

        // 공백 출력
        for (int i = 0; i < N; i++) {
            for (int j = N - i; j > 0; j--) {
                board[i][j] = 0;
                // System.out.print(" ");
            }

            // 짝수 줄이라면 0 2 4 6 ...
            // 거꾸로 출력
            // (2*i +1) 개 만큼
            // N-i 2 * i +1부터
            if (i % 2 == 0) {
                for (int j = i * 2 + 1; j > 0; j--) {
                    S = (S + 1) % 9;
                    board[i][N - i + j] = S + 1;
                    // System.out.print(S + 1);
                }
            }

            // 홀수 줄이라면 1 3 5 7 ...
            // 정방향으로 출력
            // (2*i +1) 개 만큼
            // N-i 부터
            if (i % 2 != 0) {
                for (int j = 1; j <= i * 2 + 1; j++) {
                    S = (S + 1) % 9;
                    board[i][N - i + j] = S + 1;
                    // System.out.print(S + 1);
                }
            }
        }

        for (int i[] : board) {
            for (int j : i) {
                if (j == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(j);
                }

            }
            System.out.println();
        }

        br.close();
        bw.close();
    }
}
