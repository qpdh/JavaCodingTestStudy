import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_1463 {
    final static int INF = 987654321;

    static int cache[];

    // N을 1로 만드는 최소 횟수를 반환
    public static int makeOne(int N) {
        if (N < 1) {
            return INF;
        }

        if (N == 1) {
            return 0;
        }

        if (N == 2 || N == 3) {
            return 1;
        }

        if (cache[N] != -1) {
            return cache[N];
        }

        int ret = INF;

        if (N % 3 == 0) {
            ret = Math.min(ret, 1 + makeOne(N / 3));
        }
        if (N % 2 == 0) {
            ret = Math.min(ret, 1 + makeOne(N / 2));
        }
        ret = Math.min(ret, 1 + makeOne(N - 1));

        cache[N] = ret;
        return cache[N];
    }

    // N을 1로 만드는 최소 횟수를 반환
    public static int makeOne2(int N) {
        if (N < 1) {
            return INF;
        }

        if (N == 1) {
            cache[N] = 0;
            return cache[N];
        }

        if (N == 2 || N == 3) {
            cache[N] = 1;
            return cache[N];
        }

        if (cache[N] != -1) {
            return cache[N];
        }

        int ret = INF;

        if (N % 3 == 0) {
            ret = Math.min(ret, 1 + cache[N / 3]);
        }
        if (N % 2 == 0) {
            ret = Math.min(ret, 1 + cache[N / 2]);
        }
        ret = Math.min(ret, 1 + cache[N - 1]);

        cache[N] = ret;
        return cache[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        cache = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            cache[i] = -1;
        }

        for(int i=1;i<N+1;i++){
            makeOne2(i);
        }

        bw.write(cache[N] + "\n");

        br.close();
        bw.close();
    }
}
