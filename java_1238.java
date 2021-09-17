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

public class java_1238 {
    static class Pair {
        int node;
        int cost;

        public Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static Vector<Vector<Pair>> adjList;

    // 초기 최댓값
    final static int INF = 987654321;

    public static int[] dijkstra(int K) {
        int[] dist = new int[adjList.size()];
        Arrays.fill(dist, INF);

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                // TODO Auto-generated method stub
                return o1.cost - o2.cost;
            }

        });

        dist[K] = 0;
        priorityQueue.add(new Pair(K, 0));

        while (!priorityQueue.isEmpty()) {
            int here = priorityQueue.peek().node;
            int fromCost = priorityQueue.peek().cost;

            priorityQueue.poll();

            if (dist[here] < fromCost) {
                continue;
            }

            for (int i = 0; i < adjList.get(here).size(); i++) {
                int there = adjList.get(here).get(i).node;
                int toCost = fromCost + adjList.get(here).get(i).cost;

                if (toCost < dist[there]) {
                    dist[there] = toCost;
                    priorityQueue.add(new Pair(there, toCost));
                }
            }
        }

        return dist;
    }

    public static int dijkstra2(int start, int K) {
        int[] dist = new int[adjList.size()];
        Arrays.fill(dist, INF);

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                // TODO Auto-generated method stub
                return o1.cost - o2.cost;
            }

        });

        dist[start] = 0;
        priorityQueue.add(new Pair(start, 0));

        while (!priorityQueue.isEmpty()) {
            int here = priorityQueue.peek().node;
            int fromCost = priorityQueue.peek().cost;

            priorityQueue.poll();

            if (dist[here] < fromCost) {
                continue;
            }

            for (int i = 0; i < adjList.get(here).size(); i++) {
                int there = adjList.get(here).get(i).node;
                int toCost = fromCost + adjList.get(here).get(i).cost;

                if (toCost < dist[there]) {
                    dist[there] = toCost;
                    priorityQueue.add(new Pair(there, toCost));
                }
            }
        }

        return dist[K];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N명의 학생
        // M 개의 단방향 도로
        // X 마을 도착점
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;

        adjList = new Vector<>();
        // 학생 수 만큼 인접리스트 데이터 삽입
        for (int i = 0; i < N; i++) {
            adjList.add(new Vector<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            adjList.get(from).add(new Pair(to, cost));
        }

        // X 마을에서 원래 마을로 가는 최단거리
        int[] KtoVillage = dijkstra(X);

        int maxCost = 0;
        for (int i = 0; i < KtoVillage.length; i++) {
            KtoVillage[i] += dijkstra2(i, X);
            if (maxCost < KtoVillage[i]) {
                maxCost = KtoVillage[i];
            }
        }

        bw.write(maxCost+"\n");

        br.close();
        bw.close();
    }
}
