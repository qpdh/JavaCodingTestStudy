import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_13265 {

    final static int RED = 1;
    final static int BLUE = 2;

    public static boolean bfs(ArrayList<ArrayList<Integer>> adj, int start, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        color[start] = 1;
        queue.add(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            int nowColor;
            if (color[now] == 1) {
                nowColor = 2;
            }
            // color[now] == 2
            else {
                nowColor = 1;
            }

            for (int i = 0; i < adj.get(now).size(); i++) {
                int to = adj.get(now).get(i);

                if (color[to] == 0) {
                    color[to] = nowColor;
                    queue.add(to);
                }

                // 인접한 노드가 같은 색으로 이미 칠해져 있다면...
                else if (color[to] == color[now]) {
                    return false;
                }
            }

        }

        return true;
    }

    public static boolean solve(ArrayList<ArrayList<Integer>> adj) {
        // 0 : notVisited
        // 1 : RED
        // 2 : BLUE
        int color[] = new int[adj.size()];

        for (int i = 0; i < adj.size(); i++) {
            if (color[i] == 0) {
                if (!bfs(adj, i, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            // n : 동그라미 개수
            int n = Integer.parseInt(st.nextToken());
            // m : 직선들의 개수
            int m = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            // 직선의 수 만 큼 데이터 할당
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;

                adj.get(from).add(to);
                adj.get(to).add(from);

            }

            // 함수 호출
            boolean result = solve(adj);

            // 가능하다면
            if (result) {
                bw.write("possible\n");
            } else {
                bw.write("impossible\n");
            }
        }

        br.close();
        bw.close();
    }
}
