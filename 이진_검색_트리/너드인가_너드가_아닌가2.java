package 이진_검색_트리;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class 너드인가_너드가_아닌가2 {
    static TreeMap<Integer, Integer> people;

    static boolean isDominated(int x, int y) {
        // x보다 오른쪽에 있는 점 중 가장 왼쪽의 점을 찾는다.
        Entry<Integer, Integer> entry = people.higherEntry(x);

        // 오른쪽에 점이 없다. -> 입력된 x가 가장 큰 값이다. -> 삽입
        if (entry == null) {
            return false;
        }

        // x보다 오른쪽에 있는 점의 y는 입력된 y보다 작아야 한다.
        return y < entry.getValue();
    }

    // 지배된느 점들을 삭제하는 메소드
    static void removeDominated(int x, int y) {
        Entry<Integer, Integer> entry = people.lowerEntry(x);

        // (x, y)보다 왼쪽에 있는 점이 없으면, 종료
        if (entry == null) {
            return;
        }

        while (true) {
            entry = people.lowerEntry(x);
            if (entry == null) {
                break;
            }
            // 내 왼쪽에 있는 점이 (x,y)에 지배되지 않으면 종료
            // 그 왼쪽은 이미 처리됐으므로..
            if (entry.getValue() > y) {
                break;
            }

            people.remove(entry.getKey());
        }
    }

    static int registered(int x, int y) {
        if (isDominated(x, y)) {
            return people.keySet().size();
        }

        removeDominated(x, y);
        people.put(x, y);
        return people.keySet().size();
    }
}
