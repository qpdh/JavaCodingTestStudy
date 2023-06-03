import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_1325 {
    static int N, M;
    static List<List<Integer>> adj;

    static List<Integer> results;
    static boolean[] isVisited[];
    static int cache[];

    static void DFS(int index) {
        cache[index] = 1;

        List<Integer> here = adj.get(index);

        for (int there : here) {
            if (cache[there] == -1) {
                DFS(there);
            }
            cache[index] += cache[there];
        }
    }

    static void BFS(int index) {
        boolean[] isVisited = new boolean[N];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(index);
        isVisited[index] = true;
        cache[index] = 1;

        while (!queue.isEmpty()) {
            int here = queue.poll();
            for (int node : adj.get(here)) {
                if (isVisited[node]) {
                    continue;
                }
                isVisited[node] = true;
                queue.add(node);
                cache[index]++;
            }
        }
    }

    static int solution() {
        cache = new int[N];

        for (int i = 0; i < N; i++) {
            BFS(i);
        }

        int maxValue = 0;
        for (int i = 0; i < N; i++) {
            if (maxValue < cache[i]) {
                maxValue = cache[i];
            }
        }

        return maxValue;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 노드 추가
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // 간선 추가
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            adj.get(to).add(from);
        }

        int maxValue = solution();

        for (int i = 0; i < N; i++) {
            if (cache[i] == maxValue) {
                bw.write((i + 1) + " ");
            }
        }
        bw.write("\n");

        br.close();
        bw.close();
    }
}
