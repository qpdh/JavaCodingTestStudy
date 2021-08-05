import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_1100 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = 0;

        // 8줄 반복
        for (int i = 0; i < 8; ++i) {
            char board[] = br.readLine().toCharArray();

            // 짝수 줄이면 0부터 시작, 아니면 1부터 시작
            int j = (i % 2 == 0) ? 0 : 1;

            for (; j < 8; j += 2) {
                if (board[j] == 'F') {
                    ++count;
                }
            }
        }

        bw.write(count + "\n");

        br.close();
        bw.close();
    }
}
