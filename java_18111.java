import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_18111 {
    static int N, M, B;
    static int board[][];

    //
    static int checkBlock(int height) {
        int time = 0;
        int block = B;

        // 256 보다 높이는 깎을 수 없음
        // 0보다 밑으로 깎을 수 없음
        if (height > 256 || height < 0) {
            return -1;
        }

        // hight 높이로 깎아보자
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 해당하는 높이보다 높이 있으면 -> 깎아야 함
                if (height < board[i][j]) {
                    time += (board[i][j] - height) * 2;
                    block += board[i][j] - height;
                }
                // 해당하는 높이보다 낮게 있으면 -> 쌓아야 함
                if (board[i][j] < height) {
                    time += (height - board[i][j]);
                    block -= height - board[i][j];
                }
            }
        }

        // 블럭이 부족하면 불가능
        if (block < 0) {
            return -1;
        } else {
            return time;
        }
    }

    static void search(int min, int max) {
        int minTime = Integer.MAX_VALUE, maxHeight = Integer.MIN_VALUE;

        for (int height = min; height <= max; height++) {

            // mid 이하로 깎을 수 있는가?
            int time = checkBlock(height);

            // 둘 다 가능하다면...
            if (time != -1) {
                if (time < minTime) {
                    minTime = time;
                    maxHeight = height;
                } else if (time == minTime) {
                    maxHeight = Math.max(height, maxHeight);
                }
            }
        }

        System.out.println(minTime + " " + maxHeight);
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 초기 블럭 갯수
        B = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        int minHeight = 256;
        int maxHeight = 0;
        // 땅의 높이 [0,256]
        // 땅 높이 데이터 삽입
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] < minHeight) {
                    minHeight = board[i][j];
                }
                if (maxHeight < board[i][j]) {
                    maxHeight = board[i][j];
                }
            }
        }

        // 몇 높이로 채워야 할까?

        // 가장 높은 땅 기준
        // 가장 낮은 땅 기준
        // -> 이분탐색?

        search(minHeight, maxHeight);

        // 1. N 높이로 채워 넣기
        // 2. N 높이로 깎기

        // 1. N 이하로 깎기, N만큼 채워넣기
        br.close();
        bw.close();
    }
}
