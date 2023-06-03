import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_7562 {
    // 나이트 이동 경우의 수
    final static int dydx[][] = { { -2, -1 }, { -1, -2 }, { 2, 1 }, { 1, 2 }, { -2, 1 }, { 2, -1 }, { -1, 2 },
            { 1, -2 } };

    // 테스트 케이스
    static int testCase;

    // 체스판 한 변의 길이
    static int l;

    static Pair startPoint, endPoint;

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + y;
            result = prime * result + x;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair other = (Pair) obj;
            if (y != other.y)
                return false;
            if (x != other.x)
                return false;
            return true;
        }

    }

    static class BoardPair {
        Pair pair;
        int count;

        public BoardPair(Pair pair, int count) {
            this.pair = pair;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            // 한 변의 길이
            l = Integer.parseInt(br.readLine());

            // 최초 위치
            StringTokenizer st = new StringTokenizer(br.readLine());
            startPoint = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            // 목표 위치
            st = new StringTokenizer(br.readLine());
            endPoint = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            int result = solution();

            System.out.println(result);
        }

        br.close();

    }

    private static int solution() {
        int result = 0;

        // 체스판
        int board[][] = new int[l][l];

        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], -1);
        }

        Queue<BoardPair> queue = new ArrayDeque<>();

        queue.add(new BoardPair(startPoint, 0));

        while (!queue.isEmpty()) {
            BoardPair here = queue.poll();

            if (here.pair.equals(endPoint)) {
                result = here.count;
                break;
            }

            // 갈 수 있는 방향
            for (int i = 0; i < dydx.length; i++) {
                try {
                    int toY = here.pair.y + dydx[i][0];
                    int toX = here.pair.x + dydx[i][1];

                    if (board[toY][toX] == -1) {
                        board[toY][toX] = here.count + 1;
                        queue.add(new BoardPair(new Pair(toY, toX), here.count + 1));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        return result;
    }
}
