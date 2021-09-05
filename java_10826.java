import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_10826 {
    static StringBuffer cache[];

    public static StringBuffer stringSum(StringBuffer a, StringBuffer b) {
        int minString = Math.min(a.length(), b.length());

        StringBuffer sb = new StringBuffer();

        int carry = 0;

        for (int i = 0; i < minString; i++) {
            int tmpSum = a.charAt(i) + b.charAt(i) - '0' - '0' + carry;

            // 캐리가 존재한다면
            if (tmpSum > 9) {
                carry = 1;
                tmpSum -= 10;
            } else {
                carry = 0;
            }

            sb.append(tmpSum);
        }

        // 찌꺼기 넣어주기
        if (a.length() > b.length()) {

            for (int i = b.length(); i < a.length(); i++) {
                int tmpSum = a.charAt(i) - '0' + carry;
                // 캐리가 존재한다면
                if (tmpSum > 9) {
                    carry = 1;
                    tmpSum -= 10;
                } else {
                    carry = 0;
                }
                sb.append(tmpSum);
            }
        } else if (a.length() < b.length()) {
            for (int i = a.length(); i < b.length(); i++) {
                int tmpSum = b.charAt(i) - '0' + carry;
                // 캐리가 존재한다면
                if (tmpSum > 9) {
                    carry = 1;
                    tmpSum -= 10;
                } else {
                    carry = 0;
                }
                sb.append(tmpSum);
            }
        } else {
            if (carry > 0) {
                sb.append(1);
            }
        }

        return sb;
    }

    public static StringBuffer fibo(int N) {
        if (N == 0) {
            return new StringBuffer("0");
        }

        if (N == 1) {
            return new StringBuffer("1");
        }

        if (cache[N] != null) {
            return cache[N];
        }

        cache[N] = stringSum(fibo(N - 1), fibo(N - 2));
        return cache[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        cache = new StringBuffer[N + 1];

        bw.write(fibo(N).reverse().toString() + "\n");

        br.close();
        bw.close();
    }
}
