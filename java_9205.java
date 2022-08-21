import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_9205 {
    final static int INF = Integer.MAX_VALUE;
    static int board[][];
    static Coordinate coordinateList[];

    static class Coordinate {
        int y;
        int x;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Coordinate [x=" + x + ", y=" + y + "]";
        }

    }

    static boolean bfs(int n) {
        boolean result = false;

        boolean isVisited[] = new boolean[n + 2];

        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        isVisited[0] = true;

        while (!q.isEmpty()) {
            int here = q.poll();

            if (here == n + 1) {
                result = true;
                break;
            }

            // System.out.println(coordinateList[here]);

            for (int i = 1; i < board.length; i++) {
                if (!isVisited[i]) {
                    if (board[here][i] <= 1000) {
                        q.add(i);
                        isVisited[i] = true;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < t; testCase++) {
            int n = Integer.parseInt(br.readLine());

            coordinateList = new Coordinate[n + 2];
            board = new int[n + 2][n + 2];

            // 데이터 좌표 입력
            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                coordinateList[i] = new Coordinate(y, x);
            }

            // 맨헤튼 거리 계산
            for (int i = 0; i < n + 2; i++) {
                for (int j = i + 1; j < n + 2; j++) {
                    board[i][j] = Math.abs(coordinateList[i].y - coordinateList[j].y)
                            + Math.abs(coordinateList[i].x - coordinateList[j].x);
                    board[j][i] = board[i][j];
                }
                board[i][i] = INF;
            }

            if (bfs(n)) {
                bw.write("happy\n");
            } else {
                bw.write("sad\n");
            }

        }

        br.close();
        bw.close();

    }
}
