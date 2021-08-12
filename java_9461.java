import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_9461 {
    static long cache[];

    // 1 1 1 2 2 3 4 5 7 9 12

    // 1[0] = 1
    // 1[1] = 1
    // 1[2] = 1
    // 2[3] = 2
    // 2[4] = 2

    // 3[5] =   2[4] + 1[0]
    // 4[6] =   3[5] + 1[1] 
    // 5[7] =   4[6] + 1[2]
    // 7[8] =   5[7] + 2[3]
    // 9[9] =   7[8] + 2[4]
    // 12[10] = 9[9] + 3[5]

    // 16[11] = 12[10] + 4[6]
    // [12] = 16[11] + 5[7]

    public static long P(int N) {
        // 0, 1 ,2
        if (N <= 2) {
            return 1;
        }

        // 3, 4
        if (N <= 4) {
            return 2;
        }

        if (cache[N] != -1) {
            return cache[N];
        }

        cache[N] = P(N - 5)+P(N - 1);
        return cache[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        cache = new long[101];

        for (int i = 0; i < 100; i++) {
            cache[i] = -1;
        }

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            bw.write(P(N-1)+"\n");
        }

        for(int i=0;i<100;i++){
            bw.write(P(i)+"\n");
        }

        br.close();
        bw.close();
    }
}
