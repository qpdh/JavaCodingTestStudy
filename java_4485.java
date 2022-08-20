import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class java_4485 {
    final static int dydx[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
    final static int INF = 987654321;

    static int board[][];

    static class Pair {
        int cost;
        Coordinate here;

        public Pair(int cost, Coordinate here) {
            this.cost = cost;
            this.here = here;
        }
    }

    static class Coordinate {
        int y, x;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    static boolean isOutOfRange(int y, int x, int N) {
        boolean result = false;
        if (y < 0 || N <= y) {
            result = true;
        } else if (x < 0 || N <= x) {
            result = true;
        }
        return result;
    }

    static int dijkstra(int N) {
        int dist[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[0][0] = board[0][0];

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                // TODO Auto-generated method stub
                return o1.cost - o2.cost;
            }

        });

        pq.add(new Pair(board[0][0], new Coordinate(0, 0)));

        while (!pq.isEmpty()) {
            int cost = pq.peek().cost;
            Coordinate here = pq.peek().here;

            pq.poll();

            if (dist[here.y][here.x] < cost)
                continue;

            for (int i = 0; i < dydx.length; i++) {
                int toY = here.y + dydx[i][0];
                int toX = here.x + dydx[i][1];

                // 범위 밖으로 벗어나는 지 체크
                if (isOutOfRange(toY, toX, N)) {
                    continue;
                }

                int nextDist = cost + board[toY][toX];

                if (dist[toY][toX] > nextDist) {
                    dist[toY][toX] = nextDist;
                    pq.add(new Pair(nextDist, new Coordinate(toY, toX)));
                }

            }

        }

        return dist[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());

            // N 이 0이면 종료
            if (N == 0) {
                break;
            }

            // 보드 초기화
            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = dijkstra(N);
            bw.write(String.format("Problem %d: %d\n", count++, result));
        }

        br.close();
        bw.close();
    }
}
