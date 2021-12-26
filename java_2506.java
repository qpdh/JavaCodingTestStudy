import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_2506 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int result = 0;
        int countSum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int check = Integer.parseInt(st.nextToken());
            if (check == 1) {
                countSum++;
            } else {
                countSum = 0;
            }

            result += countSum;

        }

        bw.write(result + "\n");

        bw.close();
        br.close();
    }
}