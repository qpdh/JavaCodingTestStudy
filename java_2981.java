import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.TreeSet;

public class java_2981 {
    static int euclid(int a, int b) {
        while (b != 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }

    static TreeSet<Integer> getDivisor(int N) {
        TreeSet<Integer> result = new TreeSet<>();

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                result.add(i);
                result.add(N / i);
            }
        }

        result.add(N);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 최대 공약수의 약수 구하기
        int N = Integer.parseInt(br.readLine());

        int numberList[] = new int[N];

        for (int i = 0; i < N; i++) {
            numberList[i] = Integer.parseInt(br.readLine());

        }

        Arrays.sort(numberList);

        // 최대 공약수 구하기
        int gcd = numberList[1] - numberList[0];
        for (int i = 1; i < N; i++) {

            gcd = euclid(gcd, numberList[i] - numberList[0]);

        }

        // 최대 공약수의 약수 구하기
        TreeSet<Integer> result = getDivisor(gcd);

        for (int num : result) {
            bw.write(num + " ");
        }
        bw.write("\n");

        br.close();
        bw.close();
    }
}
