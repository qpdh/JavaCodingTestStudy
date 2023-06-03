import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class java_1744 {
    static int N;

    static PriorityQueue<Integer> positiveQueue, negativeQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 수를 정렬한 후
        // 높은 수는 무조건 곱하자. (양수끼리는 큰 것 우선으로 곱하자)
        // 음수끼리는 무조건 곱하자. (음수끼리는 작은 것 우선으로 곱하자)
        N = Integer.parseInt(br.readLine());

        positiveQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        negativeQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);

        // 음수의 갯수가 홀수일 때 쓰일 예정
        boolean isZero = false;
        int result = 0;

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());

            // 0 추가
            if (number == 0) {
                isZero = true;
                continue;
            }

            // 1은 더하는게 이득이므로 가산
            if (number == 1) {
                result++;
                continue;
            }

            // 양수 추가
            if (number > 0) {
                positiveQueue.add(number);
                continue;
            }

            // 음수 추가
            if (number < 0) {
                negativeQueue.add(number);
                continue;
            }
        }

        result += solution(isZero);

        System.out.println(result);

        br.close();
    }

    private static int solution(boolean isZero) {
        int result = 0;

        // 양수 계산하기
        while (!positiveQueue.isEmpty()) {
            // 1개만 남은 경우
            if (positiveQueue.size() == 1) {
                result += positiveQueue.poll();
                continue;
            }

            // 두 수 확인하기
            int a = positiveQueue.poll();
            int b = positiveQueue.poll();
            result += a * b;
        }

        // 음수 계산하기
        while (!negativeQueue.isEmpty()) {
            // 1개만 남은 경우
            if (negativeQueue.size() == 1) {
                // zero가 있는 경우 -> 0이랑 곱함
                if (isZero) {
                    negativeQueue.poll();
                    continue;
                }
                // zero 가 없는 경우 -> 그냥 더하기
                result += negativeQueue.poll();
                continue;
            }

            // 두 수 확인하기
            int a = negativeQueue.poll();
            int b = negativeQueue.poll();
            result += a * b;
        }

        return result;
    }
}
