import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_4195 {
    // 테스트 케이스
    static int T;
    // 친구 관계의 수
    static int F;
    // <이름,인덱스>
    static HashMap<String, Integer> friendMap;

    static class DisjointSet {
        // parent : 각 노드의 부모 위치
        Vector<Integer> parent;
        // rank : 각 노드의 높이
        Vector<Integer> rank;

        public DisjointSet(int n) {
            parent = new Vector<>();
            rank = new Vector<>();

            // 노드 수 만큼 반복
            for (int i = 0; i < n; i++) {
                // 처음은 자기 자신이 부모
                parent.add(i);
                // 높이는 1로 초기화
                rank.add(1);
            }
        }

        // u가 속한 트리의 루트 번호를 반환한다.
        public int find(int u) {
            // 루트 번호면 자기 자신이므로 끝남.
            if (u == parent.get(u)) {
                return u;
            }

            // 루트 번호 갱신
            int ret = find(parent.get(u));
            parent.set(u, ret);

            // 랭크 갱신
            rank.set(u, rank.get(ret));

            return ret;
        }

        // u가 속한 트리와 v가 속한 트리를 합친다.
        public void merge(int u, int v) {
            // u, v의 각각 루트 노드 구하기
            u = find(u);
            v = find(v);

            // u, v가 같은 트리에 속한다면...
            // 바로 종료
            if (u == v) {
                return;
            }

            // 친구 네트워크에서 연결된 친구 수를 구해야 하므로
            // 두 랭크를 더하자
            // v+u 를 하자
            if (rank.get(u) > rank.get(v)) {
                int tmp = u;
                u = v;
                v = tmp;
            }

            // System.out.println("first Rank : " + rank.get(u));
            // System.out.println("second Rank : " + rank.get(v));
            int tmpRank = rank.get(u) + rank.get(v);

            // u의 루트를 v루트 뒤에 붙인다.
            parent.set(u, v);

            rank.set(v, tmpRank);
            rank.set(u, tmpRank);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            // 친구 관계의 수
            F = Integer.parseInt(br.readLine());

            // frient 인덱스 초기화
            int friendIndex = 0;
            // 프렌드 맵 초기화
            friendMap = new HashMap<>();
            DisjointSet disjointSet = new DisjointSet(F * 2);

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String firstFriend = st.nextToken();
                String secondFriend = st.nextToken();

                // 존재하지 않으면 일단 삽입하기
                if (friendMap.get(firstFriend) == null) {
                    friendMap.put(firstFriend, friendIndex++);
                }
                if (friendMap.get(secondFriend) == null) {
                    friendMap.put(secondFriend, friendIndex++);
                }

                disjointSet.merge(friendMap.get(firstFriend), friendMap.get(secondFriend));

                bw.write(disjointSet.rank.get(disjointSet.find(friendMap.get(firstFriend))) + "\n");

            }

        }

        br.close();
        bw.close();
    }
}
