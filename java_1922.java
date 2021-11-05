import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_1922 {
    static int N, M;
    static Vector<Pair<Integer, Pair<Integer, Integer>>> edges = new Vector<>();

    static class DisjointSet {
        Vector<Integer> set;
        Vector<Integer> rank;

        public DisjointSet(int n) {
            set = new Vector<>();
            rank = new Vector<>();

            for (int i = 0; i < n; i++) {
                set.add(i);
                rank.add(1);
            }
        }

        public int find(int n) {
            if (set.get(n) == n) {
                return n;
            }

            int ret = find(set.get(n));
            set.set(n, ret);
            return ret;
        }

        public void merge(int u, int v) {
            u = find(u);
            v = find(v);

            if (u == v) {
                return;
            }

            if (u > v) {
                int tmp = v;
                v = u;
                u = tmp;
            }

            set.set(u, v);

            if (rank.get(u) == rank.get(v)) {
                rank.set(v, rank.get(v) + 1);
            }
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

    // 인접 리스트 <from <to, cost>>
    // static Vector<Vector<Pair<Integer, Integer>>> adj;

    public static int kruskal() {
        // 최소 비용 저장
        int ret = 0;

        // // 비용 <from, to> 쌍 저장
        // Vector<Pair<Integer, Pair<Integer, Integer>>> edges = new Vector<>();

        // for (int u = 0; u < adj.size(); u++) {
        // for (int i = 0; i < adj.get(u).size(); i++) {
        // int v = adj.get(u).get(i).first, cost = adj.get(u).get(i).second;
        // edges.add(new Pair(cost, new Pair(u, v)));
        // }
        // }

        // 가중치 순으로 정렬
        edges.sort(new Comparator<Pair<Integer, Pair<Integer, Integer>>>() {

            @Override
            public int compare(Pair<Integer, Pair<Integer, Integer>> o1, Pair<Integer, Pair<Integer, Integer>> o2) {
                // TODO Auto-generated method stub
                return o1.first - o2.first;
            }

        });

        DisjointSet sets = new DisjointSet(N);

        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).first;
            int u = edges.get(i).second.first, v = edges.get(i).second.second;

            if (sets.find(u) == sets.find(v))
                continue;

            sets.merge(u, v);
            ret += cost;
        }

        return ret;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // adj = new Vector<>();

        // n : 컴퓨터의 수
        N = Integer.parseInt(br.readLine());

        // for (int i = 0; i < N; i++) {
        // adj.add(new Vector<>());
        // }

        // 연결할 수 있는 선의 수
        M = Integer.parseInt(br.readLine());

        edges = new Vector<>();

        // 컴퓨터를 연결하는 데 드는 비용
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            // adj.get(from).add(new Pair(to, cost));
            // adj.get(to).add(new Pair(from, cost));
            edges.add(new Pair<Integer, Pair<Integer, Integer>>(cost, new Pair<Integer, Integer>(from, to)));
            edges.add(new Pair<Integer, Pair<Integer, Integer>>(cost, new Pair<Integer, Integer>(to, from)));

        }

        bw.write(kruskal() + "\n");

        br.close();
        bw.close();
    }
}
