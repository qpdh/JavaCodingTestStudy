import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_11060 {
    static int N;
    static int A[];

    static int cache[][];

    public static void main(String[] args) throws IOException {
        // A_i 이하 만큼 한번에 점프할 수 있다.

        // 최소 몇 번 점프를 해야하는지 구하기

        // 못구하면 -1

        // 너비우선 탐색

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력받기
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        // A 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // A_0 위치에서 뛰어보기
        int result = solution();

        System.out.println(result);

        br.close();
    }

    // [i] 에서 오른쪽으로 최소 점프해서 갈 수 있는 값
    private static int solution() {
        cache = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -2);
        }

        int result = dp(0, 0);

        if (result >= 987654321) {
            result = -1;
        }

        return result;
    }

    private static int dp(int index, int count) {
        // 끝에 도달한 경우
        if (index == N - 1) {
            return count;
        }

        if (cache[index][count] != -2) {
            return cache[index][count];
        }

        cache[index][count] = 987654321;

        // 갈 수 있는 경우 추가
        // 뛸 수 있는 칸 수 [1,A_i]
        // 배열의 범위를 벗어나는 경우도 체크
        for (int i = 1; i <= A[index] && index + i < N; i++) {
            cache[index][count] = Math.min(cache[index][count], dp(index + i, count + 1));
        }

        return cache[index][count];
    }
}
