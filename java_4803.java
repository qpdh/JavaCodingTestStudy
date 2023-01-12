import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class java_4803 {
    static int n;
    static DisjointSet disjointSet;
    // 사이클인가? 아닌가?
    static boolean cycle[];

    static class DisjointSet {
        int parent[];

        public DisjointSet() {
            parent = new int[n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public int getParent(int index) {
            if (index == parent[index]) {
                return index;
            }

            parent[index] = getParent(parent[index]);

            return parent[index];
        }

        // 사이클이면 True
        // 정상이면 False
        public void merge(int u, int v) {
            u = getParent(u);
            v = getParent(v);

            if (cycle[u] || cycle[v]) {
                cycle[u] = true;
                cycle[v] = true;
            }

            if (u == v) {
                cycle[u] = true;
                return;
            }

            parent[v] = u;

            return;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



        /*
         * 그래프에서 어떻게 트리를 추출하는가?
         * 단순하게 트리인지 아닌지를 어떻게 아는가?
         * 1. 사이클이 없어야 한다.
         * 2. 정점이 n개 간선이 n-1개 있어야 한다.
         * 3. 두 정점 사이의 경로가 유일하다.
         * 4. 스패닝 트리
         *
         * 스패닝 트리 -> 크루스칼 알고리즘
         * DisjointSet -> 상호 배제 집합
         *
         */

        // 루프가 있는지 탐색할 것
        //
        int testCase = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            disjointSet = new DisjointSet();
            cycle = new boolean[n];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;

                disjointSet.merge(from, to);
            }

            Set<Integer> resultSet = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int node = disjointSet.getParent(i);
                // 사이클
                if (cycle[node]) {
                    continue;
                }
                resultSet.add(node);
            }

            int result = resultSet.size();

            if (result == 0) {
                System.out.printf("Case %d: No trees.\n", testCase++);
                continue;
            }

            if (result == 1) {
                System.out.printf("Case %d: There is one tree.\n", testCase++);
                continue;
            }

            System.out.printf("Case %d: A forest of %d trees.\n", testCase++, result);

        }

        br.close();
    }
}
