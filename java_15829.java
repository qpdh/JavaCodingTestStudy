import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_15829 {
    final static int MOD = 1234567891;
    final static int POW = 31;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int L = Integer.parseInt(br.readLine());

        String word = br.readLine();

        long wordToLong = 0;

        wordToLong += word.charAt(0) - 'a' + 1;

        long pow = 1;

        for (int i = 1; i < word.length(); i++) {
            pow = (pow * POW) % MOD;
            wordToLong += ((word.charAt(i) - 'a' + 1) * (pow)) % MOD;

        }

        System.out.println(wordToLong % 1234567891);

        br.close();
        bw.close();
    }
}
