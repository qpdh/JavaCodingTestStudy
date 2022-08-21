import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class java_2015 {
    static int N, K;
    static int[] numList;
    static int[] sumList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N 크기의 배열
        N = Integer.parseInt(st.nextToken());

        // 합이 K인 것이 몇 개가 있는 지
        K = Integer.parseInt(st.nextToken());

        numList = new int[N];

        st = new StringTokenizer(br.readLine());

        // 값 저장
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        sumList = new int[N];
        sumList[0] = numList[0];
        // 누적 합 저장
        for (int i = 1; i < N; i++) {
            sumList[i] = sumList[i - 1] + numList[i];
        }

        HashMap<Integer, Integer> sumCount = new HashMap<>();

        long result = 0;

        // 1 경우의 수 [0,i] 합

        // 2경우의 수 [i,j] 합
        // [i,j] 합 = sumList[j] - sumList[i-1]
        // sumList[j] - sumList[i-1] == K
        // 가 되는 경우의 수
        // sumList[j] - K == sumList[i-1]

        // sumList[i-1]가 만들어 지는 갯수를 구하자
        // 단, 0<=i<=j<=N-1

        for (int i = 0; i < N; i++) {
            // 1 경우의 수
            if (sumList[i] == K) {
                result++;
            }

            // [0,i] 까지 합 중에서 [j,i] 를 했을 때 K가 나오는 경우의 수
            // [0,i] - [j-1,i] == K
            // [0,i] - K == [j-1,i]
            // [0,i] - K 가 만들어지는 갯수
            result += sumCount.getOrDefault(sumList[i] - K, 0);
            sumCount.put(sumList[i], sumCount.getOrDefault(sumList[i], 0) + 1);
        }

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}
