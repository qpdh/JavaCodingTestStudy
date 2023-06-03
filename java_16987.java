import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_16987 {

    // 계란의 개수
    static int N;

    // 계란 클래스
    static class Egg {
        int weight;
        int durability;

        public Egg(int weight, int durability) {
            this.weight = weight;
            this.durability = durability;
        }
    }

    static Egg eggs[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        eggs = new Egg[N];

        // 계란 데이터 입력
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            eggs[i] = new Egg(weight, durability);
        }

        int result = calcBreakEgg(0);

        System.out.println(result);

        br.close();
    }

    // index 번째 계란으로 어떤 계란을 칠 것인가? -> 최대값 반환
    private static int calcBreakEgg(int index) {
        // 마지막 계란일 경우 -> 깰 수 없음
        if (index == N) {
            return 0;
        }

        // TODO 현재 계란이 깨져있는 경우
        if (eggs[index].durability <= 0) {
            return calcBreakEgg(index + 1);
        }

        int maxValue = 0;

        // 다음에 깰 계란을 선택
        for (int targetIndex = 0; targetIndex < N; ++targetIndex) {
            if (targetIndex == index) {
                continue;
            }

            // 깨진 계란의 수
            int count = 0;

            // TODO 깰 수 있는지 체크
            // 이미 깨져있는 경우 -> 깨지 않음
            if (eggs[targetIndex].durability <= 0) {
                continue;
            }

            eggs[index].durability -= eggs[targetIndex].weight;
            eggs[targetIndex].durability -= eggs[index].weight;

            // 깨졌는지 체크
            if (eggs[index].durability <= 0) {
                ++count;
            }
            if (eggs[targetIndex].durability <= 0) {
                ++count;
            }

            int tmpValue = count + calcBreakEgg(index + 1);
            maxValue = Math.max(maxValue, tmpValue);

            eggs[index].durability += eggs[targetIndex].weight;
            eggs[targetIndex].durability += eggs[index].weight;
        }

        return maxValue;
    }

}
