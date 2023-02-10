import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class java_1005 {
    static int T;
    static int N;
    static int K;
    static int W;

    static int buildingList[];

    static List<List<Integer>> tree;

    static int cache[];

    static int dp(int index) {
        if (tree.get(index).size() == 0) {
            return buildingList[index];
        }

        if (cache[index] != -1) {
            return cache[index];
        }

        cache[index] = 0;

        for (int i = 0; i < tree.get(index).size(); i++) {
            int there = tree.get(index).get(i);
            cache[index] = Math.max(cache[index], dp(there) + buildingList[index]);
        }

        return cache[index];
    }

    static int solve() {
        int result = 0;

        cache = new int[N];
        Arrays.fill(cache, -1);

        result = dp(W);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            buildingList = new int[N];
            tree = new ArrayList<>();

            // 건물 건설 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                tree.add(new ArrayList<>());
                buildingList[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken()) - 1; // x
                int from = Integer.parseInt(st.nextToken()) - 1; // y
                // x를 짓고 나서 y를 짓는다.
                // y를 짓기 위해 사전에 지어야 할 건물의 인덱스
                tree.get(from).add(to);
            }

            W = Integer.parseInt(br.readLine()) - 1;

            int result = solve();

            System.out.println(result);
        }

        br.close();
    }
}
