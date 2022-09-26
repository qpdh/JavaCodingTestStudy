import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_11265 {
    // 파티장의 크기
    static int N;
    // 손님의 수
    static int M;

    static long board[][];

    // 플로이드 워셜 사용
    static void floydWarshall() {
        // [i] 를 경유해서
        for (int i = 0; i < board.length; i++) {
            // [j] 위치에서
            for (int j = 0; j < board.length; j++) {
                // [k] 로 가는데 더 빠른가?
                for (int k = 0; k < board.length; k++) {
                    if (i != j && j != k && i != k) {
                        // 경유해서 가는 거리 계산
                        long transitDistance = board[j][i] + board[i][k];
                        // 경유하는게 더 빠르면 경유거리 저장
                        board[j][k] = Math.min(board[j][k], transitDistance);

                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // N개의 파티장
        // 모든 파티장이 직접적으로 연결될 수 있는 도로 (일방통행)

        // A -> B 로 바로갈 수 있지만, 경유해서 더 빨리갈 수 있다.

        // C 시간 뒤 A파티장에서 B파티장으로 갈 수 있는가

        // 1. 모든 파티장이 직접적으로 연결되어 있다. (인접매트릭스 구현)

        // N 파티장의 크기, 손님의 수 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 각 파티장으로 가는데 걸리는 시간 입력받기
        board = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 플로이드 워셜을 이용하여 최단거리 계산하기
        floydWarshall();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        // A 에서 B파티장으로 갈 때 C 시간 안에 갈 수 있는지 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int timeLimit = Integer.parseInt(st.nextToken());

            // 시간내로 갈 수 있다면.
            if (board[from][to] <= timeLimit) {
                bw.write("Enjoy other party\n");
            }
            // 시간내로 갈 수 없다면.
            else {
                bw.write("Stay here\n");
            }
        }

        bw.close();
        br.close();
    }
}
