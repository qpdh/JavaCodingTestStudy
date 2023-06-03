import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_11758 {
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

        pairs = new Pair[3];

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pairs[i] = new Pair(y, x);
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }

    private static int solution() {
        int ccwResult = CCW();

        if (ccwResult == 0) {
            return 0;
        }
        if (ccwResult > 0) {
            return 1;
        }
        return -1;
    }

    static int CCW() {
        return (pairs[1].x - pairs[0].x) * (pairs[2].y - pairs[0].y)
                - (pairs[2].x - pairs[0].x) * (pairs[1].y - pairs[0].y);
    }
}
