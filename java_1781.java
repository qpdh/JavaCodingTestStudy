import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class java_1781 {
    static int N;

    static PriorityQueue<Problem> problemList;

    static class Problem implements Comparable<Problem> {
        int deadLine;
        int reward;

        @Override
        public int compareTo(Problem o) {
            if (o.deadLine == deadLine) {
                return o.reward - reward;
            }
            return o.deadLine - deadLine;
        }

        public Problem(int deadLine, int reward) {
            this.deadLine = deadLine;
            this.reward = reward;
        }

    }

    static int solution() {
        int result = 0;

        PriorityQueue<Problem> availableQueue = new PriorityQueue<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                return o2.reward - o1.reward;
            }
        });

        // N 시각에 해결할 수 있는 문제 추출
        for (int i = N; i > 0; i--) {
            while (!problemList.isEmpty()) {
                Problem problem = problemList.peek();
                if (problem.deadLine >= i) {
                    availableQueue.add(problem);
                    problemList.poll();
                } else {
                    break;
                }
            }

            // reward의 내림차순 이므로...
            if (!availableQueue.isEmpty()) {
                result += availableQueue.poll().reward;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 데드라인이 가까운 것 우선
        // 컵라면 수가 많은 것 우선

        // 문제의 경우
        // 현재 시간 1
        // 1 - 5, 2 - 10, 2 - 11
        // 의 경우
        // 1 - 5를 포기하는 것이 맞다.

        // 컵라면 수의 내림차순으로 정렬해보자
        // 7 6 2 1 4 5 1
        // 해당 데드라인 가장 끝에 문제를 해결한다.
        // 아니면 그 전 날에 해결한다.

        problemList = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int deadLine = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());

            problemList.add(new Problem(deadLine, reward));
        }

        int result = solution();
        System.out.println(result);

        br.close();
    }
}
