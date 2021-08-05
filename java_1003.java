import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_1003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // n 값일 때 0의 갯수 1의 갯수
    static int cache[][];

    static int[] fibonacci(int n) throws IOException {
        if (cache[n][0] != -1) {
            return cache[n];
        }

        cache[n][0] = 0;
        cache[n][1] = 0;

        if (n == 0) {
            cache[n][0] += 1;
            return cache[n];
        } else if (n == 1) {
            cache[n][1] += 1;
            return cache[n];
        } else {
            int[] a = fibonacci(n - 1);
            int[] b = fibonacci(n - 2);
            cache[n][0] += a[0] + b[0];
            cache[n][1] += a[1] + b[1];
            return cache[n];
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        cache = new int[41][2];

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int ret[] = new int[2];
            int N = Integer.parseInt(br.readLine());

            // 캐시 초기화
            for (int j = 0; j < cache.length; j++) {
                for (int k = 0; k < cache[0].length; k++) {
                    cache[j][0] = -1;
                    cache[j][1] = -1;
                }
            }

            fibonacci(N);
            bw.write(cache[N][0] + " " + cache[N][1] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
