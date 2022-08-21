import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1107 {
    static String target;
    static int N;
    static boolean brokenButton[];

    // N보다 큰, 작은 수 바로 근처
    static int targetMin = -987654321;
    static int targetMax = 987654321;

    static boolean isZeroPossible;

    static int minNotBroken = 10;
    static int maxNotBroken = -1;

    // targetMax> number && N < number
    public static void checkNumber(int number, int position) {
        if (position == target.length()) {
            if (N - targetMin > N - number && number < N) {
                targetMin = number;
            }
            if (targetMax - N > number - N && number > N) {
                targetMax = number;
            }
            return;
        }

        // 현재 자리의 숫자
        int digit = target.charAt(position) - '0';
        // 현재 자리
        int numberPosition = (int) Math.pow(10, target.length() - position - 1);

        // 현재 숫자를 쓴다면,
        if (!brokenButton[digit]) {
            checkNumber(number + digit * numberPosition, position + 1);
        }

        // 현재 숫자를 쓰지 않는다면

        // 현재 숫자보다 살짝 큰 숫자 찾기
        // 현재 숫자보다 살짝 작은 숫자 찾기

        int brokenLarge = -1;
        int brokenLess = 10;

        for (int j = digit + 1; j < brokenButton.length; j++) {
            if (!brokenButton[j]) {
                brokenLarge = j;
                break;
            }
        }

        for (int j = digit - 1; j >= 0; j--) {
            if (!brokenButton[j]) {
                brokenLess = j;
                break;
            }
        }

        // 큰 숫자가 없다면 처리하지 않음
        if (brokenLarge != -1) {
            int tmpIndex = position + 1;
            int tmpNumber = number + brokenLarge * numberPosition;

            // 나머지 가장 작은걸로 채우기
            while (tmpIndex < target.length()) {
                // 현재 자리
                int tmpNumberPosition = (int) Math.pow(10, target.length() - tmpIndex - 1);

                if (!isZeroPossible) {
                    tmpNumber += minNotBroken * tmpNumberPosition;
                }
                tmpIndex++;
            }

            checkNumber(tmpNumber, target.length());

        }
        // 작은 숫자가 없다면 처리하지 않음
        if (brokenLess != 10) {
            int tmpIndex = position + 1;
            int tmpNumber = number + brokenLess * numberPosition;

            // 나머지 가장 작은걸로 채우기
            while (tmpIndex < target.length()) {
                // 현재 자리
                int tmpNumberPosition = (int) Math.pow(10, target.length() - tmpIndex - 1);

                tmpNumber += maxNotBroken * tmpNumberPosition;

                tmpIndex++;
            }
            // 나머지 자릿수를 가장 큰 것으로 채움
            checkNumber(tmpNumber, target.length());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 최대한 N에 있는 숫자를 누를 것
        // N에 있는 숫자가 없으면
        // 올리는게 이득인지
        // 내리는게 이득인지 판별할 것

        target = br.readLine();
        N = Integer.parseInt(target);

        // 고장난 버튼의 갯수
        int c = Integer.parseInt(br.readLine());

        brokenButton = new boolean[10];

        if (c > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < c; i++) {
                brokenButton[Integer.parseInt(st.nextToken())] = true;
            }
        }

        // 정상적인 숫자중 가장 큰 것과 가장 작은 것
        minNotBroken = 10;
        maxNotBroken = -1;

        isZeroPossible = !brokenButton[0];

        for (int i = 1; i < 10; i++) {

            if (!brokenButton[i]) {
                if (minNotBroken > i) {
                    minNotBroken = i;
                }
                if (maxNotBroken < i) {
                    maxNotBroken = i;
                }
            }
        }

        int count = Integer.MAX_VALUE;

        // 1. 숫자만 눌러서 만들 수 있는 경우
        // 숫자를 돌면서 망가진 버튼이 없어야 함
        for (int i = 0; i < target.length(); i++) {
            if (brokenButton[target.charAt(i) - '0']) {
                break;
            }

            if (i == target.length() - 1) {
                count = Math.min(count, target.length());
            }
        }

        // 2. +, - 만 눌러서 만들 수 있는 경우
        // 100번에서 +, - 로 이동하는 경우
        count = Math.min(count, Math.abs(100 - N));

        // 3-1. 증가한 자릿수에서 빼기
        int tmpNumber = minNotBroken * (int) Math.pow(10, target.length());
        for (int i = 0; i < target.length(); i++) {
            if (!isZeroPossible) {
                tmpNumber += minNotBroken * (int) Math.pow(10, target.length() - 1 - i);
            }
        }

        count = Math.min(count, Math.abs(N - tmpNumber) + target.length() + 1);

        // 3-2. 감소한 자릿수에서 더하기
        if (target.length() >= 2) {
            tmpNumber = maxNotBroken * (int) Math.pow(10, target.length() - 2);
            for (int i = 2; i < target.length(); i++) {

                tmpNumber += maxNotBroken * (int) Math.pow(10, target.length() - 1 - i);

            }
            count = Math.min(count, Math.abs(N - tmpNumber) + target.length() - 1);
        }

        checkNumber(0, 0);
        // System.out.println(targetMin + " " + targetMax);

        if (targetMax - N >= 0) {
            count = Math.min(count, targetMax - N + target.length());
        }
        if (N - targetMin >= 0) {
            count = Math.min(count, N - targetMin + target.length());
        }

        // 0을 누르고 이동하는 경우
        if (isZeroPossible) {
            count = Math.min(N + 1, count);
        }

        bw.write(count + "\n");

        br.close();
        bw.close();
    }
}
