import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class java_9295 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스
        int T = scanner.nextInt();

        for (int iter = 0; iter < T; iter++) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            bw.write("Case " + (iter + 1) + ": " + (first + second) + "\n");
        }

        scanner.close();
        bw.close();
    }
}
