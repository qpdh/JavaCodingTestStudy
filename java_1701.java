import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class java_1701 {

    // pi[i]=N[..i]의 접미사도 되고 접두사도 되는 문자열의 최대 길이
    static int getPartialMatch(CharSequence N) {
        int m = N.length();

        int pi[] = new int[m];

        // KMP로 자기 자신을 찾는다.
        // N을 N에서 찾는다. begin=0이면 자기 자신을 찾아버리니까 의미 없음

        int begin = 1, matched = 0;

        // 비교할 문자가 N의 끝에 도달할 때까지 찾으면서 부분 일치를 모두 기록한다.
        while (begin + matched < m) {
            // 현재 문자가 일치하는가?
            if (N.charAt(begin + matched) == N.charAt(matched)) {
                matched++;
                // 문자열의 갱신된 길이 반환
                pi[begin + matched - 1] = matched;
            }
            // 일치하지 않는다면 -> 다음 문자부터 비교 시작
            else {
                // 일치한게 없었다면 -> 단순 다음 문자부터
                if (matched == 0) {
                    begin++;
                }
                // 일치한게 존재했다면..이전 값을 이용하여 갱신해주기
                else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        int result = 0;
        for (int i = 0; i < pi.length; i++) {
            result = Math.max(result, pi[i]);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        // 가장 긴 문자열(length-1)부터 부분 문자열이 있는지 찾아보자
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuffer N = new StringBuffer(br.readLine());

        int result = solution(N);

        System.out.println(result);

        br.close();

    }

    // N의 부분 문자열 중 2번 이상 나오는 문자열의 최대 길이 반환
    private static int solution(StringBuffer hayStack) {
        int result = 0;

        // 길이는 N.length()-1부터 시작한다.
        for (int startIndex = 0; startIndex < hayStack.length(); startIndex++) {
            CharSequence needle = hayStack.subSequence(startIndex, hayStack.length());
            result = Math.max(result, getPartialMatch(needle));
        }

        return result;
    }
}
