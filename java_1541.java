import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class java_1541 {
    static String mathExpression;

    static Deque<Integer> operand;
    static Deque<Character> operator;

    static int subCalc() {
        int result = operand.poll();

        // -를 만나거나 끝까지 계산하기
        while (!operand.isEmpty()) {
            // + 인 경우
            if (operator.peek() == '+') {
                // 바로 더하기
                operator.poll();
                result += operand.poll();
            }

            // - 인 경우
            else if (operator.peek() == '-') {
                break;
            }
        }

        //
        return result;
    }

    static int calc() {
        // 처음은 무조건 첫 값을 가진다.
        int result = operand.poll();

        // 앞부터 연산을 해 나간다.

        // 만약 -를 만난다면 다음 -를 만나기 전 까지 +연산을 계속 진행하고, 추후에 더한다.
        while (!operand.isEmpty()) {
            // + 인 경우
            if (operator.peek() == '+') {
                // 바로 더하기
                operator.poll();
                result += operand.poll();
            }

            // - 인 경우
            else if (operator.peek() == '-') {
                // 서브루틴 호출
                operator.poll();
                result -= subCalc();
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 최소로 만들기

        // +만 있으면 상관 없음

        // -가 있는 경우

        // 10+10-20+20
        // -뒤에 +연산이 있으면 (-가 나오기 전 까지, 식의 끝까지)그 사이를 괄호로 묶는다.

        // 1. 입력값, 수식을 분리한다.
        mathExpression = br.readLine();

        operand = new ArrayDeque<>();
        operator = new ArrayDeque<>();

        int nowIndex = 0;
        int startIndex = 0;
        while (true) {
            // 문자열의 끝인 경우
            if (nowIndex == mathExpression.length()) {
                // 마지막 숫자 삽입
                operand.addLast(Integer.parseInt(mathExpression.substring(startIndex, nowIndex)));
                break;
            }

            // 수식을 만난 경우
            if (mathExpression.charAt(nowIndex) == '+' || mathExpression.charAt(nowIndex) == '-') {
                // 이전까지 누적된 숫자 삽입
                operand.addLast(Integer.parseInt(mathExpression.substring(startIndex, nowIndex)));
                startIndex = nowIndex + 1;

                // 수식 삽입
                operator.addLast(mathExpression.charAt(nowIndex));
            }

            nowIndex++;
        }

        // while (!operand.isEmpty()) {
        // System.out.println(operand.poll());
        // }

        // while (!operator.isEmpty()) {
        // System.out.println(operator.poll());
        // }

        // 10+10-20+20
        // -뒤에 +연산이 있으면 (-가 나오기 전 까지, 식의 끝까지)그 사이를 괄호로 묶는다.

        int result = calc();

        System.out.println(result);

    }
}
