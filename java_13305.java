import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_13305 {
    static class GasStation {
        // 리터당 주유가
        int costPerLitter;
        // 다음 도시까지 거리
        int length;
    }

    static GasStation gasStation[];

    public static long calcMinCost() {
        long cost = 0;

        // 현재 포함 다음으로 주유가가 작은 도시를 찾기
        // 마지막 도시는 의미 없으므로 제외
        int now = 0;
        while (now <= gasStation.length - 1) {
            // 다음으로 주유할 도시
            int minCost = gasStation[now].costPerLitter;
            long length = gasStation[now].length;

            int to;
            for (to = now + 1; to < gasStation.length; to++) {
                if (minCost > gasStation[to].costPerLitter) {
                    break;
                }
                length += gasStation[to].length;
            }

            // 맨 마지막 까지 간다면, 0 0
            // 지금 주유하는 것이 이득

            // 현재 위치부터 i도시까지 가기 위한 주유하기
            cost += length * gasStation[now].costPerLitter;
            //System.out.println("현재까지 비용 : " + cost + "거리 : "+length);

            // 현재 위치 변경
            now = to;
        }

        return cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        gasStation = new GasStation[N + 1];

        // 맨 마지막 가상의 위치
        gasStation[N] = new GasStation();
        gasStation[N].costPerLitter = 0;
        gasStation[N].length = 0;


        for (int i = 0; i < N; i++) {
            gasStation[i] = new GasStation();
        }

        // 도로 사이 거리 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            gasStation[i].length = Integer.parseInt(st.nextToken());
        }

        // 주유가 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            gasStation[i].costPerLitter = Integer.parseInt(st.nextToken());
        }

        gasStation[N - 1].length = 0;


        long ret = calcMinCost();
        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
