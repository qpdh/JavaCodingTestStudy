import java.util.Scanner;

public class java_2812 {
    static int N, K;
    static String number;

    static void solution() {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < number.length(); i++) {
            while (sb.length() != 0 && K > 0) {
                if (sb.charAt(sb.length() - 1) < number.charAt(i)) {
                    sb.deleteCharAt(sb.length() - 1);
                    K--;
                } else {
                    break;
                }
            }

            sb.append(number.charAt(i));
        }

        // 덜 지웠으면, 뒤에서부터 지우기
        while (K != 0) {
            sb.deleteCharAt(sb.length() - 1);
            K--;
        }

        // 0이 되는 경우
        if (sb.length() == 0) {
            sb.append('0');
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        K = scanner.nextInt();

        number = scanner.next();

        solution();

        scanner.close();

    }
}
