import java.util.Scanner;

public class 고장_난_송신기 {
    static int M1, M2, N;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 명렁어 문자 수
        M1 = scanner.nextInt();
        // 명렁어 문자 수
        M2 = scanner.nextInt();
        // 메시지 문자 수
        N = scanner.nextInt();

        String M1Message = scanner.next();
        String M2Message = scanner.next();
        String NMessage = scanner.next();

        // NMessage 중복 문자 제거
        StringBuffer sb = new StringBuffer();
        sb.append(NMessage.charAt(0));
        for (int i = 1; i < N; i++) {
            if (sb.charAt(sb.length() - 1) != NMessage.charAt(i)) {
                sb.append(NMessage.charAt(i));
            }
        }

        if (sb.indexOf(M1Message) != -1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        if (sb.indexOf(M2Message) != -1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        scanner.close();
    }
}
