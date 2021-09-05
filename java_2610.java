import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_2610 {
    final static int INF = 987654321;
    static int matrix[][];

    public static void floyd_warshall() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    // A <-> B
                    // i -> j
                    // i -> (0,N) 최댓값 -> 최대 의사 전달 시간 -> 최솟값을 가진 위원회원이 위원회 대표가 된다.
                    // 가중치가 1인 그래프
                    // A -> I -> B
                    // A <-> B - INF => 이어져 있지 않다.
                    if (matrix[i][j] != INF && matrix[i][k] != INF) {
                        matrix[j][k] = Math.min(matrix[j][k], matrix[i][j] + matrix[i][k]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(matrix[i], INF);
            matrix[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a - 1][b - 1] = 1;
            matrix[b - 1][a - 1] = 1;
        }

        floyd_warshall();

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        // 뭉탱이 찾기
        boolean isVisited[] = new boolean[N];

        // 위원회 수
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (isVisited[i] == false) {
                isVisited[i] = true;
                adjList.add(new ArrayList<>());
                // 1번 위원회 : A, B, C
                // 2번 위원회 : D, E, F
                adjList.get(count).add(i);
                for (int j = 0; j < N; j++) {
                    // 방문한 적 없고, 이어져 있다면 == 위원회에 속한다면
                    if (isVisited[j] == false && matrix[i][j] < INF) {
                        isVisited[j] = true;
                        adjList.get(count).add(j);
                    }
                }
                count++;
            }
        }

        // 위원회 최소값 저장
        int committeeList[] = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] < INF) {
                    committeeList[i] =  Math.max(committeeList[i],matrix[i][j]);
                }
            }
        }

        // 위원회 수 반환
        bw.write(count + "\n");
        // 각 대표 반환
        int retList[] = new int[adjList.size()];
        for (int i = 0; i < adjList.size(); i++) {
            int minIndex = adjList.get(i).get(0);
            for (int j = 1; j < adjList.get(i).size(); j++) {
                if (committeeList[minIndex] > committeeList[adjList.get(i).get(j)]) {
                    minIndex = adjList.get(i).get(j);
                }
            }
            retList[i] = minIndex+1;
            //bw.write((minIndex+1)+"\n");
        }

        Arrays.sort(retList);
        for(int i=0;i<retList.length;i++){
            bw.write(retList[i]+"\n");
        }

        br.close();
        bw.close();
    }
}