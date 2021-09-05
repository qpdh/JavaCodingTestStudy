import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_10162 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void minButtonPress(int T) throws IOException {
        // 버튼을 눌러도 정확히 맞출 수 없다면...
        if (T % 10 > 0) {
            bw.write("-1\n");
            return;
        }

        // 5분짜리를 우선 선택하는 최적해가 존재.
        // 우선 선택하지 않는다고 가정
        // 10초짜리 선택 T/10 개 선택 가능
        // 5분짜리 우선 선택 T/300 개 + T%300/60 + T%60/10 <= T/10 이므로
        // 5분짜리를 우선 선택하는 최적해가 존재.

        int count5M = 0;
        int count1M = 0;
        int count10S = 0;

        // 300초
        count5M += T / 300;
        // 60초
        count1M += T % 300 / 60;
        // 10초
        count10S += T % 60 / 10;

        bw.write(count5M + " " + count1M + " " + count10S + "\n");
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 최소 버튼 조작

        int T = Integer.parseInt(br.readLine());
        minButtonPress(T);

        br.close();
        bw.close();
    }
}