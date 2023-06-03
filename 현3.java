import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 현3 {
    static int N;
    static int jumpBlock;

    static int board[];

    static int cache[];

    static int dp(int here) {
        // 맵 끝가지 도달했다면.. 종료
        if (here >= board.length - 1) {
            return 0;
        }

        if (cache[here] != -1) {
            return cache[here];
        }

        cache[here] = 987654321;

        // 현재 위치가 점프할 수 있는 위치라면
        if (board[here] != -1) {
            cache[here] = Math.min(cache[here], dp(board[here]));
        }

        else {
            // 1~6 주사위 굴리기
            for (int i = 6; i > 0; i--) {
                cache[here] = Math.min(cache[here], dp(here + i) + 1);
            }
        }

        return cache[here];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        jumpBlock = Integer.parseInt(st.nextToken());

        board = new int[N];

        Arrays.fill(board, -1);

        for (int i = 0; i < jumpBlock; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            board[from] = to;
        }

        cache = new int[N];
        Arrays.fill(cache, -1);
        dp(0);

        int result = cache[0];

        if (result == 987654321) {
            result = -1;
        }

        System.out.println(result);

        br.close();
    }
}
