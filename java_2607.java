import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class java_2607 {
    // 입력되는 단어의 갯수
    static int N;
    // 첫 번째 단어의 26자리 알파벳 각 갯수 저장
    static int[] alphaList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 26자리 배열 생성

        // 각 알파벳마다 가진 갯수 저장

        // 입력값 마다 26자리 배열 생성 -> 인덱스의 값을 확인 -> 같거나, 차이가 1만 나는 경우 비슷한 단어 카운트

        N = Integer.parseInt(br.readLine());

        int result = 0;

        alphaList = new int[26];

        // 첫 번째 단어 배열에 추가
        String firstWord = br.readLine();
        for (int i = 0; i < firstWord.length(); i++) {
            alphaList[firstWord.charAt(i) - 'A'] += 1;
        }

        // 나머지 단어 처리
        for (int i = 1; i < N; i++) {
            int targetAlphaList[] = Arrays.copyOf(alphaList, 26);
            String targetWord = br.readLine();

            // 입력 알파벳 카운트 갱신
            for (int j = 0; j < targetWord.length(); j++) {
                targetAlphaList[targetWord.charAt(j) - 'A'] -= 1;
            }

            // 배열 내용의 합
            int tmpResult = 0;
            for (int j = 0; j < targetAlphaList.length; j++) {
                tmpResult += Math.abs(targetAlphaList[j]);
            }

            // 한 글자만 바꾸는 경우 -> +1 -1 == 0
            // 글자의 배열만 다른 경우 -> 0
            if (firstWord.length() == targetWord.length()) {
                if (tmpResult == 0 || tmpResult == 2) {
                    result += 1;
                }
            }

            // 추가해야 같아지는 경우
            // 삭제해야 같아지는 경우
            // -> 글자수가 1개 차이가 난다.
            else if (Math.abs(firstWord.length() - targetWord.length()) == 1) {
                if (tmpResult == 1) {
                    result += 1;
                }
            }

        }

        System.out.println(result);

    }
}
