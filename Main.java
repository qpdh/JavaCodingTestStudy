import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
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

        int result = binarySearch();

        br.close();
    }

    private static int binarySearch() {
        // [1,10억]
        int low = 1;
        int high = 1_000_000_000;

        while (low <= high) {
            // mid 값 계산
            int mid = (low + high) / 2;

            // 판별
            if (canMake(mid)) {
                low = mid + 1;
                continue;
            }

            // low or high 값 수정
            high = mid - 1;
        }

        return high;
    }

    private static boolean canMake(int mid) {
        // 제거해야하는 돌 섬의 수
        int stoneCount = 0;

        // 직전 섬의 거리
        int distance = 0;

        // 0 11 18 25
        // mid 4
        // 0 11 18 25

        for (int i = 1; i < stoneIsland.size(); i++) {
            // 최소 거리를 만족한다.
            if (stoneIsland.get(i) - distance >= mid) {
                distance = stoneIsland.get(i);
                continue;
            }

            // mid 거리가 되지 않는다...
            stoneCount++;
        }

        return stoneCount <= m;
    }
}
