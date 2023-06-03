import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

public class 극장_컵홀더 {
    static int N;
    static String seat;

    // 이전 좌석에 컵홀더가 있었는가?
    static int countCupHoler() {
        StringBuffer sb = new StringBuffer("*" + seat + "*");

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == 'S') {
                sb.replace(i, i + 1, "*S*");
                i++;
            }
        }

        boolean isBeforeExists = true;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '*') {
                isBeforeExists = true;
            }
            if (sb.charAt(i) == 'L') {
                if (isBeforeExists) {
                    isBeforeExists = false;
                } else {
                    sb.replace(i, i + 1, "L*");
                    isBeforeExists = true;
                    i++;
                }
            }
        }

        for (int i = 0; i < sb.length(); i++) {
            int findIndex = sb.indexOf("**");

            if (findIndex == -1) {
                break;
            } else {
                sb.replace(findIndex, findIndex + 2, "*");
            }
        }

        int result = 0;

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '*') {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // 입접한 좌석 사이에 하나의 컵 홀더
        // 열의 양쪽 끝에 두 개의 추가 컵 홀더

        // 한 쌍의 L좌석 사이에는 컵홀더 없음
        // S좌석에는 양쪽에 컵홀더 존재

        // 좌석 바로 옆에 있는 컵 홀더에 컵을 놓을 수 있는 최대 사람의 수를 찾기

        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        seat = scanner.next();

        System.out.println(Math.min(countCupHoler(), seat.length()));

        scanner.close();
    }
}
