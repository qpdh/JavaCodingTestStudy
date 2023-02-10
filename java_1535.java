import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_1535 {
    static int N;

    static int happyList[];
    static int hitList[];

    static int cache[][];

    // hitPoint일 때, index사람을 선택하거나 안하거나해서 가장 큰 기쁨을 반환
    static int dp(int index, int hitPoint) {
        if (index >= N) {
            return 0;
        }

        if (cache[index][hitPoint] != -1) {
            return cache[index][hitPoint];
        }

        cache[index][hitPoint] = 0;
        // 이번 인덱스를 선택한다.
        if (hitPoint - hitList[index] > 0) {
            cache[index][hitPoint] = Math.max(cache[index][hitPoint],
                    dp(index + 1, hitPoint - hitList[index]) + happyList[index]);
        }
        // 건너 뛴다.
        cache[index][hitPoint] = Math.max(cache[index][hitPoint], dp(index + 1, hitPoint));

        return cache[index][hitPoint];
    }

    // 0번째 사람과 인사하는 경우 -> 체력 99, 기쁨 20
    // 1번째 사람과 인사하는 경우 -> 체력 78, 기쁨 50
    // 2번째 사람과 인사하는 경우 -> X 78-79 < 0
    // 2번째 사람과 인사하지 않는 경우 -> 50

    // cache[0][100] = 50
    // 0번째와 인사, 1번째와 인사X -> 99/20 -> 20/45

    // 0번째 사람과 인사하지 않는 경우
    // 1번째 사람과 인사하는 경우 -> 79/30 -> 0X
    // 0X/1X/2O -> 25

    static int solve() {
        int result = 0;

        cache = new int[N][101];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        result = dp(0, 100);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        happyList = new int[N];
        hitList = new int[N];

        // 잃는 체력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hitList[i] = Integer.parseInt(st.nextToken());
        }

        // 기쁨
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            happyList[i] = Integer.parseInt(st.nextToken());
        }

        int result = solve();

        System.out.println(result);

        br.close();

    }
}
