import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_2573 {
    final static int dxdy[][] = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
    static boolean isVisited[][];

    // 하나의 점에서 이어진 모든 점을 방문하는 메소드
    public static void search(int[][] iceBerg, int y, int x) {
        // 범위를 벗어나면 탈락
        if (y < 0 || y >= iceBerg.length || x < 0 || x >= iceBerg[0].length)
            return;

        // 빙하가 없으면 탈락
        if (iceBerg[y][x] == 0)
            return;

        // 이미 방문했으면 탈락
        if (isVisited[y][x] == true)
            return;

        isVisited[y][x] = true;
        // System.out.println("y : "+y+" x : "+x+"접근");

        // 4방향 접근
        for (int i = 0; i < dxdy.length; i++) {
            search(iceBerg, y + dxdy[i][0], x + dxdy[i][1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N행 M열
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int iceBerg[][] = new int[N][M];

        // 빙산 데이터 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                iceBerg[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 두 덩어리가 분리 됐는가?
        boolean isSplit = false;

        // 몇 년이 지났는가? == 반복 횟수
        int year = 0;

        // 모든 빙하가 0인가?
        boolean isAllZero = true;

        // 처음 데이터 자체에서 찢어졌는지 확인하기
        isVisited = new boolean[N][M];

        boolean isBreak = false;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (iceBerg[i][j] > 0) {
                    search(iceBerg, i, j);
                    isBreak = true;
                    break;
                }
            }
            if (isBreak)
                break;
        }
        // 1,1 == 2

        // 찢어진 다른 곳이 존재하는가?
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (!isVisited[i][j] && iceBerg[i][j] > 0) {
                    // System.out.println(i + " " + j + "아직 방문하지 않음. 찢어짐");
                    isSplit = true;
                }
            }
        }
        ////////////////////////////////////////////////////

        // 분리되지 않은 동안 반복
        while (!isSplit) {
            // 빙산 주변에 0이 몇개인가?
            int surroundZero[][] = new int[N][M];
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (iceBerg[i][j] > 0) {
                        int count = 0;
                        for (int k = 0; k < 4; k++) {
                            if (iceBerg[i + dxdy[k][0]][j + dxdy[k][1]] == 0)
                                count++;
                        }
                        surroundZero[i][j] = count;
                    }
                }
            }

            // 녹이기
            // 다 녹은 빙하가 있는 지 체크
            boolean isZero = false;
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (surroundZero[i][j] > 0) {
                        iceBerg[i][j] -= surroundZero[i][j];
                        // 0밑은 0으로 만들기
                        if (iceBerg[i][j] <= 0) {
                            iceBerg[i][j] = 0;
                            isZero = true;
                        }

                    }
                }
            }

            // System.out.println("==="+year+"===");
            // for(int i=0;i<N;i++){
            // for(int j=0;j<M;j++){
            // System.out.print(iceBerg[i][j]+ " ");
            // }
            // System.out.println();
            // }

            if (isZero) {
                // 찢어졌는지 확인하기
                isVisited = new boolean[N][M];

                // 가장 먼저 빙하가 있는 점 찾기
                isBreak = false;
                for (int i = 1; i < N - 1; i++) {
                    for (int j = 1; j < M - 1; j++) {
                        if (iceBerg[i][j] > 0) {
                            search(iceBerg, i, j);
                            isBreak = true;
                            break;

                        }
                    }
                    if (isBreak)
                        break;
                }

                // 찢어진 다른 곳이 존재하는가?
                for (int i = 1; i < N - 1; i++) {
                    for (int j = 1; j < M - 1; j++) {
                        if ((!isVisited[i][j]) && iceBerg[i][j] > 0) {
                            // System.out.println(i + " " + j + "아직 방문하지 않음. 찢어짐");
                            isSplit = true;
                        }
                    }
                }
            }

            // 모든 빙하가 0인가?
            isAllZero = true;
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (iceBerg[i][j] != 0) {
                        isAllZero = false;
                    }
                }
                if (!isAllZero)
                    break;
            }

            if (isAllZero) {
                // bw.write("모든 빙하가 0임"+year);
                break;
            }

            // 년도 추가 => 계속 녹이기

            year++;

        }

        if (isAllZero) {
            // bw.write("테스트");
            bw.write("0\n");
        } else {
            bw.write(year + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}

// 1. 분리가 되었는지 확인하는 방법?
// 언제 확인하는가?
// 하나의 빙산의 0이 될 때만 체크해도 됨!!!
// 이게 제일 중요
// 한 점에서 이동을 할 것인가?
// visited?
// 그래프 탐색 이용!

// 덩어리 수를 체크하는 변수 가지기.
// 0,0 ~ N,N 까지 깊이 우선 탐색 실시
// 1, 1 에서 한 번 탐색 실시할 때 마다 1증가
// 새로운 위치에서 탐색을 실시 할 때 새롭게 들어간 노드가 없으면 증가하지 않음
// -> 덩어리 추출 가능
// -> 덩어리가 2 이상이라면? 반복횟수 반환

// 2. 빙산을 녹이기
// 배열을 돌면서
// 한번에 줄이기 해야함
// 벡터?
// 빙산이 있는 좌표 + 빙산의 높이 + 주변의 0의 개수