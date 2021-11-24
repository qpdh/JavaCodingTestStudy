import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_2576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numbers[] = new int[7];

        int result = 101;
        int sumOfOdds = 0;

        for (int i = 0; i < 7; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < 7; i++) {
            if (numbers[i] % 2 == 1) {
                sumOfOdds += numbers[i];
                if (numbers[i] < result) {
                    result = numbers[i];

                }
            }
        }

        if (result == 101) {
            result = -1;
            bw.write(result + "\n");
        } else {
            bw.write(sumOfOdds + "\n");
            bw.write(result + "\n");
        }

        br.close();
        bw.close();
    }
}
