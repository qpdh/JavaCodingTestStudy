package 최단_경로_알고리즘;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class 소방차 {

    // 노드의 수
    static int V;

    static List<List<Pair>> adj;

    static class Pair implements Comparable<Pair> {
        int index, cost;

        public Pair(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        // 비용의 오름차순
        @Override
        public int compareTo(Pair o) {
            // TODO Auto-generated method stub
            return this.cost - o.cost;
        }

    }

    static List<Integer> dijkstra(int src) {
        List<Integer> dist = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            dist.add(Integer.MAX_VALUE);
        }

        // 제자리는 0
        dist.set(src, 0);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            int cost = pq.peek().cost;
            int here = pq.peek().index;

            pq.poll();

            if (dist.get(here) < cost) {
                continue;
            }

            //
            for (int i = 0; i < adj.get(here).size(); i++) {
                int there = adj.get(here).get(i).index;
                int nextDist = cost + adj.get(here).get(i).cost;

                if (dist.get(there) > nextDist) {
                    dist.set(there, nextDist);
                    pq.add(new Pair(there, nextDist));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {

    }
}
