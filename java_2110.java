import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_2110 {
    static int N, C;
    static int houses[];

    // mid 크기로 만들 수 있는가?
    static boolean check(int mid) {
        boolean result = false;

        //
        int prePos = houses[0];
        int count = 1;
        for (int i = 1; i < N; i++) {
            if (houses[i] - prePos >= mid) {
                prePos = houses[i];
                count++;
                //
                if (count == C) {
                    return true;
                }
            }
        }

        return result;
    }

    static int solution() {

        Arrays.sort(houses);

        int low = 1;
        int high = houses[N - 1] - houses[0];

        while (low <= high) {
            int mid = (low + high) / 2;

            // mid로 만들 수 있는가? -> 더 넓은 크기로 만들어보자
            if (check(mid)) {
                low = mid+1;
                continue;
            }

            // -> 더 좁은 크기로 만들어보자
            high = mid-1;
        }

        return high;
    }

    public static void main(String[] args) throws IOException {
        // 집에 공유기 C개 설치
        // 한 집에 공유기 하나만 설치 가능
        // 두 공유기 사이의 거리를 가능한 크게
        // 1 2 4 8 9

        // 범위 = 가장 작은 x값, 그 다음 작은 x값, 가장 큰 x값
        // 해당 거리가 최소가 되도록 설치가 가능한가?

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        int result = solution();

        System.out.println(result);

        br.close();

    }
}
