import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// 9 498 * 9 499
// int X
// long X
// BigInteger

public class java_1287 {

    // 숫자 추출
    static Queue<String> extractNumber(String mathExpression) {
        Queue<String> mathQueue = new LinkedList<>();

        StringBuffer number = new StringBuffer();
        for (int i = 0; i < mathExpression.length(); i++) {
            // 현재 값이 숫자라면
            if (Character.isDigit(mathExpression.charAt(i))) {
                number.append(mathExpression.charAt(i));
            }
            // 숫자가 아닌 경우, 앞에 숫자를 처리 했는가에 따라 달라짐
            else {
                if (number.length() != 0) {
                    mathQueue.add(number.toString());
                    number = new StringBuffer();
                }
                mathQueue.add(mathExpression.substring(i, i + 1));
            }
        }

        if (number.length() != 0) {
            mathQueue.add(number.toString());
            number = new StringBuffer();
        }

        return mathQueue;
    }

    static BigInteger calc(BigInteger a, BigInteger b, String calc) {
        BigInteger result = null;

        switch (calc) {
            case "+":
                result = a.add(b);
                break;
            case "-":
                result = a.subtract(b);
                break;
            case "*":
                result = a.multiply(b);
                break;
            case "/":
                result = a.divide(b);
                break;
            default:
                return null;
        }

        return result;
    }

    // 괄호 부분 계산
    static BigInteger subCalculator(Queue<String> mathQueue) {
        BigInteger result = null;

        Deque<String> operationStack = new LinkedList<>();
        Deque<BigInteger> operandStack = new LinkedList<>();

        boolean isBracket = false;

        while (!mathQueue.isEmpty()) {
            String tmp = mathQueue.poll();
            // ) 를 만나면 종료
            if (tmp.equals(")")) {
                isBracket = true;
                break;
            }

            // 숫자면 숫자 스택에 삽입
            if (Character.isDigit(tmp.charAt(0))) {
                operandStack.addLast(new BigInteger(tmp));

                // // 만약 operationStack 가장 위가 * / 라면...
                if (!operationStack.isEmpty()) {
                    if (operationStack.peekLast().equals("*") || operationStack.peekLast().equals("/")) {
                        // 계산 해야하는데 operand가 없다면 -> 오류처리
                        if (operandStack.size() < 2) {
                            return null;
                        }

                        // b 뽑고 a 뽑기 LIFO
                        BigInteger operandB = operandStack.removeLast();
                        BigInteger operandA = operandStack.removeLast();

                        String operator = operationStack.removeLast();

                        BigInteger calcResult = calc(operandA, operandB, operator);
                        if (calcResult == null) {
                            return null;
                        }
                        operandStack.addLast(calcResult);
                    }
                }
            }

            // 사칙연산 분리하기
            else {
                switch (tmp) {
                    case "+":
                        operationStack.addLast(tmp);
                        break;
                    case "-":
                        operationStack.addLast(tmp);
                        break;
                    case "*":
                        operationStack.addLast(tmp);
                        break;
                    case "/":
                        operationStack.addLast(tmp);
                        break;
                    case "(":
                        BigInteger calcResult = subCalculator(mathQueue);
                        if (calcResult == null) {
                            return null;
                        }
                        // mathQueue.addFirst(calcResult.toString());
                        operandStack.addLast(calcResult);
                        // // 만약 operationStack 가장 위가 * / 라면...
                        if (!operationStack.isEmpty()) {
                            if (operationStack.peekLast().equals("*") || operationStack.peekLast().equals("/")) {
                                // 계산 해야하는데 operand가 없다면 -> 오류처리
                                if (operandStack.size() < 2) {
                                    return null;
                                }

                                // b 뽑고 a 뽑기 LIFO
                                BigInteger operandB = operandStack.removeLast();
                                BigInteger operandA = operandStack.removeLast();

                                String operator = operationStack.removeLast();

                                calcResult = calc(operandA, operandB, operator);
                                if (calcResult == null) {
                                    return null;
                                }
                                operandStack.addLast(calcResult);
                            }
                        }
                        break;
                    default:
                        return null;
                }
            }

            // operand 보다 operator가 더 많으면 오류.
            if (operandStack.size() < operationStack.size()) {
                return null;
            }

        }

        // 닫는 괄호 없이 빠져나왔다면 오류
        if (!isBracket) {
            return null;
        }

        // 스택을 빠져 나왔을 때,
        // operandSize == operationSize + 1 여야 함
        if (operandStack.size() != operationStack.size() + 1) {
            return null;
        }

        // 남은 스택 뽑으면서 연산하기
        while (!operationStack.isEmpty()) {
            // b 뽑고 a 뽑기 LIFO
            BigInteger operandA = operandStack.removeFirst();
            BigInteger operandB = operandStack.removeFirst();

            String operator = operationStack.removeFirst();

            BigInteger calcResult = calc(operandA, operandB, operator);
            if (calcResult == null) {
                return null;
            }
            operandStack.addFirst(calcResult);
        }

        if (operandStack.size() == 1) {
            result = operandStack.pop();
        } else {
            return null;
        }

        return result;
    }

