import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class java_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    int number = Integer.parseInt(st.nextToken());
                    // 큐에 하나 추가
                    queue.add(number);
                    break;

                case "pop":
                    // 큐가 비어있으면 -1 출력
                    if (queue.isEmpty()) {
                        bw.write("-1\n");
                    }
                    // 큐에서 하나 pop
                    else {
                        bw.write(queue.poll() + "\n");
                    }
                    break;

                case "size":
                    // 큐의 사이즈 출력
                    bw.write(queue.size() + "\n");
                    break;

                case "empty":
                    // 비어있으면 1 아니면 0 출력
                    String result = queue.isEmpty() ? "1" : "0";
                    bw.write(result + "\n");
                    break;

                case "front":
                    // 큐에 가장 앞에 있는 정수 출력
                    // 큐가 비어있으면 -1 출력
                    if (queue.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(queue.getFirst() + "\n");
                    }
                    break;

                case "back":
                    // 큐에 가장 뒤에 있는 정수 출력
                    // 큐가 비어있으면 -1 출력
                    if (queue.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(queue.getLast() + "\n");
                    }
                    break;

                default:
                    break;
            }
        }

        br.close();
        bw.close();
    }
}
