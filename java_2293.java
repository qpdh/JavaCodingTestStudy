import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_2293 {
    static int coins[];
    static int n;
    static int k;

    static int cache[];

    public static void solve() {
        cache[0] = 1;

        // [0]~[n-1] 인덱스의 동전 반복문
        for (int i = 0; i < n; i++) {
            // coins[i] 동전 까지 사용한 시점으로 부터
            // j원을 만들 수 있는 경우의 수
            // coins[i] 동전을 몇개 까지 사용할 수 있는가??
            for (int j = coins[i]; j <= k; j++) {
                cache[j] += cache[j - coins[i]];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // n 개의 동전
        n = Integer.parseInt(st.nextToken());
        // k 원 만들기
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        // 캐시 초기화
        // [index]원을 만드는 경우의 수
        cache = new int[k + 1];
        Arrays.fill(cache, 0);

        solve();

        int res = cache[k];

        bw.write(res + "\n");

        br.close();
        bw.close();
    }
}
