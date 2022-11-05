import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class java_17615 {
    static int N;
    static char ballList[];

    static class Pair {
        int startIndex, endIndex;

        public Pair(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public String toString() {
            return "Pair [startIndex=" + startIndex + ", endIndex=" + endIndex + "]";
        }

    }

    static Pair findStartEndIndex() {
        int startIndex = 0;
        char startColor = ballList[startIndex];

        for (; startIndex < ballList.length; startIndex++) {
            if (ballList[startIndex] != startColor) {
                break;
            }
        }

        int endIndex = ballList.length - 1;
        char endColor = ballList[endIndex];

        for (; endIndex >= 0; endIndex--) {
            if (ballList[endIndex] != endColor) {
                break;
            }
        }

        return new Pair(startIndex, endIndex);

    }

    static int countRB(Pair indexPair) {
        int result = Integer.MAX_VALUE;

        int redCount = 0;
        int blueCount = 0;

        // start 인덱스 부터 R의 갯수, B의 갯수를 카운트
        for (int i = indexPair.startIndex; i < ballList.length; i++) {
            if (ballList[i] == 'R') {
                redCount++;
            } else {
                blueCount++;
            }
        }

        result = Math.min(redCount, blueCount);

        redCount = 0;
        blueCount = 0;

        // end 인덱스 까지 R의 갯수, B의 갯수를 카운트
        for (int i = indexPair.endIndex; i >= 0; i--) {
            if (ballList[i] == 'R') {
                redCount++;
            } else {
                blueCount++;
            }
        }

        result = Math.min(result, Math.min(redCount, blueCount));

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 같은 색 볼 끼리 인접하게 놓이도록 하려 한다.

        // 규칙 1. 바로 옆에 달느 색깔의 볼이 있다면 전부 뛰어 넘을 수 있음

        // 규칙 2. 옮길 수 있는 볼의 색깔은 한 가지 이다. (처음 선택하는 색깔의 볼만 옮길 수 있다.)

        // 왼쪽 끝, 오른쪽 끝 연속된 볼 이후의 볼 부터 시작해보자

        //
        N = Integer.parseInt(br.readLine());

        String ballString = br.readLine();
        ballList = ballString.toCharArray();

        Pair indexPair = findStartEndIndex();

        // System.out.println(indexPair);

        // startIndex가 length 라면...
        if (indexPair.startIndex == ballString.length() || indexPair.endIndex == -1) {
            System.out.println(0);
        } else {
            // firstIndex 부터 R의 수, B의 수를 찾는다.

            // endIndex 까지 R의 수, B의 수를 찾는다.
            int result = countRB(indexPair);

            System.out.println(result);
        }

        br.close();
    }
}
