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

public class java_1753 {
    static class Pair {
        int node;
        int cost;

        public Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    // 인접 리스트
    static Vector<Vector<Pair>> graph;
    // 최단 경로 저장 배열
    static int dist[];
    // 초기 최댓값
    final static int INF = 987654321;

    public static void dijkstra(int startNode) {
        // 최단 경로 배열 할당
        dist = new int[graph.size()];
        Arrays.fill(dist, INF);

        // 우선순위 큐 생성
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                // TODO Auto-generated method stub
                return o1.cost - o2.cost;
            }

        });

        // 처음위치의 최단거리는 0
        dist[startNode] = 0;
        priorityQueue.add(new Pair(startNode, 0));

        while (!priorityQueue.isEmpty()) {
            // 맨 앞 노드 값 반환
            int here = priorityQueue.peek().node;
            int fromCost = priorityQueue.peek().cost;

            // 맨 앞 노드 빼주기
            priorityQueue.poll();

            if(dist[here]<fromCost){
                continue;
            }


            // here에서 갈 수 있는 모든 경로 찾기
            for (int i = 0; i < graph.get(here).size(); i++) {
                int there = graph.get(here).get(i).node;
                // 가는데 소모하는 총 비용
                int toCost = fromCost + graph.get(here).get(i).cost;
                // 기존 저장된 최단거리 보다 짧으면...
                if (toCost < dist[there]) {
                    priorityQueue.add(new Pair(there, toCost));
                    dist[there] = toCost;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        graph = new Vector<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수
        int V = Integer.parseInt(st.nextToken());

        // 간선의 개수
        int E = Integer.parseInt(st.nextToken());

        // 시작 정점의 번호
        int K = Integer.parseInt(br.readLine())-1;

        // 정점의 수 만큼 인접리스트 생성
        for (int i = 0; i < V; i++) {
            graph.add(new Vector<>());
        }

        // 방향 그래프 간선 삽입
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Pair(to, cost));
        }

        // 결과값 출력
        dijkstra(K);

        for(int i=0;i<dist.length;i++){
            if(dist[i] >= INF){
                bw.write("INF\n");
            }
            else{
                bw.write(dist[i]+"\n");
            }
        }

        br.close();
        bw.close();
    }
}