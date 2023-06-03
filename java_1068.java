import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_1068 {
    static int N;

    static int tree[];

    static int deleteNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        tree = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        deleteNode = Integer.parseInt(br.readLine());

        int result = solution();

        System.out.println(result);

        br.close();
    }

    private static int solution() {
        // 노드 전부를 돈다.
        // 만약 deleteNode를 만난다면 경로를 추가하지 않는다.
        // 중간 중간 방문하는 노드는 리프노드 false 처리 한다.
        // count 한다.

        // O(n^2)

        int result = 0;

        boolean isLeafNode[] = new boolean[N];
        Arrays.fill(isLeafNode, true);

        // 자식 노드를 가진 노드를 전부 false처리하자
        for (int i = 0; i < N; i++) {
            if (tree[i] == -1) {
                continue;
            }
            if (i == deleteNode) {
                continue;
            }
            isLeafNode[tree[i]] = false;
        }

        for (int i = 0; i < N; i++) {
            if (isLeafNode[i]) {
                result += dfs(i);
            }
        }

        return result;
    }

    private static int dfs(int i) {
        // 현재 위치가 루트인 경우 -> 1 반환
        if (i == -1) {
            return 1;
        }

        // 현재 위치가 deleteNode 인 경우
        if (i == deleteNode) {
            return 0;
        }

        return dfs(tree[i]);
    }
}
