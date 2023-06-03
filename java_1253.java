import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_1253 {
    // 수의 개수
    static int N;
    // 수 저장할 리스트
    static int numbers[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수 삽입
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 결과 출력
        int result = solution();

        System.out.println(result);

        br.close();
    }

    // 서로 다른 두 수의 합으로 나타낼 수 있는 수의 개수 반환하는 메서드
    private static int solution() {
        int result = 0;

        // 오름차순 정렬
        Arrays.sort(numbers);

        // index i번째 수가 좋은 수인가?
        for (int i = 0; i < numbers.length; ++i) {
            if (check(i)) {
                ++result;
            }
        }

        return result;
    }

    // index번째 수가 좋은 수 인가?
    private static boolean check(int index) {
        // 판단하고자 하는 수
        int targetNumber = numbers[index];

        // 두 포인터 이용
        int firstIndex = 0;
        int lastIndex = N - 1;

        while (firstIndex < lastIndex) {
            // 90 80 20 30 10
            // 0 1 2 3
            if (firstIndex == index) {
                firstIndex++;
                continue;
            }
            if (lastIndex == index) {
                lastIndex--;
                continue;
            }

            int sumOfNumbers = numbers[firstIndex] + numbers[lastIndex];

            // 만약 두 수로 나타낼 수 있다면..
            if (sumOfNumbers == targetNumber) {
                return true;
            }

            // sumOfNumbers 가 더 작으면
            if (sumOfNumbers < targetNumber) {
                ++firstIndex;
                continue;
            }

            // sumOfNumbers 가 더 크면 ->
            if (targetNumber < sumOfNumbers) {
                --lastIndex;
                continue;
            }
        }

        return false;
    }
}
