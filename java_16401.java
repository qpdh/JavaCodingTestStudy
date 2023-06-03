import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class java_16401 {

    // M : 조카의 수
    // N : 과자의 수
    static int M, N;

    static List<Integer> snackList;

    public static void main(String[] args) throws IOException {
        // 모든 조카에게 같은 길이의 막대 과자를 나눠주자

        // M명의 조카
        // N개의 과자
        // 조카 1명에게 줄 수 있는 막대 과자의 최대 길이

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        snackList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snackList.add(Integer.parseInt(st.nextToken()));
        }

        // 정렬
        Collections.sort(snackList);

        // 계산
        int result = binarySearch();

        System.out.println(result);

        br.close();
    }

    private static int binarySearch() {
        int low = 0;
        int high = snackList.get(snackList.size() - 1);

        while (low <= high) {
            int mid = (low + high) / 2;

            // mid 크기로 모든 조카에게 나눠줄 수 있는지 체크
            if (canGive(mid)) {
                low = mid + 1;
                continue;
            }

            high = mid - 1;
        }

        return high;
    }

    private static boolean canGive(int mid) {
        if (mid == 0) {
            return true;
        }
        // 뒤에서부터 해보자
        // mid 크기로 만들 수 있는지 체크

        int snackCount = 0;
        for (int i = snackList.size() - 1; i >= 0; i--) {
            snackCount += snackList.get(i) / mid;
        }

        return snackCount >= M;
    }
}
