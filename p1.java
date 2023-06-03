
import java.util.HashMap;
import java.util.Scanner;

public class p1 {
    static HashMap<String, Integer> dy = new HashMap<>() {
        {
            put("E", 0);
            put("W", 0);
            put("S", 1);
            put("N", -1);
        }
    };
    static HashMap<String, Integer> dx = new HashMap<>() {
        {
            put("E", 1);
            put("W", -1);
            put("S", 0);
            put("N", 0);
        }
    };
    final static int dydx[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    static class Robot {
        int y, x;
        int score;

        public Robot(int y, int x, int score) {
            this.y = y;
            this.x = x;
            this.score = score;
        }
    }

    static void move(int[][] board, Robot robot, String moveDirection, int moveLength) {
        int toY = robot.y;
        int toX = robot.x;
        int score = robot.score;

        for (int i = 0; i < moveLength; i++) {
            toY += dy.get(moveDirection);
            toX += dx.get(moveDirection);

            System.out.println("toY : " + toY + " toX : " + toX);

            if (toY < 0 || toY >= board.length || toX < 0 || toX >= board[0].length) {
                toY -= dy.get(moveDirection);
                toX -= dx.get(moveDirection);
                System.out.println("더 이상 못감");
                break;
            }
            // 장애물 만나면 종료
            if (board[toY][toX] == -1) {
                toY -= dy.get(moveDirection);
                toX -= dx.get(moveDirection);
                System.out.println("더 이상 못감");
                break;
            }

            System.out.println("현재 칸 번호 : " + board[toY][toX]);
            score += board[toY][toX];
            board[toY][toX] = 0;
        }

        robot.score = score;
        robot.y = toY;
        robot.x = toX;

        System.out.println("로봇의 위치 y : " + toY + " x : " + toX);
        System.out.println(score);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 테스트 케이스
        int T = scanner.nextInt();

        for (int testCase = 0; testCase < T; testCase++) {
            // 도로의 col
            int N = scanner.nextInt();
            // 도로의 row
            int M = scanner.nextInt();
            // 현재 로봇의 위치 X, Y
            int X = scanner.nextInt() - 1;
            int Y = scanner.nextInt() - 1;

            // 도로 생성
            int board[][] = new int[M][N];

            // 도로 데이터 입력
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }
            Robot robot = new Robot(Y, X, board[Y][X]);

            // 회전판에 존재하는 칸의 갯수
            int K = scanner.nextInt();
            // 회전판의 값들
            // 가장 앞의 수가 초깃값
            int carouselIndex = K - 1;

            int carouselBoard[] = new int[K];
            for (int i = K - 1; i >= 0; i--) {
                carouselBoard[i] = scanner.nextInt();
            }

            // 로봇을 움직인 횟수
            int L = scanner.nextInt();
            for (int moveCount = 0; moveCount < L; moveCount++) {
                String moveDirection = scanner.next();
                // 시계방향 1, 반시계방향 2
                int clockDirection = scanner.nextInt();
                int clockSpinLength = scanner.nextInt();

                // 돌리기
                if (clockDirection == 1) {
                    for (int i = 0; i < clockSpinLength; i++) {
                        carouselIndex = (carouselIndex + 1) % carouselBoard.length;
                    }

                } else if (clockDirection == 2) {
                    for (int i = 0; i < clockSpinLength; i++) {
                        carouselIndex = (carouselIndex - 1 + carouselBoard.length) % carouselBoard.length;
                    }
                }

                System.out.println("carindex" + carouselIndex);

                // carouselBoard[carouseIndex] 만큼 이동
                int moveLength = carouselBoard[carouselIndex];

                move(board, robot, moveDirection, moveLength);
            }

            System.out.println(String.format("#%d %d %d %d", testCase + 1, robot.score, robot.x + 1, robot.y + 1));

        }

        scanner.close();

    }
}
