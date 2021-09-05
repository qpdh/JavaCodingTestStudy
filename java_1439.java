import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class java_1439 {
    static Integer[] ropeList;

    public static int solveRope() {
        int maxWeight = ropeList[0];

        // 추가 로프 선택
        for (int i = 1; i < ropeList.length; i++) {
            maxWeight = Math.max(maxWeight, ropeList[i] * (i + 1));
        }

        return maxWeight;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ropeList = new Integer[N];
        for (int i = 0; i < N; i++) {
            ropeList[i] = Integer.parseInt(br.readLine());
        }
        // 내림차순 정렬
        Arrays.sort(ropeList, Collections.reverseOrder());

        int ret = solveRope();
        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
