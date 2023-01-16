import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class java_13023 {

    // 인접 행렬
    static List<List<Integer>> adj;
    // 노드의 수, 간선의 수
    static int N, M;

    // 최대 거리
    static int maxDegree;

    // 중복 방문처리
    static boolean[] isVisited;

    // 깊이 우선 탐색
    static void dfs(int index, int degree) {
        // 거리가 4라면
        // A-B-C-D-E 형태가 만들어진다. -> 종료
        if (degree == 4) {
            maxDegree = 4;
            return;
        }

        List<Integer> thisNode = adj.get(index);

        for (int nextNode : thisNode) {
            if (!isVisited[nextNode]) {
                isVisited[nextNode] = true;
                dfs(nextNode, degree + 1);
                // 다른 방향으로 가면 최대가 될 수 있으므로 false처리 후 추후 재방문
                isVisited[nextNode] = false;
            }
        }

    }

    // A-B-C-D-E 형태의 친구 관계가 존재하는지 판단하는 메소드
    static boolean solution() {
        // 모든 노드를 돌면서 최대 거리가 4 이상이 되는지 체크
        for (int i = 0; i < N; i++) {
            isVisited = new boolean[N];
            isVisited[i] = true;
            dfs(i, 0);

            // 길이가 4 이상인가?
            if (4 <= maxDegree) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // degree 가 4인 친구관계가 존재하는가?
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 노드 삽입
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // 간선 삽입
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        boolean result = solution();

        if (result) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

        br.close();
    }
}
