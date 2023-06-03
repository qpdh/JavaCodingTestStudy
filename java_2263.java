import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_2263 {

    static int n;

    static int inorderTree[];
    static int inorderIndex[];
    static int postorderTree[];

    static class Node {
        int number;
        Node left;
        Node right;
    }

    static void prerder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return;
        }
        // 루트는 postorderTree[-1];

        // 인오더 기준 왼쪽 자식, 오른쪽 자식을 구한다.
        // 8 4 9 2 5 1 6 3 10 7 11

        // 포스트오더 기준 왼쪽 자식, 오른쪽 자식을 구한다.
        // 8 9 4 5 2 6 10 11 7 3 1

        // 1 2 4 8 9 5 3 6 7 10 11

        // 부분 트리의 루트
        int root = postorderTree[postEnd];

        System.out.print(root + " ");

        // 인오더에서 왼쪽 오른쪽을 구분하는 인덱스
        // 이 인덱스 좌, 우로 왼쪽 자식 오른쪽 자식이 구분된다.
        int index = inorderIndex[root];

        //
        // 루트의 왼쪽을 찾아 떠나기
        prerder(inStart, index - 1, postStart, postStart + (index - inStart) - 1);

        // 루트의 오른쪽을 찾아 떠나기
        prerder(index + 1, inEnd, postStart + (index - inStart), postEnd - 1);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        inorderTree = new int[n + 1];
        postorderTree = new int[n + 1];
        inorderIndex = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            inorderTree[i] = Integer.parseInt(st.nextToken());
            inorderIndex[inorderTree[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            postorderTree[i] = Integer.parseInt(st.nextToken());
        }

        // LVR
        // LRV

        prerder(1, n, 1, n);

        br.close();
    }
}
