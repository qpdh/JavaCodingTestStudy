import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_17129 {
    // 4방향 이동을 위한 배열
    final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    // 행, 열
    static int n, m;
    // 정보섬 데이터
    static int board[][];

    // 가족의 y, x좌표
    static Pair family;

    // y, x 쌍을 저장하기 위한 클래스
    static class Pair {
        int y, x, degree;

        public Pair(int y, int x, int degree) {
            this.y = y;
            this.x = x;
            this.degree = degree;
        }

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
            this.degree = 0;
        }
    }

    // 가장 가까운 음식까지의 최단거리 반환
    // 만약 어떠한 음식도 도달할 수 없으면 0 반환
    static int solution() {
        // 중복 방문처리를 위한 배열
        boolean isVisited[][] = new boolean[n][m];

        // 너비우선 탐색
        Queue<Pair> queue = new ArrayDeque<>();

        queue.add(family);

        while (!queue.isEmpty()) {
            Pair here = queue.poll();

            for (int i = 0; i < dydx.length; i++) {
                int toY = here.y + dydx[i][0];
                int toX = here.x + dydx[i][1];
                try {
                    // 이미 방문한 곳은 또 방문하지 않음
                    if (isVisited[toY][toX]) {
                        continue;
                    }

                    // 벽은 갈 수 없음
                    if (board[toY][toX] == 1) {
                        continue;
                    }

                    // 다음 위치가 음식 중 하나에 해당하면
                    if (3 <= board[toY][toX] && board[toY][toX] <= 5) {
                        return here.degree + 1;
                    }

                    // 방문처리
                    isVisited[toY][toX] = true;
                    // 큐에 경로 추가
                    queue.add(new Pair(toY, toX, here.degree + 1));

                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 식구 2
        // 청국장 3
        // 스시 4
        // 맥앤치즈 5

        // 가장 가까운 위치

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        // 정보섬 데이터 입력받기
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                int thisBoard = line.charAt(j) - '0';
                board[i][j] = thisBoard;

                // 만약 가족들의 위치라면 따로 객체형태로 저장
                if (thisBoard == 2) {
                    family = new Pair(i, j);
                    continue;
                }
            }
        }

        int result = solution();

        if (result == 0) {
            System.out.println("NIE");
        }

        if (result != 0) {
            System.out.println("TAK");
            System.out.println(result);
        }

        br.close();
    }
}
