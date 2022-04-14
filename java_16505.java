import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class java_16505 {
    static char star[][];

    // 중앙이 뚫린 별
    public static void drawStar(int y, int x, int width) {
        if (width < 4) {
            return;
        }

        // 최소 단위
        if (width == 4) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4 - i; j++) {
                    star[y + i][x + j] = '*';
                }
            }
            star[y + 1][x + 1] = ' ';
            return;
        }

        // width는 삼각형 밑변 길이
        // width는 반씩 줄어듦
        // width가 4면 가장 작은 형태임

        // 찍어야하는 별 형태는 4가지

        // 빈 공간을 찍어야 한다면 -> 빈 공간 찍기

        // 삼각형 좌측 상단 위치 반환
        // (x, y), (x + width/2, y), (x, y + width/2)
        drawStar(y, x, width / 2);
        drawStar(y, x + width / 2, width / 2);
        drawStar(y + width / 2, x, width / 2);
        // drawStar(y + 1, x + width / 2 - 1, width / 2, true);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        if (N == 0) {
            bw.write("*");
            bw.close();
            br.close();
            return;
        }

        else if (N == 1) {
            bw.write("**\n");
            bw.write("*");
            bw.close();
            br.close();
            return;
        }

        star = new char[(int) Math.pow(2, N)][(int) Math.pow(2, N)];

        for (int i = 0; i < star.length; i++) {
            Arrays.fill(star[i], ' ');
        }

        drawStar(0, 0, (int) Math.pow(2, N));

        for (int i = 0; i < star.length; i++) {
            for (int j = 0; j < star[i].length - i; j++) {
                System.out.print(star[i][j]);
            }
            System.out.println();
        }

        br.close();
        bw.close();
    }
}
