import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_6593 {
    final static int[][] dzdydx = { { 0, 0, 1 }, { 0, 0, -1 }, { 0, 1, 0 }, { 0, -1, 0 }, { 1, 0, 0 }, { -1, 0, 0 } };

    static char board[][][];
    // 빌딩의 층 L(1 ≤ L ≤ 30)
    static int L;
    // 행 R(1 ≤ R ≤ 30)
    static int R;
    // 열 C(1 ≤ C ≤ 30)
    static int C;

    static class Point {
        private int z, y, x;
        private int count;

        public Point() {
            this.setCount(0);
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }

        @Override
        public boolean equals(Object obj) {

            Point target = (Point) obj;
            if (this.z == target.getZ() && this.y == target.getY() && this.x == target.getX()) {
                return true;
            }
            return false;
        }

    }

    public static boolean outOfRange(Point thisPoint, int[] delta) {
        boolean result = false;

        int toZ = thisPoint.getZ() + delta[0];
        int toY = thisPoint.getY() + delta[1];
        int toX = thisPoint.getX() + delta[2];

        if (toZ < 0 || L <= toZ) {
            result = true;
        } else if (toY < 0 || R <= toY) {
            result = true;
        } else if (toX < 0 || C <= toX) {
            result = true;
        }

        return result;
    }

    public static int solve(Point start, Point escape) {

        boolean isVisited[][][] = new boolean[L][R][C];

        int time = -1;

        Queue<Point> queue = new LinkedList<>();

        queue.add(start);

        isVisited[start.getZ()][start.getY()][start.getX()] = true;

        while (!queue.isEmpty()) {

            Point thisPoint = queue.poll();

            // 목적지에 도달하면 종료
            if (thisPoint.equals(escape)) {
                time = thisPoint.getCount();
                queue.clear();
                break;
            }

            //
            for (int i = 0; i < dzdydx.length; i++) {
                if (outOfRange(thisPoint, dzdydx[i])) {
                    continue;
                }

                // 갈 좌표 계산
                int toZ = thisPoint.getZ() + dzdydx[i][0];
                int toY = thisPoint.getY() + dzdydx[i][1];
                int toX = thisPoint.getX() + dzdydx[i][2];

                // 벽은 갈 수 없음
                if (board[toZ][toY][toX] == '#') {
                    continue;
                }

                // 이미 방문 했으면 또 방문하지 않음
                if (isVisited[toZ][toY][toX]) {
                    continue;
                }

                Point nextPoint = new Point();
                nextPoint.setZ(toZ);
                nextPoint.setY(toY);
                nextPoint.setX(toX);
                nextPoint.setCount(thisPoint.getCount() + 1);

                isVisited[nextPoint.getZ()][nextPoint.getY()][nextPoint.getX()] = true;

                queue.add(nextPoint);
            }
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {

            Point start = new Point();
            Point escape = new Point();

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 첫 번째 줄 L, R, C 입력 받기
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            // 3개가 전부 0이면 종료
            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            // 판 생성
            board = new char[L][R][C];

            // 데이터 입력
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String rowData = br.readLine();
                    for (int k = 0; k < C; k++) {
                        board[i][j][k] = rowData.charAt(k);

                        if (board[i][j][k] == 'S') {
                            start = new Point();
                            start.setZ(i);
                            start.setY(j);
                            start.setX(k);
                        } else if (board[i][j][k] == 'E') {
                            escape = new Point();
                            escape.setZ(i);
                            escape.setY(j);
                            escape.setX(k);
                        }
                    }
                }
                br.readLine();
            }

            // 시간 계산 메소드 호출
            int result = solve(start, escape);

            // 결과 출력
            if (result > 0) {
                bw.write("Escaped in " + result + " minute(s).\n");
            } else {
                bw.write("Trapped!\n");
            }

        }

        br.close();
        bw.close();

    }
}
