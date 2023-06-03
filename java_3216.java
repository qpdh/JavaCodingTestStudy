import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_3216 {
    static int N;

    static int songPlayTimes[];
    static int songDownloadTimes[];

    static int solution() {
        int result = songDownloadTimes[0];

        int bonusTime = 0;

        for (int i = 1; i < N; i++) {
            int waitTime = songDownloadTimes[i] - songPlayTimes[i - 1];

            if (waitTime < 0) {
                bonusTime += Math.abs(waitTime);
                continue;
            }

            if (waitTime - bonusTime > 0) {
                result += waitTime - bonusTime;
                bonusTime = 0;
                continue;
            }

            bonusTime -= waitTime;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1번 노래를 듣는데 기다리는 시간 1
        // 2번 노래를 듣는데 기다리는 시간 5 - 2
        // 3번 노래를 듣는데 기다리는 시간 3 - 1
        // 4번 노래를 듣는데 기다리는 시간 4 - 3
        // 1 3 2 1 -> 7

        // 1번 노래를 듣는데 기다리는 시간 1
        // 2번 노래를 듣는데 기다리는 시간 2 - 1
        // 3번 노래를 듣는데 기다리는 시간 1 - 1
        // 4번 노래를 듣는데 기다리는 시간 1 - 3
        // 5번 노래를 듣는데 기다리는 시간 3 - 2
        // 1 1 0 -1 ->
        // 2

        // + 누적합과
        // - 누적합을 구하자

        // 기본적인 연산 : 현재곡의 다운로드 시간 - 이전곡의 재생시간
        N = Integer.parseInt(br.readLine());

        songDownloadTimes = new int[N];
        songPlayTimes = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            songPlayTimes[i] = Integer.parseInt(st.nextToken());
            songDownloadTimes[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }
}
