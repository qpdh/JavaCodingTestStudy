import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_2565 {
    static int utilityPole[];
    static int cache[][];

    // 전봇대 최댓값
    static int maxValue;
    //
    static int keyList[];

    // i 포함 or 포함X [i] 들을 오름차순으로 만들 때 최대 갯수
    static int dp(int index, int preValue) {
        if (index >= keyList.length) {
            return 0;
        }

        if (cache[keyList[index]][preValue] != -1) {
            return cache[keyList[index]][preValue];
        }

        cache[keyList[index]][preValue] = 0;

        // 오름차여야 함
        // 현재 전선 선택
        if (preValue < utilityPole[keyList[index]]) {
            cache[keyList[index]][preValue] = Math.max(cache[keyList[index]][preValue],
                    1 + dp(index + 1, utilityPole[keyList[index]]));
        }
        // 오름차가 아닌 경우
        // 현재 전선 선택X 다음 전선 선택

        cache[keyList[index]][preValue] = Math.max(cache[keyList[index]][preValue], dp(index + 1, preValue));

        return cache[keyList[index]][preValue];

        //
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        utilityPole = new int[501];

        cache = new int[501][501];
        for (int i = 0; i < 501; i++) {
            Arrays.fill(cache[i], -1);
        }

        int n = Integer.parseInt(br.readLine());

        keyList = new int[n];

        maxValue = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            utilityPole[A] = B;
            if (maxValue < A) {
                maxValue = A;
            }
            keyList[i] = A;
        }

        Arrays.sort(keyList);

        int result = dp(0, 0);

        System.out.println(n - result);

        br.close();
    }
}
