import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 카1 {
    static class Solution {
        // A, B, C 약관의 유효기간
        static HashMap<Character, Integer> dueMap;

        // 날짜로 부터 유효기간 계산하기
        // date로부터 due 이후 날짜 출력
        // result 날짜까지 보관 가능하다.
        public String calcExpire(String date, int due) {

            // 년 월 일 분리
            StringTokenizer st = new StringTokenizer(date, ".");
            int year = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            //
            long totalDate = year * 12 * 28 + (month - 1) * 28 + day;
            totalDate += due * 28;

            long resultYear = totalDate / 28 / 12;
            long resultMonth = totalDate / 28 % 12 + 1;
            long resultDay = totalDate % 28 - 1;

            if (resultDay <= 0) {
                resultMonth -= 1;
                resultDay = 28 + resultDay;
            }
            if (resultMonth <= 0) {
                resultYear -= 1;
                resultMonth = 12 + resultMonth;
            }

            System.out.println(date + "의 만기 날짜 " + resultYear + "/" + resultMonth + "/" + resultDay);

            return resultYear + "." + resultMonth + "." + resultDay;
        }

        // 삭제되어야 하는지 판단하기, 유효기간이 지났는가
        public boolean isDeleted(String today, String expireDate, int due) {
            String dueDate = calcExpire(expireDate, due);
            // System.out.println("만료 전 마지막 날짜" + dueDate);

            // 날짜가 지났는지 판단하자
            StringTokenizer todayTokenizer = new StringTokenizer(today, ".");
            StringTokenizer dueDateTokenizer = new StringTokenizer(dueDate, ".");

            // 년도 비교
            int todayYear = Integer.parseInt(todayTokenizer.nextToken());
            int dueDateYear = Integer.parseInt(dueDateTokenizer.nextToken());

            // 오늘 년도보다 이전에 만기가 됐다면..
            if (dueDateYear < todayYear) {
                return true;
            }
            if (todayYear < dueDateYear) {
                return false;
            }

            int todayMonth = Integer.parseInt(todayTokenizer.nextToken());
            int dueDateMonth = Integer.parseInt(dueDateTokenizer.nextToken());

            // 오늘 달보다 이전에 만기가 됐다면..
            if (dueDateMonth < todayMonth) {
                return true;
            }
            if (todayMonth < dueDateMonth) {
                return false;
            }

            int todayDate = Integer.parseInt(todayTokenizer.nextToken());
            int dueDateDate = Integer.parseInt(dueDateTokenizer.nextToken());

            // 오늘 날짜보다 이전에 만기가 됐다면..
            if (dueDateDate < todayDate) {
                return true;
            }
            if (todayDate < dueDateDate) {
                return false;
            }

            return false;
        }

        public int[] solution(String today, String[] terms, String[] privacies) {
            // for (int i = 1; i <= 100; i++) {
            // System.out.println(calcExpire("2021.05.01", i));
            // }
            // calcExpire("2021.05.02", 6);
            // calcExpire("2021.07.01", 6);
            // calcExpire("2022.02.19", 3);
            // calcExpire("2022.02.20", 3);

            ArrayList<Integer> resultArrayList = new ArrayList<>();

            dueMap = new HashMap<>();

            // 기간 데이터 삽입
            for (int i = 0; i < terms.length; i++) {
                StringTokenizer st = new StringTokenizer(terms[i]);
                char term = st.nextToken().charAt(0);
                int due = Integer.parseInt(st.nextToken());

                dueMap.put(term, due);
            }

            for (int i = 0; i < privacies.length; i++) {
                int number = i + 1;
                StringTokenizer st = new StringTokenizer(privacies[i]);

                String date = st.nextToken();
                char term = st.nextToken().charAt(0);

                // 삭제되어야 한다면 삽입
                if (isDeleted(today, date, dueMap.get(term))) {
                    System.out.println(date + "만기 " + number);
                    resultArrayList.add(number);
                }

            }

            // arrayList to 배열
            int result[] = new int[resultArrayList.size()];
            for (int i = 0; i < resultArrayList.size(); i++) {
                result[i] = resultArrayList.get(i);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        // n개의 개인 정보
        // 약관 마다 보관 유효기간 존재
        // 유효기간이 지나면 파기

        // A 약관이 12달 2021 1월 5일 이라면 2022 1월 4일까지 보관 가능

        // 모든 달은 28일까지 있다고 가정
        Solution solution = new Solution();
        String terms[] = { "A 6", "B 12", "C 3" };
        // String terms[] = { "Z 3", "D 5" };
        String privacies[] = { "2021.05.02 A", "2021.07.01 B", "2022.02.19 C",
                "2022.02.20 C" };
        // String privacies[] = { "2019.01.01 D", "2019.11.15 Z", "2019.08.02 D",
        // "2019.07.01 D", "2018.12.28 Z" };

        solution.solution("2022.05.19", terms, privacies);
    }
}
