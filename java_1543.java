import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class java_1543 {
    static String docs;
    static String word;

    // docs에서 index부터 시작하는 부분 문자열이
    // word와 같은가?
    static boolean isIn(int index) {

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != docs.charAt(i + index)) {
                return false;
            }
        }

        return true;
    }

    // docs 에서 word가 중복되지 않고, 몇 번 들어가는지 체크
    static int findCount() {
        int count = 0;

        // docs : abab == 4
        // word : ab == 2

        // index는 2까지만 해도 상관 없음

        // => docs.length() - word.length() + 1
        for (int i = 0; i < docs.length() - word.length() + 1;) {
            // i번 부터 탐색을 시작했을 때, 단어가 맞아 떨어진다면...
            // i+단어길이 이후부터 탐색하자
            if (isIn(i)) {
                count++;
                i += word.length();
            } else {
                i++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문서
        docs = br.readLine();
        // 찾고 싶은 단어
        word = br.readLine();

        // 중복되지 않게 선택
        // 중복되지 않게 선택은 -> 앞부터 찾아나가고, 찾으면 그 다음부터 또 찾는다. n^2
        int result = findCount();

        System.out.println(result);

        br.close();
    }
}
