import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_9465 {
    static int cache[][];

    // [y][x]위치에 있는 우표를 떼어낸 뒤, 최댓값 반환
    public static int solve(int[][] array, int y, int x) {

        if (x >= array[0].length) {
            return 0;
        }

        if (cache[y][x] != -1) {
            return cache[y][x];
        }

        cache[y][x] = 0;

        // 현재 우표 + 나머지 뽑을 수 있는 우표 최댓값
        if (y == 1) {
            try {
                cache[y][x] = array[0][x + 1] + solve(array, 0, x + 1);
            } catch (Exception e) {
            }

            try {
                cache[y][x] = Math.max(cache[y][x], array[0][x + 2] + solve(array, 0, x + 2));
                cache[y][x] = Math.max(cache[y][x], array[1][x + 2] + solve(array, 1, x + 2));
            } catch (Exception e) {
            }

        } else {
            try {
                cache[y][x] = array[1][x + 1] + solve(array, 1, x + 1);
            } catch (Exception e) {
            }
            try {
                cache[y][x] = Math.max(cache[y][x], array[1][x + 2] + solve(array, 1, x + 2));
                cache[y][x] = Math.max(cache[y][x], array[0][x + 2] + solve(array, 0, x + 2));
            } catch (Exception e) {
            }

        }

        // System.out.println("y : " + y + " x : " + x + " " + cache[y][x]);

        return cache[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int n = Integer.parseInt(br.readLine());

            int array[][] = new int[2][n];

            // 데이터 삽입
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int j = 0;
                while (st.hasMoreTokens()) {
                    array[i][j] = Integer.parseInt(st.nextToken());
                    j++;
                }
            }

            // 기타 초기화
            cache = new int[2][n];
            for (int i = 0; i < 2; i++) {
                Arrays.fill(cache[i], -1);
            }

            int result = 0;

            // 맨 처음 뜯는 위치
            result = Math.max(solve(array, 0, 0) + array[0][0], solve(array, 1, 0) + array[1][0]);

            bw.write(result + "\n");
        }

        br.close();
        bw.close();
    }
}