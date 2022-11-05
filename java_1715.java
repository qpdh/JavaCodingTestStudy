import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class java_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            priorityQueue.add(Long.parseLong(br.readLine()));
        }

        long result = 0;
        while (priorityQueue.size() != 1) {
            long first = priorityQueue.poll();
            long second = priorityQueue.poll();
            result += first + second;
            priorityQueue.add(first + second);
        }

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}
