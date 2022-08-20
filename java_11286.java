import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class java_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                if (Math.abs(o1) == Math.abs(o2)) {
                    return o1 - o2;
                }
                return Math.abs(o1) - Math.abs(o2);
            }
        });

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            // 값 출력
            if (x == 0) {
                // 사이즈가 0이면 0을 출력
                if (priorityQueue.size() == 0) {
                    bw.write("0\n");
                }
                // 규칙에 따른 값 출력
                else {
                    bw.write(priorityQueue.poll() + "\n");
                }
            }
            // 값 추가
            else {
                priorityQueue.add(x);
            }
        }

        br.close();
        bw.close();
    }
}
