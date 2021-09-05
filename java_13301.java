import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class java_13301 {
    static long cache[];

    public static long tile(int n) {
        if (n <= 2)
            return 1;

        if (cache[n] != -1) {
            return cache[n];
        }

        cache[n]  = tile(n-1)+tile(n-2);
        return cache[n];
    }

    public static long calcRound(int n) {
        if (n == 1) {
            return 4;
        }
        return tile(n) * 4 + tile(n - 1) * 2;
    }

    public static void main(String[] args) throws IOException {
        cache = new long[81];
        Arrays.fill(cache, -1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // for(int i=1;i<=80;i++){
        //     bw.write(calcRound(i) + "\n");
        // }
        bw.write(calcRound(N) + "\n");

        br.close();
        bw.close();
    }
}
