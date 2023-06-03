import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 신고_결과_받기 {
    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 몇 번 신고 당했는지 체크
        HashMap<String, Integer> decalrationMap = new HashMap<>();
        // 중복 체크 신고 방지
        HashMap<String, Boolean> isChecked = new HashMap<>();
        // 이메일을 몇개 받았는지 체크
        HashMap<String, Integer> emailCount = new HashMap<>();
        // to을 신고한 유저 목록
        HashMap<String, HashSet<String>> declare = new HashMap<>();

        for (int i = 0; i < report.length; i++) {

            if (!isChecked.getOrDefault(report[i], false)) {
                StringTokenizer st = new StringTokenizer(report[i]);
                String from = st.nextToken();
                String to = st.nextToken();

                isChecked.put(report[i], true);

                decalrationMap.put(to, decalrationMap.getOrDefault(to, 0) + 1);
                if (declare.get(to) == null) {
                    declare.put(to, new HashSet<>());
                }
                declare.get(to).add(from);
            }
        }

        for (String key : decalrationMap.keySet()) {
            // k 번 이상 신고 당하면 정지
            // 신고한 유저에게 이메일 보내기
            if (decalrationMap.get(key) >= k) {
                for (String from : declare.get(key)) {
                    // 해당 유저를 신고했다면...
                    emailCount.put(from, emailCount.getOrDefault(from, 0) + 1);

                }
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            answer[i] = emailCount.getOrDefault(id_list[i], 0);
        }

        return answer;
    }

    public static void main(String[] args) {
        // 누가 누구를 신고를 했는가를 저장해야 함 (중복 신고 방지)
        // 해당 유저가 몇 번 신고를 당했는지 저장해야 함
        // 신고 처리 이후 k 이상 신고 당한 유저 검색

        String[] id_list = {
                "muzi", "frodo", "apeach", "neo"
        };

        String[] report = {
                "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"
        };

        int k = 2;

        solution(id_list, report, k);
    }
}
