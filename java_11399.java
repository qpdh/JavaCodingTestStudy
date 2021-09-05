import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_11399 {
    static int P[];

    public static int calcMinTime() {

        // A <= B 라고 하자
        // A가 가장 작은 시간, B가 그 외의 선택이라고 하자
        // N+A -> (N+A) + (N + A + B) -> 2N + 2A + B
        // N+B -> (N+B) + (N + B + A) -> 2N + 2B + A
        // 2A + B <= 2B + A 가 된다.
        // 따라서 가장 작은 시간을 선택하는 최적해가 존재한다.

        Arrays.sort(P);

        int minTime = 0;
        int countSum = 0;

        for (int i = 0; i < P.length; i++) {
            countSum += P[i];
            minTime += countSum;
        }

        return minTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 데이터 삽입
        int N = Integer.parseInt(br.readLine());
        
        P = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        // 함수 호출
        int ret = calcMinTime();

        // 결과 출력
        bw.write(ret+"\n");

        br.close();
        bw.close();
    }
}
