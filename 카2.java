public class 카2 {
    static class Solution {
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            long answer = 0;

            // done count == n*2 면 종료
            int doneCount = 0;

            // 최대 배달 거리
            int maxDeliveryIndex = 0;
            // 최대 픽업 거리
            int maxPickupIndex = 0;

            for (int i = 0; i < n; i++) {
                if (deliveries[i] == 0) {
                    doneCount++;
                } else {
                    maxDeliveryIndex = i;
                }
                if (pickups[i] == 0) {
                    doneCount++;
                } else {
                    maxPickupIndex = i;
                }

            }

            while (doneCount < n * 2) {
                // 1. 배달하기
                // 가장 먼 곳 우선 배달
                // 남은 배송수량 만큼 가져갈 것
                long nowCapacity = 0;

                // 먼 곳부터 어디어디 배달 할 지 정하자
                // i에 .get(i) 만큼 배달한다.

                int toIndex = 0;
                for (int i = maxDeliveryIndex; i >= 0; i--) {
                    if (nowCapacity == cap) {
                        break;
                    }
                    if (deliveries[i] == 0) {
                        continue;
                    }
                    // 어디까지 가는가?
                    if (deliveries[i] != 0) {
                        toIndex = Math.max(toIndex, i);
                    }

                    // 배달해야 하는 양과 총 가져갈 수 있는 양을 구하자
                    if (cap - nowCapacity < deliveries[i]) {

                        // 일부 배달
                        deliveries[i] -= cap - nowCapacity;

                        nowCapacity = cap;

                    } else {

                        nowCapacity += deliveries[i];
                        // 배달 완료
                        deliveries[i] = 0;
                        maxDeliveryIndex = i;
                        doneCount++;
                    }
                }

                // 어디까지 이동하는가?
                // 배열의 마지막 까지 추가로 이동해야 하는 경우
                //
                answer += toIndex + 1;

                // 배달 전부 처리
                nowCapacity = 0;

                // 픽업 준비
                for (int i = maxPickupIndex; i >= 0; i--) {
                    if (nowCapacity == cap) {
                        break;
                    }
                    if (pickups[i] == 0) {
                        continue;
                    }
                    // 어디까지 가는가?
                    if (pickups[i] != 0) {
                        if (toIndex < i) {
                            answer += i - toIndex;
                        }
                        toIndex = Math.max(toIndex, i);
                    }

                    // 픽업해야 하는 양과 총 가져갈 수 있는 양을 구하자
                    if (cap - nowCapacity < pickups[i]) {

                        // 일부 픽업
                        pickups[i] -= cap - nowCapacity;

                        nowCapacity = cap;

                    } else {

                        nowCapacity += pickups[i];

                        // 전부 픽업
                        pickups[i] = 0;
                        maxPickupIndex = i;
                        doneCount++;
                    }
                }

                answer += toIndex + 1;

            }

            System.out.println(answer);

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int cap = 4;
        int n = 5;
        int deliveries[] = { 1, 0, 3, 1, 2 };
        int pickups[] = { 0, 3, 0, 4, 0 };
        solution.solution(cap, n, deliveries, pickups);

        // n개의 집에 택배 배달
        // 빈 재활용 택배 상자 수거
        // i번째 집은 i만큼 떨어져 있음 [0]-[2] 2만큼 떨어져 있음

        // 최대 cap개를 실을 수 있다.

        // 배달과 동시에 빈 상자 수거해서 물류창고에 내림

        // 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아오는 최소 이동 거리

        // 먼 거리부터 갔다가 오자.

        // 수거는 가까운 것 우선.
    }
}
