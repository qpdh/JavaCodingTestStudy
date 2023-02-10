import java.util.Scanner;

public class java_1612 {
    static int N;

    // 입력받은 값의 자릿수를 계산하는 메소드
    static int countNumberLength(int number) {
        String stringNumber = Integer.toString(number);

        return stringNumber.length();
    }

    static int solution() {
        // 2나 5의 배수면 절대 만들 수 없음
        if (N % 2 == 0 || N % 5 == 0) {
            return -1;
        }

        int numberLength = countNumberLength(N);

        // 해당 값으로 나누어 떨어지는지 확인하기
        long checkNumber = 0;
        for (int i = 0; i < numberLength; i++) {
            checkNumber = checkNumber * 10 + 1;
        }

        int result = numberLength;
        while (true) {
            if (checkNumber % N == 0) {
                break;
            }

            checkNumber = (checkNumber % N) * 10 + 1;
            result++;
        }

        return result;
    }

    public static void main(String[] args) {
        // N의 배수중에서 1만으로 이루어진 수가 있는가?

        // N의 값보다 큰 1로만 이루어진 수가 N으로 나누어 떨어지는가?

        // 11 / 3 -> X
        // 111 / 3 -> O

        // 1 2 3 4 5 6 7 8 9
        // 2 4 6 8 10
        // 3 6 9 12
        //

        // 어떤 수가 N으로 나누어 떨어지는지 확인하는 방법?
        // N이 짝수인 경우 만들어지는가? -> X
        // N이 홀수인 경우부터 해보자.

        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        int result = solution();

        System.out.println(result);

        scanner.close();
    }
}
