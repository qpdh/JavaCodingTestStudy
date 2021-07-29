import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_1977 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        // tmpMin 자체가 제곱수 이면 처리
        int tmpMin = (int) Math.sqrt(M);

        int sumRet = 0;
        int minRet = (tmpMin * tmpMin == M) ? tmpMin : (tmpMin + 1);

        if(minRet * minRet > N){
            bw.write("-1\n");
            bw.close();
            br.close();
            return;
        }

        for (int i = minRet; i * i <= N; i++) {
            sumRet += i * i;
        }

        bw.write(sumRet + "\n");
        bw.write(minRet * minRet + "\n");

        bw.close();
        br.close();

    }
}
