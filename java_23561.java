import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_23561 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 3N명
        int N = Integer.parseInt(br.readLine());

        // [N] [3N-1-2] 차이
        int crew[] = new int[3 * N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < crew.length; i++) {
            crew[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(crew);

        bw.write((crew[3 * N - 1 - N] - crew[N]) + "\n");

        br.close();
        bw.close();
    }
}
