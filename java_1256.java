import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_1256 {
    static int N, M, K;
    static long cache[][];

    public static long calcCombination(int n, int r) {
        if (r == 0 || n == r) {
            return 1;
        }

        if (r == -1) {
            return -1;
        }

        if (cache[n][r] != -1) {
            return cache[n][r];
        }

        cache[n][r] = calcCombination(n - 1, r - 1) + calcCombination(n - 1, r);

        if (cache[n][r] > 1_000_000_000) {
            cache[n][r] = Integer.MAX_VALUE;
        }

        return cache[n][r];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // a의 갯수
        N = Integer.parseInt(st.nextToken());

        // z의 갯수
        M = Integer.parseInt(st.nextToken());

        // k번째 문자열이 무엇인가?
        K = Integer.parseInt(st.nextToken());

        cache = new long[N + M + 1][N + M + 1];
        for (int i = 0; i < N + M + 1; i++) {
            Arrays.fill(cache[i], -1);
        }

        // 1. 단순히 뽑아서 자동정렬시켜보자

        // a를 N개
        // z를 M개 써서 만드는 경우의 수

        // a 5개
        // b 4개
        // _a_a_a_a_a_
        // b가 들어갈 수 있는 자리 6개, 중복 가능
        // 6^4

        // _a_a_

        // a 100개
        // b 100개
        // b가 들어갈 수 있는 자리 101개, 중복 가능
        // 101^100

        ////////////////////////////

        // _a_a_a_
        // b -> 4
        // bb -> aaabb::aabab,aabba::abaab,ababa,abbaa
        // bbb ->aaabbb::aababb,aabbab,aabbba::abaabb,ababab,ababba,abbaab,abbaba,abbbaa
        // [i]

        /*
         * 다른 방법
         * a 5개
         * b 4개
         * _a_a_a_a_a_
         * b가 들어갈 수 있는 자리 (N+1)개, 중복 가능
         * 사전 순서대로 집어넣어보자.
         *
         * 맨 끝에 공간을 이용하는 경우 1^3 가지
         * 맨 끝 2 공간을 이용하는 경우의 수 2C3 가지 (맨 끝 2번째 공간은 무조건 이용)
         * 맨 끝 3 공간을 이용하는 경우의 수 3C3 가지 (맨 끝 3번째 공간은 무조건 이용)
         * 맨 끝 4 공간을 이용하는 경우의 수 4C3 가지 (맨 끝 4번째 공간은 무조건 이용)
         * 맨 끝 5 공간을 이용하는 경우의 수 5C3 가지 (맨 끝 5번째 공간은 무조건 이용)
         *
         * a________
         * 8자리에 a를 5개 배치하는 경우의 수
         *
         * 맨 처음 a로 시작할 때 경우의 수
         * 8자리에 a를 5개 배치하는 경우의 수 8C3 56가지
         *
         *
         */

        // combination(0, 0, N, M, new StringBuffer());

        // if (resultSet.size() < K) {
        // System.out.println(-1);
        // } else {
        // for (int i = 0; i < K - 1; i++) {
        // resultSet.pollFirst();
        // }
        // System.out.println(resultSet.pollFirst());
        // }

        // aazz
        // azaz
        // azza
        // zaaz
        // zaza
        // zzaa

        // 맨 앞에 a가 오는 경우의 수
        // 3가지
        // 맨 앞에 a를 배치하고 나머지 위치에서 a위치 선택하기
        // (countN+countM-1)_C_(countN-1)

        //

        StringBuffer sb = new StringBuffer();
        // i 번째 자리가 무엇인지 찾아보자
        int countN = N;
        int countM = M;
        int countK = K;
        for (int i = 0; i < M + N; i++) {
            // i번째 자리가 a인 경우

            long comb = calcCombination(countN - 1 + countM, countN - 1);
            //
            if (countN > 0 && countK <= comb) {
                sb.append('a');
                countN -= 1;
            } else if (countM > 0) {
                sb.append('z');
                countM -= 1;
                countK -= comb;
            }
        }

        if (sb.length() != M + N) {
            bw.write("-1\n");
        } else {
            bw.write(sb.toString() + "\n");
        }

        br.close();
        bw.close();
    }
}
