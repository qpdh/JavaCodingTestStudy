import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_15681 {
    // 정점의 수 N
    // 루트의 번호 R
    // 쿼리의 수 Q
    static int N, R, Q;

    // 해당 정점을 루트로 하는 서브트리에 속한 정점의 수
    static int cache[];

    static int parent[];
    static boolean isVisited[];
    static ArrayList<ArrayList<Integer>> adj;

    public static void DFS(int thisNode, int parentNode) {
        for (int i = 0; i < adj.get(thisNode).size(); i++) {
            int targetNode = adj.get(thisNode).get(i);

            if (!isVisited[targetNode]) {
                if (parent[targetNode] == -1) {
                    parent[targetNode] = thisNode;
                }
                isVisited[targetNode] = true;
                DFS(targetNode, thisNode);
            }
        }
    }

    public static int DP(int thisNode) {
        if (cache[thisNode] != -1) {
            return cache[thisNode];
        }

        cache[thisNode] = 1;

        // 각 자식들의 합
        for (int i = 0; i < adj.get(thisNode).size(); i++) {
            int targetNode = adj.get(thisNode).get(i);
            // 부모 노드가 아닌 노드를 선택해야 함
            if (targetNode != parent[thisNode]) {
                cache[thisNode] += DP(targetNode);
            }
        }

        return cache[thisNode];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()) - 1;
        Q = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // N-1줄
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;

            adj.get(first).add(second);
            adj.get(second).add(first);
        }

        // R 부터 DFS돌리자
        isVisited = new boolean[N];
        parent = new int[N];
        Arrays.fill(parent, -1);
        parent[R] = R;
        DFS(R, R);

        cache = new int[N];
        Arrays.fill(cache, -1);
        DP(R);

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine()) - 1;
            System.out.println(cache[query]);
        }

        br.close();
    }
}
