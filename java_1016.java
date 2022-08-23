import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_1016 {
    public static boolean[] isPrime;
    public static boolean[] check;

    // N까지 소수 체크
    public static void check_prime(long min, long max) {
        isPrime = new boolean[(int) Math.sqrt(max) + 1];
        check = new boolean[(int) (max - min + 1)];

        Arrays.fill(isPrime, true);
        Arrays.fill(check, true);

        for (long i = 2; i <= (int) Math.sqrt(max); i++) {
            if (!isPrime[(int) i]) {
                continue;
            }

            for (long j = i * i; j < Math.sqrt(max); j += i * i) {
                isPrime[(int) j] = false;
            }

            // min이 i의 제곱수로 나누어 떨어지면.. min 그대로 배수를 제곱수 처리
            // min이 i의 제곱수로 나누어 떨어지지 않으면...min보다 큰 제곱수부터 제곱수 처리
            long squared = i * i;
            long j = min % squared == 0 ? min : squared * ((min / squared) + 1);
            for (; j <= max; j += squared) {
                check[(int) (j - min)] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        // 1보다 큰 제곱수
        // 4 9 16 25 36 49 64 81 ...

        // 16으로 나누어 떨어진다면 4로도 나누어 떨어짐
        // 4로도 나누어 떨어진다면 16으로도 나누어 떨어짐
        // -> 소수의 제곱수만 구하자

        // 소수 제곱 -> 체로 거르자

        // 제곱 ㄴㄴ 수
        // 1 2 3 5 6 7 8 10

        // 1. 소수 구하기
        // 어디까지의 소수를 구할 것인가?
        // Math.sqrt(max) 까지의 소수를 구하자
        // 2. 체로 거르기

        check_prime(min, max);
        // for (int i = 0; i < isPrime.length; i++) {
        // System.out.println("i : " + i + " : [ " + isPrime[i] + " ]");
        // }

        long result = 0;

        for (long i = min; i <= max; i++) {
            if (check[(int) (i - min)]) {
                result++;
            }
        }

        System.out.println(result);

        br.close();
        bw.close();
    }
}
