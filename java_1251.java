import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class java_1251 {
    static TreeSet<String> resultTreeSet;

    public static void slicingWord(String word) {

        // sb1 [0,first)
        // sb2 [first,second)
        for (int first = 1; first <= word.length() - 2; ++first) {
            for (int second = first + 1; second <= word.length() - 1; ++second) {
                StringBuffer sb1 = new StringBuffer(word.substring(0, first));
                StringBuffer sb2 = new StringBuffer(word.substring(first, second));
                StringBuffer sb3 = new StringBuffer(word.substring(second));

                String reverseWord = sb1.reverse().toString() + sb2.reverse() + sb3.reverse();
                resultTreeSet.add(reverseWord);
            }
        }
        // // sb1 [0,first)
        // // sb2 [first,second)
        // // sb3 [second, word.length())
        // for (int second = 2; second <= word.length() - 1; ++second) {
        //     for (int first = 1; first <= second; ++first) {
        //         StringBuffer sb1 = new StringBuffer(word.substring(0, first));
        //         StringBuffer sb2 = new StringBuffer(word.substring(first, second));
        //         StringBuffer sb3 = new StringBuffer(word.substring(second));

        //         String reverseWord = sb1.reverse().toString() + sb2.reverse() + sb3.reverse();
        //         resultTreeSet.add(reverseWord);
        //     }
        // }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        resultTreeSet = new TreeSet<>();

        // first second third
        // 까지 포함하는 슬라이싱

        // 단어 입력 받기
        String word = br.readLine();

        // 슬라이싱
        slicingWord(word);

        // 결과 출력
        bw.write(resultTreeSet.pollFirst() + "\n");

        br.close();
        bw.close();
    }
}
