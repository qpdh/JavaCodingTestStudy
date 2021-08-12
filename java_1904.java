import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_1904 {
    final static int MOD = 15746;
    static int cache[];

    // 타일이 N개 있을 때
    // 만들 수 있는 2진수열의 수
    // 앞에 뭘 설치했는가?
    public static int tiling(int N) {
        if (N == 1) {
            return 1;
        }

        if (N == 2) {
            return 2;
        }

        if (cache[N] != -1) {
            return cache[N];
        }

        cache[N] = tiling(N-1) % MOD + tiling(N-2)%MOD;
        cache[N] %= MOD;
        
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

        bw.write(tiling(N) + "\n");

        br.close();
        bw.close();
    }
}
