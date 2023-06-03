import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 당구_게임_Lead {

    public static void main(String[] args) throws IOException {
        // 2명의 사람이 당구 게임
        // 여러 Round
        // 총 합산으로 승자 결정

        // 합산 점수 차이를 계산, 그 Round까지의 리더를 계산
        // 최대 Lead를 얻는 Player가 승리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int sumA = 0;
        int sumB = 0;

        int leadA = 0;
        int leadB = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sumA += Integer.parseInt(st.nextToken());
            sumB += Integer.parseInt(st.nextToken());

            int tmp = Math.abs(sumA - sumB);
            // 비기는 경우는 없음
            if (sumA > sumB) {
                if (leadA < tmp) {
                    leadA = tmp;
                }

            } else if (sumB < sumA) {
                if (leadB < tmp) {
                    leadB = tmp;
                }
            }
        }

        if (leadA > leadB) {
            System.out.printf("1 %d\n", leadA);
        } else {
            System.out.printf("2 %d\n", leadB);
        }

        br.close();
    }

}
