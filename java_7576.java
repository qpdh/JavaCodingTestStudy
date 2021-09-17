import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_7576 {
    static int board[][];
    static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static Queue<Pair> queue;

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static void bfs() {

        boolean isVisited[][] = new boolean[board.length][board[0].length];
        //isVisited[startY][startX] = true;

        while (!queue.isEmpty()) {
            Pair nowNode = queue.poll();

            for (int i = 0; i < dydx.length; i++) {
                int toY = nowNode.y + dydx[i][0];
                int toX = nowNode.x + dydx[i][1];

                // 배열의 범위를 벗어나면 안됨
                if (toY < 0 || toY >= board.length || toX < 0 || toX >= board[0].length) {
                    continue;
                }
                // 이미 방문 했다면...
                else if (isVisited[toY][toX]) {
                    continue;
                }
                // 토마토 여야 하며
                else if (board[toY][toX] == -1) {
                    continue;
                }
                // 안익은 토마토 중에서 작은 값 선택
                else {
                    //isVisited[toY][toX] = true;
                    // 안익은 토마토면 익게 만들기
                    if (board[toY][toX] == 0) {
                        board[toY][toX] = board[nowNode.y][nowNode.x] + 1;
                        queue.add(new Pair(toY, toX));
                    }
                    // // 더 빨리 익는 경우 시간 정정하기
                    // else if (board[toY][toX] >= board[nowNode.y][nowNode.x] + 1) {
                    //     board[toY][toX] = board[nowNode.y][nowNode.x] + 1;
                    //     queue.add(new Pair(toY, toX));
                    // }

                }
            }
        }

        return;
    }

    public static int findMaxDate() {
        // 가장 큰 값 찾기
        int maxDate = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // System.out.print(board[i][j] + " ");

                // 안익은 경우가 있으면 바로 반환
                if (board[i][j] == 0) {
                    maxDate = -1;
                    return maxDate;
                }
                
                if (maxDate < board[i][j]) {
                    maxDate = board[i][j];
                }
            }
            // System.out.println();
        }

        return maxDate - 1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // M : 상자의 가로 칸의 수
        // N : 상자의 세로 칸의 수
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 2차원 배열 상자 초기화
        board = new int[N][M];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    queue.add(new Pair(i,j));
                }
            }
        }

        // 익은 토마토에서 부터 탐색을 시작하되, 탐색이 종료되어도 안익은 토마토가 존재하면 -1 반환
        // 익은 토마토가 2개가 있다면? 더 짧은 값을 선택...
        // 모든 토마토가 익었다면... 가장 큰 값을 선택
        bfs();

        int ret = findMaxDate();

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
