import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_2644 {
    static ArrayList<ArrayList<Integer>> adjList;

    public static int bfs(int x, int y) {

        // x로부터 촌수 계산
        int villager[] = new int[adjList.size()];
        // 촌수를 -1로 초기화
        Arrays.fill(villager, -1);

        Queue<Integer> queue = new LinkedList<>();

        // 시작 위치 저장
        // 시작 촌수를 0으로
        villager[x] = 0;
        queue.add(x);

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            // System.out.println("현재 위치 : " + nowNode);

            for (int i = 0; i < adjList.get(nowNode).size(); i++) {
                // 방문하지 않은 상태이면...
                if (villager[adjList.get(nowNode).get(i)] == -1) {
                    villager[adjList.get(nowNode).get(i)] = villager[nowNode] + 1;
                    // 갈 수 있는 노드 삽입
                    queue.add(adjList.get(nowNode).get(i));
                }
            }
        }

        // x에서 y로 갈 수 없으면...
        // -1 반환
        return villager[y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 사람들의 수 입력받기
        int n = Integer.parseInt(br.readLine());

        // 인접 리스트 생성
        adjList = new ArrayList<>();

        // 인접 리스트 데이터 삽입
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // 촌수를 계산할 두 사람 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 두 사람도 자연수 이므로 1씩 빼준다.
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            // 부모 자식 관계 대입
            // 1뺀 값으로 저장
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        // 함수 호출
        int ret = bfs(x, y);

        // 결과 출력
        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
