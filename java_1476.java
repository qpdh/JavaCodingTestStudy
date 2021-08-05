import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1 <= E <= 15
        // 1 <= S <= 28
        // 1 <= M <= 19

        StringTokenizer st = new StringTokenizer(br.readLine());

        int checkE = Integer.parseInt(st.nextToken())-1;
        int checkS = Integer.parseInt(st.nextToken())-1;
        int checkM = Integer.parseInt(st.nextToken())-1;

        int E = 0, S = 0, M = 0;

        int year = 1;
        while (true) {
            if (checkE == E && checkS == S && checkM == M) {
                break;
            }

            E = (E + 1) % 15;
            S = (S + 1) % 28;
            M = (M + 1) % 19;

            //System.out.println(E + " " + S + " " + M);
            ++year;
        }

        bw.write(year + "\n");

        br.close();
        bw.close();
    }
}
