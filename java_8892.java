import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class java_8892 {
    static int T;
    static int k;
    static String words[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트케이스 입력
        T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; ++testCase) {
            // 단어의 개수 입력
            k = Integer.parseInt(br.readLine());

            // 단어 입력
            words = new String[k];
            for (int i = 0; i < k; ++i) {
                words[i] = br.readLine();
            }

            String result = solution();
            System.out.println(result);
        }

        br.close();
    }

    /**
     * 두 단어를 합쳐 팰린드롬을 만들기 -> 만들 수 없으면 0 출력
     *
     *
     * @return 팰린드롬 | 0
     */
    private static String solution() {
        //
        for (int i = 0; i < k; i++) {
            String thisWord = words[i];

            // i를 뒤집은 단어가 배열 내에 존재하는 지 체크
            for (int j = 0; j < k; j++) {
                if (i == j) {
                    continue;
                }

                // 두 단어를 합치자
                String mixedWord = thisWord + words[j];

                // 만약 두 단어를 합친 것이 회문이 된다면..
                if (mixedWord.equals(new StringBuffer(mixedWord).reverse().toString())) {
                    // 회문 출력
                    return mixedWord;
                }

            }
        }

        return "0";
    }
}
