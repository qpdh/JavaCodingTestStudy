import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_25215 {
    static String sentence;

    public static int solve() {
        int count = 0;

        boolean isUpper = false;

        for (int i = 0; i < sentence.length(); i++) {
            char alpha = sentence.charAt(i);

            // 소문자라면
            if (alpha >= 'a') {
                if (isUpper) {
                    if (i + 1 < sentence.length()) {
                        if (sentence.charAt(i + 1) >= 'a') {
                            isUpper = false;
                        }

                    }
                    count += 2;
                } else {
                    count += 1;
                }
            }

            // 대문자라면
            else {
                if (!isUpper) {
                    if (i + 1 < sentence.length()) {
                        if (sentence.charAt(i + 1) <= 'Z') {
                            isUpper = true;
                        }

                    }
                    count += 2;
                } else {
                    count += 1;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        sentence = br.readLine();

        int result = solve();

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}
