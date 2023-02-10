import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class java_20208 {
    static int N, M, H;

    static List<Pair> milkList;

    static Pair home;

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int calcHealth(int health, Pair here, Pair there) {
        int result = health;

        // 거리만큼 이동
        result -= calcDistance(here, there);

        // 우유만큼 회복
        result += H;

        return result;
    }

    // 두 지점 사이 거리
    static int calcDistance(Pair a, Pair b) {
        int yDistance = Math.abs(a.y - b.y);
        int xDistance = Math.abs(a.x - b.x);

        return yDistance + xDistance;
    }

    static int dfs(Pair here, int health, int count, boolean[] isVisited) {

        int result = Integer.MIN_VALUE;

        // 집으로 가는 경우
        if (calcDistance(here, home) <= health) {
            result = count;
        }

        for (int i = 0; i < milkList.size(); i++) {
            Pair there = milkList.get(i);
            // 도달 할 수 없으면 처리 안함
            if (calcDistance(there, here) > health) {
                continue;
            }

            if (!isVisited[i]) {
                isVisited[i] = true;
                result = Math.max(result, dfs(there, calcHealth(health, here, there), count + 1, isVisited));
                isVisited[i] = false;
            }
        }

        return result;
    }

    // 마실 수 있는 민트초코우유의 최대 갯수 반환
    static int solution() {

        int result = 0;

        boolean isVisited[] = new boolean[milkList.size()];
        // 현재 위치 체력에서 마실 수 있는 민트초코의 최대 갯수
        for (int i = 0; i < milkList.size(); i++) {
            Pair there = milkList.get(i);
            if (calcDistance(home, there) > M) {
                continue;
            }
            isVisited[i] = true;
            result = Math.max(result, dfs(there, calcHealth(M, home, there), 1, isVisited));
            isVisited[i] = false;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 마을의 크기 N * N

        // 초기 체력 M

        // 체력 증가량 H

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        milkList = new ArrayList<>();

        // 우유의 총 합은 10개를 넘지 않으므로..-> 희소행렬 자료구조 사용
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int number = Integer.parseInt(st.nextToken());
                if (number == 2) {
                    milkList.add(new Pair(i, j));
                    continue;
                }
                if (number == 1) {
                    home = new Pair(i, j);
                }
            }
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }
}
