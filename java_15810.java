import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class java_15810 {
    // N : 스태프의 수
    // M : 풍선의 개수
    static int N, M;

    static List<Integer> staffList;

    // time 내로 목표 풍선을 제작할 수 있는가?
    static boolean canMake(long time) {
        long balloonCount = 0;

        for (int staff : staffList) {
            balloonCount += time / staff;
        }

        return balloonCount >= M;
    }

    static long binarySearch() {

        long low = 1;
        long high = (long)staffList.get(0) * M;

        while (low <= high) {
            long mid = (low + high) / 2;

            // mid 시간에 만들 수 있는가?
            boolean result = canMake(mid);

            if (result) {
                high = mid - 1;
                continue;
            }

            // 만들 수 없다면 -> 시간을 늘려보기
            low = mid + 1;
        }

        return low;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // M개를 만들기 위해 최소 시간
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        staffList = new ArrayList<>();

        // 데이터 삽입
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            staffList.add(Integer.parseInt(st.nextToken()));
        }

        // 오름차순 정렬
        Collections.sort(staffList);

        // M개의 풍선 만들기의 최소시간
        long result = binarySearch();

        System.out.println(result);

        br.close();
    }
}
