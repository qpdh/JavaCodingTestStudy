import java.util.Arrays;
import java.util.Scanner;

public class java_23351 {
    static int board[];

    static int N, K, A, B;

    // 말리기
    // true : 0인 화분 생성
    static boolean dry() {
        for (int i = 0; i < board.length; i++) {
            board[i] -= 1;
            if (board[i] == 0) {
                return true;
            }
        }
        return false;
    }

    // 최소누적합 인덱스 찾기
    static int findIndex() {
        int index = 0;

        // [0:A) 까지 합
        int minSum = 0;
        int tmpSum = 0;
        for (int i = 0; i < A; i++) {
            minSum += board[i];
        }

        //
        tmpSum = minSum;

        // 시작 인덱스는 [N-A-1:N-1) 까지
        for (int i = 0; i < N - A; i++) {
            tmpSum = tmpSum - board[i] + board[i + A];
            // System.out.println(tmpSum);
            if (tmpSum < minSum) {
                minSum = tmpSum;
                index = i + 1;
            }
        }
        return index;
    }

    // 물 주기
    static void water(int startIndex) {
        for (int i = startIndex; i < startIndex + A; i++) {
            board[i] += B;
        }
    }

    static int solution() {
        int date = 1;
        while (true) {
            // 연속된 A개의 합 중 가장 작은 시작 인덱스 출력
            int index = findIndex();
            // 물주기
            water(index);
            // 말리기
            if (dry()) {
                break;
            }

            date++;
        }

        return date;
    }

    public static void main(String[] args) {
        // 일직선 N개의 화문에 캣닢이 하나씩 심어져 있다.
        // K수문

        // 연속된 A개의 화분에 물을 준다. B만큼 수분 증가
        // 모든 수분 1씩 감소
        // 0이된 화분 죽음

        // 첫 캣닢이 죽는 날짜?

        Scanner scanner = new Scanner(System.in);

        // 화분의 크기
        N = scanner.nextInt();

        // 화분 초기화
        board = new int[N];

        // 초기 수분
        K = scanner.nextInt();
        Arrays.fill(board, K);

        // 연속된 A개의 화분에 물주기
        A = scanner.nextInt();
        // B만큼 증가
        B = scanner.nextInt();

        int result = solution();

        System.out.println(result);

        scanner.close();

    }
}
