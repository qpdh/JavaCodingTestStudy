import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class java_13549 {
    // 수빈이의 위치
    static int N;
    // 동생의 위치
    static int K;

    static int dist[] = new int[100001];

    static class Pair {
        int here;
        int second;

        public Pair(int here, int second) {
            this.here = here;
            this.second = second;
        }
    }

    static void findRoute(int here, int second) {

        Deque<Pair> deque = new ArrayDeque<>();

        deque.add(new Pair(here, 0));

        while (!deque.isEmpty()) {
            Pair now = deque.poll();

            // 만난다면...
            if (now.here == K) {
                if (now.second < dist[now.here]) {
                    dist[now.here] = now.second;
                }
            }

            else {
                try {
                    if (now.here < K) {
                        if (now.second < dist[now.here * 2]) {
                            dist[now.here * 2] = now.second;
                            deque.add(new Pair(now.here * 2, now.second));
                        }
                    }
                } catch (IndexOutOfBoundsException exception) {
                }

                try {
                    if (now.here < K) {
                        if (now.second + 1 < dist[now.here + 1]) {
                            dist[now.here + 1] = now.second + 1;
                            deque.add(new Pair(now.here + 1, now.second + 1));
                        }
                    }
                } catch (IndexOutOfBoundsException exception) {
                }

                try {
                    if (now.second + 1 < dist[now.here - 1]) {
                        dist[now.here - 1] = now.second + 1;
                        deque.add(new Pair(now.here - 1, now.second + 1));
                    }
                } catch (IndexOutOfBoundsException exception) {
                }

            }

        }

        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 데이터 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, Integer.MAX_VALUE);

        // 수빈이가 가는 방법 X+1, X-1, 2*X
        findRoute(N, 0);

        System.out.println(dist[K]);

        bw.close();
        br.close();
    }
}
