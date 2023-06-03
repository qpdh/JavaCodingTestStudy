package Street11;

import java.util.HashMap;

public class p1 {
    public static int solution(String S) {
        // Implement your solution here
        HashMap<Character, Boolean> hashMap = new HashMap<>();

        for (int i = 0; i < S.length(); i++) {
            boolean isEvenAlpha = hashMap.getOrDefault(S.charAt(i), false);
            if (isEvenAlpha) {
                hashMap.remove(S.charAt(i));
            } else {
                hashMap.put(S.charAt(i), true);
            }
        }

        // 홀수인 문자 찾기
        int result = hashMap.size();

        return result;
    }

    public static void main(String[] args) {
        // acbcbba -> acbcba
        // axxxaxa
        // axxxa
        // aaaa
        System.out.println(solution("acbcbba"));

    }
}
