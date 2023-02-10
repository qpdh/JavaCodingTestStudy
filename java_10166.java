import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_10166 {
    static int euclid(int a, int b) {
        while (b != 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int D1 = Integer.parseInt(st.nextToken());
        int D2 = Integer.parseInt(st.nextToken());

        int result = 0;

        // HashMap, Map ->
        // 1.2 + 0.1 != 1.3

        // 0000 0000 0000 0000 0000 0000 0000 0000

        // 0.25 -> 0.01_(2)
        // 0.75 -> 0.11_(2)
        // 0.00000000000000000000000000000000001
        // HashMap -> Key X

        // GCD -> (360/D_i)/360 D번 반복
        // 90 180 270 360
        // 1/4 2/4 3/4 4/4
        // 360, 90 GCD 90
        // 1/4 2/4 3/4 4/4

        //

        // ( , )
        // (a, b)

        boolean map[][] = new boolean[D2 + 1][D2 + 1];

        for (int i = D1; i < D2 + 1; i++) {

            //
            for (int j = 1; j <= i; j++) {
                // 최소 공배수
                int gcd = euclid(i, j);
                // 분모
                int a = (int) j / gcd;
                // 분자
                int b = (int) i / gcd;

                if (!map[a][b]) {
                    map[a][b] = true;
                    result++;
                }
            }
        }

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}