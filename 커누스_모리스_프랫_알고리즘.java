import java.util.ArrayList;
import java.util.List;

public class 커누스_모리스_프랫_알고리즘 {

    List<Integer> kmpSearch(String H, String N) {
        List<Integer> result = new ArrayList<>();

        // pi[i] = N[..i]의 접미사도 되고, 접두사도 되는 문자열의 최대 길이
        List<Integer> pi = getPartialMatchNaive(N);

        // begin=matched=0 에서 시작
        int begin = 0, matched = 0;
        while (begin <= H.length() - N.length()) {
            if (matched < N.length() && H.charAt(begin + matched) == N.charAt(matched)) {
                matched++;
                // N글자가 모두 일치했다면 답에 추가
                if (matched == N.length()) {
                    result.add(begin);
                }
            } else {
                // matched가 0인 경우에는 다음칸부터 계속
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi.get(matched - 1);
                    // begin을 옮긴 후에 접두사는 이미 일치함
                    matched = pi.get(matched - 1);
                }
            }
        }

        return result;
    }

    static List<Integer> getPartialMatchNaive(String N) {
        // pi[i] = N[..i]의 접미사도 되고, 접두사도 되는 문자열의 최대 길이
        List<Integer> pi = new ArrayList<>();

        for (int i = 0; i < N.length(); i++) {
            pi.add(0);
        }

        // 접미사가 i부터 시작하면서
        for (int i = 1; i < N.length(); i++) {
            // j+1글자가 일치한다.
            for (int j = 0; i + j < N.length(); j++) {
                if (N.charAt(i + j) != N.charAt(j)) {
                    break;
                }

                pi.set(i + j, Math.max(pi.get(i + j), j + 1));
            }
        }

        System.out.println(pi);

        return pi;
    }

    //
    static List<Integer> getPartialMatch(String N) {
        // pi[i] = N[..i]의 접미사도 되고, 접두사도 되는 문자열의 최대 길이
        List<Integer> pi = new ArrayList<>();
        for (int i = 0; i < N.length(); i++) {
            pi.add(0);
        }

        // KMP로 자기 자신을 찾는다.
        // N을 N에서 찾는다 begin=0은 처리 안함
        int begin = 1, matched = 0;

        // 비교할 문자가 N의 끝에 도달할 때 까지 찾으면서 부분 일치를 모두 기록한다.
        while (begin + matched < N.length()) {
            if (N.charAt(begin + matched) == N.charAt(matched)) {
                matched++;
                pi.set(begin + matched - 1, matched);
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi.get(matched - 1);
                    matched = pi.get(matched - 1);
                }
            }
        }

        return pi;
    }

    // S의 접미사면서, S`의 접두사인 문자열의 최대 길이
    static int maxPalindrome(String S, String SPrime) {

        List<Integer> pi = getPartialMatch(S);

        int begin = 0, matched = 0;
        while (begin < S.length()) {
            if (S.charAt(begin + matched) == SPrime.charAt(matched)) {
                matched++;
                if (begin + matched == S.length()) {
                    return matched;
                }
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi.get(matched - 1);
                    matched = pi.get(matched - 1);
                }
            }
        }

        return 0;

    }

    static List<Integer> getPrefixSuffixList(String S) {
        List<Integer> result = new ArrayList<>();

        List<Integer> pi = getPartialMatch(S);
        int k = S.length();

        while (k > 0) {
            result.add(k);

            k = pi.get(k - 1);
        }

        return result;
    }

    static List<Integer> kmpSearch2(String H, String N) {
        List<Integer> result = new ArrayList<>();
        List<Integer> pi = getPartialMatch(N);

        int matched = 0;
        for (int i = 0; i < H.length(); i++) {
            // matched번 글자와 짚더미의 해당 글자가 불일치한 경우
            // 현재 대응된 글자의 수를 pi[matched-1]로 줄인다.
            while (matched > 0 && H.charAt(i) != N.charAt(matched)) {
                matched = pi.get(matched - 1);
                // 글자가 대응될 경우
                if (H.charAt(i) == N.charAt(matched)) {
                    matched++;
                    if (matched == N.length()) {
                        result.add(i - N.length() + 1);
                        matched = pi.get(matched - 1);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String S = "abcd";
        int result = maxPalindrome(S, new StringBuffer(S).reverse().toString());
        System.out.println(result);
        System.out.println(S.length() * 2 - result);
        // 8 3 1
        // anon
        // anona
        // anonona

        // 공통 최대 접두사 접미사.
        // result = 3
        // aaaa
        // S.length()*2- result

    }
}
