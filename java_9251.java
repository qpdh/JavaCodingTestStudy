import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_9251 {
    static String A, B;
    static int cache[][];

    // 1. ACAYKP
    // 2. CAPCAK

    // ACAK

    // 1. 2. 를 옮기는 경우

    // 1. CAYKP
    // 2. CAPCAK

    // ACAK
    // ACAK

    // A의 인덱스, B의 인덱스로 시작하는 공통 부분 수열 찾기
    public static int LCS(int indexA, int indexB) {
        // 둘 중 하나가 끝까지 다 탐색하면 종료
        if (A.length() == indexA || B.length() == indexB) {
            return 0;
        }

        if (cache[indexA][indexB] != -1) {
            return cache[indexA][indexB];
        }

        // A로 시작하는 인덱스와 B로 시작하는 인덱스의 문자가 다르면..
        if (A.charAt(indexA) != B.charAt(indexB)) {
            cache[indexA][indexB] = Math.max(LCS(indexA + 1, indexB), LCS(indexA, indexB + 1));
        }
        // 같으면 1 추가
        else {
            cache[indexA][indexB] = Math.max(cache[indexA][indexB], 1 + LCS(indexA + 1, indexB + 1));
        }

        return cache[indexA][indexB];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        A = br.readLine();
        B = br.readLine();

        cache = new int[A.length()][B.length()];

        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                cache[i][j] = -1;
            }
        }

        int ret = -1;
        // 0,0 ~ A.len,B.len
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                ret = Math.max(ret, LCS(i, j));
            }
        }

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
