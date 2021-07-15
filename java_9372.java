import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_9372 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean planeArray[][];
    static boolean isVisit[];

    // 최소 비행기 종류 수 출력 == 깊이 우선 탐색
    public static int minPlane(int from) throws IOException {
        isVisit[from] = true;

        int result = 0;
        Vector<Integer> stack = new Vector<>();

        stack.add(from);

        while (!stack.isEmpty()) {
            int now = stack.remove(stack.size() - 1);

            // 방문하지 않는 곳 확인
            for (int i = 0; i < isVisit.length; i++) {
                // 갈 수 있는 곳 이여야 하며, 방문한 적이 없어야 함
                if (planeArray[now][i] == true && isVisit[i] == false) {
                    isVisit[i] = true;
                    stack.add(i);
                    result += 1;
                    //bw.write("from : " + now + " to : " + i);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // N/N 그래프 생성
            planeArray = new boolean[N][N];
            // 방문 했는 지 확인
            isVisit = new boolean[N];

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                planeArray[from][to] = true;
                planeArray[to][from] = true;
            }

            // for (int k = 0; k < M; k++) {
            //     for (int j = 0; j < M; j++) {
            //         bw.write(planeArray[k][j] + " ");
            //     }
            //     bw.write("\n");
            // }

            bw.write(minPlane(0) + "\n");

        }

        bw.flush();
        bw.close();
        br.close();
    }
}
