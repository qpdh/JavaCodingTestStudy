import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_27313 {
    // 애니메이션이 N개
    static int N;
    // M시간동안 보기
    static int M;
    // K개씩 묶어서 보기
    static int K;

    static int animations[];

    /*
     * 보지 않은 애니메이션 중 K개 이하를 "동시에" 보기
     * 새로운 애니메이션을 도중에 추가 불가
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 애니메이션의 길이 목록
        animations = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            animations[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(animations);

        // 짧은 것 우선적으로 보면 좋음
        // 비슷한 시간이 걸릴수록 묶어서 보는게 좋음

        // K개를 꽉 채워보는것이 항상 이득인가? -> 아니다.
        // 항상 긴 시간따라 움직인다.
        // 짧은 것 부터 K개 보기
        // animations[i] 까지 보기
        // animations[i-1] 까지 보고 animations[i+2] 까지 보기

        // 현재 i부터 볼 차례고, 남은 시간이 M일 때, 최대 갯수?

        // 최악으로 걸리는 시간 == 1개씩 모든 애니메이션 보기
        // 최선으로 걸리는 시간 == 모든 애니메이션을 한번에 보기

        // 몇번으로 나눠볼 것인가? -> 매개변수

        /////////////////////////////////////////////
        // 몇 개의 애니메이션을 볼 수 있는가? -> 매개변수
        /////////////////////////////////////////////

        int result = solution();

        System.out.println(result);

        br.close();
    }

    private static int solution() {
        int low = 1;
        int high = N;
        int mid = -1;

        while (low <= high) {
            mid = (low + high) / 2;

            // mid 개의 애니메이션을 볼 수 있는가?
            boolean canWatch = checkWatch(mid);

            // 볼 수 있다면.. 더 많은 애니메이션 봐보기
            if (canWatch) {
                low = mid + 1;
                continue;
            }

            // 더 적은 애니메이션 봐보기
            high = mid - 1;
        }

        return high;
    }

    // goalAnimation개의 애니메이션을 볼 수 있는가?
    private static boolean checkWatch(int goalAnimation) {
        // 볼 수 없다면..
        if (animations[goalAnimation - 1] > M) {
            return false;
        }

        // K개씩 나누어 적절하게 분배해보자
        // 길이가 긴 것 부터 K개씩 나누어 보는 것이 이득이다.
        long time = 0;

        // [0,goalAnimation-1] 애니메이션을 봐야 함
        int index = goalAnimation - 1;

        while (true) {
            // 시간 추가
            time += animations[index];

            // 음수라면..나머지 다 본 것임
            if (index - K < 0) {
                break;
            }

            // K개 보기
            index -= K;
        }

        return time <= M;
    }
}
