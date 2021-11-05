import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_11780 {
    static class Node {
        int number;
        int cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
    }

    static int[][] matrix;

    // i->j 로 가기 위해 무조건 들려야 하는 곳
    static int[][] route;

    static Vector<Vector<Node>> adj;

    final static int INF = 987654321;

    public static void floyd_warshall() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    // A <-> B
                    // i -> j
                    // 가중치가 1인 그래프
                    // A -> I -> B
                    // A <-> B - INF => 이어져 있지 않다.
                    if (matrix[i][j] != INF && matrix[i][k] != INF) {

                        if(matrix[j][k] > matrix[i][j] + matrix[i][k]){
                            matrix[j][k] = matrix[i][j] + matrix[i][k];
                            route[i][k] = i;
                        }
                        // matrix[j][k] = Math.min(matrix[j][k], matrix[i][j] + matrix[i][k]);

                        // // j->k 로 최단 경로로 가기 위해 i를 무조건 들려야 한다.
                        // route[j][k] = i;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 도시의 개수 n
        int n = Integer.parseInt(br.readLine());

        //
        matrix = new int[n][n];
        route = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], INF);
            Arrays.fill(route[i], -1);
        }

        //
        adj = new Vector<>();
        for (int i = 0; i < n; i++) {
            adj.add(new Vector<>());
        }

        // 버스의 개수 m
        int m = Integer.parseInt(br.readLine());

        // m개의 데이터 삽입
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            matrix[from][to] = cost;
            adj.get(from).add(new Node(to, cost));
        }

        floyd_warshall();

        for (int[] row : matrix) {
            for (int data : row) {
                // 갈 수 없다면 0 출력
                if (data == INF) {
                    bw.write("0 ");
                } else {
                    bw.write(data + " ");
                }
            }
            bw.write("\n");
        }

        for(int i=0;i<route.length;i++){
            for(int j=0;j<route[0].length;j++){
                System.out.print(route[i][j]+" ");
            }
            System.out.println();
            
        }

        br.close();
        bw.close();
    }
}
