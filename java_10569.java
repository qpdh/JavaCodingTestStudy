import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class java_10569 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = scanner.nextInt();
        for (int iter = 0; iter < T; iter++) {
            int V = scanner.nextInt();
            int E = scanner.nextInt();

            bw.write((2-V+E)+"\n");
        }

        scanner.close();
        bw.close();

    }
}