    // 일단 괄호 없이 연산해보자
    public static BigInteger calculator(Queue<String> mathQueue) {
        BigInteger result = null;

        Deque<String> operationStack = new LinkedList<>(); // + - * /
        Deque<BigInteger> operandStack = new LinkedList<>(); // 숫자

        while (!mathQueue.isEmpty()) {
            String tmp = mathQueue.poll();

            // 숫자면 숫자 스택에 삽입
            if (Character.isDigit(tmp.charAt(0))) {
                operandStack.addLast(new BigInteger(tmp));

                // // 만약 operationStack 가장 위가 * / 라면...
                // 1, 2
                // *
                // 1 * 2
                if (!operationStack.isEmpty()) {
                    if (operationStack.peekLast().equals("*") || operationStack.peekLast().equals("/")) {
                        // 계산 해야하는데 operand가 없다면 -> 오류처리
                        if (operandStack.size() < 2) {
                            return null;
                        }

                        // b 뽑고 a 뽑기 LIFO
                        BigInteger operandB = operandStack.removeLast();
                        BigInteger operandA = operandStack.removeLast();

                        String operator = operationStack.removeLast();

                        BigInteger calcResult = calc(operandA, operandB, operator);
                        if (calcResult == null) {
                            return null;
                        }
                        operandStack.addLast(calcResult);
                    }
                }
            }

            // 사칙연산 분리하기
            else {
                switch (tmp) {
                    case "+":
                        operationStack.addLast(tmp);
                        break;
                    case "-":
                        operationStack.addLast(tmp);
                        break;
                    case "*":
                        operationStack.addLast(tmp);
                        break;
                    case "/":
                        operationStack.addLast(tmp);
                        break;
                    case "(":
                        // ((4-3))
                        BigInteger calcResult = subCalculator(mathQueue);
                        if (calcResult == null) {
                            return null;
                        }
                        // mathQueue.addFirst(calcResult.toString());
                        operandStack.addLast(calcResult);
                        // // 만약 operationStack 가장 위가 * / 라면...
                        if (!operationStack.isEmpty()) {
                            if (operationStack.peekLast().equals("*") || operationStack.peekLast().equals("/")) {
                                // 계산 해야하는데 operand가 없다면 -> 오류처리
                                if (operandStack.size() < 2) {
                                    return null;
                                }

                                // b 뽑고 a 뽑기 LIFO
                                BigInteger operandB = operandStack.removeLast();
                                BigInteger operandA = operandStack.removeLast();

                                String operator = operationStack.removeLast();

                                calcResult = calc(operandA, operandB, operator);
                                if (calcResult == null) {
                                    return null;
                                }
                                operandStack.addLast(calcResult);
                            }
                        }
                        break;
                    default:
                        return null;
                }
            }

            // operand 항상 2개 이상
            // operand 의 수 > operator

            // operand 보다 operator가 더 많으면 오류.
            if (operandStack.size() < operationStack.size()) {
                return null;
            }

        }

        // 스택을 빠져 나왔을 때,
        // operandSize == operationSize + 1 여야 함
        if (operandStack.size() != operationStack.size() + 1) {
            return null;
        }

        // 남은 스택 뽑으면서 연산하기
        while (!operationStack.isEmpty()) {
            // b 뽑고 a 뽑기 LIFO
            BigInteger operandA = operandStack.removeFirst();
            BigInteger operandB = operandStack.removeFirst();

            String operator = operationStack.removeFirst();

            BigInteger calcResult = calc(operandA, operandB, operator);
            if (calcResult == null) {
                return null;
            }
            operandStack.addFirst(calcResult);
        }

        if (operandStack.size() == 1) {
            result = operandStack.pop();
        } else {
            return null;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 스택에 저장하여 연산하자

        // 수식 입력받기
        String mathExpression = br.readLine();

        Queue<String> mathQueue = extractNumber(mathExpression);

        BigInteger result = calculator(mathQueue);

        if (result == null) {
            System.out.println("ROCK");
        } else {
            System.out.println(result);
        }

        // 곱하기 더하기 우선순위를 어떻게 계산하는가?

        // 1 + (2 + 3 * 4) * 5 의 경우
        // 1 + ( 2 + 3 * 4 )

        // 괄호의 경우 재귀 호출하기
        // -> 언제까지? -> ) 만날 때 까지
        // 계산 결과 반환

        // 2 + 3 * 4 의 경우
        // 2 push
        // + push
        // 3 push
        // * push
        // 4 -> 1. 숫자라면 pop op, pop num 해서 연산하기
        // -> 2. 괄호라면 재귀 호출

        br.close();
        bw.close();
    }
}
