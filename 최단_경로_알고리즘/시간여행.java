package 최단_경로_알고리즘;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 시간여행 {
    // 테스트 케이스의 수
    static int C;
    // 은하계의 수
    static int G;
    // 웜홀의 수
    static int W;

    // 그래프의 인접 리스트
    static List<List<Pair>> adj;

    // reachable[u][v] = u 에서 v로 가는 경로가 있는가?
    static boolean reachable[][];

    static class Pair {
        int index, cost;

        public Pair(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    // src에서 target으로 가는 최단거리를 계산한다.
    // 가중치가 음의 무한대인 경로가 있으면 -INF를 반환한다.
    static int bellmanFord(int src, int target) {
        // 모든 경로의 최단 거리 상한을 무한대로
        List<Integer> upper = new ArrayList<>();
        Collections.fill(upper, Integer.MAX_VALUE);

        // 시작 지점 -> 시작 지점은 0
        upper.set(src, 0);

        // 반복은 V-1번한다.
        for (int i = 0; i < G - 1; i++) {
            // 시작지점
            for (int here = 0; here < G; here++) {
                // 시작지점에서 갈 수 있는 곳
                for (int j = 0; j < adj.get(here).size(); j++) {
                    int there = adj.get(here).get(j).index;
                    int cost = adj.get(here).get(j).cost;

                    // 갱신작업
                    // s->there > s->here->there 인 경우 갱신
                    if (upper.get(there) > upper.get(here) + cost) {
                        upper.set(there, upper.get(here) + cost);
                    }
                }
            }
        }

        // V번째에 갱신이 있는지 체크
        for (int here = 0; here < G; here++) {
            for (int i = 0; i < adj.get(here).size(); i++) {
                int there = adj.get(here).get(i).index;
                int cost = adj.get(here).get(i).cost;
                if (upper.get(there) > upper.get(here) + cost) {
                    // src -> here 까지 갈 수 있고, here -> there 까지 갈 수 있으면 음수 사이클 완성
                    if (reachable[src][here] && reachable[here][there]) {
                        return Integer.MIN_VALUE;
                    }
                }
            }
        }

        return upper.get(target);
    }

    public static void main(String[] args) {

    }
}
