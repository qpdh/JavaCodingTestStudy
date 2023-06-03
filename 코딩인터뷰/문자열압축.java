package 코딩인터뷰;

public class 문자열압축 {

    static String stringCompression(String word) {
        StringBuffer compressedWordBuffer = new StringBuffer();

        char alpha = word.charAt(0);
        int count = 1;

        for (int i = 1; i < word.length(); i++) {
            if (alpha != word.charAt(i)) {
                compressedWordBuffer.append(Character.toString(alpha) + count);
                alpha = word.charAt(i);
                count = 1;
                continue;
            }
            count++;
        }

        // 마지막 인덱스 처리
        compressedWordBuffer.append(Character.toString(alpha) + count);

        if (word.length() == compressedWordBuffer.length()) {
            return word;
        }

        return compressedWordBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(stringCompression("aabcccccaaa"));
        System.out.println(stringCompression("aa"));
        System.out.println(stringCompression("aaaAAaa"));
        System.out.println(stringCompression("aacbba"));
    }
}
