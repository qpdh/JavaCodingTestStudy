import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_14501 {
    static int N;

    static int timeList[];
    static int priceList[];

    static int cache[];

    // today부터 최대의 price를 버는 방법.
    static int dp(int today) {
        if (today == N) {
            return 0;
        }

        if (cache[today] != -1) {
            return cache[today];
        }

        cache[today] = 0;
        // 오늘 상담을 하는 경우
        if (today + timeList[today] <= N) {
            cache[today] = Math.max(cache[today], dp(today + timeList[today]) + priceList[today]);
        }
        // 오늘 상담을 하지 않는 경우
        cache[today] = Math.max(cache[today], dp(today + 1));

        return cache[today];
    }

    static int solve() {
        int result = 0;

        //
        cache = new int[N];
        Arrays.fill(cache, -1);

        result = dp(0);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        timeList = new int[N];
        priceList = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            timeList[i] = Integer.parseInt(st.nextToken());
            priceList[i] = Integer.parseInt(st.nextToken());
        }

        //
        int result = solve();

        System.out.println(result);

        br.close();
    }
}
