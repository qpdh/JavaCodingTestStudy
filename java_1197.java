import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_1197 {
    static int V;
    static int E;
    static Vector<Pair<Integer, Pair<Integer, Integer>>> edges;

    static class DisjointSet {
        Vector<Integer> parents;
        Vector<Integer> rank;

        public DisjointSet(int n) {
            parents = new Vector<>();
            rank = new Vector<>();

            for (int i = 0; i < n; i++) {
                parents.add(i);
                rank.add(i);
            }
        }

        public int find(int u) {
            if (u == parents.get(u)) {
                return u;
            }

            int ret = find(parents.get(u));
            parents.set(u, ret);
            return ret;
        }

        public void merge(int u, int v) {
            u = find(u);
            v = find(v);

            if (u == v)
                return;

            if (u > v) {
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

    static class Pair<A, B> {
        A first;
        B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    public static long kruskal() {
        long ret = 0;

        edges.sort(new Comparator<Pair<Integer,Pair<Integer,Integer>>>(){

            @Override
            public int compare(Pair<Integer, Pair<Integer, Integer>> o1,
                    Pair<Integer, Pair<Integer, Integer>> o2) {
                // TODO Auto-generated method stub
                return o1.first-o2.first;
            }
            
        });

        DisjointSet sets = new DisjointSet(V);

        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).first;
            int u = edges.get(i).second.first;
            int v = edges.get(i).second.second;

            if (sets.find(u) == sets.find(v)) {
                continue;
            }

            sets.merge(u, v);

            ret += cost;
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // V : 정점의 개수
        V = Integer.parseInt(st.nextToken());

        // E : 간선의 개수
        E = Integer.parseInt(st.nextToken());

        edges = new Vector<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Pair<Integer, Pair<Integer, Integer>>(cost, new Pair<Integer, Integer>(from, to)));
            edges.add(new Pair<Integer, Pair<Integer, Integer>>(cost, new Pair<Integer, Integer>(to, from)));
        }

        long ret = kruskal();

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
