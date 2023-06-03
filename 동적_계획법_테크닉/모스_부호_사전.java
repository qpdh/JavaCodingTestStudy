package 동적_계획법_테크닉;

public class 모스_부호_사전 {

    static int skip;
    final static int M = 1_000_000_000 + 100;

    static int bino[][];

    static void calcBino() {
        bino = new int[201][201];
        for (int i = 0; i <= 200; i++) {
            // nC0 == nCn == 1 이므로
            bino[i][0] = bino[i][i] = 1;

            for (int j = 1; j < i; j++) {
                bino[i][j] = Math.min(M, bino[i - 1][j - 1] + bino[i - 1][j]);
            }
        }
    }

    // s : 지금까지 만든 신호
    // n : 더 필요한 -의 갯수
    // m : 더 필요한 o의 갯수
    // skip 개를 건너 뛰고 출력한다.
    static String kth(int n, int m, int skip) {
        // n이 0이면 나머지는 o이다.
        if (n == 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < m; i++) {
                sb.append('o');
            }
            return sb.toString();
        }

        // -를 선택해야 하는 경우
        if (skip < bino[n + m - 1][n - 1]) {
            return "-" + kth(n - 1, m, skip);
        }

        // o를 선택해야 하는 경우
        return "o" + kth(n, m - 1, skip - bino[n + m - 1][n - 1]);
    }

    // s : 지금까지 만든 신호
    // n : 더 필요한 -의 갯수
    // m : 더 필요한 o의 갯수
    // skip 개를 건너 뛰고 출력한다.
    static void generateMorse3(int n, int m, String s) {
        //
        if (skip < 0) {
            return;
        }
        // n==m==0 인 경우 -> 더 만들 필요가 없다
        if (n == 0 && m == 0) {
            // skip 횟수가 0이면 출력한다.
            if (skip == 0) {
                System.out.println(s);
            }
            skip--;
            return;
        }

        // 현재 경우의 수를 제거할 수 있는 경우 -> 이후 조합은 신경쓰지 않음
        if (bino[n + m][n] <= skip) {
            skip -= bino[n + m][n];
            return;
        }

        // n>0인 경우 -를 우선적으로 만든다.
        if (n > 0) {
            generateMorse3(n - 1, m, s + "-");
        }
        // m>0인 경우 o를 만든다.
        if (n > 0) {
            generateMorse3(n, m - 1, s + "o");
        }
    }

    // s : 지금까지 만든 신호
    // n : 더 필요한 -의 갯수
    // m : 더 필요한 o의 갯수
    // skip 개를 건너 뛰고 출력한다.
    static void generateMorse2(int n, int m, String s) {
        //
        if (skip < 0) {
            return;
        }
        // n==m==0 인 경우 -> 더 만들 필요가 없다
        if (n == 0 && m == 0) {
            // skip 횟수가 0이면 출력한다.
            if (skip == 0) {
                System.out.println(s);
            }
            skip--;
            return;
        }

        // n>0인 경우 -를 우선적으로 만든다.
        if (n > 0) {
            generateMorse2(n - 1, m, s + "-");
        }
        // m>0인 경우 o를 만든다.
        if (n > 0) {
            generateMorse2(n, m - 1, s + "o");
        }
    }

    // s : 지금까지 만든 신호
    // n : 더 필요한 -의 갯수
    // m : 더 필요한 o의 갯수
    static void generateMorse(int n, int m, String s) {
        // n==m==0 인 경우 -> 더 만들 필요가 없다
        if (n == 0 && m == 0) {
            System.out.println(s);
            return;
        }

        // n>0인 경우 -를 우선적으로 만든다.
        if (n > 0) {
            generateMorse(n - 1, m, s + "-");
        }
        // m>0인 경우 o를 만든다.
        if (n > 0) {
            generateMorse(n, m - 1, s + "o");
        }
    }

    public static void main(String[] args) {

    }
}
