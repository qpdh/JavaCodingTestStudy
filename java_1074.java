import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_1074 {
    static int N;
    static int r, c;

    static int count;
    static int resultCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solution();

        System.out.println(resultCount);

        br.close();
    }

    private static void solution() {
        count = 0;
        resultCount = 0;

        move(0, 0, N);

        return;
    }

    // 현재 크기를 4등분하기
    // size = 부분 행렬의 크기 -> /2값을 4등분 해야함
    private static void move(int y, int x, int n) {
        int size = (int) Math.pow(2, n);

        // 최소크기의 경우
        if (size == 1) {
            resultCount = count;
            return;
        }

        // 좌상단
        if ((x <= c && c < x + (size / 2)) && (y <= r && r < y + (size / 2))) {
            move(y, x, n - 1);
        } else {
            count += (int) Math.pow(2, 2 * n - 2);
        }

        // 우상단
        if ((x + (size / 2) <= c && c < x + (size)) && (y <= r && r < y + (size / 2))) {
            move(y, x + (size / 2), n - 1);
        } else {
            count += (int) Math.pow(2, 2 * n - 2);
        }

        // 좌하단
        if ((x <= c && c < x + (size / 2)) && (y + (size / 2) <= r && r < y + (size))) {
            move(y + (size / 2), x, n - 1);
        } else {
            count += (int) Math.pow(2, 2 * n - 2);
        }

        // 우하단
        if ((x + (size / 2) <= c && c < x + (size)) && (y + (size / 2) <= r && r < y + (size))) {
            move(y + (size / 2), x + (size / 2), n - 1);
        } else {
            count += (int) Math.pow(2, 2 * n - 2);
        }
    }

}
