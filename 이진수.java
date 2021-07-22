/*
You should use the standard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
public class 이진수 {
    static String Answer;

    public static void main(String args[]) throws Exception {
        /*
         * The method below means that the program will read from input.txt, instead of
         * standard(keyboard) input. To test your program, you may save input data in
         * input.txt file, and call below method to read from the file when using
         * nextInt() method. You may remove the comment symbols(//) in the below
         * statement and use it. But before submission, you must remove the freopen
         * function or rewrite comment symbols(//).
         */

        /*
         * Make new scanner from standard input System.in, and read data.
         */
        Scanner sc = new Scanner(System.in);
        // Scanner sc = new Scanner(new FileInputStream("input.txt"));

        int T = sc.nextInt();
        for (int test_case = 0; test_case < T; test_case++) {

            // Answer = 0;
            /////////////////////////////////////////////////////////////////////////////////////////////
            /*
             * Implement your algorithm here. The answer to the case will be stored in
             * variable Answer.
             */
            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to standard output(screen).
            int n = sc.nextInt();
            int t = sc.nextInt();
            String b = sc.next();

            Answer = toBianry(b, t);

            System.out.println("Case #" + (test_case + 1));
            System.out.println(Answer);
        }
    }

    public static String toBianry(String b, int t) {

        char a[] = new char[b.length()];

        for (int i = 0; i < a.length; i++) {
            a[i] = '-';
        }

        boolean isCheck[] = new boolean[b.length()];

        // 확실하게 0, 1인 곳 찾기
        // i +- t 가 배열 밖이면?
        // b가 0일 때
        int i = 0;
        while (true) {
            if (i >= b.length())
                break;

            if (b.charAt(i) == '1') {
                if (i + t >= b.length()) {
                    a[i - t] = '1';
                    isCheck[i - t] = true;
                } else if (i - t < 0) {
                    a[i + t] = '1';
                    isCheck[i + t] = true;
                }
            }

            else if (b.charAt(i) == '0') {
                if (i + t >= b.length()) {
                    a[i - t] = '0';
                    isCheck[i - t] = true;
                }

                else if (i - t < 0) {
                    a[i + t] = '0';
                    isCheck[i + t] = true;
                }

                else {
                    a[i + t] = '0';
                    a[i - t] = '0';
                    isCheck[i + t] = true;
                    isCheck[i - t] = true;
                }
            }

            i++;
        }

        // j 기준으로 탐색
        for (int j = 0; j < a.length - t; j++) {
            if (a[j] == '-') {
                if (b.charAt(j + t) == '0') {
                    a[j] = '0';
                    isCheck[j] = true;
                } else if (b.charAt(j + t) == '1') {

                    if (a[j + t + t] == '1') {
                        a[j] = '0';
                    } else if (a[j + t + t] == '0') {
                        a[j] = '1';
                    }
                }
            }
        }

        int count = 0;
        for (int j = 0; j < a.length; j++) {
            if (a[j] == '-')
                count += 1;
        }
        int j = 0;
        for (; j < a.length; j++) {
            if (a[j] == '-') {
                break;
            }
        }

        // j번째부터 진짜 최소 1찾기
        ////////////////////////////
        ///////////////
        //
        //

        // if (j + (2 * t) >= a.length) {
        //     a[j] = '1';
        // } else {
        //     a[j] = '0';
        //     j += 2 * t;
        // }

        // for (int k = j; k < a.length;) {
        //     if (k + (2 * t) >= a.length) {
        //         a[k] = '1';
        //         break;
        //     }

        //     if (k + (4 * t) >= a.length) {
        //         a[k] = '1';
        //         a[k + (2 * t)] = '0';
        //         break;
        //     }

        //     if (k + (6 * t) >= a.length) {
        //         a[k] = '1';
        //         a[k + (2 * t)] = '0';
        //         a[k + (4 * t)] = '1';
        //         break;
        //     }

        //     else {
        //         a[k] = '1';
        //         a[k + (2 * t)] = '0';
        //         a[k + (4 * t)] = '0';
        //         k += 6 * t;
        //     }
        // }

        String ret = new String(a);
        return ret;
    }
}