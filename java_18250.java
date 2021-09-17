import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_18250 {
    static Vector<Vector<Integer>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시의 수
        int N = Integer.parseInt(st.nextToken());

        adj = new Vector<>();
        for (int i = 0; i < N; i++) {
            adj.add(new Vector<>());
        }

        // 철도 노선의 수
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        br.close();
        bw.close();
    }
}
