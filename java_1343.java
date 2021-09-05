import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_1343 {
    public static String poliomino(String[] tokens) {
        // System.out.println(tokens.length);
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < tokens.length; i++) {
            // 토큰마다 X의 수가 홀수면 채우기 불가
            if (tokens[i].length() % 2 != 0) {
                return "-1";
            }
            int tokenLength = tokens[i].length();

            while (tokenLength > 0) {
                // 길이는 항상 짝수이므로..
                // 2 칸이면 B로 덮기
                // 그게 아니라면 A로 덮기
                if (tokenLength >= 4) {
                    tokenLength -= 4;
                    sb.append("AAAA");
                } else {
                    tokenLength -= 2;
                    sb.append("BB");
                }

            }

            if (i + 1 < tokens.length) {
                sb.append(".");
            }

        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 데이터 입력
        String board = br.readLine();

        // 함수 호출
        String ret = poliomino(board.split("\\.", -1));

        // 결과 출력
        bw.write(ret + "\n");

        br.close();
        bw.close();

    }
}
