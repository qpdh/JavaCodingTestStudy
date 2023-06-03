import java.security.Key;

public class 트립의_구현 {
    static class Node<KeyType> {
        KeyType key;

        // 이 노드의 우선순위(priority)
        // 이 노드를 루트로 하는 서브트리의 크기(size)
        int priority, size;

        // 두 자식 노드의 레퍼런스
        Node<KeyType> left, right;

        // 난수 우선순위 생성
        Node(KeyType key) {
            priority = (int) Math.random() * 10000;
            this.key = key;
            size = 1;
            left = null;
            right = null;
        }

        public void setLeft(Node<KeyType> newLeft) {
            left = newLeft;
            calcSize();
        }

        public void setRight(Node<KeyType> newRight) {
            right = newRight;
            calcSize();
        }

        public void calcSize() {
            size = 1;
            if (left != null) {
                size += left.size;
            }
            if (right != null) {
                size += right.size;
            }
        }

    }

    static class NodePair {
        Node<Integer> first, second;

        public NodePair(Node<Integer> first, Node<Integer> second) {
            this.first = first;
            this.second = second;
        }
    }

    static NodePair split(Node<Integer> root, int key) {
        if (root == null) {
            return new NodePair(null, null);
        }
        // 루트가 key 미만이면 오른쪽 서브트리를 쪼갠다
        if (root.key < key) {
            NodePair rs = split(root.right, key);
            root.setRight(rs.first);
            return new NodePair(root, rs.second);
        }

        // 루트가 key 이상이면 왼쪽 서브트리를 쪼갠다.
        NodePair ls = split(root.left, key);
        root.setLeft(ls.second);
        return new NodePair(ls.first, root);
    }

    public static void main(String[] args) {

    }
}
