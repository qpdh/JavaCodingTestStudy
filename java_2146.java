import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_2146 {

    public static final int INF = 987654321;

    public static final int[][] dydx = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    public static int[][] cache;

    // N 지도의 크기
    public static int N;
    // 지도 정보
    public static int board[][];

    // <덩어리<가장자리 묶음>>
    public static Vector<Vector<Pair>> corner = new Vector<>();

    // isVisited
    public static boolean isVisited[][] = new boolean[N][N];

    public static class Pair {
        public int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {

            if (this.y == ((Pair) obj).y && this.x == ((Pair) obj).x) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "y : " + y + " x : " + x;
        }
    }

    // 가장자리 찾기
    public static void dfsAll() {
        isVisited = new boolean[N][N];

        int islandNum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isVisited[i][j] && board[i][j] == 1) {
                    dfs(i, j, islandNum++);
                }
            }
        }

    }

    public static void dfs(int y, int x, int islandNum) {
        // 방문 했는 지 확인
        if (isVisited[y][x]) {
            return;
        }

        isVisited[y][x] = true;

        for (int i = 0; i < dydx.length; i++) {
            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            // 범위 넘어가는 지 확인
            if (!outOfRange(toY, toX)) {

                // 다음 위치가 1인지 확인
                if (board[toY][toX] == 1) {
                    dfs(toY, toX, islandNum);
                }

                // 다음 위치가 0이면 모서리이므로 추가
                else if (board[toY][toX] == 0) {
                    try {
                        corner.elementAt(islandNum);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        corner.add(new Vector<>());
                    }

                    Pair newPair = new Pair(y, x);
                    if (!corner.get(islandNum).contains(newPair)) {
                        corner.get(islandNum).add(newPair);
                    }

                    // System.out.println(toY + " " + toX + " 데이터 추가");
                }
            }

        }
    }

    public static boolean outOfRange(int y, int x) {
        if (y < 0 || board.length <= y) {
            return true;
        }

        if (x < 0 || board.length <= x) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfsAll();

        // for (int i = 0; i < corner.size(); i++) {
        // System.out.println(i + "번째 마을의 모서리들");
        // for (int j = 0; j < corner.get(i).size(); j++) {
        // System.out.println(corner.get(i).get(j));
        // }
        // }

        int result = INF;
        for (int i = 0; i < corner.size() - 1; i++) {
            for (int j = i + 1; j < corner.size(); j++) {
                for (int k = 0; k < corner.get(i).size(); k++) {
                    // 첫 번째 섬의 모서리 위치
                    int fromY = corner.get(i).get(k).y;
                    int fromX = corner.get(i).get(k).x;

                    for (int l = 0; l < corner.get(j).size(); l++) {
                        // 두 번째 섬의 모서리 위치
                        int toY = corner.get(j).get(l).y;
                        int toX = corner.get(j).get(l).x;

                        int distance = Math.abs(fromY - toY) + Math.abs(fromX - toX) - 1;
                        if (distance < result) {
                            result = distance;
                        }
                    }
                }
            }
        }

        bw.write(result + "");

        br.close();
        bw.close();

    }
}
