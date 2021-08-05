import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class java_1021 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int indexList[] = new int[M];
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            deque.addLast(Integer.parseInt(st.nextToken()));
        }

        int nowIndex = 0;
        // 반을 넘어간 위치에 있으면 탈락

        deque.pollFirst();


        bw.close();
        br.close();
    }
}
