import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class java_1958 {
    static String firstString, secondString, thirdString;

    static int cache[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        firstString = br.readLine();
        secondString = br.readLine();
        thirdString = br.readLine();

        int result = solution();

        System.out.println(result);

        br.close();
    }

    private static int solution() {
        // 캐시 초기화
        cache = new int[firstString.length()][secondString.length()][thirdString.length()];
        for (int i = 0; i < firstString.length(); i++) {
            for (int j = 0; j < secondString.length(); j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }

        // 시작 인덱스 처리
        int result = 0;

        for (int i = 0; i < firstString.length(); i++) {
            for (int j = 0; j < secondString.length(); j++) {
                for (int k = 0; k < thirdString.length(); k++) {
                    result = Math.max(result, dp(i, j, k));
                }
            }
        }

        return result;
    }

    // firstIndex, secondIndex, thirdIndex에서 시작하는 공통 부분 문자열의 최댓값 찾기
    private static int dp(int firstIndex, int secondIndex, int thirdIndex) {
        // 하나라도 문자열의 끝에 도달하면 0 반환
        if (firstIndex == firstString.length() || secondIndex == secondString.length()
                || thirdIndex == thirdString.length()) {
            return 0;
        }

        // 캐시값이 이미 존재하는 경우
        if (cache[firstIndex][secondIndex][thirdIndex] != -1) {
            return cache[firstIndex][secondIndex][thirdIndex];
        }

        // 캐시 초기화
        cache[firstIndex][secondIndex][thirdIndex] = 0;

        char firstChar = firstString.charAt(firstIndex);
        char secondChar = secondString.charAt(secondIndex);
        char thirdChar = thirdString.charAt(thirdIndex);

        // A, B, C가 모두 일치하는 경우
        // 인덱스 1씩 증가하여 처리
        if (firstChar == secondChar && secondChar == thirdChar) {
            cache[firstIndex][secondIndex][thirdIndex] = Math.max(cache[firstIndex][secondIndex][thirdIndex],
                    1 + dp(firstIndex + 1, secondIndex + 1, thirdIndex + 1));
        }

        // A, B, C가 모두 일치하지 않는 경우
        // firstIndex 증가시켜보기
        // secondIndex 증가시켜보기
        // thirdIndex 증가시켜보기
        else {
            cache[firstIndex][secondIndex][thirdIndex] = Math.max(cache[firstIndex][secondIndex][thirdIndex],
                    dp(firstIndex + 1, secondIndex, thirdIndex));
            cache[firstIndex][secondIndex][thirdIndex] = Math.max(cache[firstIndex][secondIndex][thirdIndex],
                    dp(firstIndex, secondIndex, thirdIndex + 1));
            cache[firstIndex][secondIndex][thirdIndex] = Math.max(cache[firstIndex][secondIndex][thirdIndex],
                    dp(firstIndex, secondIndex + 1, thirdIndex));
        }

        return cache[firstIndex][secondIndex][thirdIndex];
    }
}
