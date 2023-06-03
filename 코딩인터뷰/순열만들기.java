package 코딩인터뷰;

import java.util.HashMap;

// 대소문자, 빈글자 무시
//
public class 순열만들기 {

    // Tact Coa -> tactcoa
    static boolean checkPalinPerm(String word) {
        // 전처리 word의 글자수가 홀수일 때 -> 문자가 홀수개인 문자가 1개, 나머진 짝수개가 있어야 함

        word = word.toLowerCase();
        HashMap<Character, Boolean> map = new HashMap<>();

        int charCount = 0;

        for (int i = 0; i < word.length(); i++) {
            // 공백일 경우 무시
            if (word.charAt(i) == ' ') {
                continue;
            }

            // 문자 갯수 삽입
            if (map.getOrDefault(word.charAt(i), false)) {
                map.remove(word.charAt(i));
            } else {
                map.put(word.charAt(i), true);
            }

            charCount++;
        }

        // 전처리 word의 글자수가 짝수일 때 -> 문자의 갯수 각각 짝수개 있어야 함
        if (charCount % 2 == 0) {
            return map.keySet().size() == 0;
        } else {
            return map.keySet().size() == 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(checkPalinPerm("Tact Coa"));
        System.out.println(checkPalinPerm("Tact Boa"));
        System.out.println(checkPalinPerm("aabbc"));
        System.out.println(checkPalinPerm("aabc"));
    }
}
