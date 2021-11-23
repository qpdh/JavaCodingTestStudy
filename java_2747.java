import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class java_2747 {
    static int table[];

    static int solve(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int ret = 0;

        if (table[n] != -1) {
            return table[n];
        }

        ret = solve(n - 1) + solve(n - 2);

        table[n] = ret;

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        table = new int[n+1];
        Arrays.fill(table, -1);

        int result = solve(n);

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}
