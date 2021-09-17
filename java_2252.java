import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_2252 {
    static Vector<Vector<Integer>> adj;
    //
    static boolean[] visited;

    // 결과를 뒤집어 주기 위한 결과 저장 벡터
    static Vector<Integer> result = new Vector<>();

    // 깊이 우선 탐색의 구현
    public static void dfs(int here) {
        // System.out.println(here + ": 방문");
        visited[here] = true;

        for (int i = 0; i < adj.get(here).size(); i++) {
            int there = adj.get(here).get(i);
            // 아직 방문한 적이 없다면 방문한다
            if (!visited[there]) {
                dfs(there);
            }
        }

        // dfs()가 종료할 때 마다 현재 정점의 번호를 기록
        result.add(here + 1);
    }

    // 모든 정점을 방문한다
    public static void dfsAll() {

        // 모든 정점을 순회하면서, 아직 방문한 적 없으면 방문한다.
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 학생의 수
        int N = Integer.parseInt(st.nextToken());

        adj = new Vector<>();
        for (int i = 0; i < N; i++) {
            adj.add(new Vector<>());
        }
        visited = new boolean[N];

        // 키를 비교한 횟수
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            adj.get(from).add(to);
        }

        dfsAll();

        // 결과를 뒤집은 값을 출력
        for (int i = result.size() - 1; i >= 0; i--) {
            bw.write(result.get(i) + " ");
        }

        br.close();
        bw.close();

    }
}
