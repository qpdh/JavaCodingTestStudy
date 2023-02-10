import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class java_11559 {
    static char board[][];

    final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static boolean[][] isVisited;

    // 상하좌우 연결 체크 -> 4개 이상 -> 삭제
    // 중력 작용
    // 상하좌우 연결 체크
    // 삭제가 없다면 종료
    // chainCount 반환
    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    // 상하좌우 깊이 우선 탐색
    static void DFS(int y, int x, List<Pair> popList) {
        for (int i = 0; i < dydx.length; i++) {
            // 이동할 좌표
            int toY = y + dydx[i][0];
            int toX = x + dydx[i][1];

            try {
                // 방문하지 않았으며..
                if (!isVisited[toY][toX]) {
                    // 같은 puyo인가?
                    if (board[y][x] == board[toY][toX]) {
                        // 방문처리
                        isVisited[toY][toX] = true;
                        // popList에 해당 좌표 추가
                        popList.add(new Pair(toY, toX));
                        DFS(toY, toX, popList);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
    }

    // 연쇄가 일어나는지 체크
    static boolean popCheck() {
        isVisited = new boolean[12][6];

        List<Pair> popList = new ArrayList<>();

        for (int i = board.length - 1; i >= 0; i--) {
            int blankCount = 0;

            for (int j = board[0].length - 1; j >= 0; j--) {
                // . 이면 blankCount 증가
                if (board[i][j] == '.') {
                    blankCount++;
                }
                // 그게 아니면.. 4개 이상 연결되는지 체크
                else if (!isVisited[i][j]) {
                    // 방문처리
                    isVisited[i][j] = true;
                    List<Pair> semiPopList = new ArrayList<>();
                    semiPopList.add(new Pair(i, j));
                    DFS(i, j, semiPopList);

                    // 4개 이상이라면.. 터뜨리기 리스트에 추가
                    if (semiPopList.size() >= 4) {
                        popList.addAll(semiPopList);
                    }

                }

            }

            // 한 행에 빈 공간이 6개라면.. 종료
            if (blankCount == 6) {
                break;
            }
        }

        // popList에 있는 좌표 전부 .으로 바꾸기
        for (Pair pair : popList) {
            board[pair.y][pair.x] = '.';
        }

        // 터진게 1개라도 있으면 연쇄처리
        if (popList.size() > 0) {
            return true;
        }
        return false;
    }

    // 중력 작용
    public static void gravity() {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = board.length - 1; j >= 0; j--) {
                if (board[j][i] != '.') {
                    // 내리기
                    int k = j + 1;
                    while (k < 12) {
                        if (board[k][i] != '.') {
                            k--;
                            break;
                        }
                        k++;
                    }
                    if (k >= 12) {
                        k--;
                    }
                    board[k][i] = board[j][i];
                    if (j != k) {
                        board[j][i] = '.';
                    }

                }
            }
        }
    }

    public static int puyoPuyo() {
        int chainCount = 0;
        while (true) {
            if (!popCheck()) {
                break;
            }
            gravity();
            chainCount++;
        }

        return chainCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 12행 6열
        board = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            board[i] = line.toCharArray();
        }

        int result = puyoPuyo();

        System.out.println(result);

        br.close();
    }
}
