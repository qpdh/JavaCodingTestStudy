/*
3 4
2 1 1
3 1 1
2 3 5
1 3 2

맞는 답 : 3, 틀린 답 : -1

3 6
1 2 1
2 3 1
3 1 1
2 1 3
3 2 3
1 3 3
맞는 답 : 3, 틀린 답 : 4

3 6
1 2 100
1 2 1
3 2 3
3 2 70
1 3 4
2 3 5
75
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_1956 {
    final static int INF = 987654321;

    static class Node {
        int number;
        int cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
    }

    // 노드를 저장하는 인접배열
  //  static ArrayList<Node> adjList[];
    // 노드를 저장하는 매트릭스
    static Integer matrix[][];
  //  static boolean isVisited[][];
  //  static int cache[];

    // x에서 시작해서 x로 돌아오는 최소값 반환 알고리즘
    // public static int solve(int now, int goal, int length) {

    // // System.out.println("현재 " + now + " 목표 " + goal + "길이 " + length);

    // if (now == goal) {
    // // System.out.println("현재 " + now + " 목표 " + goal + " 사이클 완성" + length);
    // return length;
    // }

    // // 현재 위치를 포함하는 사이클이 존재하는 경우
    // if (cache[now] != -1) {
    // if (cache[now] < length) {
    // return INF;
    // }
    // }

    // int tmp = INF;

    // int count = 0;

    // for (int i = 0; i < adjList[now].size(); i++) {
    // if (isVisited[now][i] == false) {
    // isVisited[now][i] = true;
    // tmp = Math.min(tmp, solve(adjList[now].get(i).number, goal, length +
    // adjList[now].get(i).cost));
    // isVisited[now][i] = false;
    // } else {
    // count++;
    // }
    // }

    // // 더 이상 갈 수 없다면..
    // if (count == adjList[now].size()) {
    // // System.out.println("더이상 갈 수 없음");
    // return 987654351;
    // }

    // cache[now] = tmp;
    // return cache[now];
    // }

    public static void floyd_warshall() {
        // i를 경유하는 j->k로 가는 방법
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    // j->i 갈 수 있어야 함
                    // i->k 갈 수 있어야 함
                    // => j->k 갈 수 있음
                    if (matrix[j][i] != INF && matrix[i][k] != INF) {
                        matrix[j][k] = Math.min(matrix[j][k], matrix[j][i] + matrix[i][k]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

       // adjList = new ArrayList[V];
      //  isVisited = new boolean[V][V];
        matrix = new Integer[V][V];

        // 매트릭스 무한으로 가득 채우기
        for (int i = 0; i < V; i++) {
            Arrays.fill(matrix[i], INF);
        }

       // cache = new int[V];

        // // i를 포함하는 사이클의 길이
        // for (int i = 0; i < V; i++) {

        //     cache[i] = -1;
        // }

        // for (int i = 0; i < V; i++) {
        // adjList[i] = new ArrayList<>();
        // }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // adjList[a - 1].add(new Node(b - 1, c));
            matrix[a - 1][b - 1] = c;
        }

        int ret = INF;

        floyd_warshall();

        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        for (int i = 0; i < V; i++) {
            ret = Math.min(ret, matrix[i][i]);
        }

        // for (int i = 0; i < V; i++) {
        // for (int j = 0; j < adjList[i].size(); j++) {

        // // i에서 j로 출발한 상태에서 시작!
        // // 다시 i로 돌아오면 끝!
        // isVisited[i][j] = true;
        // ret = Math.min(ret, solve(adjList[i].get(j).number, i,
        // adjList[i].get(j).cost));
        // isVisited[i][j] = false;

        // }
        // }

        if (ret >= INF) {
            bw.write("-1\n");
        } else {
            bw.write(ret + "\n");
        }
        br.close();
        bw.close();
    }
}
