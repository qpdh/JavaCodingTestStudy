import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class java_9934 {
    static int K;
    static int tree[];
    static Deque<Integer> queue;
    static boolean isVisited[];

    public static void trap(int thisNode) {
        if (thisNode == 0) {
            return;
        }
        // 현재 빌딩과 왼쪽, 오른쪽 자식에 있는 빌딩을 모두 방문 했다면..
        if (thisNode * 2 >= tree.length || tree[thisNode * 2] != 0) {
            if (thisNode * 2 + 1 >= tree.length || tree[thisNode * 2 + 1] != 0) {
                if (tree[thisNode] != 0) {
                    trap(thisNode / 2);
                }
            }
        }

        // 왼쪽 자식의 빌딩에 들어가지 않았다면 왼쪽 자식으로 이동
        try {
            if (tree[thisNode * 2] == 0) {
                trap(thisNode * 2);
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        // 왼쪽 자식을 갖고 있지 않거나, 왼쪽 자식에 있는 빌딩을 이미 들어갔다면, 현재 노드에 있는 빌딩 들어가기
        if (thisNode * 2 >= tree.length || tree[thisNode * 2] != 0) {
            tree[thisNode] = queue.poll();
        }

        // 현재 빌딩을 이미 갔다 온 상태이고, 오른쪽 자식을 가지는 경우 -> 오른쪽 자식으로 이동
        if (tree[thisNode] != 0) {
            if (thisNode * 2 + 1 < tree.length) {
                trap(thisNode * 2 + 1);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 깊이가 K인 완전 이진 트리 -> 포화 이진 트리
        // 2^K-1 개의 노드 -> 1차원 배열 사용
        K = Integer.parseInt(br.readLine());

        tree = new int[(int) Math.pow(2, K)];

        queue = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < tree.length - 1; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }

        trap(1);

        int targetCount = 0;
        int count = 0;
        for (int i = 1; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
            count++;
            if (count == Math.pow(2, targetCount)) {
                System.out.println();
                targetCount++;
                count = 0;
            }
        }

        br.close();
    }
}
