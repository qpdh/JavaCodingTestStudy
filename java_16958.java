import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class java_16958 {
    // N : 도시의 수
    // T : 텔레포트하는데 걸리는 시간
    static int N, T;

    static City cities[];

    static int cityMatrix[][];

    static class City {
        boolean isSpecial;
        int x, y;

        public City(boolean isSpecial, int x, int y) {
            this.isSpecial = isSpecial;
            this.x = x;
            this.y = y;
        }

        // 텔레포트를 고려한 거리 계산
        public int calcDistance(City target) {
            int distance = Math.abs(this.x - target.x) + Math.abs(this.y - target.y);

            // 텔레포트가 가능한 경우
            if (this.isSpecial && target.isSpecial) {
                return Math.min(distance, T);
            }

            // 텔레포트가 불가능한 경우 (해밀턴 거리 반환)
            return distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        cities = new City[N];
        cityMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            boolean isSpecial = Integer.parseInt(st.nextToken()) == 1 ? true : false;
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            City city = new City(isSpecial, x, y);
            cities[i] = city;
            Arrays.fill(cityMatrix[i], Integer.MAX_VALUE);
            cityMatrix[i][i] = 0;
        }

        // 거리 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cityMatrix[i][j] = cities[i].calcDistance(cities[j]);
                cityMatrix[j][i] = cities[i].calcDistance(cities[j]);
            }
        }

        // 최단거리 계산하기
        floydWarshall();

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cityA = Integer.parseInt(st.nextToken()) - 1;
            int cityB = Integer.parseInt(st.nextToken()) - 1;

            System.out.println(cityMatrix[cityA][cityB]);
        }

        br.close();
    }

    private static void floydWarshall() {
        // i를 들려서
        for (int i = 0; i < N; i++) {
            // j에서
            for (int j = 0; j < N; j++) {
                // k를 가는 방법
                for (int k = 0; k < N; k++) {
                    if (j == k || k == i) {
                        continue;
                    }
                    // j->i->k 가는 거리 계산
                    // 거리 갱신
                    cityMatrix[j][k] = Math.min(cityMatrix[j][i] + cityMatrix[i][k], cityMatrix[j][k]);
                }
            }
        }
    }

}
