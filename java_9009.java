import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class java_9009 {
    static int T;
    static int n;

    static int cache[];

    // number 이하의 가장 큰 Fib 값 반환
    static int findFib(int number) {
        int low = 0;
        int high = cache.length - 1;

        while (low < high) {
            int mid = (low + high) / 2;

            if (fib(mid) == number) {
                return number;
            }

            if (fib(mid) < number) {
                low = mid + 1;
                continue;
            }

            high = mid;

        }

        return fib((low + high) / 2 - 1);
    }

    // 피보나치 값 반환
    static int fib(int number) {
        if (number == 0 || number == 1) {
            return number;
        }

        if (cache[number] != -1) {
            return cache[number];
        }

        cache[number] = fib(number - 1) + fib(number - 2);

        return cache[number];
    }

    static void solution() {
        List<Integer> result = new ArrayList<>();

        while (n != 0) {
            int tmpResult = findFib(n);
            result.add(tmpResult);
            n -= tmpResult;
        }

        // System.out.println(result);

        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        cache = new int[50];
        Arrays.fill(cache, -1);

        for (int testCase = 0; testCase < T; testCase++) {
            n = Integer.parseInt(br.readLine());
            solution();
        }

        br.close();
    }
}
