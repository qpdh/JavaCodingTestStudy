import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_2245 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        
        for (int i = 1; i <= N; i++) {
            // 1~N 까지 줄 개수만큼 별 찍기
            for (int j = 0; j < i; j++) {
                bw.write("*");
            }

            // 공백 찍기
            // N-i 줄 * 2 개 만큼 공백

            for (int j = 0; j < (N - i); j++) {
                bw.write("  ");
            }

            // 1~N 까지 줄 개수만큼 별 찍기
            for (int j = 0; j < i; j++) {
                bw.write("*");
            }
            bw.write("\n");
        }

        for (int i = N-1; i > 0; i--) {
            // 1~N 까지 줄 개수만큼 별 찍기
            for (int j = 0; j < i; j++) {
                bw.write("*");
            }

            // 공백 찍기
            // N-i 줄 * 2 개 만큼 공백

            for (int j = 0; j < (N - i); j++) {
                bw.write("  ");
            }

            // 1~N 까지 줄 개수만큼 별 찍기
            for (int j = 0; j < i; j++) {
                bw.write("*");
            }
            bw.write("\n");
        }

        br.close();
        bw.close();

    }
}
