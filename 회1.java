import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 회1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        double N = Double.parseDouble(br.readLine());

        int checkN = (int) Math.ceil(Math.sqrt(N));

        System.out.println(checkN);

        br.close();
        bw.close();
    }
}
