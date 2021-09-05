import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1932 {
    static int triArray[][];
    static int cache[][];

    // [y][x] 위치로 부터 밑으로 최대값
    public static int routeMax(int y, int x) {
        if (y >= triArray.length) {
            return 0;
        }

        // 캐시가 존재한다면
        if (cache[y][x] != -1) {
            return cache[y][x];
        }

        cache[y][x] = 0;

        cache[y][x] = Math.max(cache[y][x], triArray[y][x] + routeMax(y + 1, x));
        cache[y][x] = Math.max(cache[y][x], triArray[y][x] + routeMax(y + 1, x + 1));

        return cache[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        triArray = new int[n][];
        cache = new int[n][];

        for (int i = 0; i < n; i++) {
            triArray[i] = new int[i + 1];
            cache[i] = new int[i + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < i + 1; j++) {
                triArray[i][j] = Integer.parseInt(st.nextToken());
                cache[i][j] = -1;
            }
        }

        int ret = routeMax(0, 0);
        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
