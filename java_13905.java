import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_13905 {
    final static int INF = 987654321;

    static Vector<Pair<Integer, Pair<Integer, Integer>>> edges;

    static class Pair<A, B> {
        A first;
        B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    static class DisjointSet {
        Vector<Integer> parents;
        Vector<Integer> rank;

        public DisjointSet(int n) {
            parents = new Vector<>();
            rank = new Vector<>();

            for (int i = 0; i < n; i++) {
                parents.add(i);
                rank.add(0);
            }
        }

        public int find(int u) {
            if (parents.get(u) == u) {
                return u;
            }

            int ret = find(parents.get(u));

            parents.set(u, ret);
            return ret;
        }

        public void merge(int u, int v) {

            u = find(u);
            v = find(v);

            if (u == v) {
                return;
            }

            if (rank.get(u) > rank.get(v)) {
                int tmp = v;
                v = u;
                u = tmp;
            }

            parents.set(u, v);
            if (rank.get(u) == rank.get(v)) {
                rank.set(v, rank.get(v) + 1);
            }
        }

    }

    public static int kruskal(int n, int start, int end) {
        int retCost = INF;

        // 내림차순 정렬
        edges.sort(new Comparator<Pair<Integer, Pair<Integer, Integer>>>() {

            @Override
            public int compare(Pair<Integer, Pair<Integer, Integer>> o1,
                    Pair<Integer, Pair<Integer, Integer>> o2) {
                return o2.first - o1.first;
            }
        });

        DisjointSet sets = new DisjointSet(n);

        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).first;
            int from = edges.get(i).second.first;
            int to = edges.get(i).second.second;

            if (sets.find(from) == sets.find(to)) {
                continue;
            }

            sets.merge(from, to);

            retCost = Math.min(retCost, cost);

            if (sets.find(start) == sets.find(end)) {
                return retCost;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        edges = new Vector<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N : 집의 수
        int N = Integer.parseInt(st.nextToken());
        // M : 다리의 수
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // S : 숭이의 출발 위치
        int S = Integer.parseInt(st.nextToken()) - 1;
        // E : 혜빈의 위치
        int E = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Pair<Integer, Pair<Integer, Integer>>(cost, new Pair<>(from, to)));
            edges.add(new Pair<Integer, Pair<Integer, Integer>>(cost, new Pair<>(to, from)));
        }

        long tmp = kruskal(N, S, E);

        if (tmp == -1) {
            bw.write("0\n");
        } else {
            bw.write(tmp + "\n");
        }

        br.close();
        bw.close();
    }
}
