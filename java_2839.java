import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_2839 {
    final static int INF = 987654321;
    static int cache[];

    // N킬로그램 배달해야 할 때
    // 최소 봉지 수를 반환
    public static int sugarDelivery(int N) {
        // 2개 이하의 사탕은 묶지 못함.
        if (N <= 2) {
            return INF;
        }

        // 5개를 1묶음으로
        // 가장 적게 쓰기
        if (N == 5) {
            return 1;
        }

        // 3개를 1묶음으로
        // 가장 적게 쓰기
        if (N == 3) {
            return 1;
        }

        if (cache[N] != -1) {
            return cache[N];
        }

        // 3개 짜리로 나누느냐, 5개 짜리로 나누느냐
        // 더 작은 봉지 수 가져가기
        cache[N] = Math.min(sugarDelivery(N - 3), sugarDelivery(N - 5)) + 1;

        return cache[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        cache = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            cache[i] = -1;
        }

        int ret = sugarDelivery(N);
        // 만들 수 없는 경우
        if (ret >= 5000) {
            bw.write(-1 + "\n");
        }
        // 만들 수 있는 경우
        else {
            bw.write(ret + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
