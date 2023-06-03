import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_13398 {
    static int n;
    static int cache[][];
    static int numberList[];
    static int result;

    // 현재 위치를 포함하여
    static int dp(int index, int jumped) {
        // 배열의 범위를 벗어나면..
        if (index >= n) {
            return 0;
        }

        if (cache[index][jumped] != Integer.MIN_VALUE) {
            return cache[index][jumped];
        }

        // 바로 종료하는 경우
        cache[index][jumped] = Math.max(cache[index][jumped], numberList[index]);

        // 현재 숫자부터 시작할 것인가?
        cache[index][jumped] = Math.max(cache[index][jumped], numberList[index] + dp(index + 1, jumped));

        // 다음 위치 건너뛰는 경우
        if (jumped == 0) {
            if (index + 1 < n) {
                cache[index][jumped] = Math.max(cache[index][jumped], numberList[index] + dp(index + 2, 1));
            }
        }

        if (result < cache[index][jumped]) {
            result = cache[index][jumped];
        }

        // 무조건 한 개는 선택해야 한다.
        return cache[index][jumped];
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        // 캐시 초기화
        cache = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], Integer.MIN_VALUE);
        }

        // 결과값 초기화
        result = Integer.MIN_VALUE;

        // n개의 수열 입력받기
        numberList = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numberList[i] = Integer.parseInt(st.nextToken());
        }

        dp(0, 0);

        // for (int i = 0; i < n; i++) {
        // if (result < cache[i][0]) {
        // result = cache[i][0];
        // }
        // if (result < cache[i][1]) {
        // result = cache[i][1];
        // }
        // }
        // result = Math.max(result, dp(0, 1));

        System.out.println(result);

        // for (int i[] : cache) {
        // System.out.println(i[0] + " " + i[1]);
        // }
        // 수를 제거하는 경우 | 제거하지 않는 경우
        // 더하기 시작하기

        // cache[i][j]
        // i번째부터 더하기
        // [j] :: 0 제거하지 않았다.
        // [j] :: 1 이전에 이미 제거했다.

        // [i] 이후 연속한 수의 합의 최댓값

        // [0][0] 0이후 연속한 수의 합의 최댓값 (제거안한 버전)
        // [0][1] 0이후 연속한 수의 합의 최댓값 (제거한 버전)
        // [i][0] [i][1] 중 max값

        br.close();
        bw.close();
    }
}
