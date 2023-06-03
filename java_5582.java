import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class java_5582 {
    static String firstWord, secondWord;
    static int cache[][];

    // firstIndex 시작하고, secondIndex(포함) 시작하는 공통 부분 문자열의 최댓값 저장
    // abcabcabc
    // bcbcbc
    // bcbcbc
    static int dp(int firstIndex, int secondIndex) {
        if (firstIndex >= firstWord.length() || secondIndex >= secondWord.length()) {
            return 0;
        }

        if (cache[firstIndex][secondIndex] != -1) {
            return cache[firstIndex][secondIndex];
        }

        // 다른 경우
        if (firstWord.charAt(firstIndex) != secondWord.charAt(secondIndex)) {
            return 0;
        }

        cache[firstIndex][secondIndex] = 0;

        // 현재 인덱스의 문자 값이 같은가?

        cache[firstIndex][secondIndex] = Math.max(
                cache[firstIndex][secondIndex], 1 + dp(firstIndex + 1, secondIndex + 1));

        return cache[firstIndex][secondIndex];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 두 문자열을 입력 받음

        // first 에서 시작하는 인덱스를 i
        // second 에서 시작하는 인덱스를 j

        // 두 문자의 공통 부분 문자열 체크
        // DP 처리

        firstWord = br.readLine();
        secondWord = br.readLine();

        cache = new int[firstWord.length()][secondWord.length()];

        for (int i = 0; i < firstWord.length(); i++) {
            Arrays.fill(cache[i], -1);
        }

        int result = 0;
        for (int i = 0; i < firstWord.length(); i++) {
            for (int j = 0; j < secondWord.length(); j++) {
                result = Math.max(result, dp(i, j));
            }
        }

        System.out.println(result);

        br.close();
    }
}
