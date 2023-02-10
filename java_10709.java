import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_10709 {
    // 높이, 너비
    static int H, W;

    static char board[][];
    static int result[][];

    static void solution() {
        //

        for (int i = 0; i < board.length; i++) {
            int cloudX = -1;
            for (int j = 0; j < board[i].length; j++) {
                // 구름 위치 갱신
                if (board[i][j] == 'c') {
                    cloudX = j;
                }
                // 구름이 있다면 몇 분 후에 구름이 오는지 저장
                if (cloudX != -1) {
                    result[i][j] = j - cloudX;
                }
            }
        }
    }

    static void printResult() {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        // 구름은 1분이 지날 때 마다 동쪽으로 이동

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        board = new char[H][W];
        result = new int[H][W];

        // c는 구름 .는 맑음
        for (int i = 0; i < board.length; i++) {
            board[i] = br.readLine().toCharArray();
            Arrays.fill(result[i], -1);
        }

        // 각 행의 0열부터 돌면서 구름이 있는지 체크, 마지막 구름 위치를 갱신, 현재 열-마지막 구름의 열 값 출력
        // 만약 구름이 없다면 -1 출력

        solution();

        printResult();

        br.close();
    }
}
