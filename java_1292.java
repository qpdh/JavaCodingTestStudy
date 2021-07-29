import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1292 {
    public static int solve(int A, int B) {
        int innerCount = 1;

        int ret = 0;

        int i = 1;

        while (i <= B) {
            // innerCount를 innerCount만큼 반복
            for (int j = 0; j < innerCount; j++) {
                //System.out.println(innerCount);

                if (i >= A) {
                    ret += innerCount;
                }

                i++;

                if (i > B)
                    break;
            }
            innerCount++;
        }
        // 앞 부분 버리기
        return ret;

    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // N으로 시작하는 수열의 위치는?
        // 1은 0 번째
        // 2는 1 번째
        // 3은 1+2 번째
        // 4는 1+2+3 번째
        // ...
        // N은 1+2+...+(N-1) 번째

        // 언제 까지 N인 수열인가?
        // 1은 1개
        // 2는 2개
        // ...
        // N는 N개

        // 인덱스 범위로 표현한다면?
        // 1은 0번째 1개
        // 2는 1번째 ~ 2번째
        // 3은 3번째 ~ 5번째
        // 4는 6번째 ~ 9번째

        // N은 1+2+...+(N-1) 번째 ~ 1+2+...+(N-1) + (N-1) 번째까지
        // N은 (N-1)(N)/2 번째 ~ ((N-1)(N)/2) + (N-1) 번째 까지
        bw.write(solve(A, B) + "\n");

        bw.close();
        br.close();

    }
}
