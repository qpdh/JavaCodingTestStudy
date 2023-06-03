import java.util.ArrayList;

public class 카3 {
    static class Solution {

        static ArrayList<String> combinationList = new ArrayList<>();

        static void combination(int n, int r, int selected) {
            if (n == r) {
                combinationList.add(selected + "");
                return;
            }
            // r번째를 뽑을 차례
            for (int i = 1; i <= 4; i++) {
                combination(n, r + 1, selected + (int) Math.pow(10, r) * i);
            }
        }

        public int[] solution(int[][] users, int[] emoticons) {
            int[] answer = { 0, 0 };

            combination(emoticons.length, 0, 0);

            // 4^7 번 하기? 2^14 1024 2048 10000번?

            for (int i = 0; i < combinationList.size(); i++) {
                int subCount = 0;
                int cost = 0;

                for (int[] user : users) {
                    long sumCost = 0;

                    for (int j = 0; j < emoticons.length; j++) {
                        if ((combinationList.get(i).charAt(j) - '0') * 10 >= user[0]) {
                            sumCost += emoticons[j] * (10 - (combinationList.get(i).charAt(j) - '0')) / 10;
                        }
                    }

                    // 구매를 하게 되는 경우
                    if (sumCost < user[1]) {
                        cost += sumCost;

                    }
                    // 구독을 하게 되는 경우
                    else {
                        subCount++;
                    }
                    // System.out.println("가격 : " + sumCost);

                    // i번째 이모티콘을 몇 %할인할 것인가?

                    // 가입자를 늘리기 위해선 무조건 낮은 %해서 가능한지 판단해보기

                    // 최소 할인 가격이 사용자가 생각한 가격 이상이 된다면... 플러스에 가입

                }

                if (answer[0] < subCount) {
                    answer[0] = subCount;

                    answer[1] = cost;
                } else if (answer[0] == subCount) {
                    answer[0] = subCount;
                    if (answer[1] < cost) {
                        answer[1] = cost;
                    }

                }
            }

            System.out.println(answer[0] + " " + answer[1]);

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] users = { { 40, 10000 }, { 25, 10000 } };

        int[] emoticons = { 7000, 9000 };
        solution.solution(users, emoticons);
        // 가입자를 최대한 늘리기
        // 판매액 최대로 늘리기

        // n명의 사람에게 m개를 할인하여 판매
        // 이모티콘마다 할인율 다름

        // 사용자는 일정 비율 이상 할인하는 이모티콘을 모두 구매
        // 구매 합이 일정 이상 된다면 취소하고 플러스에 가입

        // 10 20 30 40 중 하나로 설정된다.
        // 입력값이 아님.
    }
}
