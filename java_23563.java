import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

import java.util.PriorityQueue;

import java.util.StringTokenizer;

public class java_23563 {
    final static int INF = 987654321;
    final static int dydx[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    static int H, W;

    static char[][] board;
    static int[][] dist;
    // static boolean[][] isVisited;

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

    static boolean isOutOfRange(int y, int x) {

        boolean result = false;

        if (y < 0 || H <= y) {
            result = true;
        } else if (x < 0 || W <= x) {
            result = true;
        }

        return result;

    }

    static boolean isNearWall(int y, int x) {

        for (int i = 0; i < dydx.length; i++) {
            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            if (board[toY][toX] == '#') {
                return true;
            }
        }
        return false;
    }

    static void bfs(int y, int x, PriorityQueue<Pair> pq) {

        for (int i = 0; i < dydx.length; i++) {
            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            if (isOutOfRange(toY, toX))
                continue;

            // 가는 위치가 길인지 체크
            if (board[toY][toX] == '#') {
                continue;
            }

            // 옆에 벽이 있어야 계속 전파 가능
            if (isNearWall(toY, toX)) {
                // 거리가 더 짧으면 이득임
                if (dist[toY][toX] > dist[y][x]) {
                    dist[toY][toX] = dist[y][x];
                    pq.add(new Pair(dist[toY][toX], new Coordinate(toY, toX)));
                    bfs(toY, toX, pq);
                }
            }
        }
    }

    static int dijkstra(Coordinate start, Coordinate end) {
        dist = new int[H][W];

        for (int i = 0; i < H; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[start.y][start.x] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                // TODO Auto-generated method stub
                return o1.cost - o2.cost;
            }

        });

        pq.add(new Pair(0, new Coordinate(start.y, start.x)));

        if (isNearWall(start.y, start.x)) {
            bfs(start.y, start.x, pq);
        }

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
                if (isOutOfRange(toY, toX)) {
                    continue;
                }

                // 가는 위치가 길인지 체크
                if (board[toY][toX] == '#') {
                    continue;
                }

                int nextDist = cost + 1;

                if (dist[toY][toX] > nextDist) {
                    dist[toY][toX] = nextDist;
                    if (isNearWall(toY, toX)) {
                        bfs(toY, toX, pq);
                    }
                    pq.add(new Pair(nextDist, new Coordinate(toY, toX)));
                }

            }

        }

        return dist[end.y][end.x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        Coordinate start = null;
        Coordinate end = null;

        board = new char[H][W];
        // isVisited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            String row = br.readLine();
            for (int j = 0; j < W; j++) {
                board[i][j] = row.charAt(j);

                if (board[i][j] == 'S') {
                    start = new Coordinate(i, j);
                }
                if (board[i][j] == 'E') {
                    end = new Coordinate(i, j);
                }
            }
        }

        int result = dijkstra(start, end);

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}
