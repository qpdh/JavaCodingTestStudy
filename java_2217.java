import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;



public class java_2217 {
    static Integer rope[];

    public static int calcRope() {

        int maxWeight = rope[0];

        for (int i = 1; i < rope.length; i++) {
            maxWeight = Math.max(maxWeight, rope[i] * (i + 1));
        }

        return maxWeight;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        rope = new Integer[N];
        for (int i = 0; i < N; i++) {
            rope[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(rope, Collections.reverseOrder());

        int ret = calcRope();

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
