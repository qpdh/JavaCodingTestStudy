import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class java_1766 {
    static class Adjacency_List {
        private ArrayList<ArrayList<Integer>> adjLists;

        public Adjacency_List(int maxVertex) {
            adjLists = new ArrayList<ArrayList<Integer>>(maxVertex);

            for (int i = 0; i < maxVertex; i++) {
                adjLists.add(new ArrayList<Integer>());
            }
        }

        public void insertEdge(int v1, int v2) {
            adjLists.get(v1).add(v2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N 문제의 수, M 정보의 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 인접 리스트 생성
        Adjacency_List adjacency_List = new Adjacency_List(N);

        int information[][] = new int[N][2];

        for (int i = 0; i < N; i++) {
            information[i][0] = i;
        }

        // 정보의 수 M 만큼 정보 입력받기
        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());

            adjacency_List.insertEdge(pre - 1, post - 1);

            information[post - 1][1] += 1;
        }

        while (true) {

            int nowNumber = -1;

            // 가장 먼저 풀어야 하는 문제를 벡터에 저장
            for (int i = 0; i < N; i++) {
                if (information[i][1] == 0) {
                    information[i][1] -= 1;
                    nowNumber = i;
                    break;
                }
            }

            // 문제가 없다면 종료
            if (nowNumber == -1) {
                break;
            }

            bw.write((nowNumber + 1) + " ");

            ArrayList p = adjacency_List.adjLists.get(nowNumber);

            for (int j = 0; j < p.size(); j++) {
                information[(int) p.get(j)][1] -= 1;
            }

        }

        bw.flush();
        bw.close();
        br.close();
    }
}
