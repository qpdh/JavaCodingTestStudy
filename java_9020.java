import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_9020 {
    static int cache[];

    public static boolean isPrime(int N) {
        if (cache[N] != -1) {
            // 0이면 소수가 아님
            if (cache[N] == 0) {
                return false;
            }
            // 1이면 소수임
            else {
                return true;
            }
        }

        int i = 2;

        while (i <= Math.sqrt(N)) {
            if (N % i == 0) {
                cache[N] = 0;
                return false;
            }
            ++i;
        }
        cache[N] = 1;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        cache = new int[10001];

        for (int i = 0; i < 10001; i++) {
            cache[i] = -1;
        }

        for (int i = 0; i < T; i++) {
            // 4인경우 2+2
            // 나머지 최소 3부터
            // 소수인지 판별법
            // 루트 N까지 소수인지 판별

            int n = Integer.parseInt(br.readLine());

            int minDiff = 987654321;
            int pare[] = new int[2];

            for (int j = 2; j <= n / 2; j++) {
                if (isPrime(j)) {
                    if (isPrime(n - j)) {
                        if (n - j - j < minDiff) {
                            pare[0] = j;
                            pare[1] = n - j;
                            minDiff = n - j - j;
                        }
                        //System.out.println(j + " " + (n - j));
                    }
                }
            }

            bw.write(pare[0]+" "+pare[1]+"\n");
        }
        br.close();
        bw.close();

    }
}
