import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class java_23559 {
    // N : N일동안 먹기
    // X : X원 이하를 쓰기
    static int N, X;

    // tasteA : 5000원짜리 메뉴의 맛
    // tasteB : 1000원짜리 메뉴의 맛
    static int tasteA[], tasteB[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N 일 동안
        N = Integer.parseInt(st.nextToken());
        // X 원 이하 쓰기
        X = Integer.parseInt(st.nextToken());

        tasteA = new int[N];
        tasteB = new int[N];

        // 일단 무조건 맛있는걸 먹는다.
        int startTaste = 0;
        int startCost = 0;
        boolean is5000[] = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            // 5000원 짜리 메뉴의 맛
            tasteA[i] = Integer.parseInt(st.nextToken());
            // 1000원 짜리 메뉴의 맛
            tasteB[i] = Integer.parseInt(st.nextToken());

            if (tasteA[i] < tasteB[i]) {
                startTaste += tasteB[i];
                startCost += 1000;
            } else {
                is5000[i] = true;
                startTaste += tasteA[i];
                startCost += 5000;
            }
        }

        int result = solution(startTaste, startCost, is5000);

        System.out.println(result);

        br.close();

    }

    private static class Pair implements Comparable<Pair> {
        int date;
        int difference;

        public Pair(int date, int difference) {
            this.date = date;
            this.difference = difference;
        }

        // 맛 차이의 오름차순
        @Override
        public int compareTo(Pair o) {
            // TODO Auto-generated method stub
            return this.difference - o.difference;
        }
    }

    // 맛의 합을 최대화
    private static int solution(int startTaste, int startCost, boolean is5000[]) {
        // 맛있는거 우선 선택
        // 가격이 싼것 우선 선택

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (is5000[i]) {
                priorityQueue.add(new Pair(i, Math.abs(tasteA[i] - tasteB[i])));
            }
        }

        int cost = startCost;
        int totalTaste = startTaste;

        while (!priorityQueue.isEmpty()) {
            // 가격내 충분히 다 먹을 수 있다면..종료
            if (cost <= X) {
                break;
            }

            Pair thisFoodDifference = priorityQueue.poll();
            int thisDate = thisFoodDifference.date;

            // -5000 +1000
            cost = cost - 5000 + 1000;
            totalTaste = totalTaste - tasteA[thisDate] + tasteB[thisDate];
        }

        return totalTaste;
    }
}
