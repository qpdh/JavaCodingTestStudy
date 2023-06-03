import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class java_2302 {
    // 좌석의 개수
    static int N;
    // 고정석(VIP)의 개수
    static int M;

    // [i]번째 인덱스부터 시작할 때
    static int cache[];

    static boolean seats[];

    // 현재 인덱스부터 사람들이 좌석에 앉을 수 있는 방법의 가짓수
    static int dp(int index) {
        // 마지막 인덱스 까지 도달했다면..종료
        if (index >= N) {
            return 1;
        }

        // 이미 값이 존재한다면..
        if (cache[index] != -1) {
            return cache[index];
        }

        // 현재 값이 VIP 라면?
        if (seats[index]) {
            return dp(index + 1);
        }

        // index, index+1
        // index+1, index, index+2

        cache[index] = 0;

        // 바꾸지 않는 경우
        cache[index] += dp(index + 1);
        // [i,i+1] 바꾸는 경우
        // 다음 인덱스가 존재해야하며, VIP가 아니여야 함
        if (index + 1 < N) {
            if (!seats[index + 1]) {
                cache[index] += dp(index + 2);
            }
        }

        return cache[index];
    }

    static int solution() {
        // 캐시 초기화
        cache = new int[N];
        Arrays.fill(cache, -1);

        int result = dp(0);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        seats = new boolean[N];
        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());
            seats[vip - 1] = true;
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }
}
