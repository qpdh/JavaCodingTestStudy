import java.util.Scanner;

public class 반복없는_숫자 {
    static int solution(int number) {
        if (number == 0) {
            System.out.println(1);
            return 1;
        }
        // 9자리를 넘어서는 경우
        // 0은 사용하지 않는다.
        // 987654321
        if (number >= 987654321) {
            System.out.println(0);
            return 0;
        }

        // number의 자릿수
        // 987654 보다 큰 경우 -> 자릿수 증가
        int baseNumber = 987654321;
        int baseNumberReverse = 12345679;
        int numberLength = Integer.toString(number).length();
        if (number >= (int) (baseNumber / Math.pow(10, (9 - numberLength)))) {
            // // 자릿수를 증가시켜야 함
            // System.out.println("자릿수 증가 필요");
            // // 123456789
            // System.out.println("표현 자릿수 " + numberLength);
            return (int) (baseNumberReverse / Math.pow(10, (9 - numberLength - 2)));
            // System.out.println((int) (baseNumberReverse / Math.pow(10, (9 - numberLength
            // - 2))));
        } else if (numberLength == 9) {
            // 9자리인 경우
            StringBuffer sb = new StringBuffer(Integer.toString(number));
            int beforeNumber = sb.charAt(sb.length() - 1) - '0';
            for (int i = sb.length() - 2; i >= 0; i--) {
                // System.out.println(beforeNumber + " " + (sb.charAt(i) - '0'));
                if (beforeNumber > sb.charAt(i) - '0') {
                    // System.out.println("커지는 인덱스" + i);
                    int n1 = sb.charAt(i) - '0';
                    int n2 = sb.charAt(i + 1) - '0';
                    // System.out.println("숫자" + n1 + " " + n2);
                    sb.replace(i, i + 2, n2 + "" + n1);
                    break;
                }
                beforeNumber = sb.charAt(i) - '0';
            }

            return Integer.parseInt(sb.toString());
            // System.out.println(sb.toString());
        } else {
            // 0~9
            boolean isUsed[] = new boolean[10];

            String stringNumber = Integer.toString(number);
            // 자릿수 그대로
            // 현재 자릿수 이상되는 숫자 넣기
            StringBuffer sb = new StringBuffer();
            boolean isLarger = false;
            for (int i = 0; i < stringNumber.length(); i++) {
                int thisNumber = stringNumber.charAt(i) - '0';

                if (thisNumber == 0) {
                    thisNumber++;
                }
                if (stringNumber.length() - 1 == i && !isLarger) {
                    thisNumber++;
                }

                for (int j = thisNumber; j < 10; j++) {
                    if (!isUsed[j]) {
                        if (j > thisNumber) {
                            isLarger = true;
                        }
                        isUsed[j] = true;
                        sb.append(j);
                        break;
                    }
                }
            }

            if (sb.toString().equals(stringNumber) || sb.length() < stringNumber.length()) {
                sb = new StringBuffer(stringNumber);
                int beforeNumber = sb.charAt(sb.length() - 1) - '0';
                for (int i = sb.length() - 2; i >= 0; i--) {
                    // System.out.println(beforeNumber + " " + (sb.charAt(i) - '0'));
                    if (beforeNumber > sb.charAt(i) - '0') {
                        // System.out.println("커지는 인덱스" + i);
                        int n1 = sb.charAt(i) - '0';
                        int n2 = sb.charAt(i + 1) - '0';
                        // System.out.println("숫자" + n1 + " " + n2);
                        sb.replace(i, i + 2, n2 + "" + n1);
                        break;
                    }
                    beforeNumber = sb.charAt(i) - '0';
                }
            }

            return Integer.parseInt(sb.toString());
            // System.out.println(sb.toString());
        }
    }

    static void solution2(int number) {
        if (number == -1) {
            System.out.println("1");
            return;
        }

        // number의 자릿수
        // -123456789 보다 작아야 하는 경우 -> 자릿수 감소
        int baseNumber = 123456789;
        int baseNumberReverse = 987654321;

        number = Math.abs(number);
        int numberLength = Integer.toString(number).length();

        if (number <= (int) (baseNumber / Math.pow(10, (9 - numberLength)))) {
            // 자릿수를 감소시켜야 함
            System.out.println("-" + (int) (baseNumberReverse / Math.pow(10, (9 - numberLength + 1))));
        }
        // 9자리인 경우
        else if (numberLength == 9) {
            StringBuffer sb = new StringBuffer(Integer.toString(number));
            int beforeNumber = sb.charAt(sb.length() - 1) - '0';
            for (int i = sb.length() - 2; i >= 0; i--) {
                // System.out.println(beforeNumber + " " + (sb.charAt(i) - '0'));
                if (beforeNumber < sb.charAt(i) - '0') {
                    // System.out.println("커지는 인덱스" + i);
                    int n1 = sb.charAt(i) - '0';
                    int n2 = sb.charAt(i + 1) - '0';
                    // System.out.println("숫자" + n1 + " " + n2);
                    sb.replace(i, i + 2, n2 + "" + n1);
                    break;
                }
                beforeNumber = sb.charAt(i) - '0';
            }

            System.out.println("-" + sb.toString());
        } else {
            // 0~9
            boolean isUsed[] = new boolean[10];

            String stringNumber = Integer.toString(number);
            // 자릿수 그대로
            // 현재 자릿수 이상되는 숫자 넣기
            StringBuffer sb = new StringBuffer();
            boolean isSmaller = false;
            for (int i = 0; i < stringNumber.length(); i++) {
                int thisNumber = stringNumber.charAt(i) - '0';

                if (isSmaller) {
                    thisNumber = 9;
                }

                if (i == stringNumber.length() - 1 && !isSmaller) {

                }

                for (int j = thisNumber; j >= 1; j--) {
                    if (!isUsed[j]) {
                        if (j < thisNumber) {
                            isSmaller = true;
                        }
                        isUsed[j] = true;
                        sb.append(j);
                        break;
                    }
                }
            }

            if (sb.toString().equals(stringNumber) || sb.length() < stringNumber.length()) {
                sb = new StringBuffer(stringNumber);
                int beforeNumber = sb.charAt(sb.length() - 1) - '0';
                for (int i = sb.length() - 2; i >= 0; i--) {
                    // System.out.println(beforeNumber + " " + (sb.charAt(i) - '0'));
                    if (beforeNumber < sb.charAt(i) - '0') {
                        // System.out.println("커지는 인덱스" + i);
                        int n1 = sb.charAt(i) - '0';
                        int n2 = sb.charAt(i + 1) - '0';
                        // System.out.println("숫자" + n1 + " " + n2);
                        sb.replace(i, i + 2, n2 + "" + n1);
                        break;
                    }
                    beforeNumber = sb.charAt(i) - '0';
                }
            }

            System.out.println("-" + sb.toString());
        }
    }

    public static void main(String[] args) {
        // 자릿수가 증가하는 경우
        // 자릿수가 그대로인 경우
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        if (number >= 0) {
            System.out.println(solution(number));
        } else {
            number += 999;
            System.out.println(solution(number) - 999);
        }

        scanner.close();
    }
}
