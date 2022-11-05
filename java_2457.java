import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class java_2457 {
    static Flower flowerList[];

    static int N;

    static class Flower {
        int startDate, endDate;

        int floweringDate;

        static int baseDate = 301;

        public Flower(int startMonth, int startDate, int endMonth, int endDate) {
            this.startDate = startMonth * 100 + startDate;
            this.endDate = endMonth * 100 + endDate;

            this.floweringDate = -1;
            calcFloweringDate();
        }

        // baseMonth, baseDate 이하이고, 피는 날을 반환
        public void calcFloweringDate() {
            int startM = startDate / 100;
            int startD = startDate % 100;

            int baseM = baseDate / 100;
            int baseD = baseDate % 100;

            int endM = endDate / 100;
            int endD = endDate % 100;

            // base 날짜 이전에 피는 꽃이여야 함
            if (baseM < startM) {
                this.floweringDate = -1;
                return;
            }

            if (baseM == startM && baseD < startD) {
                this.floweringDate = -1;
                return;
            }

            if (endM < baseM) {
                this.floweringDate = -1;
                return;
            }

            if (endM == baseM && endD <= baseD) {
                this.floweringDate = -1;
                return;
            }

            this.floweringDate = endDate - baseDate;
            return;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 꽃

        // 같은 해에 피어서 같은 해에 진다.

        // 하나의 꽃은 피는 날과 지는 날이 정해져 있다.

        // 5월 8일에 피고 6월 13일에 지는 꽃은
        // 5월 8일 부터 6월 12 일까지 피어있음

        // 1. 3월 1일부터 11월 30일 까지 한 가지 이상 피어있도록 한다.
        // 2. 심는 꽃의 수를 최대한 적게한다.

        // 데이터 입력받기
        N = Integer.parseInt(br.readLine());

        flowerList = new Flower[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int startMonth = Integer.parseInt(st.nextToken());
            int startDate = Integer.parseInt(st.nextToken());

            int endMonth = Integer.parseInt(st.nextToken());
            int endDate = Integer.parseInt(st.nextToken());

            flowerList[i] = new Flower(startMonth, startDate, endMonth, endDate);
        }

        int count = 0;
        while (true) {
            // 12월 부터 피는 꽃이 기준이 된다면 종료
            if (Flower.baseDate > 1130) {
                break;
            }

            // 개화기간 내림차순 정렬
            Arrays.sort(flowerList, new Comparator<Flower>() {
                @Override
                public int compare(Flower o1, Flower o2) {
                    return o2.floweringDate - o1.floweringDate;
                }
            });

            // 꽃을 선택할 수 없다면..
            if (flowerList[0].floweringDate <= 0) {
                count = 0;
                break;
            }

            // System.out.println(flowerList[0]);

            // flowerList[0] 의 지는 날짜에 피는 꽃을 찾자
            Flower.baseDate = flowerList[0].endDate;

            for (int i = 0; i < flowerList.length; i++) {
                flowerList[i].calcFloweringDate();
            }

            count++;
        }

        System.out.println(count);

        // 3월 1일 이하이며(이전에 피며) 피어있는 기간이 가장 긴 꽃을 선택
        // 그 기간의 지는 값을 저장

        // 그 기간의 지는 값 이하이며.. 피어있는 기간이 가장 긴 꽃을 선택
        // ...

        // 값이 11월 30일을 넘어서게 된다면 종료

        br.close();
    }
}
