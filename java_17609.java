import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class java_17609 {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String word = br.readLine();

            int result = checkPalindrome(word, 0, word.length() - 1, false);

            System.out.println(result);
        }

        br.close();

    }

    // 입력 문자열이 회문인지, 유사회문인지 판단하는 메소드
    // word, firstIndex, lastIndex, isDeleted
    private static int checkPalindrome(String word, int firstIndex, int lastIndex, boolean isDeleted) {
        // 모든 문자열을 비교했다면.. 종료
        // 유사회문, 회문 판단 처리
        if (lastIndex <= firstIndex) {
            return isDeleted ? 1 : 0;
        }

        // 현재 위치를 비교
        char firstChar = word.charAt(firstIndex);
        char lastChar = word.charAt(lastIndex);

        // 두 문자가 같은 경우
        if (firstChar == lastChar) {
            return checkPalindrome(word, firstIndex + 1, lastIndex - 1, isDeleted);
        }

        // 이미 지운적이 있는 경우 -> 회문, 유사회문이 아님
        if (isDeleted) {
            return 2;
        }

        // 두 문자가 다른 경우
        // 지운적이 없다면..firstChar 또는 lastChar을 지우자
        return Math.min(checkPalindrome(word, firstIndex + 1, lastIndex, true),
                checkPalindrome(word, firstIndex, lastIndex - 1,
                        true));
    }
}
