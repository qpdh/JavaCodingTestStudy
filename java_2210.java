import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
//import java.util.Iterator;
import java.util.StringTokenizer;

public class java_2210 {
    static HashSet<Integer> result;
    static int board[][];

    static int by[] = { -1, 0, 0, 1 };
    static int bx[] = { 0, 1, -1, 0 };

    // 숫자 케이스 찾기
    public static void countCase(int y, int x, int nowCount, int nowResult) {
        // 재귀 배열 범위 안에 있는지 확인
        if (!inRange(y, x)) {
            return;
        }

        // 5번 이동 했는지 확인
        if (nowCount == 6) {
            result.add(nowResult);
            return;
        }

        nowResult = nowResult * 10 + board[y][x];

        // 이동한 위치 데이터 추가
        for (int i = 0; i < 4; i++) {
            int nextY = y + by[i];
            int nextX = x + bx[i];
            countCase(nextY, nextX, nowCount + 1, nowResult);
        }

    }

    // 배열의 범위를 벗어났는 지 확인
    public static boolean inRange(int y, int x) {

        if (y < 0 || y > 4)
            return false;

        if (x < 0 || x > 4)
            return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        result = new HashSet<>();
        board = new int[5][5];

        for (int y = 0; y < 5; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < 5; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
                // bw.write(board[y][x] + " ");
            }
            // bw.write("\n");
        }

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                countCase(y, x, 0, 0);
            }
        }

        bw.write(result.size() + "\n");

        // 결과값 표시
        // Iterator<Integer> it = result.iterator();
        // while (it.hasNext()) {
        //     System.out.println(it.next());
        // }

        bw.flush();
        br.close();
        bw.close();
    }
}
