import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class java_17471 {
    static int N;
    static int cost[];

    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    static ArrayList<Pair> combinationList = new ArrayList<>();

    static boolean[] isVisited;

    static class Pair {
        List<Integer> A;
        List<Integer> B;

        public Pair(ArrayList<Integer> a, ArrayList<Integer> b) {
            A = new ArrayList<>(a);
            B = new ArrayList<>(b);
        }

        @Override
        public String toString() {
            return "Pair [A=" + A + ", B=" + B + "]";
        }

    }

    // index를 선택할 차례
    static void combination(int index, ArrayList<Integer> A, ArrayList<Integer> B) {
        // index-1 까지 전부 뽑았다면..
        if (index == N) {
            // 1개는 적어도 뽑아야 한다.
            if (A.size() == 0 || B.size() == 0) {
                return;
            }
            // 2개씩 중복이 주어지므로 중복은 삽입하지 않음
            if (A.get(0) < B.get(0)) {
                combinationList.add(new Pair(A, B));
            }

            return;
        }

        // 현재 index를 뽑을 것인가?
        A.add(index);
        combination(index + 1, A, B);
        A.remove(Integer.valueOf(index));

        // 아니라면 B에 삽입

        B.add(index);
        combination(index + 1, A, B);
        B.remove(Integer.valueOf(index));
    }

    static void DFS(List<Integer> list, int index) {
        for (int i = 0; i < adj.get(index).size(); i++) {
            int to = adj.get(index).get(i);
            // to 가 list안에 포함되어 있어야 함
            if (list.contains(to)) {
                // 방문한 적이 없어야 함
                if (!isVisited[to]) {
                    isVisited[to] = true;
                    DFS(list, to);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        adj = new ArrayList<>();

        N = Integer.parseInt(br.readLine());

        cost = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        // N 개의 도시 삽입
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // 연결 관계 삽입
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int adjCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < adjCount; j++) {
                int to = Integer.parseInt(st.nextToken()) - 1;
                adj.get(i).add(to);
                adj.get(to).add(i);
            }

        }

        // 구역을 2 구역으로 나누기
        // pair1, pair2
        combination(0, new ArrayList<>(), new ArrayList<>());

        int result = Integer.MAX_VALUE;
        for (Pair pair : combinationList) {
            isVisited = new boolean[N];

            // 두 선거구 각각이 연결되어 있는 지 확인하기
            isVisited[pair.A.get(0)] = true;
            DFS(pair.A, pair.A.get(0));

            isVisited[pair.B.get(0)] = true;
            DFS(pair.B, pair.B.get(0));

            // 연결 체크
            boolean isConnected = true;
            for (int i = 0; i < N; i++) {
                if (!isVisited[i]) {

                    isConnected = false;
                    break;
                }
            }

            // 연결이 안되어있으면 -> 다른 경우의 수
            if (!isConnected) {
                continue;
            }

            // 연결 되어 있으면 계산
            int tmpResult = 0;
            for (int i = 0; i < pair.A.size(); i++) {
                tmpResult += cost[pair.A.get(i)];
            }

            for (int i = 0; i < pair.B.size(); i++) {
                tmpResult -= cost[pair.B.get(i)];
            }

            tmpResult = Math.abs(tmpResult);

            result = Math.min(result, tmpResult);
        }

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        System.out.println(result);

        // 각 구역이 서로 연결되어있는 지 확인

        br.close();
    }
}
