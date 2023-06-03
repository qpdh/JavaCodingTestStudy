import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_16974 {

    // N : 레벨-N 버거
    // X : 아래서부터 X장 먹음
    static int N;
    static long X;

    // 각 레벨 버거에 들어가는 버거의 크기
    static long buggers[];
    // 각 레벨 버거에 들어가는 패티의 양
    static long patties[];

    // N레벨 버거를 밑에서부터 X개 먹었을 때 먹는 패티의 양
    static long count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());

        long result = solution();

        System.out.println(result);

        br.close();
    }

    private static long solution() {
        // 캐시 초기화
        count = 0;

        buggers = new long[N + 1];
        patties = new long[N + 1];

        Arrays.fill(buggers, -1);
        Arrays.fill(patties, -1);

        patties[0] = 1;
        buggers[0] = 1;

        dp(N);

        countPatties(N, X);

        return count;
    }

    private static void countPatties(int n, long x) {
        // 더 못먹는 경우
        if (x == 0) {
            return;
        }

        // 0레벨 버거의 경우
        if (n == 0) {
            count++;
            return;
        }

        // 맨 밑에있는 번을 먹음
        x--;

        // n-1 버거를 다 먹을 수 있는가?
        if (buggers[n - 1] == x) {
            x -= buggers[n - 1];
            count += patties[n - 1];
            return;
        }
        // n-1 버거를 먹고도 남는가? + 패티하나 먹기
        else if (buggers[n - 1] < x) {
            x -= buggers[n - 1] + 1;
            count += patties[n - 1] + 1;
            countPatties(n - 1, x);
        }
        // n-1 버거를 다 못먹는가?
        else {
            countPatties(n - 1, x);
        }
    }

    // 현재 레벨이 level 일 때, 버거의 크기 반환
    private static long dp(int level) {
        // 레벨 0은 패티만 존재
        if (level == 0) {
            return 1;
        }

        // 버거가 이미 존재하면..처리
        if (buggers[level] != -1) {
            return buggers[level];
        }

        // [B] [N-1버거] [P] [N-1버거] [B]
        buggers[level] = 3 + dp(level - 1) * 2;
        patties[level] = 1 + patties[level - 1] * 2;

        return buggers[level];
    }
}
