package 탐욕법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도시락_데우기 {
    // 테스트 케이스의 수
    static int C;
    // 도시락의 수
    static int n;

    static LunchBox[] lunchBoxs;

    //
    static class LunchBox implements Comparable<LunchBox> {
        int m, e;

        @Override
        public int compareTo(LunchBox o) {
            if (m == o.m) {
                return o.e - e;
            }
            return m - o.m;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        C = Integer.parseInt(br.readLine());

        for (int i = 0; i < C; i++) {
            n = Integer.parseInt(br.readLine());

            lunchBoxs = new LunchBox[n];
            for (int j = 0; j < n; j++) {
                lunchBoxs[i] = new LunchBox();
            }

            // 데우는 데 걸리는 시간
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int m = Integer.parseInt(st.nextToken());
                lunchBoxs[i].m = m;
            }

            // 먹는 데 걸리는 시간
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int e = Integer.parseInt(st.nextToken());
                lunchBoxs[i].e = e;
            }

            int result = solution();
            System.out.println(result);
        }

        br.close();
    }
}
