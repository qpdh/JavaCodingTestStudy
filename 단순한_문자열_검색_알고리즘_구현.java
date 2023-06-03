import java.util.ArrayList;
import java.util.List;

public class 단순한_문자열_검색_알고리즘_구현 {

    // H의 부분 문자열로 N이 출현하는 시작위치들을 모두 반환한다.
    public static List<Integer> naiveSearch(String H, String N) {
        List<Integer> result = new ArrayList<>();

        // 모든 시작 위치를 다 시도해 본다.
        for (int begin = 0; begin + N.length() <= H.length(); begin++) {
            boolean matched = true;

            // N문자열이 H의 begin 인덱스부터 전부 들어가는 지 체크
            for (int i = 0; i < N.length(); i++) {
                if (H.charAt(begin + i) != N.charAt(i)) {
                    matched = false;
                    break;
                }
            }

            if (matched) {
                result.add(begin);
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
