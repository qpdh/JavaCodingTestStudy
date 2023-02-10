import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_2343 {
    // 강의의 수 (N)
    // 블루레이의 수 (M)
    static int N, M;

    static int lectures[];

    // size로 모든 강의를 담을 수 있는가?
    static boolean canMake(int size) {
        // 이전 누적합
        int preSize = 0;

        int blueLayCount = 0;

        for (int i = 0; i < N; i++) {
            if (size < lectures[i] - preSize) {
                // i-1까지 담을 수 있다.
                preSize = lectures[i - 1];
                // 블루레이 1장 소모
                blueLayCount++;

                // 가진 블루레이의 수를 초과했다면..
                if (i <= N - 1 && blueLayCount >= M) {
                    return false;
                }
            }
        }
        // 디스크가 남는다 -> 이전 강의를 남은 디스크에 분배.(어처피 최소 1개는 들어갈 수 있도록 용량을 조절하므로..)
        //

        return true;
    }

    static int solution(int maxLength) {
        int result = 0;

        int low = maxLength;
        int high = lectures[N - 1] + 1;

        while (low < high) {
            // 블루레이를 mid 크기로 만들 수 있는가?
            int mid = (low + high) / 2;

            boolean able = canMake(mid);

            if (able) {
                high = mid;
                continue;
            }

            low = mid + 1;
        }

        System.out.println(low);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lectures = new int[N];
        st = new StringTokenizer(br.readLine());
        lectures[0] = Integer.parseInt(st.nextToken());
        int maxLength = 0;
        for (int i = 1; i < N; i++) {
            int lecture = Integer.parseInt(st.nextToken());
            if (maxLength < lecture) {
                maxLength = lecture;
            }
            lectures[i] = lectures[i - 1] + lecture;
        }

        int result = solution(maxLength);

        br.close();
    }
}
