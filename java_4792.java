import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_4792 {
    // n : 정점의 개수
    // m : 간선의 개수
    // k : 파란색 간선의 개수
    static int n, m, k;

    static Vector<Pair<Integer, Integer>> redEdges;
    static Vector<Pair<Integer, Integer>> blueEdges;

    // 파란색을 뽑는 인덱스 저장
    static Vector<Vector<Integer>> selectedBlueEdges;

    static class Pair<A, B> {
        A first;
        B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

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
                int tmp = u;
                u = v;
                v = tmp;
            }

            parents.set(u, v);

            if (rank.get(u) == rank.get(v)) {
                rank.set(v, rank.get(v) + 1);
            }
        }
    }

    public static boolean kruskal(int blueEdgeIndex) {
        boolean ret = true;

        DisjointSet sets = new DisjointSet(n);

        for (int i = 0; i < k; i++) {
            int u = blueEdges.get(selectedBlueEdges.get(blueEdgeIndex).get(i)).first;
            int v = blueEdges.get(selectedBlueEdges.get(blueEdgeIndex).get(i)).second;

            // k개로 사이클이 되면 안됨!
            // 바로 불가능 처리
            if (sets.find(u) == sets.find(v)) {
                return false;
            }

            sets.merge(u, v);
        }

        // 붉은 간선으로 스패닝 트리 만들기
        for (int i = 0; i < redEdges.size(); i++) {
            int u = redEdges.get(i).first;
            int v = redEdges.get(i).second;

            if (sets.find(u) == sets.find(v)) {
                continue;
            }

            sets.merge(u, v);
        }

        // 스패닝 트리가 완성 되었는지 확인하기
        // 오류 있을 것 같음 확인해야함
        for (int i = 1; i < n; i++) {
            if (sets.find(i) != sets.find(i - 1)) {
                return false;
            }
        }

        // 1. 파란색 간선 k개를 선택해보기
        // 파란색 선택 시 사이클이 되지 않도록 하기..
        // 이후 빨간색 만으로 스패닝 트리 만들기
        // 스패닝 트리가 완성되었는지 어떻게 확인?
        // parent set 의 값이 하나로 통일..

        // 2. 각 간선을 선택할 지 안할지.. 2^n
        // 스패닝 트리 만들기...
        // 파란색 간선의 수 찾기...

        return ret;

    }

    // 파란색 간선 k개를 뽑는 알고리즘
    public static void comb(boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            // 선택된 파란 간선 저장 벡터
            Vector<Integer> tmpVector = new Vector<>();

            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    tmpVector.add(i);
                }
            }

            selectedBlueEdges.add(tmpVector);
            return;
        }

        if (depth == n) {
            return;
        }

        visited[depth] = true;
        comb(visited, depth + 1, n, r - 1);

        visited[depth] = false;
        comb(visited, depth + 1, n, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            blueEdges = new Vector<>();
            redEdges = new Vector<>();
            selectedBlueEdges = new Vector<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 정점의 개수
            n = Integer.parseInt(st.nextToken());

            // 0 0 0 입력 시 종료
            if (n == 0) {
                break;
            }

            // 간선의 개수
            m = Integer.parseInt(st.nextToken());

            // 파란색 간선의 개수
            k = Integer.parseInt(st.nextToken());

            // 데이터 삽입
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                char color = st.nextToken().charAt(0);

                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;

                // 빨간색 간선일 때
                if (color == 'R') {
                    redEdges.add(new Pair<Integer, Integer>(from, to));
                }

                // 파란색 간선일 때
                else if (color == 'B') {
                    blueEdges.add(new Pair<Integer, Integer>(from, to));
                }
                // 잘못된 입력
                else {

                }
            }

            // 파란 간선 조합 찾기
            boolean[] visited = new boolean[n];
            comb(visited, 0, blueEdges.size(), k);

            boolean canMake = false;

            // 각 파란 간선을 선택했을 때 만들 수 있는지 체크
            for (int i = 0; i < selectedBlueEdges.size(); i++) {
                if (kruskal(i)) {
                    canMake = true;
                    break;
                }
            }

            if (canMake) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        br.close();
        bw.close();
    }
}
