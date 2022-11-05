    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Arrays;
    import java.util.StringTokenizer;

    public class java_1719 {
        // 집하장의 갯수
        static int n;
        // 경로의 갯수
        static int m;

        static int board[][];
        static int boardRoute[][];

        static void floydWarshall() {
            boardRoute = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    boardRoute[i][j] = j;
                }
                boardRoute[i][i] = -1;
            }

            // i를 들려서
            // j에서
            // k로 가는 방법
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        // 경유가 가능하다면
                        if (board[j][i] != Integer.MAX_VALUE && board[i][k] != Integer.MAX_VALUE) {
                            // 비용이 더 작다면
                            if (board[j][i] + board[i][k] < board[j][k]) {
                                // j-k를 가기위해 i를 들려야 한다.
                                boardRoute[j][k] = i;
                                board[j][k] = board[j][i] + board[i][k];
                            }
                        }
                    }
                }
            }
        }

        // start에서 end로 갈 때 바로 들려야 하는 곳
        static int findRoute(int start, int end) {
            if (boardRoute[start][end] == end) {
                return end;
            }
            return findRoute(start, boardRoute[start][end]);
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            board = new int[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(board[i], Integer.MAX_VALUE);
                board[i][i] = 0;
            }

            // 비용 저장
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;

                int cost = Integer.parseInt(st.nextToken());

                board[from][to] = cost;
                board[to][from] = cost;
            }

            floydWarshall();

            // 최단경로를 위한 경로표의 작성
            for (int i = 0; i < n; i++) {
                // i에서 j로 가는 데 가장 먼저 들리는 곳 저장
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        boardRoute[i][j] = findRoute(i, j);
                    }

                }
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (boardRoute[i][j] == -1) {
                        System.out.print("- ");
                    } else {
                        System.out.print((boardRoute[i][j] + 1) + " ");
                    }

                }
                System.out.println();
            }
            // A -> B 로 가기 위한 최단 경로 사이에
            // C가 존재한다.
            // A -> C -> B 순서로 간다.

            // dist 줄어드는 연산을 할 때 삽입할것?

            // 다익스트라보다 floydWarshall 쓰는게 맞을지도

            br.close();
        }
    }
