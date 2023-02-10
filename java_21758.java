import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_21758 {
    static int N;
    static int hive[];
    static int sumOfHive[];

    static long findMaxValue(int hiveIndex) {
        long result = 0;

        // 한 쪽 끝에 있는 경우
        if (hiveIndex == 0) {
            // 하나는 n-1에 고정
            for (int i = 1; i < N - 1; i++) {
                // n-1 합 저장
                long tmp = sumOfHive[N - 2] - hive[i];

                tmp += sumOfHive[i - 1];

                result = Math.max(result, tmp);
            }

            return result;
        }

        // 한 쪽 끝에 있는 경우
        if (hiveIndex == N - 1) {
            // 하나는 0에 고정
            for (int i = 1; i < N - 1; i++) {
                // [1]~[N-1] 합 저장
                long tmp = sumOfHive[N - 1] - hive[0] - hive[i];

                tmp += sumOfHive[N - 1] - sumOfHive[i];

                result = Math.max(result, tmp);
            }

            return result;
        }

        // 중간에 있는 경우
        // 양쪽 끝에 벌을 둔다.
        // [1]~[hiveIndex]
        // [hiveIndex]~[n-2]
        result += sumOfHive[hiveIndex] - sumOfHive[0];
        result += sumOfHive[N - 2] - sumOfHive[hiveIndex - 1];

        return result;
    }

    static long solution() {
        long result = 0;

        // 벌통의 위치
        for (int i = 0; i < N; i++) {
            result = Math.max(findMaxValue(i), result);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 벌통이 한쪽 끝에 있는 경우
        // -> 하나는 무조건 다른 한 쪽 끝 (다른 한 쪽 끝이 MAX값이여도 어처피 못먹음)
        // -> N번 반복해서 최댓값 찾기

        // 벌통이 중간에 있는 경우
        // -> 양쪽 끝에 2개를 두는 것이 이득인가?
        // --> [1]부터 [벌통] 까지 합 + [벌통]부터 [n-2] 까지 합

        N = Integer.parseInt(br.readLine());
        hive = new int[N];
        sumOfHive = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        hive[0] = Integer.parseInt(st.nextToken());
        sumOfHive[0] = hive[0];

        for (int i = 1; i < N; i++) {
            hive[i] = Integer.parseInt(st.nextToken());
            sumOfHive[i] = sumOfHive[i - 1] + hive[i];
        }

        long result = solution();

        System.out.println(result);

        br.close();
    }
}
