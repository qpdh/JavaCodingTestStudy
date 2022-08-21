import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_2485 {

    static int euclid(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 나무 데이터 저장
        int trees[] = new int[N];

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(br.readLine());
            if (i >= 1) {
                trees[i] = trees[i] - trees[0];
            }
        }

        // 최대 공약수 구하기
        // -> (trees[N-1] / 최대 공약수) - N + 1
        int gcd = trees[1];
        for (int i = 1; i < N; i++) {
            gcd = euclid(gcd, trees[i]);
        }

        int result = (int) (trees[N - 1] / gcd) - N + 1;
        System.out.println(result);

        br.close();
        bw.close();
    }
}
