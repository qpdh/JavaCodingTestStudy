import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_11725 {
    static int N;

    // tree의 한 노드의 자식만을 가진다.
    static ArrayList<ArrayList<Integer>> tree;

    // 해당 번호의 노드가 부모가 있는지 확인
    static int hasParent[];

    //
    static boolean isVisited[];

    public static void dfs(int parent, int thisNode) {
        for (int i = 0; i < tree.get(thisNode).size(); i++) {
            int targetNode = tree.get(thisNode).get(i);
            if (hasParent[targetNode] == -1) {
                hasParent[targetNode] = thisNode;
            }

            if (!isVisited[targetNode]) {
                isVisited[targetNode] = true;
                dfs(thisNode, targetNode);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 부모가 둘 일 순 없다.
        N = Integer.parseInt(br.readLine());

        // 트리 초기화
        tree = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        hasParent = new int[N];
        Arrays.fill(hasParent, -1);
        hasParent[0] = 0;

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;

            // 둘 중 하나의 노드의 부모가 존재한다면.. 다른것은 무조건 자식이다.
            if (hasParent[first] != -1) {
                tree.get(first).add(second);
                hasParent[second] = first;
            } else if (hasParent[second] != -1) {
                tree.get(second).add(first);
                hasParent[first] = second;
            }

            // 둘 다 부모의 정보가 있는 경우는 트리가 아님
            // 둘 다 부모의 정보가 없으면.. 둘다 추가
            else {
                tree.get(second).add(first);
                tree.get(first).add(second);
            }
        }

        isVisited = new boolean[N];
        dfs(0, 0);

        for (int i = 1; i < N; i++) {
            System.out.println(hasParent[i] + 1);
        }

        br.close();
    }
}
