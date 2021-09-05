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

    // // 최근에 color 색깔로 칠하고
    // // N번째 집을 칠해야 할 때 최소값
    // public static int painting(int N, int color) {

    //     // 다 칠했다면...
    //     if (N >= house.length) {
    //         int ret = INF;
    //         for (int i = 0; i < 3; i++) {
    //             if (i != color) {
    //                 ret = Math.min(ret, house[N - 1][color]);
    //             }
    //         }
    //         return ret;
    //     }

    //     // 캐시가 존재 한다면
    //     if (cache[N][color] != -1) {
    //         return cache[N][color];
    //     }

    //     cache[N][color] = INF;

    //     // 현재 칠해야 하는 RGB 3개의 색깔
    //     for (int i = 0; i < 3; i++) {
    //         // 이전에 칠한 색깔과 겹치면 안됨
    //         if (i != color) {
    //             cache[N][color] = Math.min(cache[N][color], house[N - 1][color] + painting(N + 1, i));
    //         }
    //     }

    //     //System.out.println(N + "이고" + color + "일 때 : " + cache[N][color]);

    //     return cache[N][color];
    // }

    // 현재 N번째를 color로 칠해야할 때
    // 최소값
    public static int painting2(int N, int color) {

        // 다 칠했다면...
        if (N >= house.length) {
            return 0;
        }

        // 캐시가 존재 한다면
        if (cache[N][color] != -1) {
            return cache[N][color];
        }

        cache[N][color] = INF;

        // 현재 칠해야 하는 RGB 3개의 색깔
        for (int i = 0; i < 3; i++) {
            // 이전에 칠한 색깔과 겹치면 안됨
            if (i != color) {
                cache[N][color] = Math.min(cache[N][color], house[N][color] + painting2(N + 1, i));
            }
        }

        //System.out.println(N + "이고" + color + "일 때 : " + cache[N][color]);

        return cache[N][color];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        house = new int[N][3];
        cache = new int[N][3];

        // 캐시 초기화, 데이터 삽입
        // N개의 집
        for (int i = 0; i < N; i++) {
            // RGB 정수형 데이터
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
                cache[i][j] = -1;
            }
        }

        int ret = INF;
        for (int i = 0; i < 3; i++) {
            ret = Math.min(ret, painting2(0, i));
        }

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
