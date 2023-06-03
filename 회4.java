import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 회4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int board[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        // 학생마다 한번이라도 같은 반이였는 어쩌구
        int result[] = new int[N];

        for (int i = 0; i < N; i++) {
            // j+1 학년
            // 다른 친구들
            for (int k = 0; k < N; k++) {
                for (int j = 0; j < 5; j++) {
                    if (i != k) {
                        if (board[i][j] == board[k][j]) {
                            result[i]++;
                            break;
                        }
                    }
                }

            }
        }

        int maxIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[maxIndex] < result[i]) {
                maxIndex = i;
            }

        }
        System.out.println(maxIndex + 1);
        br.close();
        bw.close();
    }
}
