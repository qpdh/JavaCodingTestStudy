import java.util.ArrayDeque;
import java.util.Deque;

public class 카4 {
    static class Solution {
        static Node tree;
        static int orderIndex;
        static StringBuffer binaryInteger;

        static class Node {
            Node left;
            Node right;
            Node parent;
            int value;
            int level;

            public Node(int level, Node parent) {
                this.value = -1;
                this.level = level;

                this.parent = parent;

                this.left = null;
                this.right = null;
            }
        }

        static void makeTree(Node now, int nowHeight, int maxHeight) {
            if (nowHeight < maxHeight) {
                now.left = new Node(nowHeight, now);
                now.right = new Node(nowHeight, now);
                makeTree(now.left, nowHeight + 1, maxHeight);
                makeTree(now.right, nowHeight + 1, maxHeight);
            }
        }

        static boolean levelOrder(Node root, int maxHeight) {
            Deque<Node> deque = new ArrayDeque<>();

            deque.add(root);

            while (!deque.isEmpty()) {
                Node now = deque.removeFirst();
                if (now.value == 1) {
                    if (now.parent != null) {
                        if (now.parent.value == 0) {

                            return false;
                        }
                    }

                }

                if (now.left != null) {
                    deque.add(now.left);
                }
                if (now.right != null) {
                    deque.add(now.right);
                }
            }

            return true;
        }

        static void inorder(Node now) {
            if (now == null) {
                return;
            }
            inorder(now.left);
            now.value = binaryInteger.charAt(orderIndex++) - '0';
            // System.out.println(now.value);
            inorder(now.right);
        }

        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];

            // 1차원 정수 배열이 주어짐
            for (int i = 0; i < numbers.length; i++) {
                // 해당 숫자를 이진수로 바꿨을 때 갯수가 포화트리의 갯수와 일치해야 함
                // 포화트리 노드의 갯수
                // 1 -> 1
                // 2 -> 3
                // 3 -> 7
                // 4 -> 15

                // n -> 2^n -1

                tree = new Node(0, null);
                orderIndex = 0;

                binaryInteger = new StringBuffer();

                while (numbers[i] > 0) {
                    binaryInteger.append((numbers[i] % 2) + "");
                    numbers[i] /= 2;
                }

                // binaryInteger = Long.toBinaryString(numbers[i]);
                // System.out.println(binaryInteger);
                long binaryLength = binaryInteger.length();
                // System.out.println(binaryLength);

                // 가장 가까운 높이 n 계산
                long count = 1;
                int height = 0;
                while (count <= binaryLength) {
                    count *= 2;
                    height++;
                }
                count -= 1;

                // System.out.println("포화 트리의 크기 : " + count);

                // count 될 때 까지 앞에 0채우기
                while (binaryInteger.length() < count) {
                    binaryInteger.append("0");
                }
                binaryInteger.reverse();

                // System.out.println(binaryInteger);

                makeTree(tree, 1, height);
                inorder(tree);

                // 자식은 1인데 부모가 0이면 만들 수 없음
                boolean result = levelOrder(tree, height);
                if (result) {
                    answer[i] = 1;
                    // System.out.println(numbers[i] + " 만들 수 있음");
                } else {
                    answer[i] = 0;
                    // System.out.println(numbers[i] + " 만들 수 없음");
                }

            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        long numbers[] = { 2 };

        solution.solution(numbers);
        // 이진수를 저장할 빈 문자열 생성
        // 더미 노드 추가 포화 이진 트리 생성

        // 더미 노드라면 문자열 뒤에 0 추가
        // 더미노드가 아니라면 문자열 뒤에 1 추가

        // 이진수를 십진수로 변환

        // 전위우선 탐색
    }
}
