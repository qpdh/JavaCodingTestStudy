import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1654 {
    static long[] lineList;
    static int N;

    // 나눠서 N 이상이 되는지 파악하기
    static boolean check(long mid) {
        // 나눠서 N 이상이 되는지 파악하기
        int result = 0;
        for (int i = 0; i < lineList.length; i++) {
            result += lineList[i] / mid;
        }

        if (N <= result) {
            return true;
        } else {
            return false;
        }
    }

    static long binarySearch(long low, long high) {

        while (low + 1 < high) {
            long mid = (low + high) / 2;

            // 만약 N개 이상 만들 수 있다면...
            if (check(mid)) {
                // 더 길게 잘라보기
                low = mid;
            }
            // N개 미만으로 만들어 진다면..
            else {
                high = mid;
            }
        }

        if (check(low + 1)) {
            low++;
        }

        return low;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 오영식이 가지고 있는 랜선의 개수
        int K = Integer.parseInt(st.nextToken());
        // 필요한 랜선의 개수
        N = Integer.parseInt(st.nextToken());

        long maxLength = 0;
        lineList = new long[K];
        for (int i = 0; i < K; i++) {
            lineList[i] = Long.parseLong(br.readLine());

            if (maxLength < lineList[i]) {
                maxLength = lineList[i];
            }
        }

        // i 로 나누었을 때 몫의 합 <= N 이 되어야 함
        // 이분 탐색?
        // N/2 로 나눠보고 ...

        long result = binarySearch(0, maxLength);

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}
