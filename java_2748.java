import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_2748 {
    static long cache[];

    public static long fib(int n) {
        if (cache[n] != -1) {
            return cache[n];
        }

        cache[n] = fib(n - 1) + fib(n - 2);
        return cache[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        cache = new long[n + 1];
        for (int i = 0; i < n + 1; i++) {
            cache[i] = -1;
        }

        cache[0] = 0;
        cache[1] = 1;

        bw.write(fib(n) + "\n");

        br.close();
        bw.close();

    }
}
