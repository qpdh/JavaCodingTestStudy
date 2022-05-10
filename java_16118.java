import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_16118 {
    // 그루터기의 수
    public static int N;
    // 오솔길의 수
    public static int M;

    // 매우 큰 값
    final static double INF = 987654321;

    // 그래프의 인접 리스트
    static Vector<Vector<Pair>> adj;

    static class Pair {
        double cost;
        int here;

        public Pair(double cost, int here) {
            this.cost = cost;
            this.here = here;
        }
    }

    static class DistWolf {
        double cost;
        boolean isRunned;

        public DistWolf(double cost, boolean isRunned) {
            this.cost = cost;
            this.isRunned = isRunned;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return cost + "";
        }

        public void setRunned() {
            this.isRunned = !this.isRunned;
        }
    }

    static Vector<Double> dijkstra(int src) {
        // 최단 경로 저장 배열
        // 매우 큰 값으로 초기화
        Vector<Double> dist = new Vector<>();
        for (int i = 0; i < N; i++) {
            dist.add(INF);
        }

        // 현재 위치 값 0으로 바꾸기
        dist.set(src, 0d);

        // 우선순위 큐 생성
        // 최단 경로 우선순위
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.cost < o2.cost) {
                    return -1;
                } else {
                    return 1;
                }
            }

        });

        pq.add(new Pair(0, src));

        while (!pq.isEmpty()) {
            double cost = pq.peek().cost;
            int here = pq.peek().here;
            pq.poll();
            // 지금 꺼냇 것보다 더 짧은 경로를 알고 있다면 무시할 것
            if (dist.get(here) < cost)
                continue;

            // 인접한 정점들을 모두 검사할 것
            for (int i = 0; i < adj.get(here).size(); i++) {
                int there = adj.get(here).get(i).here;
                double nextDist = cost + adj.get(here).get(i).cost;
                // 더 짧은 경로를 발견하면 dist[] 를 갱신, 우선순위 큐로 넣는다.
                if (dist.get(there) > nextDist) {
                    dist.set(there, nextDist);
                    pq.add(new Pair(nextDist, there));
                }
            }
        }
        return dist;
    }

    static Vector<Double> dijkstra_wolf(int src) {
        // 최단 경로 저장 배열
        // 매우 큰 값으로 초기화
        Vector<Vector<DistWolf>> dist = new Vector<>();
        for (int i = 0; i < N; i++) {
            dist.add(new Vector<>());

            dist.get(i).add(new DistWolf(INF, false));
            dist.get(i).add(new DistWolf(INF, true));
        }

        // 현재 위치 값 0으로 바꾸기
        dist.get(src).set(0, new DistWolf(0, false));
        dist.get(src).set(1, new DistWolf(INF, true));

        // 우선순위 큐 생성
        // 최단 경로 우선순위
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(0);

        while (!pq.isEmpty()) {
            int here = pq.peek();
            pq.poll();
            // // 지금 꺼냇 것보다 더 짧은 경로를 알고 있다면 무시할 것
            // if (dist.get(here).cost < cost)
            // continue;

            // 인접한 정점들을 모두 검사할 것
            for (int i = 0; i < adj.get(here).size(); i++) {
                int there = adj.get(here).get(i).here;
                double thereCost = adj.get(here).get(i).cost;

                boolean check = false;

                // 이전에 달렸으면 거리가 2배가 됨
                // 이전에 달리지 않았으면 거리가 절반이 됨
                for (int j = 0; j < dist.get(here).size(); j++) {
                    double nextDist;
                    if (dist.get(here).get(j).isRunned) {
                        nextDist = dist.get(here).get(1).cost + thereCost * 2;
                    } else {
                        nextDist = dist.get(here).get(0).cost + thereCost / 2;
                    }

                    // 이전에 달렸고 더 짧으면 보류해야 함
                    // 더 짧은 경로를 발견하면 dist[] 를 갱신, 우선순위 큐로 넣는다.
                    if (!dist.get(here).get(j).isRunned) {
                        DistWolf thereDist = dist.get(there).get(1);
                        if (nextDist < thereDist.cost) {
                            thereDist.cost = nextDist;
                            dist.get(there).set(1, thereDist);

                            if (!check) {
                                pq.add(there);
                                check = !check;
                            }

                        }
                    }

                    // 이전에 달리지 않았고 더 짧으면 무조건 빠른길임
                    else if (dist.get(here).get(j).isRunned) {
                        DistWolf thereDist = dist.get(there).get(0);
                        if (nextDist < thereDist.cost) {
                            thereDist.cost = nextDist;
                            dist.get(there).set(0, thereDist);
                            pq.add(there);

                            if (!check) {
                                pq.add(there);
                                check = !check;
                            }
                        }
                    }
                    // 이전에 달리지 않았고 더 길면 안좋은 길임
                }

            }
        }

        Vector<Double> result = new Vector<>();
        for (int i = 0; i < dist.size(); i++) {
            double minDist = INF;
            for (int j = 0; j < dist.get(i).size(); j++) {
                if (dist.get(i).get(j).cost < minDist) {
                    minDist = dist.get(i).get(j).cost;
                }
            }
            result.add(minDist);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 여우는 무조건 최단거리
        // 늑대는 긴 거리를 최대한 빠르게 가야함

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new Vector<>();
        for (int i = 0; i < N; i++) {
            adj.add(new Vector<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            double cost = Double.parseDouble(st.nextToken());

            adj.get(from).add(new Pair(cost, to));
            adj.get(to).add(new Pair(cost, from));
        }

        Vector<Double> result_wolf = dijkstra_wolf(0);
        Vector<Double> result_fox = dijkstra(0);

        int result = 0;
        for (int i = 0; i < result_wolf.size(); i++) {
            if (result_fox.get(i) < result_wolf.get(i)) {
                result++;
            }
            // System.out.println((i + 1) + "번 나무 || 늑대 : " + result_wolf.get(i) + " 여우 : "
            // + result_fox.get(i));
        }

        bw.write(result + "");

        br.close();
        bw.close();
    }
}
