import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_1459 {
    // 목적지 x, y
    static int x, y;
    // 십자 이동 비용
    static int crossCost;
    // 대각선 이동 비용
    static int diagonalCost;

    static long solution() {
        long result = 0;

        // 둘 다 10억 이하이므로 더해도 문제 없음
        int xy = x + y;

        // 대각선 비용이 더 이득인 경우
        if (diagonalCost < crossCost * 2) {
            xy -= Math.min(x, y) * 2;
            // 최대한 대각선으로 이동하기
            result += (long) (Math.min(x, y)) * diagonalCost;
        }

        // 같은 선상에서 2칸 차이나면 대각선으로 이동한다.
        if (diagonalCost < crossCost) {
            result += (long) (xy / 2 * 2) * diagonalCost;
            xy -= xy / 2 * 2;
        }

        result += (long) (xy) * crossCost;

        return result;
    }

    public static void main(String[] args) throws IOException {
        // 대각선을 가면 이득인 경우 -> 대각선 걸리는 시간 < 가로세로 걸리는 시간 * 2

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        crossCost = Integer.parseInt(st.nextToken());
        diagonalCost = Integer.parseInt(st.nextToken());

        long result = solution();

        System.out.println(result);

        br.close();
    }
}
