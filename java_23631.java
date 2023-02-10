import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class java_23631 {
    static int T;
    static int N, K;

    static int findn() {
        int low = 0;
        int high = 5000;

        while (low < high) {
            int mid = (low + high) / 2;

            BigInteger bigInteger = BigInteger.valueOf(mid);
            bigInteger = bigInteger.multiply(BigInteger.valueOf(mid + 1));
            bigInteger = bigInteger.multiply(BigInteger.valueOf(K));

            if (bigInteger.compareTo(BigInteger.valueOf(2 * N)) == -1) {
                low = mid + 1;
                continue;
            }

            high = mid;
            continue;
        }

        return low;
    }

    static String solution() {
        // n(n+1)/2 <= N 인 n의 최댓값 찾기 -> 이분탐색
        // System.out.println("==============");
        int n = findn();
        // System.out.println("findn : " + n);

        // 직전 좌표와 남은 거리 구하기
        // 1. 직전 좌표(절댓값)
        // System.out.println("nowLength" + nowLength);
        // 2. 부호 부여
        int pos = 0;
        // System.out.println("pos " + pos);

        if (n % 2 == 0) {
            pos = -K * ((n + 1) / 2);
            pos -= N - 1 - (n * (n + 1) * K / 2);
            return pos + " L";
            // System.out.println(pos + " L");
        } else {
            pos = K * ((n + 1) / 2);
            pos += N - 1 - (n * (n + 1) * K / 2);
            return pos + " R";
            // System.out.println(pos + " R");
        }

        // 3. 남은 거리 구하기
        // System.out.println("leftLength : " + leftLength);

        //
        // 다음 가야할 방향 구하기

        // 좌표 구하기
        // n이 홀수면 +
        // n이 짝수면 -
        // (kn+1)/2 값
    }

    public static void main(String[] args) throws IOException {
        // N-1 미터를 뛰기
        //
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            // K
            // K*2
            // K*3
            // ...
            // 초기항 K, 등차 K인 등비수열의 합
            //
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            String result = solution();
            bw.write(result + "\n");

        }

        br.close();
        bw.close();
    }
}
