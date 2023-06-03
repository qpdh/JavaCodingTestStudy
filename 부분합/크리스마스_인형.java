package 부분합;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 크리스마스_인형 {
    static int K;
    static int psum[];

    static int waysToBuy() {
        final int MOD = 20091101;

        long result = 0;

        List<Long> count = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            count.set(psum[i], count.get(psum[i]) + 1);
        }

        // 두 번 이상 본 적이 있다면 이 중 두 개를 선택하는 방법의 수를 더한다.
        for (int i = 0; i < K; i++) {
            // 두 번 이상 등장했다면..
            if (count.get(i) >= 2) {
                result = (result + (count.get(i) * (count.get(i) - 1) / 2)) % MOD;
            }
        }

        return (int) result;
    }

    static int maxBuys() {
        int result[] = new int[psum.length];

        int prev[] = new int[K];
        Arrays.fill(prev, -1);

        for (int i = 0; i < psum.length; i++) {
            // i번째 상자를 아예 고려하지 않는 경우
            if (i > 0) {
                result[i] = result[i - 1];
            } else {
                result[i] = 0;
            }

            int loc = prev[psum[i]];
            if (loc != -1) {
                result[i] = Math.max(result[i], result[loc] + 1);
            }
            prev[psum[i]] = i;
        }

        return result[result.length - 1];
    }

    public static void main(String[] args) {

    }
}
