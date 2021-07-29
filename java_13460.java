import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 구슬 클래스
// 좌표를 가짐.
class Marble {
    int y, x;
    char color;

    public Marble(int y, int x, char color) {
        this.y = y;
        this.x = x;
        this.color = color;
    }
}

public class java_13460 {
    final static int dydx[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static Marble blueMarble;
    static Marble redMarble;
    static char board[][];

    public static int mazeAlgo(int y, int x, int count) {
        // y, x 4방향으로 움직이는 경우

        // 10번 넘게 움직였지만, 안되는 경우
        if (count > 10)
            return -1;

        

        return 0;
    }

    public static void moveMarble(Marble marble, int dy, int dx) {
        // 움직이는 것은
        // 좌우
        // 상하
        // 둘 중 하나임.

        if (dx != 0) {
            if (board[marble.y][marble.x + dx] == '.') {
                // 원래 구슬이 있던 위치를 빈 공간으로 바꿈
                board[marble.y][marble.x] = '.';

                // 구슬의 위치를 옮김
                marble.x += dx;
                board[marble.y][marble.x] = marble.color;
            }
        }

        else if (dy != 0) {
            if (board[marble.y + dy][marble.x] == '.') {
                // 원래 구슬이 있던 위치를 빈 공간으로 바꿈
                board[marble.y][marble.x] = '.';

                // 구슬의 위치를 옮김
                marble.y += dy;
                board[marble.y][marble.x] = marble.color;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        // 보드 데이터 입력
        for (int i = 0; i < N; i++) {

            String oneLine = br.readLine();

            for (int j = 0; j < M; j++) {
                board[i][j] = oneLine.charAt(j);

                if (oneLine.charAt(j) == 'B') {
                    blueMarble = new Marble(i, j, 'B');
                } else if (oneLine.charAt(j) == 'R') {
                    redMarble = new Marble(i, j, 'R');
                }
            }
        }

        // 보드 출력해보기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        // 미로 찾기 알고리즘?

        br.close();
        bw.close();
    }
}
