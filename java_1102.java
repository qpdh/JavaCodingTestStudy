import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1102 {
    final static int INF = 987654321;
    static int board[][];
    static boolean isOn[];
    static int P;

    static int cache[];

    // 발전소 2진수화
    //

    // 순서가 정해져 있는상태
    // 발전소 상태에서 최소한으로 P개 키기
    public static int solve(int bin) {
        int index = Integer.bitCount(bin);

        // P개 이상의 발전기가 작동되어야 함
        if (index >= P) {
            return 0;
        }

        // 발전기가 전부 꺼져있고 한 개이상 켜야한다면
        // 불가능
        if (index == 0) {
            return -1;
        }

        if (cache[bin] != -1) {
            return cache[bin];
        }

        int ret = INF;

        // 돌아가는 발전기로부터
        // 안돌아가는 발전기 돌리기
        for (int i = 0; i < board.length; i++) {
            // 만약 고장나지 않은 발전소 -> 고장난 발전소
            if (isOn[i]) {
                for (int j = 0; j < board.length; j++) {
                    // 발전소 작동시키기
                    if (!isOn[j]) {
                        isOn[j] = true;
                        ret = Math.min(ret, board[i][j] + solve(bin + (int) Math.pow(2, j)));
                        isOn[j] = false;
                    }
                }
            }
        }

        cache[bin] = ret;
        return cache[bin];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        isOn = new boolean[N];
        cache = new int[(int)Math.pow(2,N+1)];

        for (int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }

        // 비용 삽입, 캐시 초기화
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuffer sb = new StringBuffer(br.readLine());
        int bin = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == 'Y') {
                isOn[i] = true;
                bin += Math.pow(2, i);
            }
        }

        P = Integer.parseInt(br.readLine());

        int ret = solve(bin);

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}