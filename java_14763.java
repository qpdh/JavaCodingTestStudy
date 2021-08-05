import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_14763 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int count = 0;

        // 3으로 나누어 떨어지는가?
        // 나누어 떨어진다면..
        // 언제까지 나누는가?

        // 2로 나누어 떨어지는가?

        // 아니면 1빼주기

        // 3으로 나누어 떨어진다면, 
        if (N % 3 == 0) {
            count = N / 3;
        }

        if (N % 3 == 1) {
            // 3으로 나눈 나머지가 1이라면, 3으로 계속 나눈다
            count = N / 3;
        } else if (N % 3 == 2) {
            // 3으로 나눈 나머지가 2라면. 3으로 나눈 몫을 취하고, 1을 빼는 연산을 한다
            count = N / 3 + 1;
        } else { // N%3 == 0
            // 3으로 나눈 나머지가 0이라면, 3이 될때 까지 나누고 (N/3-1)번
            count = N / 3;
        }

        bw.write(count + "\n");

        br.close();
        bw.close();
    }
}
