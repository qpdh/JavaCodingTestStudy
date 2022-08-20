import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_4386 {
    static Vector<Pair<Double, Pair<Integer, Integer>>> edges;
    static Vector<Pair<Double, Double>> nodes;
    static int n;

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

        public DisjointSet(int n) {
            parents = new Vector<>();

            for (int i = 0; i < n; i++) {
                parents.add(i);
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

            if (u == v) {
                return;
            }

            if (u > v) {
                int tmp = v;
                v = u;
                u = tmp;
            }

            parents.set(u, v);
        }
    }

    public static double kruskal() {
        double ret = 0;

        edges.sort(new Comparator<Pair<Double, Pair<Integer, Integer>>>() {

            @Override
            public int compare(Pair<Double, Pair<Integer, Integer>> o1,
                    Pair<Double, Pair<Integer, Integer>> o2) {
                // TODO Auto-generated method stub
                if (o1.first <= o2.first) {
                    return -1;
                } else {
                    return 1;
                }
            }

        });

        DisjointSet sets = new DisjointSet(n);

        for (int i = 0; i < edges.size(); i++) {
            double cost = edges.get(i).first;
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

        n = Integer.parseInt(br.readLine());

        nodes = new Vector<>();
        edges = new Vector<>();

        // 노드 데이터 삽입
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            nodes.add(new Pair<Double, Double>(x, y));
        }

        // 거리 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 같은 노드끼리는 계산할 필요가 없음
                if (i == j) {
                    continue;
                }

                double distance = Math
                        .sqrt(Math.pow((nodes.get(i).first - nodes.get(j).first), 2)
                                + Math.pow((nodes.get(i).second - nodes.get(j).second), 2));

                edges.add(new Pair<Double, Pair<Integer, Integer>>(distance, new Pair<>(i, j)));
                edges.add(new Pair<Double, Pair<Integer, Integer>>(distance, new Pair<>(j, i)));
            }
        }

        double ret = kruskal();

        bw.write(String.format("%.2f", ret) + "\n");

        br.close();
        bw.close();
    }
}
