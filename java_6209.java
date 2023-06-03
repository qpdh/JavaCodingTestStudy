import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class java_6209 {
    // d : 돌섬으로부터 탈출구 까지 거리
    // n : 돌섬의 수
    // m : 제거할 수 있는 돌섬의 수
    static int d, n, m;

    static List<Integer> stoneIsland;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        stoneIsland = new ArrayList<>();

        stoneIsland.add(0);
        stoneIsland.add(d);

        for (int i = 0; i < n; i++) {
            stoneIsland.add(Integer.parseInt(br.readLine()));
        }

        // 입력 값은 돌섬->작은 돌섬 까지 거리
        // 원하는 값은 작은 돌섬->작은 돌섬 까지 거리를 찾아 m개 지우기

        // 일단 오름차순 정렬
        Collections.sort(stoneIsland);

        //

        // 0 2 11 14 17 20 25
        // 2-0 = 2 // 1번 인덱스 지우기
        // 11-2 = 9 // 2번 인덱스 지우기
        // 14-11 = 3
        // 17-14 = 3
        // 21-17 = 3
        // 25-21 = 4 // 6번 인덱스 지우기

        // 0 11 14 17 20 25
        // 11-0 = 11
        // 14-11 = 3
        // 17-14 = 3
        // 21-17 = 3
        // 25-21 = 4

        // 0 11 17 20 25
        // 11-0 = 11
        // 17-11 = 6
        // 21-17 = 3
        // 25-21 = 4

        // 최솟값 찾아서 더해주기 복잡도 O(N*M)

        // 거리의 최솟값이 k가 되도록 만들 수 있는가?
        int result = binarySearch();

        System.out.println(result);

        br.close();

    }

    private static int binarySearch() {
        int low = 1;
        int high = 1_000_000_000;

        while (low <= high) {
            int mid = (low + high) / 2;

            // m개를 제거하여 적어도 mid보다 크게 점프할 수 있다면..
            if (canJump(mid)) {
                low = mid + 1;
                continue;
            }

            high = mid - 1;
        }

        return high;
    }

    // mid 이상으로 거리가 되는지 체크
    private static boolean canJump(int mid) {
        // 제거해야 하는 최소 돌섬의 수
        int stoneCount = 0;

        // 직전 섬으로부터 거리
        int nowDistance = 0;

        for (int i = 1; i < stoneIsland.size(); i++) {
            // mid 거리 이상으로 된다면...
            if (stoneIsland.get(i) - nowDistance >= mid) {
                nowDistance = stoneIsland.get(i);
                continue;
            }

            // 안되면...제거
            stoneCount++;
        }

        // m개 이하로 제거할 수 있으면 종료
        return stoneCount <= m;
    }
}
