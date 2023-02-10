import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_14613 {
    static double winPercent;
    static double losePercent;
    static double drawPercent;

    // [i]번 경기를 했을 때 [j] 점일 확률
    static double cache[][];

    static double bronze;
    static double silver;
    static double gold;
    static double platinum;
    static double diamond;

    static void partitionTier() {
        for (int i = 0; i <= 2000; i++) {
            if (0 <= i && i < 500) {
                bronze += cache[20][i];
                continue;
            }

            if (500 <= i && i < 1000) {
                silver += cache[20][i];
                continue;
            }

            if (1000 <= i && i < 1500) {
                gold += cache[20][i];
                continue;
            }

            if (1500 <= i && i < 2000) {
                platinum += cache[20][i];
                continue;
            } else {
                diamond += cache[20][i];
            }

        }
    }

    static void dp() {
        for (int i = 1; i <= 20; i++) {
            for (int j = 0; j <= 2000; j++) {
                if (cache[i - 1][j] > 0) {
                    cache[i][j + 50] += cache[i - 1][j] * winPercent;
                    cache[i][j - 50] += cache[i - 1][j] * losePercent;
                    cache[i][j] += cache[i - 1][j] * drawPercent;
                }
            }
        }
    }

    static void printResult() {
        System.out.printf("%.8f\n", bronze);
        System.out.printf("%.8f\n", silver);
        System.out.printf("%.8f\n", gold);
        System.out.printf("%.8f\n", platinum);
        System.out.printf("%.8f\n", diamond);
    }

    static void solve() {
        cache = new double[21][2001];
        cache[0][1000] = 1;

        dp();

        partitionTier();

        printResult();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        winPercent = Double.parseDouble(st.nextToken());
        losePercent = Double.parseDouble(st.nextToken());
        drawPercent = Double.parseDouble(st.nextToken());

        solve();

        br.close();
    }
}
