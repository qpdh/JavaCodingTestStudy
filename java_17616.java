import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_17616 {
    // 나를 가리키는 adj 추가
    // 내가 가리키는 adj 추가
    static Vector<Vector<Integer>> adjFrom;
    static Vector<Vector<Integer>> adjTo;

    static int N, M, X;

    static class Pair {
        int u, v;

    }

    public static Pair bfs(int start) {
        Pair result = new Pair();

        int count = 0;

        boolean isVisited[] = new boolean[N];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        isVisited[start] = true;

        // 내가 가리키는 == 내가 이기는
        while (!queue.isEmpty()) {
            int thisPoint = queue.poll();

            for (int i = 0; i < adjTo.get(thisPoint).size(); i++) {
                int nextPoint = adjTo.get(thisPoint).get(i);
                if (isVisited[nextPoint]) {
                    continue;
                }

                queue.add(nextPoint);
                isVisited[nextPoint] = true;
            }
            count++;
        }

        result.v = N - count + 1;

        count = 0;
        queue.add(start);
        // 나를 가리키는 == 내가 지는
        while (!queue.isEmpty()) {
            int thisPoint = queue.poll();

            for (int i = 0; i < adjFrom.get(thisPoint).size(); i++) {
                int nextPoint = adjFrom.get(thisPoint).get(i);
                if (isVisited[nextPoint]) {
                    continue;
                }

                queue.add(nextPoint);
                isVisited[nextPoint] = true;
            }
            count++;
        }

        result.u = count;

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // N명 만큼 인접리스트 추가
        adjTo = new Vector<>();
        adjFrom = new Vector<>();
        for (int i = 0; i < N; i++) {
            adjTo.add(new Vector<>());
            adjFrom.add(new Vector<>());
        }

        // 데이터 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            adjTo.get(from).add(to);
            adjFrom.get(to).add(from);
        }

        Pair result = bfs(X - 1);

        bw.write(result.u + " " + result.v + "\n");

        br.close();
        bw.close();
    }
}
