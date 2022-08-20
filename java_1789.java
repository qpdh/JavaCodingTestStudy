import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_1789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long S = Long.parseLong(br.readLine());

        long sum = 0;

        long N = 1;
        int count = 0;

        while (true) {
            sum += N++;
            count++;

            if (sum > S) {
                count--;
                break;
            }
        }

        bw.write(count + "\n");

        br.close();
        bw.close();
    }
}
