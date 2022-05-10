import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_9466 {
    static int[] isVisited;
    static boolean[] isFinished;
    static int count;

    static int nodeCount;

    public static void dfs(int[] board, int start) {
        isVisited[start] = nodeCount++;

        int next = board[start];

        if (isVisited[next] == -1) {
            dfs(board, next);
        } else if (!isFinished[next]) {
            count += isVisited[start] - isVisited[next] + 1;
        }

        isFinished[start] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int test = 0; test < testCase; test++) {
            // 학생의 수
            int n = Integer.parseInt(br.readLine());

            // 초기화
            int board[] = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                board[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            count = 0;
            nodeCount = 0;
            isVisited = new int[n];
            Arrays.fill(isVisited, -1);
            isFinished = new boolean[n];

            for (int i = 0; i < board.length; i++) {
                if (!isFinished[i]) {
                    dfs(board, i);
                }
            }

            bw.write((n - count) + "\n");

        }

        br.close();
        bw.close();
    }
}
