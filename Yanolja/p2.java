package Yanolja;

public class p2 {

    // [start, end] 중복문자 중 비용
    public static int calcCost(String S, int[] C, int start, int end) {
        int cost = 0;

        int maxCost = C[start];

        for (int i = start; i <= end; i++) {
            if (maxCost < C[i]) {
                maxCost = C[i];
            }
            cost += C[i];
        }

        return cost - maxCost;
    }

    public static int solution(String S, int[] C) {
        // Implement your solution here

        int cost = 0;
        // 1. 중복된 문자열 찾기 [start,end]
        char alpha = S.charAt(0);
        int start = 0;
        int end = 0;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == alpha) {
                end = i;
                continue;
            }
            // 다른 경우
            else {
                // 최소비용 찾아내기
                if (end - start > 0) {
                    cost += calcCost(S, C, start, end);
                }

                alpha = S.charAt(i);
                start = i;
                end = i;
            }
        }

        // 마지막에 끝나는 경우
        if (S.charAt(S.length() - 1) == alpha) {
            if (end - start > 0) {
                cost += calcCost(S, C, start, end);
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        // K번째 문자를 삭제 -> C[K] 발생
        int C[] = { 1, 2, 1, 2, 1, 2 };
        int result = solution("aabbcc", C);

        System.out.println(result);
    }
}
