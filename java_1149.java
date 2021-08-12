import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1149 {
    final static int INF = 987654321;

    // 각 집마다 RGB 칠하는 값
    static int house[][];

    static int cache[][];

    // 현재 색깔이 color고
    // 현재 N+1번째의 집을 칠해야 할 때
    // 최소값
    public static int painting(int N, int color) {

        // 다 칠했다면...
        if (N == house.length - 1) {
            return house[N][color];
        }

        // 캐시가 존재 한다면
        if (cache[N][color] != -1) {
            return cache[N][color];
        }

        cache[N][color] = INF;

        // RGB 3개의 색깔
        for (int i = 0; i < 3; i++) {
            if (i != color) {
                cache[N][i] = Math.min(cache[N][color], house[N][color] + painting(N - 1, house[N + 1][i]));
            }
        }

        return cache[N][color];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        house = new int[N][3];
        cache = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
                cache[i][j] = -1;
            }
        }

        int ret = INF;
        for (int i = 0; i < 3; i++) {
            Math.min(ret, house[0][i] + painting(0, house[0][i]));
        }

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
