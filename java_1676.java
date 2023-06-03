import java.util.Scanner;

public class java_1676 {

    // number!에서 5의 개수를 카운트한다.
    static int solution(int number) {
        // 0은 1을 반환한다.
        if (number == 0) {
            return 0;
        }

        int tmp = number;
        int count5 = 0;
        while (tmp > 0) {
            count5 += tmp / 5;
            tmp /= 5;
        }

        // 5의 개수 반환한다.
        int result = count5;
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int result = solution(N);

        System.out.println(result);

        scanner.close();

    }
}
