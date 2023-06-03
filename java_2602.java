import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class java_2602 {
    final static int ANGEL = 0;
    final static int DEVIL = 1;

    static long cache[][][];

    static int scrollLength;
    static int bridgeLength;

    static char scroll[];

    static char angelBridge[];
    static char devilBridge[];

    // [천사|악마] 부터 시작하며 [i] 문자를 읽을 차례에 [j] 위치 이후(포함)에서 만들 수 있는 경우의 수
    static long dp(int place, int scrollIndex, int bridgeIndex) {
        // 스크롤 순서대로 모두 건너면 성공
        if (scrollIndex >= scrollLength) {
            return 1;
        }
        // 스크롤 순서대로 모두 건너지 못하고 끝까지 와버린 상황
        if (bridgeIndex >= bridgeLength) {
            return 0;
        }

        // 캐시가 이미 존재한다면...
        if (cache[place][scrollIndex][bridgeIndex] != -1) {
            return cache[place][scrollIndex][bridgeIndex];
        }

        cache[place][scrollIndex][bridgeIndex] = 0;
        // dp 처리

        // 현재 위치가 천사라면
        if (place == ANGEL) {
            // 악마 다리에서 탐색
            for (int i = bridgeIndex; i < bridgeLength; i++) {
                // 스크롤에 해당하는 다음 위치 찾기
                if (devilBridge[i] == scroll[scrollIndex]) {
                    cache[place][scrollIndex][bridgeIndex] += dp(DEVIL, scrollIndex + 1, i + 1);
                }
            }
        }

        // 현재 위치가 악마라면
        else if (place == DEVIL) {
            // 천사 다리에서 탐색
            for (int i = bridgeIndex; i < bridgeLength; i++) {
                // 스크롤에 해당하는 다음 위치 찾기
                if (angelBridge[i] == scroll[scrollIndex]) {
                    cache[place][scrollIndex][bridgeIndex] += dp(ANGEL, scrollIndex + 1, i + 1);
                }
            }
        }

        // 결과 반환
        return cache[place][scrollIndex][bridgeIndex];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 출발은 아무데서나 가능 [0] 시작 [1] 시작 2개로 나뉜다.
        // 왔다 갔다 해야한다.
        // 모든 문자열을 밟아야 한다.

        scroll = br.readLine().toCharArray();
        scrollLength = scroll.length;

        // System.out.println(scroll);

        angelBridge = br.readLine().toCharArray();
        devilBridge = br.readLine().toCharArray();
        bridgeLength = angelBridge.length;

        // 캐시 초기화
        cache = new long[2][scrollLength][bridgeLength];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < scrollLength; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }

        dp(ANGEL, 0, 0);
        dp(DEVIL, 0, 0);

        // for (int c1[][] : cache) {
        // for (int c2[] : c1) {
        // for (int c3 : c2) {
        // System.out.println(c3);
        // }
        // }
        // }

        long result = Math.max(cache[ANGEL][0][0], 0);
        result += Math.max(cache[DEVIL][0][0], 0);

        System.out.println(result);

        // [i] 문자까지 읽었고 현재 [천사] [악마] 일때 경우의 수
        // 현재 위치가 [j] 이며.

        // (i :: 문자 까지 읽음, j :: 현재 위치, 현재 천사|악마)
        // 20 * 100 * 2 = 4000

        br.close();
        bw.close();
    }
}
