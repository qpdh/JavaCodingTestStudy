import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_3020 {
    static int N, H;

    static int cave[];
    // [H][0] 이하는 무조건 부딪힌다.
    // [H][1] 이상은 무조건 부딪힌다.
    static int caveEscape[][];

    // 높이가 H일 때 부숴야 하는 갯수
    static int breakList[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 동굴의 길이 N 미터(N 짝수)
        // 동굴의 높이 H 미터

        // 첫 번째 장애물은 항상 석순
        // 종유석 석순 반복

        //

        // [i] 높이에 있을 때 피해야하는 종유석의 수, 석순의 수 저장하기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        cave = new int[N];
        caveEscape = new int[H][2];

        for (int i = 0; i < N; i++) {
            cave[i] = Integer.parseInt(br.readLine());

            // 석순의 경우
            if (i % 2 == 0) {
                caveEscape[cave[i] - 1][0] += 1;
            }
            // 종유석의 경우
            else {
                caveEscape[H - cave[i]][1] += 1;
            }

        }

        // 석순은 역방향으로 더해주기
        for (int i = H - 1; i > 0; i--) {
            caveEscape[i - 1][0] += caveEscape[i][0];
        }

        // 종유석은 정방향으로 더해주기
        for (int i = 1; i < H; i++) {
            caveEscape[i][1] += caveEscape[i - 1][1];
        }

        // 두개의 합
        breakList = new int[H];

        for (int i = 0; i < H; i++) {
            breakList[i] = caveEscape[i][0] + caveEscape[i][1];
        }

        // 최솟값과 최솟값의 갯수 찾기
        int minValue = breakList[0];
        int minCount = 1;
        for (int i = 1; i < H; i++) {
            if (breakList[i] < minValue) {
                minCount = 1;
                minValue = breakList[i];
            } else if (breakList[i] == minValue) {
                minCount++;
            }
        }

        System.out.println(minValue + " " + minCount);

        br.close();
    }
}
