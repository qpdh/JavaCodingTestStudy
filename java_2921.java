import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_2921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            for (int j = N - i-1; j > 0; j--) {
                bw.write(" ");
            }
            for (int j = 0; j <= i; j++) {
                bw.write("* ");
            }
            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}
