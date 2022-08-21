import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1474 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String wordList[] = new String[N];

        int wordListTotalLength = 0;
        for (int i = 0; i < N; i++) {
            wordList[i] = br.readLine();
            wordListTotalLength += wordList[i].length();
        }

        // 일단 모든 문자열 길이의 합을 구해보자
        // wordListTotalLength

        // 거기서 모든 문자 사이에 _를 N-1개 끼워넣을 수 있는가?
        // 거기서 모든 문자 사이에 _를 N-1개 끼워넣을 수 있는가?
        // ...

        // 끼워넣을 수 없다면 몇개 까지 끼워넣을 수 있는가?
        int insert_Count = (int) (M - wordListTotalLength) / (N - 1);
        // System.out.println("insert_Count : " + insert_Count);

        // 끼워넣을 수 없다면 몇개의 _를 더 넣어야 하는가?
        int left_Count = (M - wordListTotalLength) % (N - 1);
        // System.out.println("left_Count : " + left_Count);

        // 어디서 부터 끼워 넣어야 하는가?
        // 'Z' 이후, 단어가 소문자로 시작하는 것 부터
        // 앞쪽부터 검사한다.

        boolean isAdditionalInsertIndex[] = new boolean[N];

        for (int i = 1; i < N; i++) {
            if (left_Count == 0) {
                break;
            }
            // 소문자로 시작한다면...
            if ('a' <= wordList[i].charAt(0)) {
                isAdditionalInsertIndex[i] = true;
                left_Count--;
            }
        }

        // 모두 대문자라면,,, 뒤에서 부터 단어를 선택
        for (int i = N - 1; i >= 1; i--) {
            if (left_Count == 0) {
                break;
            }
            // 대문자로 시작한다면...
            if (wordList[i].charAt(0) <= 'Z') {
                isAdditionalInsertIndex[i] = true;
                left_Count--;

            }
        }

        // 만들기
        bw.write(wordList[0]);
        for (int i = 1; i < N; i++) {
            // 공통 _ 넣기
            for (int j = 0; j < insert_Count; j++) {
                bw.write("_");
            }
            // 추가적인 _ 넣기
            if (isAdditionalInsertIndex[i]) {
                bw.write("_");
            }
            // 단어 넣기
            bw.write(wordList[i]);
        }
        bw.write("\n");

        br.close();
        bw.close();
    }
}
