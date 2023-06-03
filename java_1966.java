import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_1966 {

    static class Pair {
        int index;
        int importance;

        public Pair(int index, int importance) {
            this.index = index;
            this.importance = importance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 문서의 수
            int N = Integer.parseInt(st.nextToken());
            // 원하는 문서가 몇 번째에 놓여있는 지
            int M = Integer.parseInt(st.nextToken());

            Queue<Pair> queue = new LinkedList<>();

            // 우선순위 큐 생성
            PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o2.importance - o1.importance;
                }
            });

            // 문서 데이터 삽입
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int importance = Integer.parseInt(st.nextToken());
                priorityQueue.add(new Pair(i, importance));
                queue.add(new Pair(i, importance));
            }

            // poll 하면서 index가 M인 녀석이 언제 빠지는지 체크
            int countResult = 1;
            while (true) {
                // 중요도가 가장 높은 문서 체크
                Pair mostImportantDocument = priorityQueue.peek();
                // 큐 가장 앞에 있는 문서의 중요도 체크
                Pair thisDocument = queue.peek();

                // 가장 중요한 문서라면..
                if (thisDocument.importance == mostImportantDocument.importance) {
                    priorityQueue.poll();
                    queue.poll();

                    // 원하는 문서의 index와 같다면 -> 종료
                    if (thisDocument.index == M) {
                        break;
                    }

                    countResult++;
                }

                // 아니라면 맨 뒤로 넣어줌
                else {
                    queue.add(queue.poll());
                }

            }

            bw.write(countResult + "\n");
        }

        br.close();
        bw.close();
    }
}
