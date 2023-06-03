import java.util.Arrays;

public class 에디터_전쟁 {
    static int N;

    class DisjointSet {
        int parents[];
        int rank[];
        // enemy[i] i 가 루트인 경우, 해당 집합과 적대 관계인 집합의 루트의 번호
        int enemy[];
        // size[i] i 가 루트인 경우, 해당 집합의 크기
        int size[];

        public DisjointSet(int n) {
            parents = new int[n];
            rank = new int[n];
            enemy = new int[n];
            size = new int[n];
            Arrays.fill(size, -1);
            Arrays.fill(enemy, -1);
            Arrays.fill(parents, -1);
        }

        public int getParent(int n) {
            if (parents[n] == -1) {
                return parents[n];
            }
            parents[n] = getParent(parents[n]);
            return parents[n];
        }

        public int merge(int a, int b) {
            if (a == -1 || b == -1) {
                return Math.max(a, b);
            }

            a = getParent(a);
            b = getParent(b);

            if (a == b) {
                return a;
            }

            if (rank[a] > rank[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            parents[a] = b;
            if (rank[a] == rank[b]) {
                rank[b]++;
            }
            size[b] += size[a];

            return b;
        }

        // 서로 적대 집합인지 확인
        public boolean dis(int a, int b) {
            a = getParent(a);
            b = getParent(b);

            if (a == b) {
                return false;
            }

            int u = merge(a, enemy[b]);
            int v = merge(b, enemy[b]);

            enemy[u] = v;
            enemy[v] = u;

            return true;
        }

        // 서로 적대 같은인지 확인
        public boolean ack(int a, int b) {
            a = getParent(a);
            b = getParent(b);

            if (enemy[a] == b) {
                return false;
            }

            int u = merge(a, enemy[b]);
            int v = merge(b, enemy[b]);

            enemy[u] = v;

            if (b != -1) {
                enemy[v] = u;
            }

            return true;
        }

    }

    static int calcMax(DisjointSet disjointSet) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            if (disjointSet.parents[i] == i) {
                int enemy = disjointSet.enemy[i];

                if (enemy > i)
                    continue;

                int mySize = disjointSet.size[i];
                int enemySize = enemy == -1 ? 0 : disjointSet.size[enemy];

                result += Math.max(mySize, enemySize);
            }
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
