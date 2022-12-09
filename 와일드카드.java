
public class 와일드카드 {
    // wildcard가 word에 대응되는지 판단하는 메소드
    static boolean match(String wildcard, String word) {

        // wildcard[index], word[index]
        int index = 0;
        while (index < wildcard.length() && index < word.length()
                && (wildcard.charAt(index) == '?' || wildcard.charAt(index) == word.charAt(index))) {
            index++;
        }

        // index == wildcard.length() 인 경우
        // word도 끝나야 참 아니면 거짓
        if (index == wildcard.length()) {
            return index == word.length();
        }

        // *를 만나서 끝난 경우
        if (wildcard.charAt(index) == '*') {
            for (int i = 0; i + index < word.length(); i++) {
                if (match(wildcard.substring(i + 1), word.substring(index + i))) {
                    return true;
                }
            }
        }

        return false;
    }

    // -1 : 아직 처리 안함
    // 0 : 매칭 X
    // 1 : 매칭 O
    static int cache[][];
    static String wildcard, word;

    static int matchMemoized(int wildIndex, int wordIndex) {
        if (cache[wildIndex][wordIndex] != -1) {
            return cache[wildIndex][wordIndex];
        }

        // 맞추기
        while (wildIndex < wildcard.length() && wordIndex < word.length()
                && (wildcard.charAt(wildIndex) == '?' || wildcard.charAt(wildIndex) == word.charAt(wordIndex))) {
            wildIndex++;
            wordIndex++;
        }

        // index == wildcard.length() 인 경우
        // word도 끝나야 참 아니면 거짓
        if (wildIndex == wildcard.length()) {
            cache[wildIndex][wordIndex] = wordIndex == word.length() ? 1 : 0;
            return cache[wildIndex][wordIndex];
        }

        // *를 만나서 끝난 경우
        if (wildcard.charAt(wildIndex) == '*') {
            for (int i = 0; i + wordIndex < word.length(); i++) {
                if (matchMemoized(wildIndex + 1, wordIndex + i) == 1) {
                    cache[wildIndex][wordIndex] = 1;
                    return cache[wildIndex][wordIndex];
                }
            }
        }

        cache[wildIndex][wordIndex] = 0;

        return cache[wildIndex][wordIndex];
    }

    static int matchMemoized2(int wildIndex, int wordIndex) {
        if (cache[wildIndex][wordIndex] != -1) {
            return cache[wildIndex][wordIndex];
        }

        // 맞추기
        while (wildIndex < wildcard.length() && wordIndex < word.length()
                && (wildcard.charAt(wildIndex) == '?' || wildcard.charAt(wildIndex) == word.charAt(wordIndex))) {
            cache[wildIndex][wordIndex] = matchMemoized2(wildIndex + 1, wordIndex + 1);
        }

        // index == wildcard.length() 인 경우
        // word도 끝나야 참 아니면 거짓
        if (wildIndex == wildcard.length()) {
            cache[wildIndex][wordIndex] = wordIndex == word.length() ? 1 : 0;
            return cache[wildIndex][wordIndex];
        }

        // *를 만나서 끝난 경우
        if (wildcard.charAt(wildIndex) == '*') {
            // *를 그냥 넘기는 경우
            cache[wildIndex][wordIndex] = matchMemoized2(wildIndex + 1, wordIndex);

            // 1번 스킵하는 경우
            if (wordIndex < word.length()) {
                cache[wildIndex][wordIndex] = matchMemoized2(wildIndex, wordIndex + 1);
            }

            return cache[wildIndex][wordIndex];
        }

        cache[wildIndex][wordIndex] = 0;

        return cache[wildIndex][wordIndex];
    }

    public static void main(String[] args) {

    }
}
