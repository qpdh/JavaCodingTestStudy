import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_5557 {
    static int N;
    static int numbers[];
    static int lastNumber;

    // [i][j] i-1번째 숫자까지 계산했고 값이 [j]일 때 나머지로 만들 수 있는 경우의 수
    static long cache[][];

    // 20이 넘지 않는 음이아닌 정수
    // lastNumber를 만들 수 있는 경우의 수
    static long dp(int index, int value) {
        // 기저사례: 마지막 인덱스까지 처리가 된 경우
        if (index >= numbers.length) {
            if (value == lastNumber) {
                return 1;
            }
            return 0;
        }

        // 기저사례: 캐시값이 이미 존재하는 경우
        if (cache[index][value] != -1) {
            return cache[index][value];
        }

        cache[index][value] = 0;

        // 1. +
        // 더했을 때 20초과면 안된다.
        if (value + numbers[index] <= 20) {
            cache[index][value] += dp(index + 1, value + numbers[index]);
        }

        // 2. -
        // 뺐을 때 0미만이면 안된다.
        if (0 <= value - numbers[index]) {
            cache[index][value] += dp(index + 1, value - numbers[index]);
        }

        return cache[index][value];
    }

    static long solution() {
        // N-1개의 숫자
        // 0~20까지의 값을 가진다.
        cache = new long[N - 1][21];

        // 캐시 초기화
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }

        // 계산
        long result = dp(1, numbers[0]);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N - 1; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        lastNumber = Integer.parseInt(st.nextToken());

        long result = solution();

        System.out.println(result);

        br.close();
    }
}
