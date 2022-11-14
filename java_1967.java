import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class java_1967 {
    static int n;

    static ArrayList<Node> tree;

    static List<Integer> leafNode;

    static boolean[] isVisited;

    static class Node {
        boolean isLeafNode;
        List<Pair> adj;

        public Node() {
            this.isLeafNode = true;
            this.adj = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node [isLeafNode=" + isLeafNode + ", adj=" + adj + "]";
        }

    }

    static class Pair {
        int cost, child;

        public Pair(int cost, int child) {
            this.cost = cost;
            this.child = child;
        }

        @Override
        public String toString() {
            return "Pair [cost=" + cost + ", child=" + child + "]";
        }
    }

    // 현재 노드로부터 자식 노드
    static int calcRadius(int node) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(0);
        priorityQueue.add(0);

        for (int i = 0; i < tree.get(node).adj.size(); i++) {
            int targetNode = tree.get(node).adj.get(i).child;

            isVisited = new boolean[n];
            isVisited[targetNode] = true;
            isVisited[node] = true;
            int tmp = calcLength(targetNode) + tree.get(node).adj.get(i).cost;

            priorityQueue.add(tmp);
            priorityQueue.poll();
        }

        return priorityQueue.poll() + priorityQueue.poll();
    }

    // node를 루트로하는 가장 긴 길이 반환
    static int calcLength(int here) {
        if (tree.get(here).isLeafNode) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < tree.get(here).adj.size(); i++) {
            if (!isVisited[tree.get(here).adj.get(i).child]) {
                isVisited[tree.get(here).adj.get(i).child] = true;
                result = Math.max(result, calcLength(tree.get(here).adj.get(i).child) + tree.get(here).adj.get(i).cost);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        // 한 말단에서 다른 말단 까지 거리

        // 두 노드의 최소 공통 조상 찾기

        tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new Node());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()) - 1;
            int child = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            tree.get(parent).adj.add(new Pair(cost, child));
            tree.get(child).adj.add(new Pair(cost, parent));
            tree.get(parent).isLeafNode = false;
        }

        // for (int i = 0; i < tree.size(); i++) {
        // System.out.println(tree.get(i));
        // }

        int result = 0;
        // 현재 노드로부터 왼쪽 최대합 + 오른쪽 최대합 구하기
        for (int i = 0; i < n; i++) {
            int radius = calcRadius(i);
            result = Math.max(result, radius);
        }

        System.out.println(result);

        br.close();
    }
}
