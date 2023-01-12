import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_1991 {
    static int N;
    static Node tree[];

    static class Node {
        char me, left, right;

        public Node(char me, char left, char right) {
            this.me = me;
            this.left = left;
            this.right = right;
        }
    }

    static int alphaIntegerConvert(char alpha) {
        return alpha - 'A';
    }


    static void preOrder(char alpha) {
        if (alpha == '.') {
            return;
        }

        int index = alphaIntegerConvert(alpha);

        System.out.print(alpha);
        preOrder(tree[index].left);
        preOrder(tree[index].right);
    }

    static void inOrder(char alpha) {
        if (alpha == '.') {
            return;
        }

        int index = alphaIntegerConvert(alpha);

        inOrder(tree[index].left);
        System.out.print(alpha);
        inOrder(tree[index].right);
    }

    static void postOrder(char alpha) {
        if (alpha == '.') {
            return;
        }

        int index = alphaIntegerConvert(alpha);

        postOrder(tree[index].left);
        postOrder(tree[index].right);
        System.out.print(alpha);
    }

    static void solution() {
        preOrder('A');
        System.out.println();
        inOrder('A');
        System.out.println();
        postOrder('A');
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tree = new Node[26];

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char me = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            Node node = new Node(me, left, right);
            int index = alphaIntegerConvert(me);
            tree[index] = node;
        }

        solution();

        br.close();
    }
}
