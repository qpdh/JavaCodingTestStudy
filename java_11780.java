import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class java_11780 {
    // 도시의 개수
    static int n;
    // 버스의 개수
    static int m;

    // [i]에서 [j]로가는데 최솟값 저장
    static int board[][];
    // [i]에서 [j]로 가는데 중간 경유지 저장
    static int transitBoard[][];

    //
    // [i][j]로 가는데 경로 저장
    // [i][k] -> [k][j] 로 간다면
    // i j k 를 출력해야 함
    // i가 k로 가는 방법
    // k로 갈때 경유하는 방법 [i][k]
    // [i][k] 가 -1 이라면.. 직행이다

    // i 삽입 [i][j] 삽입 (재귀)
    //

    // k가 j로 가는 방법

    static Deque<Integer> makeRoute(int start, int end) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(start + 1);

        makeWaypoint(deque, start, end);

        return deque;
    }

    // start 와 end 사이의 모든 경유지를 가져온다.
    // [start][end] = waypoint
    // [start][waypoint] 만약 -1이라면 waypoint를 넣고
    // -1이 아니라면 [start][waypoint2] 에 대해 또 실행한다.
    static boolean makeWaypoint(Deque<Integer> deque, int start, int end) {
        if (end == -1 || start == -1) {
            return false;
        }
        if (transitBoard[start][end] == -1) {
            deque.add(end + 1);
            return false;
        } else {
            makeWaypoint(deque, start, transitBoard[start][end]);
            // deque.add(transitBoard[start][end] + 1);

            makeWaypoint(deque, transitBoard[start][end], end);
            // deque.add(end + 1);
        }
        return true;
    }

    static void floydWarshall() {
        // i 를 경유하고
        for (int i = 0; i < n; i++) {
            // j 에서 시작하여
            for (int j = 0; j < n; j++) {
                // k 로가는 경우
                for (int k = 0; k < n; k++) {
                    if (board[j][i] != Integer.MAX_VALUE && board[i][k] != Integer.MAX_VALUE) {
                        if (i != j && j != k && i != k) {
                            int distance = board[j][i] + board[i][k];

                            // 더 작은 값이면 i를 경유하는 것이 맞다.
                            if (distance < board[j][k]) {
                                board[j][k] = distance;
                                transitBoard[j][k] = i;
                            }

                        }
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // n 입력받기
        n = Integer.parseInt(br.readLine());

        // m 입력 받기
        m = Integer.parseInt(br.readLine());

        // 버스 데이터 입력 받기
        board = new int[n][n];
        transitBoard = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], Integer.MAX_VALUE);
            Arrays.fill(transitBoard[i], -1);
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            board[from][to] = Math.min(board[from][to], cost);
        }

        // floyd warshall 알고리즘 사용하기
        floydWarshall();

        // 최소비용 출력하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != Integer.MAX_VALUE) {
                    bw.write(board[i][j] + " ");
                } else {
                    bw.write("0 ");
                }
            }
            bw.write("\n");
        }

        // i에서 j로 가는 최소비용이 포함되어있는 도시의 개수 k
        // i에서 j로 가는 경로
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != Integer.MAX_VALUE) {
                    Deque<Integer> deque = makeRoute(i, j);

                    bw.write(deque.size() + " ");
                    while (!deque.isEmpty()) {
                        bw.write(deque.poll() + " ");
                    }
                    bw.write("\n");

                    // i 넣고

                    // [i][j] 중간 경유지를 넣는다.

                    // j 를 넣는다

                } else {
                    bw.write("0\n");
                }

            }

        }

        br.close();
        bw.close();
    }
}
