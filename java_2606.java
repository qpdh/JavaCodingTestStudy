import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_2606 {
    static ArrayList<Integer> adjList[];

    public static int solve() {
        Queue<Integer> queue = new LinkedList<>();

        // 1번 컴퓨터로부터 방문할 수 있는 다른 컴퓨터의 수
        int count = 0;

        boolean isVisited[] = new boolean[adjList.length];

        queue.add(0);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            // 방문한 적이 없으면.. 삽입
            if (!isVisited[now]) {
                // 방문 처리
                count++;
                isVisited[now] = true;

                // 데이터 삽입
                for (int i = 0; i < adjList[now].size(); i++) {
                    queue.add(adjList[now].get(i));
                }
            }
        }

        // 자기 자신은 빼야하므로...
        return count - 1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 노드의 수
        int N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 링크의 수
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 배열은 0부터 시작이기 때문에 1씩 빼준다.
            adjList[from - 1].add(to - 1);
            adjList[to - 1].add(from - 1);
        }

        bw.write(solve() + "\n");

        br.close();
        bw.close();

    }
}
