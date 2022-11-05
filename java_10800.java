import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class java_10800 {
    // 공의 갯수
    static int N;
    static int sumOfColorList[];

    static class Trio {
        int ballColor, ballSize, index;
        int score;

        public Trio(int ballColor, int ballSize, int index) {
            this.ballColor = ballColor;
            this.ballSize = ballSize;
            this.index = index;
            this.score = 0;
        }

        @Override
        public String toString() {
            return "Trio [ballColor=" + ballColor + ", ballSize=" + ballSize + ", index=" + index + ", score=" + score
                    + "]";
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 자기 공보다 크기가 작고 색이 다른 공을 잡아 그 공의 크기만큼 점수를 얻는다.

        N = Integer.parseInt(br.readLine());

        Trio trio[] = new Trio[N];

        // 현재 인덱스의 공의 크기보다 작은 색깔마다의 점수의 누적 합
        sumOfColorList = new int[N];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int ballColor = Integer.parseInt(st.nextToken()) - 1;
            int ballSize = Integer.parseInt(st.nextToken());
            trio[i] = new Trio(ballColor, ballSize, i);
        }

        // 크기 순으로 정렬
        Arrays.sort(trio, new Comparator<Trio>() {
            @Override
            public int compare(Trio o1, Trio o2) {
                // TODO Auto-generated method stub
                return o1.ballSize - o2.ballSize;
            }
        });

        int totalScore = 0;
        sumOfColorList[trio[0].ballColor] = 0;
        trio[0].score = 0;

        int startIndex = 0;

        // 오름차순이므로 작은 것은 존재하지 않음
        for (int i = 0; i < N - 1; i++) {

            // 크기가 같다면.. 저장하지 않고, 반복문 다시
            if (trio[i].ballSize == trio[i + 1].ballSize) {
                trio[i + 1].score = totalScore - sumOfColorList[trio[i + 1].ballColor];
            }

            // 크기가 다르다면... 현재 공이 이전 공보다 큰 것임
            else {
                for (int index = startIndex; index < i + 1; index++) {
                    sumOfColorList[trio[index].ballColor] += trio[index].ballSize;
                    totalScore += trio[index].ballSize;
                }

                trio[i + 1].score = totalScore - sumOfColorList[trio[i + 1].ballColor];

                startIndex = i + 1;
            }
        }

        // 인덱스 순으로 정렬
        Arrays.sort(trio, new Comparator<Trio>() {
            @Override
            public int compare(Trio o1, Trio o2) {
                // TODO Auto-generated method stub
                return o1.index - o2.index;
            }
        });

        // 사이즈 계산
        for (int i = 0; i < N; i++) {
            bw.write(trio[i].score + "\n");
        }

        bw.close();
        br.close();
    }
}
