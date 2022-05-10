import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class java_2638 {
    private final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
    // N행 M열
    private static int N, M;

    private static int board[][];

    // 바깥 공간인지 확인
    private static boolean outBlank[][];

    // 좌표 저장을 위한 클래스
    static class Pair {
        private int y, x;

        Pair(int y, int x) {
            this.setY(y);
            this.setX(x);
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
    }

    public static void blankCheck() {
        boolean[][] isVisited = new boolean[N][M];

        // 0,0 부터 이어진 공간이여야 바깥공간임
        dfs(0, 0, isVisited);
    }

    // y, x 에서 내부 공간이 비어있는 지 확인
    // dfs 돌리면서 가장자리로 갈 수 있는지 체크
    public static void dfs(int y, int x, boolean[][] isVisited) {
        // 내부 공간이면 false
        // 내부 공간이 아니면 true

        isVisited[y][x] = true;
        outBlank[y][x] = true;

        for (int i = 0; i < dydx.length; i++) {
            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            // 범위를 벗어나는 지 확인
            if (toY < 0 || N <= toY || toX < 0 || M <= toX) {
                continue;
            }

            if (board[toY][toX] == 0) {
                // 방문 한 적 있으면 방문 안함
                if (isVisited[toY][toX]) {
                    continue;
                }

                dfs(toY, toX, isVisited);
            }

        }
    }

    public static boolean isBlank(int y, int x, int k) {
        int toY = y + dydx[k][0];
        int toX = x + dydx[k][1];

        // 범위를 벗어나는 지 확인
        if (toY < 0 || N <= toY || toX < 0 || M <= toX) {
            return false;
        }

        // 0이 내부 공간인지 확인
        if (board[toY][toX] == 0) {
            if (outBlank[toY][toX]) {
                return true;
            }
        }

        // 1이면 치즈이므로 false 반환
        // 내부 공간이라면 false 반환
        return false;

    }

    // 녹이기
    // 녹이는 데이터가 있으면 true 반환
    // 아니면 false 반환
    public static boolean melt() {
        // 바깥 빈 공간 확인
        blankCheck();

        // 녹일 좌표 저장
        List<Pair> meltList = new ArrayList<>();

        // 배열을 돌면서 처음 1이 나오는 위치 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                // 현재 좌표가 1이라면
                if (board[i][j] == 1) {
                    // 빈 공간이 2개 이상인지 찾기
                    int count = 0;

                    // 상하좌우 중 녹일 수 있는 공간이 존재하는가?
                    // 치즈 내부는 카운트로 치지 않음
                    for (int k = 0; k < dydx.length; k++) {
                        if (isBlank(i, j, k)) {
                            count++;
                        }
                    }

                    // 빈 공간이 2곳 이상이라면 -> 녹이기
                    if (count >= 2) {
                        meltList.add(new Pair(i, j));
                    }
                }
            }
        }

        // 녹이기
        for (Pair point : meltList) {
            board[point.getY()][point.getX()] = 0;
        }

        // 녹일 수 있으면 true 반환
        if (meltList.size() >= 1) {
            return true;
        }
        // 녹일 수 없으면 false 반환
        return false;

    }

    public static int solve() {
        int result = 0;
        outBlank = new boolean[N][M];

        while (melt()) {
            result++;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        // 치즈 데이터 삽입
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = solve();

        bw.write(res + "");

        br.close();
        bw.close();

    }
}
