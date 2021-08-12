import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_9184 {
    static int cache[][][];

    public static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0)
            return 1;

        if (a > 20 || b > 20 || c > 20)
            return w(20, 20, 20);

        if (cache[a][b][c] != -1) {
            return cache[a][b][c];
        }

        if (a < b && b < c) {
            cache[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
            return cache[a][b][c];
        }

        cache[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
        return cache[a][b][c];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            int ret = 0;
            if (a <= 0 || b <= 0 || c <= 0) {
                ret = w(a, b, c);
            } 
            
            else if (a > 20 || b > 20 || c > 20) {
                cache = new int[21][21][21];
                for (int i = 0; i < 21; i++) {
                    for (int j = 0; j < 21; j++) {
                        for (int k = 0; k < 21; k++) {
                            cache[i][j][k] = -1;
                        }
                    }
                }
                ret = w(a, b, c);
            }

            else {
                cache = new int[a + 1][b + 1][c + 1];
                for (int i = 0; i < a + 1; i++) {
                    for (int j = 0; j < b + 1; j++) {
                        for (int k = 0; k < c + 1; k++) {
                            cache[i][j][k] = -1;
                        }
                    }
                }
                ret = w(a, b, c);
            }

            bw.write("w(" + a + ", " + b + ", " + c + ") = " + ret + "\n");
        }

        br.close();
        bw.close();
    }
}
