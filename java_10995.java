import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class java_10995 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            // 홀수면 띄어쓰기 추가
            if (i % 2 == 1) {
                bw.write(" ");
            }

            for (int j = 0; j < N; j++) {
                bw.write("* ");
            }

            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}
