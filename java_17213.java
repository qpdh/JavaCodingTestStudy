import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class java_17213 {
    static int N, M;
    // N개의 종류 중에서 중복을 허용하여 M-N 개를 훔치는 경우의 수
    // [0] 번째 훔치기... [i-1]번째 훔치기

    // [i]번째 과일을 선택, 추가로 훔쳐야 하는 과일이 [j]일 때 경우의 수
    // [i][0] = 1
    // [][0] = 0
    static int cache[][];

    // nCr = n-1Cr + n-1Cr-1
    static int dp(int fruit, int remainCount) {
        // 전부 다 훔쳤다면...경우의 수 추가
        if (remainCount == 0) {
            return 1;
        }
        // 만약 과일 갯수 범위를 벗어나면..불가능
        if (N <= fruit) {
            return 0;
        }


        if (cache[fruit][remainCount] != -1) {
            return cache[fruit][remainCount];
        }

        // 캐시 초기화
        cache[fruit][remainCount] = 0;

        for (int i = 0; i <= remainCount; i++) {
            // fruit 과일을 i개 만큼 훔친다.
            // fruit+1 과일을 remainCount-i 만큼 훔치는 경우의 수
            cache[fruit][remainCount] += dp(fruit + 1, remainCount - i);
        }

        return cache[fruit][remainCount];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N개의 과일 종류
        N = Integer.parseInt(br.readLine());

        // M개를 훔치자
        M = Integer.parseInt(br.readLine());

        // 적어도 1개를 훔쳐야 하므로...
        // 1개를 이미 훔친 상태에서 시작한다.
        //
        // 훔쳐야 하는 과일의 수
        int toStealCount = M - N;

        // (N)_C_(M-N)

        // 캐시 초기화
        cache = new int[N][toStealCount + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        int result = dp(0, toStealCount);

        System.out.println(result);

        br.close();
        bw.close();
    }
}
