import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class java_12896 {
    static int N;

    static List<List<Integer>> adj;
    static boolean[] isVisited;

    static int maxDegree;
    static int maxNode;

    static void dfs(int index, int degree) {
        List<Integer> thisNode = adj.get(index);

        if (thisNode.size() == 1) {
            if (maxDegree < degree) {
                maxDegree = degree;
                maxNode = index;
            }
        }

        for (int nextNode : thisNode) {
            if (!isVisited[nextNode]) {
                isVisited[nextNode] = true;
                dfs(nextNode, degree + 1);
            }
        }

    }

    static int solution() {
        // 0에서 가장 먼 거리
        isVisited = new boolean[N];
        isVisited[0] = true;
        dfs(0, 0);

        // maxNode에서 가장 먼 거리
        maxDegree = 0;
        isVisited = new boolean[N];
        isVisited[maxNode] = true;
        dfs(maxNode, 0);

        int result = (int) Math.ceil(maxDegree / 2.0);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        int result = solution();

        System.out.println(result);

        br.close();

    }
}
