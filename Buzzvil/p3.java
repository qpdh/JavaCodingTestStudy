package Buzzvil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class p3 {
    // [start,end)
    public static boolean isGoodString(String binString, int start, int end) {
        int zeroCount = 0, oneCount = 0;
        for (int i = start; i < end; i++) {
            if (binString.charAt(i) == '0') {
                zeroCount++;
                continue;
            }
            if (binString.charAt(i) == '1') {
                oneCount++;
                continue;
            }

            // 접두사에서 0의 개수가 더 큰 경우
            if (oneCount < zeroCount) {
                return false;
            }
        }

        // 0의 갯수와 1의 갯수가 같아야 함
        return zeroCount == oneCount;
    }

    public static String largestMagical(String binString) {
        // 0의 갯수와 1의 갯수가 같다.
        // 1의 개수는 0의 개수보다 많아야한다.

        // 0이 나왔을 때, 다음이 1이라면 나눠보기 -> 만들어진다면 추가

        // 연속적인 좋은 문자열 찾기
        // i 기준으로 연속적으로 좋은 문자열이 존재하는가?
        // 
        for (int i = 0; i < binString.length(); i++) {
            if (binString.charAt(i) == '0') {

            }
        }

        return "";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String binString = bufferedReader.readLine();

        String result = largestMagical(binString);

        bufferedReader.close();

    }
}
