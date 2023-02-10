import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_3190 {
    final static int dydx[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    static int N, K, L;

    static int board[][];
    static Queue<Move> moveQueue;
    static Deque<Pair> snake;

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    //
    static class Move {
        int time;
        char direction;

        public Move(int time, char direction) {
            this.time = time;
            this.direction = direction;
        }
    }

    static void printBoard() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 이동 알고리즘
    // true 면 잘 이동한 것
    // false면 충돌한 것
    static boolean move(int moveIndex) {
        // 몸 길이를 늘려 머리를 다음칸에 위치시킨다.
        int toY = snake.getFirst().y + dydx[moveIndex][0];
        int toX = snake.getFirst().x + dydx[moveIndex][1];

        // 앞에 사과가 있다면 사과가 없어지고 꼬리는 없어지지 않음
        // 맵 밖으로 나가게 된다면 or 자기 자신과 만난다면 종료
        try {
            // 꼬리랑 만난다면 -> 터짐
            if (board[toY][toX] == 1) {
                // System.out.println("뱀 이랑 만남");
                return false;
            }
            // 사과가 없다면 꼬리 1칸 줄어듦
            if (board[toY][toX] != 2) {
                Pair tail = snake.getLast();
                board[tail.y][tail.x] = 0;
                snake.removeLast();
            }

            // 사과를 만난다면
            // 머리 추가
            snake.addFirst(new Pair(toY, toX));

            board[toY][toX] = 1;

            // printBoard();
        }
        // 맵 밖으로 나간다면 -> 터짐
        catch (ArrayIndexOutOfBoundsException e) {
            // System.out.println("맵 밖으로 나감");
            return false;
        }

        return true;
    }

    // 게임 처리
    static int solution() {
        int time = 0;

        int moveIndex = 1;

        while (true) {
            // 현재 시간과 큐의 가장 앞의 값이 일치하면
            // 읽기
            // L이면 인덱스 1 감소
            // D면 인덱스 1 증가
            if (!moveQueue.isEmpty()) {
                Move moveCheck = moveQueue.peek();
                // 방향을 바꿀 시간이 됐다면
                if (moveCheck.time == time) {
                    if (moveCheck.direction == 'L') {
                        moveIndex = ((moveIndex - 1) + dydx.length) % dydx.length;
                    } else if (moveCheck.direction == 'D') {
                        moveIndex = ((moveIndex + 1)) % dydx.length;
                    }
                    // 맨 앞 이동 없애기
                    moveQueue.poll();
                }
            }

            time++;

            // 밖에 나가게 되는지 체크
            if (!move(moveIndex)) {
                // System.out.println("체크");
                break;
            }

        }

        return time;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드의 크기
        N = Integer.parseInt(br.readLine());

        // 보드 초기화
        board = new int[N][N];
        snake = new ArrayDeque<>();

        // 사과의 갯수
        K = Integer.parseInt(br.readLine());

        // 사과 추가
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 가장 좌측 상단이 (1,1)
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            // 사과 추가
            board[y][x] = 2;
        }
        // 최초 뱀의 위치
        board[0][0] = 1;
        snake.add(new Pair(0, 0));

        // 뱀의 방향 변환 횟수
        L = Integer.parseInt(br.readLine());

        moveQueue = new ArrayDeque<>();

        // 방향 데이터 삽입
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            moveQueue.add(new Move(time, direction));
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }
}
