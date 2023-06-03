import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class java_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int number;
            switch (command) {
                case "push_front":
                    number = Integer.parseInt(st.nextToken());
                    // 덱 앞에 하나 추가
                    deque.addFirst(number);
                    break;

                case "push_back":
                    number = Integer.parseInt(st.nextToken());
                    // 덱 뒤에 하나 추가
                    deque.addLast(number);
                    break;

                case "pop_front":
                    // 덱이 비어있으면 -1 출력
                    if (deque.isEmpty()) {
                        bw.write("-1\n");
                    }
                    // 덱에서 하나 pop
                    else {
                        bw.write(deque.pollFirst() + "\n");
                    }
                    break;

                case "pop_back":
                    // 덱이 비어있으면 -1 출력
                    if (deque.isEmpty()) {
                        bw.write("-1\n");
                    }
                    // 덱에서 하나 pop
                    else {
                        bw.write(deque.pollLast() + "\n");
                    }
                    break;

                case "size":
                    // 덱의 사이즈 출력
                    bw.write(deque.size() + "\n");
                    break;

                case "empty":
                    // 비어있으면 1 아니면 0 출력
                    String result = deque.isEmpty() ? "1" : "0";
                    bw.write(result + "\n");
                    break;

                case "front":
                    // 덱에 가장 앞에 있는 정수 출력
                    // 덱이 비어있으면 -1 출력
                    if (deque.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(deque.getFirst() + "\n");
                    }
                    break;

                case "back":
                    // 덱에 가장 뒤에 있는 정수 출력
                    // 덱이 비어있으면 -1 출력
                    if (deque.isEmpty()) {
                        bw.write("-1\n");
                    } else {
                        bw.write(deque.getLast() + "\n");
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
