import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_1003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int fibonacci(int n, int ret[]) throws IOException {
        if (n == 0) {
            ret[0] += 1;
            return 0;
        } else if (n == 1) {
            ret[1] += 1;
            return 1;
        } else {
            return fibonacci(n - 1, ret) + fibonacci(n - 2, ret);
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int ret[] = new int[2];
            int N = Integer.parseInt(br.readLine());
            fibonacci(N, ret);
            bw.write(ret[0] + " " + ret[1] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
