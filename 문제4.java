import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 문제4 {

    static class Solution {
        final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        static char[][] board;

        final static boolean LEFT = false;
        final static boolean RIGHT = true;

        static boolean moveHorizon2(int y, int x, boolean delta) {
            if (delta == LEFT) {
                try {
                    // 해당 위치에 홀더가 있어야 함
                    if (board[y][x - 2] != 'H') {

                        return false;
                    }
                    // x -> x-2 까지 위에 아무것도 없어야 함
                    for (int i = x; i >= x - 2; i--) {
                        if (board[y - 1][i] != '.') {
                            return false;
                        }
                    }
                    if (board[y][x - 1] != '.') {
                        return false;
                    }
                    // [y][x-1]에 빈칸이여야 함
                    return true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    return false;
                }
            } else {
                try {
                    // 해당 위치에 홀더가 있어야 함
                    if (board[y][x + 2] != 'H') {

                        return false;
                    }
                    // x -> x+2 까지 위에 아무것도 없어야 함
                    for (int i = x; i <= x + 2; i++) {
                        if (board[y - 1][i] != '.') {
                            return false;
                        }
                    }
                    // [y][x+1]에 빈칸이여야 함
                    if (board[y][x + 1] != '.') {
                        return false;
                    }
                    return true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    return false;
                }
            }
        }

        static boolean moveHorizon3(int y, int x, boolean delta) {
            try {
                if (delta == LEFT) {

                    // 해당 위치에 홀더가 있어야 함
                    if (board[y][x - 3] != 'H') {

                        return false;
                    }
                    // x -> x-3 까지 위에 아무것도 없어야 함
                    for (int i = x; i >= x - 3; i--) {
                        if (board[y - 1][i] != '.') {
                            return false;
                        }
                    }
                    // [y][x-1]에 빈칸이여야 함
                    if (board[y - 1][x - 1] != '.' || board[y][x - 2] != '.') {
                        return false;
                    }

                    return true;
                } else {

                    // 해당 위치에 홀더가 있어야 함
                    if (board[y][x + 3] != 'H') {

                        return false;
                    }
                    // x -> x+2 까지 위에 아무것도 없어야 함
                    for (int i = x; i <= x + 3; i++) {
                        if (board[y - 1][i] != '.') {
                            return false;
                        }
                    }
                    // [y][x+1]에 빈칸이여야 함
                    if (board[y][x + 1] != '.' || board[y][x + 2] != '.') {
                        return false;
                    }
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }

        static boolean moveUp2(int y, int x) {
            try {
                // 바로 위 칸이 빈칸이여야 함
                if (board[y - 1][x] != '.') {
                    return false;
                }
                if (board[y - 2][x] != 'H') {
                    return false;
                }
                return true;

            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }

        static boolean moveCross(int y, int x, boolean delta) {
            try {
                // / 방향으로 가는 경우
                if (delta == RIGHT) {

                    if (board[y - 1][x + 1] != 'H') {
                        return false;
                    }
                    if (board[y - 1][x] != '.' || board[y][x + 1] != '.') {
                        return false;
                    }
                    return true;
                }
                // \ 방향으로 가는 경우
                else {
                    if (board[y - 1][x - 1] != 'H') {
                        return false;
                    }
                    if (board[y - 1][x] != '.' || board[y][x - 1] != '.') {
                        return false;
                    }
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }

        }

        static class Pair {
            int y, x, count;

            public Pair(int y, int x, int count) {
                this.y = y;
                this.x = x;
                this.count = count;
            }

        }

        public int[][] solution(String[] wall) {
            int[][] result = new int[wall.length][wall[0].length()];

            board = new char[wall.length][wall[0].length()];
            for (int i = 0; i < wall.length; i++) {
                Arrays.fill(result[i], -1);
            }
            // 데이터 삽입
            for (int i = 0; i < wall.length; i++) {
                for (int j = 0; j < wall[i].length(); j++) {
                    board[i][j] = wall[i].charAt(j);
                    if (board[i][j] == '.' || board[i][j] == 'X') {
                        result[i][j] = 0;
                    }
                }
            }

            // 좌|우로 2칸 이동
            //
            // 시작 위치는 무조건 [wall.length-1][0]
            Deque<Pair> deque = new ArrayDeque<>();
            deque.add(new Pair(wall.length - 1, 0, 1));

            result[wall.length - 1][0] = 1;

            while (!deque.isEmpty()) {
                Pair now = deque.removeFirst();

                for (int i = 0; i < dydx.length; i++) {
                    int toY = now.y + dydx[i][0];
                    int toX = now.x + dydx[i][1];
                    try {
                        if (board[toY][toX] == 'H') {
                            if (result[toY][toX] == -1 || now.count + 1 < result[toY][toX]) {
                                result[toY][toX] = now.count + 1;
                                deque.add(new Pair(toY, toX, now.count + 1));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }

                }

                if (moveHorizon3(now.y, now.x, true)) {
                    if (result[now.y][now.x + 3] == -1 || now.count + 1 < result[now.y][now.x + 3]) {
                        result[now.y][now.x + 3] = now.count + 1;
                        deque.add(new Pair(now.y, now.x + 3, now.count + 1));
                    }
                }
                if (moveHorizon3(now.y, now.x, false)) {
                    if (result[now.y][now.x - 3] == -1 || now.count + 1 < result[now.y][now.x - 3]) {
                        result[now.y][now.x - 3] = now.count + 1;
                        deque.add(new Pair(now.y, now.x - 3, now.count + 1));
                    }
                }
                if (moveHorizon2(now.y, now.x, true)) {
                    if (result[now.y][now.x + 2] == -1 || now.count + 1 < result[now.y][now.x + 2]) {
                        result[now.y][now.x + 2] = now.count + 1;
                        deque.add(new Pair(now.y, now.x + 2, now.count + 1));
                    }
                }
                if (moveHorizon2(now.y, now.x, false)) {
                    if (result[now.y][now.x - 2] == -1 || now.count + 1 < result[now.y][now.x - 2]) {
                        result[now.y][now.x - 2] = now.count + 1;
                        deque.add(new Pair(now.y, now.x - 2, now.count + 1));
                    }
                }
                if (moveUp2(now.y, now.x)) {
                    if (result[now.y - 2][now.x] == -1 || now.count + 1 < result[now.y - 2][now.x]) {
                        result[now.y - 2][now.x] = now.count + 1;
                        deque.add(new Pair(now.y - 2, now.x, now.count + 1));
                    }
                }
                if (moveCross(now.y, now.x, true)) {
                    if (result[now.y - 1][now.x + 1] == -1 || now.count + 1 < result[now.y - 1][now.x + 1]) {
                        result[now.y - 1][now.x + 1] = now.count + 1;
                        deque.add(new Pair(now.y - 1, now.x + 1, now.count + 1));
                    }
                }
                if (moveCross(now.y, now.x, false)) {
                    if (result[now.y - 1][now.x - 1] == -1 || now.count + 1 < result[now.y - 1][now.x - 1]) {
                        result[now.y - 1][now.x - 1] = now.count + 1;
                        deque.add(new Pair(now.y - 1, now.x - 1, now.count + 1));
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        // 인접한 홀드로 이동 가능
        // 좌 2칸 3칸 떨어진 홀드로 가기 위해서
        // 1. 두 홀드 바로 위쪽 칸들이 .
        // 2. 두 홀드 사이 .
        // 3. 직선 바로 위

        // 바로 위가 빈칸이면 2칸 떨어진 홀드로 이동 가능
        // 바로 위는 불가능

        // 왼쪽 위에 위치한 홀드로 가기 위해서 바로 왼쪽과 바로 위칸이 빈칸
        // 오른쪽 위에 위치한 홀드로 가기 위해서 바로 오른쪽 칸과 바로 위 칸이 빈칸

        Solution solution = new Solution();
        String[] wall = { "....HH", "H..H.H" };
        solution.solution(wall);
    }
}
