import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 보안종결자 {
    final static int ALPHABETS = 26;

    static class TrieNode {
        TrieNode children[];
        // -1이면 종료
        int terminal;
        int first;

        TrieNode fail;
        List<Integer> output;

        public TrieNode() {
            children = new TrieNode[ALPHABETS];
        }

        void insert(String key, int id) {
            if (first == -1)
                first = id;
            if (key.length() == 0) {
                terminal = id;
            } else {
                int next = toNumber(key);

                if (children[next] == null) {
                    children[next] = new TrieNode();
                }

                children[next].insert(key.substring(1), id);
            }
        }

        // 몇 번의 키를 눌러야 하는가?
        int type(String key, int id) {
            if (key.length() == 0)
                return 0;

            if (first == id)
                return 1;
            int next = toNumber(key);
            return 1 + children[next].type(key.substring(1), id);
        }

        int toNumber(String key) {
            return key.charAt(0);
        }

        TrieNode find(String word) {
            if (word.length() == 0) {
                return this;
            }

            return this.children[word.charAt(0)].find(word.substring(1));
        }

    }

    static void computeFailFunc(TrieNode root) {
        Queue<TrieNode> q = new ArrayDeque<>();

        root.fail = root;

        q.add(root);

        while (!q.isEmpty()) {
            TrieNode here = q.poll();
            for (int edge = 0; edge < ALPHABETS; edge++) {
                TrieNode child = here.children[edge];
                if (child == null) {
                    continue;
                }

                if (here == root) {
                    child.fail = root;
                } else {
                    TrieNode t = here.fail;
                    while (t != root && t.children[edge] == null) {
                        t = t.fail;
                    }
                    if (t.children[edge] != null)
                        t = t.children[edge];
                }
                child.output = child.fail.output;
                if (child.terminal != -1) {
                    child.output.add(child.terminal);
                }
                q.add(child);
            }

        }
    }

    static class Pair<A, B> {
        A a;
        B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

    }

    List<Pair<Integer, Integer>> ahoCorasick(String s, TrieNode root) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        TrieNode state = root;

        for (int i = 0; i < s.length(); i++) {
            int chr = s.charAt(0);
            while (state != root && state.children[chr] == null) {
                state = state.fail;
            }
            if (state.children[chr] != null) {
                state = state.children[chr];
            }
            for (int j = 0; j < state.output.size(); j++) {
                result.add(new Pair<Integer, Integer>(i, state.output.get(j)));
            }
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
