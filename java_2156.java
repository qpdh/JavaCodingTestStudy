import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class java_2156 {
    static int waters[];
    static int[] cache;

    // // 현재 인덱스를 먹어야 하는데
    // // 이전에 연속으로 얼마나 먹었는가?
    // public static int loop(int pre, int index, int count) {
    // // 종료 조건
    // // 1. 마지막 포도주 잔까지 마셨을 경우
    // // 2. 마지막 -1 이지만 이전에 연속으로 마셨을 경우
    // if (index >= waters.length || (index >= waters.length - 1 && count >= 2)) {
    // return pre;
    // }

    // // 이미 최댓값이 저장되어 있다면
    // // 최댓값 반환
    // if (cache[index][count] != -1) {
    // return cache[index][count];
    // }

    // int take1 = 0, take2 = 0;
    // // 킹우의 수
    // // 연속으로 2번 미만으로 먹었어야 함
    // if (count < 2) {
    // // 1. 현재 것 먹을 수 있으면 먹기
    // take1 = loop(pre + waters[index], index + 1, count + 1);
    // }
    // // 2. 현재 것 먹을 수 있지만 안먹기
    // // 3. 현재 것 먹을 수 없고 안먹기
    // take2 = loop(pre, index + 1, 0);

    // cache[index][count] = Math.max(take1, take2);

    // return cache[index][count];
    // }

    public static int solve(int n, int[] waters) {
        // cache 만들기
        // [n] n 포도주위치에 왔을 때 누적된 최대 포도주의 양
        cache = new int[n];

        cache[0] = waters[0];

        for (int i = 1; i < n; i++) {
            // i번째 포도주에서
            // 2연속으로 먹는 경우
            int take1 = 0, take2 = 0;
            if (i >= 1) {
                take1 = waters[i - 1] + waters[i];
                if (i >= 3) {
                    take1 += cache[i - 3];
                }
            }

            if (i >= 2) {
                // 1연속으로만 먹는 경우
                take2 = cache[i - 2] + waters[i];
            }

            cache[i] = Math.max(take1, take2);

            // 아얘 건너 뛰는 경우
            cache[i] = Math.max(cache[i - 1], cache[i]);

        }

        int res = 0;
        for (int i = 0; i < cache.length; i++) {
            if (res < cache[i]) {
                res = cache[i];
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        waters = new int[n];

        for (int i = 0; i < n; i++) {
            waters[i] = Integer.parseInt(br.readLine());
        }

        int res = solve(n, waters);

        bw.write(res + "\n");

        br.close();
        bw.close();

    }
}
