import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_16395 {
    static int cache[][];

    public static int pascal(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }

        if (cache[n][k] != -1) {
            return cache[n][k];
        }

        cache[n][k] = pascal(n - 1, k - 1) + pascal(n - 1, k);
        return cache[n][k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        cache = new int[30][30];
        for (int i = 0; i < 30; i++) {
            Arrays.fill(cache[i], -1);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bw.write(pascal(n-1, k-1) + "\n");

        br.close();
        bw.close();

    }
}
