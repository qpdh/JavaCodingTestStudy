import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 카5 {
    static StringBuffer table[][] = new StringBuffer[50][50];

    static HashMap<String, ArrayList<Pair>> updateMap = new HashMap<>();
    static HashMap<Pair, Pair> mergeMap = new HashMap<>();

    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {
            Pair pair2 = (Pair) obj;
            // TODO Auto-generated method stub
            if (pair2.y == this.y && pair2.x == this.x) {
                return true;
            }
            return false;
        }

    }

    static void update(int r, int c, String value) {
        int y = r - 1;
        int x = c - 1;

        ArrayList<Pair> toCopyList = updateMap.getOrDefault(value, new ArrayList<>());
        toCopyList.add(new Pair(r, c));

        table[y][x] = new StringBuffer(value);

    }

    // value1을 가진 셀을 모두 value2로 바꿈
    static void update(String value1, String value2) {
        ArrayList<Pair> pairList = updateMap.get(value1);

        for (Pair pair : pairList) {
            int y = pair.y;
            int x = pair.x;
            table[y][x] = new StringBuffer(value2);
        }

        ArrayList<Pair> toCopyList = updateMap.getOrDefault(value2, new ArrayList<>());

        toCopyList.addAll(pairList);
        updateMap.remove(value1);
    }

    static void merge(int r1, int c1, int r2, int c2) {
        // 병합하기
        Pair parent1 = mergeMap.get(new Pair(r1 - 1, c1 - 1));
        Pair parent2 = mergeMap.get(new Pair(r1 - 2, c1 - 2));

    }

    static void unmrge(int r, int c) {
        
    }

    static void print(int r, int c) {

    }

    static class Solution {
        public String[] solution(String[] commands) {
            String[] answer = {};

            for (String command : commands) {
                StringTokenizer st = new StringTokenizer(command);

                String com = st.nextToken();

                switch (com) {
                    case "UPDATE":
                        if (st.countTokens() == 3) {
                            update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken());
                        } else if (st.countTokens() == 2) {
                            update(st.nextToken(), st.nextToken());
                        }
                        break;
                    case "MERGE":
                        break;
                    case "UNMERGE":
                        break;
                    case "PRINT":
                        break;
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        // r = y-1
        // c = x-1
        Solution solution = new Solution();

        String[] commands = { "UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean",
                "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant",
                "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4",
                "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4" };
        solution.solution(commands);
    }
}
