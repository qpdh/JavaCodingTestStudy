import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_1711 {
    static int N;
    static Pair pairs[];

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        pairs = new Pair[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            Pair pair = new Pair(y, x);
            pairs[i] = pair;
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }

    // 직각 삼각형의 갯수 반환하는 메소드
    private static int solution() {
        int count = 0;

        //
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = j + 1; k < N; k++) {
                    if (j == k || k == i) {
                        continue;
                    }

                    Pair firstPair = pairs[i];
                    Pair secondPair = pairs[j];
                    Pair thirdPair = pairs[k];

                    Pair firstVector = new Pair(secondPair.y - firstPair.y, secondPair.x - firstPair.x);
                    Pair secondVector = new Pair(thirdPair.y - firstPair.y, thirdPair.x - firstPair.x);

                    if ((long) (firstVector.y) * secondVector.y + (long) (firstVector.x) * secondVector.x == 0) {
                        count++;
                    }

                }
            }
        }

        return count;
    }
}
