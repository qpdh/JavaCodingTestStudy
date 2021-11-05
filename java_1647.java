import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_1647 {

    // static Vector<Vector<Pair<Integer, Integer>>> adj;

    static Vector<Pair<Integer, Pair<Integer, Integer>>> edges;

    // 집의 개수 N
    static int N;
    // 길의 개수 M
    static int M;

    static class DisjointSet {
        Vector<Integer> parents, rank;

        public DisjointSet(int n) {
            parents = new Vector<>();
            rank = new Vector<>();
            for (int i = 0; i < n; i++) {
                parents.add(i);
                rank.add(1);
            }
        }

        public int find(int n) {
            if (n == parents.get(n)) {
                return n;
            }

            int ret = find(parents.get(n));
            parents.set(n, ret);
            return ret;
        }

        public void merge(int u, int v) {
            u = find(u);
            v = find(v);

            if (u == v)
                return;

            if (rank.get(u) > rank.get(v)) {
                int tmp = u;
                u = v;
                v = tmp;
            }

            parents.set(u, v);

            if (rank.get(u) == rank.get(v))
                rank.set(v, rank.get(v) + 1);
        }
    }

    static class Pair<A, B> {
        A first;
        B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    public static int kruskal() {
        int ret = 0;

        // Vector<Pair<Integer, Pair<Integer, Integer>>> edges = new Vector<>();
        // for (int u = 0; u < adj.size(); u++) {
        // for (int i = 0; i < adj.get(u).size(); i++) {
        // int v = adj.get(u).get(i).first;
        // int cost = adj.get(u).get(i).second;

        // edges.add(new Pair<Integer, Pair<Integer, Integer>>(cost, new Pair<Integer,
        // Integer>(u, v)));
        // }
        // }

        edges.sort(new Comparator<Pair<Integer, Pair<Integer, Integer>>>() {

            @Override
            public int compare(Pair<Integer, Pair<Integer, Integer>> o1, Pair<Integer, Pair<Integer, Integer>> o2) {
                // TODO Auto-generated method stub
                return o1.first - o2.first;
            }

        });

        DisjointSet sets = new DisjointSet(N);

        int maxCost = 0;

        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).first;
            int u = edges.get(i).second.first;
            int v = edges.get(i).second.second;

            if (sets.find(u) == sets.find(v))
                continue;

            sets.merge(u, v);

            if (maxCost < cost) {
                maxCost = cost;
            }
            ret += cost;
        }

        return ret - maxCost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 집의 개수 N
        N = Integer.parseInt(st.nextToken());
        // 길의 개수 M
        M = Integer.parseInt(st.nextToken());

        // 인접리스트 초기화
        edges = new Vector<>();

        // adj = new Vector<Vector<Pair<Integer, Integer>>>(100_000);
        // for (int i = 0; i < N; i++) {
        // adj.add(new Vector<Pair<Integer, Integer>>(1_000_000));
        // }

        // 데이터 삽입
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Pair<Integer, Pair<Integer, Integer>>(cost, new Pair<Integer, Integer>(from, to)));
            edges.add(new Pair<Integer, Pair<Integer, Integer>>(cost, new Pair<Integer, Integer>(to, from)));
            // adj.get(from).add(new Pair<Integer, Integer>(to, cost));
            // adj.get(to).add(new Pair<Integer, Integer>(from, cost));
        }

        int minCost = kruskal();

        bw.write(minCost + "\n");

        br.close();
        bw.close();
    }
}
