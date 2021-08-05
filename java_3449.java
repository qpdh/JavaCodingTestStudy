import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_3449 {

    public static int calHamming(String a, String b) {
        int result = 0;

        // a와 b의 길이가 같기 때문에
        // 아무 길이 써도 상관 없음
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String a = br.readLine();
            String b = br.readLine();
            int result = calHamming(a, b);

            bw.write("Hamming distance is " + result + ".\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}