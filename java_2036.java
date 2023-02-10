import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class java_2036 {
    static int n;

    static List<Integer> negativeNumberList;
    static List<Integer> positiveNumberList;

    static long solution() {
        long result = 0;

        // 정렬
        Collections.sort(negativeNumberList);

        // -는 -끼리 모아야 이득임
        // -만 우선적으로 처리하자
        for (int i = 0; i < negativeNumberList.size(); i++) {
            int now = negativeNumberList.get(i);

            // 마지막 값이라면 -> 그냥 더한다.
            if (i == negativeNumberList.size() - 1) {
                result += now;
                continue;
            }

            // 다음 값 미리 추출
            int next = negativeNumberList.get(i + 1);
            // 곱할 것인가 더할 것인가?

            // 1. 현재가 음수이고 다음이 양수가 아니라면 -> 곱한다.
            result += ((long)now) * next;
            i++;

            continue;

        }

        Collections.sort(positiveNumberList, (a, b) -> b - a);

        for (int i = 0; i < positiveNumberList.size(); i++) {
            int now = positiveNumberList.get(i);

            // 마지막 값이라면...
            if (i == positiveNumberList.size() - 1) {
                result += now;
                continue;
            }

            // 다음 값 미리 추출
            int next = positiveNumberList.get(i + 1);

            if (next == 1) {
                result += now;
                continue;
            }

            // 곱할 것인가 더할 것인가?
            result += ((long)now) * next;
            i++;
            continue;

        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        negativeNumberList = new ArrayList<>();
        positiveNumberList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine());
            if (0 < number) {
                positiveNumberList.add(number);
                continue;
            }

            negativeNumberList.add(number);
        }

        long result = solution();

        System.out.println(result);

        br.close();
    }
}
