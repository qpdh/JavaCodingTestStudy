import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_3085 {
    // 가로 세로 처리
    final static boolean ROW = false;
    final static boolean COLUMN = true;

    // 사탕이 들어있는 보드 [N][N]
    static char board[][];

    // 현재 줄, 방향으로 연속된 사탕의 최댓값
    public static int countCandy(int yx, boolean delta) {
        int maxCount = 1;
        int count = 1;

        int toYX = 1;

        char candy;

        // 한 행
        if (delta == ROW) {
            // 현재 위치의 사탕
            candy = board[yx][0];

            while (true) {
                try {
                    // 같은 색상의 사탕을 만나면 +1
                    if (candy == board[yx][toYX++]) {
                        count++;
                    }
                    // 다른 색깔의 사탕을 만나면
                    // 최댓값이면 갱신 -> 카운트 값 초기화
                    else {
                        candy = board[yx][toYX - 1];
                        if (maxCount < count) {
                            maxCount = count;
                        }
                        count = 1;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // 최댓값이면 갱신 -> 카운트 값 초기화
                    if (maxCount < count) {
                        maxCount = count;
                    }
                    return maxCount;
                }
            }
        }

        // 한 열
        // (delta == ROW)
        else {
            // 현재 위치의 사탕
            candy = board[0][yx];

            while (true) {
                try {
                    // 같은 색상의 사탕을 만나면 +1
                    if (candy == board[toYX++][yx]) {
                        count++;
                    }
                    // 다른 색깔의 사탕을 만나면
                    // 최댓값이면 갱신 -> 카운트 값 초기화
                    else {
                        candy = board[toYX - 1][yx];
                        if (maxCount < count) {
                            maxCount = count;
                        }
                        count = 1;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // 최댓값이면 갱신
                    if (maxCount < count) {
                        maxCount = count;
                    }
                    return maxCount;
                }
            }
        }

    }

    // 판을 돌면서 인접한 2개의 사탕 위치를 변경

    public static boolean changeCandySpace(int y, int x, boolean delta) {
        char tmpCandy = board[y][x];

        // 같은 줄에 있는 것을 바꾸기
        if (delta == ROW) {
            try {
                if (board[y][x] == board[y][x + 1]) {
                    return false;
                }
                board[y][x] = board[y][x + 1];
                board[y][x + 1] = tmpCandy;
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }

        // 같은 열에 있는 것을 바꾸기
        else {
            try {
                if (board[y][x] == board[y + 1][x]) {
                    return false;
                }
                board[y][x] = board[y + 1][x];
                board[y + 1][x] = tmpCandy;
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }

        }
        return true;
    }

    public static int solve() {
        int maxCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                maxCount = Math.max(maxCount, countCandy(j, COLUMN));
                maxCount = Math.max(maxCount, countCandy(j, ROW));

                // 위 아래 바꾸기
                if (changeCandySpace(i, j, COLUMN)) {
                    maxCount = Math.max(maxCount, countCandy(j, COLUMN));
                    //
                    maxCount = Math.max(maxCount, countCandy(i, ROW));
                    //
                    maxCount = Math.max(maxCount, countCandy(i + 1, ROW));

                    // 위 아래 바꾸기
                    changeCandySpace(i, j, COLUMN);
                }
                // 좌 우 바꾸기
                if (changeCandySpace(i, j, ROW)) {

                    //
                    maxCount = Math.max(maxCount, countCandy(i, ROW));
                    //
                    maxCount = Math.max(maxCount, countCandy(j, COLUMN));
                    //
                    maxCount = Math.max(maxCount, countCandy(j + 1, COLUMN));

                    // 좌 우 바꾸기
                    changeCandySpace(i, j, ROW);
                }
            }
        }

        return maxCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 연속된 사탕을 카운트 하는 함수
        // 1. 가로로 연속된 사탕을 카운트
        // 2. 세로로 연속된 사탕을 카운트

        // 연속된 사탕 중 가장 많은 사탕을 반환

        // 2개의 위치를 바꿔보기,
        // -> 2개의 위치는 [i][j] 기준 [i+1][j], [i][j+1] 만 하면 됨

        int N = Integer.parseInt(br.readLine());

        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String string = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = string.charAt(j);
            }
        }

        System.out.println(solve());

        br.close();
        bw.close();
    }
}
