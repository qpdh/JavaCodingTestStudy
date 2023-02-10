import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_2960 {
    static int N, K;
    static boolean numbers[];

    // K번째 지워진 수를 반환하는 메소드
    static int solution() {
        numbers = new boolean[N + 1];

        int count = 0;

        for (int i = 2; i <= N; i++) {
            if (numbers[i]) {
                continue;
            }

            // i의 배수를 모두 지운다.
            for (int j = i; j <= N; j += i) {
                if (numbers[j]) {
                    continue;
                }

                numbers[j] = true;
                count++;

                if (count == K) {
                    return j;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = solution();

        System.out.println(result);

        br.close();
    }
}
