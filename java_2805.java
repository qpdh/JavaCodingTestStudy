import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_2805 {
    static int treeList[];
    // N : 나무의 수
    static int N;
    // M : 가져가려고 하는 나무의 길이
    static int M;

    static int result;

    static int binarySearch(int low, int high) {

        // 중앙값 : 자르는 높이 기준 (이 높이면 잘림)
        int mid = (low + high) / 2;

        long result = 0;
        // 돌면서 자를 때 얻는 값 찾기
        for (int i = 0; i < treeList.length; i++) {
            if (mid < treeList[i]) {
                result += treeList[i] - mid;
            }
        }
        // System.out.println("result : " + result);
        if (high <= low) {
            if (M <= result) {
                return mid;
            } else {
                return 0;
            }
        }
        // M미터 보다 많은 나무를 가져갈 수 있다.
        if (M <= result) {
            // 중앙을 넘어가면 끝
            return Math.max(binarySearch(mid + 1, high), mid);

        } else {
            return binarySearch(low, mid);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 나무의 수 입력 받기
        N = Integer.parseInt(st.nextToken());
        // 나무의 길이 입력 받기
        M = Integer.parseInt(st.nextToken());

        // 이분 탐색으로 실시할 것
        // 최대 자를 수 있는 높이 : 입력받은 나무 중 가장 큰 것이 길이
        // 최소 자를 수 있는 높이 : 1

        // 나무를 잘랐을 때 가져가는 나무의 길이 구하기

        treeList = new int[N];

        int maxLength = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            treeList[i] = Integer.parseInt(st.nextToken());
            if (maxLength < treeList[i]) {
                maxLength = treeList[i];
            }
        }

        // 이분탐색 시작
        System.out.println(binarySearch(1, maxLength - 1));

        br.close();
        bw.close();
    }
}
