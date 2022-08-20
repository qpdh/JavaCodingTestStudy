import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_20160 {
    // 매우 큰 값
    final static int INF = Integer.MAX_VALUE;
    static int V;
    static int E;

    static int[] yacList;
    static int start;

    static Vector<Vector<Pair>> adj;

    static class Pair {
        int cost, here;

        public Pair(int cost, int here) {
            this.cost = cost;
            this.here = here;
        }
    }

    static Vector<Integer> dijkstra(int src) {
        // 최단 경로 저장 배열
        // 매우 큰 값으로 초기화
        Vector<Integer> dist = new Vector<>();
        for (int i = 0; i < V; i++) {
            dist.add(INF);
        }

        // 현재 위치 값 0으로 바꾸기
        dist.set(src, 0);

        // 우선순위 큐 생성
        // 최단 경로 우선순위
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.cost - o2.cost;
            }

        });

        pq.add(new Pair(0, src));

        while (!pq.isEmpty()) {
            int cost = pq.peek().cost;
            int here = pq.peek().here;
            pq.poll();
            // 지금 꺼냇 것보다 더 짧은 경로를 알고 있다면 무시할 것
            if (dist.get(here) < cost)
                continue;

            // 인접한 정점들을 모두 검사할 것
            for (int i = 0; i < adj.get(here).size(); i++) {
                int there = adj.get(here).get(i).here;
                int nextDist = cost + adj.get(here).get(i).cost;
                // 더 짧은 경로를 발견하면 dist[] 를 갱신, 우선순위 큐로 넣는다.
                if (dist.get(there) > nextDist) {
                    dist.set(there, nextDist);
                    pq.add(new Pair(nextDist, there));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adj = new Vector<>();

        for (int i = 0; i < V; i++) {
            adj.add(new Vector<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            adj.get(from).add(new Pair(cost, to));
            adj.get(to).add(new Pair(cost, from));
        }

        st = new StringTokenizer(br.readLine());
        yacList = new int[10];
        for (int i = 0; i < 10; i++) {
            yacList[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        start = Integer.parseInt(br.readLine()) - 1;

        // 시작지점에서 갈 수 있는 시간
        Vector<Integer> startDist = dijkstra(start);

        // 야쿠르트 아줌마가 i지점을 방문하는 가장 늦은 시간
        long yacCost[] = new long[V];

        Arrays.fill(yacCost, -1);

        yacCost[yacList[0]] = 0;

        long time = 0;
        int now = yacList[0];

        for (int i = 1; i < 10; i++) {
            Vector<Integer> tmpTimeVector = dijkstra(now);

            long tmpTime = tmpTimeVector.get(yacList[i]);

            // 갈 수 없다면
            // i -> i+2...
            if (tmpTime == INF) {
                continue;
            }

            // 갈 수 있다면
            now = yacList[i];

            time += tmpTime;

            yacCost[yacList[i]] = time;
        }

        int result = -1;

        for (int i : startDist) {
            System.out.println("startDist : " + i);
        }

        for (long i : yacCost) {
            System.out.println("yacCost : " + i);
        }

        for (int i = 0; i < V; i++) {
            if (yacCost[i] == -1) {
                continue;
            }
            if (startDist.get(i) <= yacCost[i]) {
                result = i + 1;
                break;
            }
        }

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}
