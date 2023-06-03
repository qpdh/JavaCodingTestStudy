public class 안녕히_그리고_물고기는_고마웠어요 {

    final static int ALPHABETS = 26;

    static class TrieNode {
        TrieNode children[];
        // -1이면 종료
        int terminal;

        int first;

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

    static int countKeys(TrieNode trie, String word) {
        TrieNode node = trie.find(word);
        if (node == null || node.terminal == -1)
            return word.length();

        return trie.type(word, node.terminal);
    }

    static class Pair<A, B> {
        A a;
        B b;
    }

    public static void main(String[] args) {
        // 빈도의 내림차, 사전의 오름차 정렬
    }
}
