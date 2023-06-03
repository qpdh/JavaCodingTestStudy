import java.util.ArrayDeque;
import java.util.Deque;

public class 문제3 {
    static class Solution {
        final static int fireDyDx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 },
                { 1, 1 } };
        final static int iceDyDx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        static boolean[][] isVisited;
        static long village[][];

        static class Pair {
            int y, x;
            int length;

            public Pair(int y, int x, int length) {
                this.y = y;
                this.x = x;
                this.length = length;
            }
        }

        //
        static void fireBFS(int n, int y, int x, int length, int minute) {
            Deque<Pair> deque = new ArrayDeque<Pair>();
            deque.add(new Pair(y, x, 0));

            while (!deque.isEmpty()) {
                Pair now = deque.removeFirst();

                for (int i = 0; i < fireDyDx.length; i++) {
                    int toY = now.y + fireDyDx[i][0];
                    int toX = now.x + fireDyDx[i][1];

                    // 방문 처리
                    try {
                        if (!isVisited[toY][toX]) {
                            isVisited[toY][toX] = true;
                            village[toY][toX] += 1;
                            if (now.length + 1 < minute) {
                                deque.add(new Pair(toY, toX, now.length + 1));
                            }
                        }

                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }

                }
            }

        }

        // 현재 위치 기준 이동 횟수가 n분 이하여야 함
        static void iceBFS(int n, int y, int x, int length, int minute) {
            Deque<Pair> deque = new ArrayDeque<Pair>();
            deque.add(new Pair(y, x, 0));

            while (!deque.isEmpty()) {
                Pair now = deque.removeFirst();

                for (int i = 0; i < iceDyDx.length; i++) {
                    int toY = now.y + iceDyDx[i][0];
                    int toX = now.x + iceDyDx[i][1];

                    // 방문 처리
                    try {
                        if (!isVisited[toY][toX]) {
                            isVisited[toY][toX] = true;
                            village[toY][toX] -= 1;
                            if (now.length + 1 < minute) {
                                deque.add(new Pair(toY, toX, now.length + 1));
                            }
                        }

                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }

                }
            }
        }

        public long[][] solution(int n, int m, int[][] fires, int[][] ices) {
            // n*n 의 마을 크기
            village = new long[n][n];

            // m 분 후의 온도를 알고 싶다.

            for (int i = 1; i <= m; i++) {
                for (int j = 0; j < fires.length; j++) {
                    isVisited = new boolean[n][n];

                    int y = fires[j][0] - 1;
                    int x = fires[j][1] - 1;
                    village[y][x] += 1;
                    isVisited[y][x] = true;
                    fireBFS(n, y, x, 0, i);
                }
                for (int j = 0; j < ices.length; j++) {
                    isVisited = new boolean[n][n];
                    int y = ices[j][0] - 1;
                    int x = ices[j][1] - 1;
                    village[y][x] -= 1;
                    isVisited[y][x] = true;
                    iceBFS(n, y, x, 0, i);
                }
            }

            // fire의 위치를 담은 2차원 배열
            // ice의 위치를 담은 2차원 배열

            long[][] answer = village;
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] fires = { { 5, 2 }, { 1, 3 }, { 5, 2 } };
        int[][] ices = { { 1, 5 }, { 3, 2 } };
        solution.solution(5, 3, fires, ices);
    }
}
