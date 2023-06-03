package 최단_경로_알고리즘;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 벨만_포드 {
    // 정점의 개수
    static int V;

    // 그래프의 인접 리스트
    static List<List<Pair>> adj;

    static class Pair {
        int index, cost;

        public Pair(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    static List<Integer> bellamanFord(int src) {
        // 모든 경로의 최단 거리 상한을 무한대로
        List<Integer> upper = new ArrayList<>();
        Collections.fill(upper, Integer.MAX_VALUE);

        // 시작 지점 -> 시작 지점은 0
        upper.set(src, 0);

        // 경로가 갱신 되었는지 확인
        boolean updated = false;

        // 반복은 V번한다.
        for (int i = 0; i < V; i++) {
            updated = false;
            // 시작지점
            for (int here = 0; here < V; here++) {
                // 시작지점에서 갈 수 있는 곳
                for (int j = 0; j < adj.get(here).size(); j++) {
                    int there = adj.get(here).get(j).index;
                    int cost = adj.get(here).get(j).cost;

                    // 갱신작업
                    // s->there > s->here->there 인 경우 갱신
                    if (upper.get(there) > upper.get(here) + cost) {
                        // 갱신 성공 처리
                        updated = true;
                        upper.set(there, upper.get(here) + cost);
                    }
                }
            }

            // 현재 반복 때 아무런 갱신이 없다면 음수사이클이 없고, 최적 경로를 찾았다.
            if (!updated) {
                break;
            }
        }

        // V번째에 갱신이 생긴 경우
        if (updated) {
            upper.clear();
        }

        return upper;
    }

    public static void main(String[] args) {

    }
}
