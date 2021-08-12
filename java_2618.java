import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Unit {
    int y, x;

    public Unit(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class java_2618 {
    final static int INF = 987654321;
    static Unit incident[];
    static int cache[][];

    // AAA ABB ABA ...
    //
    public static int calDistance(int Y, int X, Unit index) {
        return Math.abs(Y - index.y) + Math.abs(X - index.x);
    }

    // 최근 경찰차(A or B)가 이동을 한 상태에서
    // 이동한 거리의 합이 짧은 경우를 반환
    public static int solve(int AY, int AX, int BY, int BX, int A, int B, int nowIndex) {
        if (nowIndex == incident.length) {
            // System.out.println("끝에 도달");
            return 0;
        }

        if (cache[A][B] != -1) {
            // System.out.println(A+" " +B+"기저사례"+cache[A][B]);
            return cache[A][B];
        }

        // 거리 계산
        Unit nowIncident = incident[nowIndex];

        int distanceA = calDistance(AY, AX, nowIncident);
        int distanceB = calDistance(BY, BX, nowIncident);

        // System.out.println(distanceA + "aaa" + distanceB);

        cache[A][B] = INF;

        int selectA = solve(nowIncident.y, nowIncident.x, BY, BX, nowIndex, B, nowIndex + 1) + distanceA;

        cache[A][B] = Math.min(cache[A][B], selectA);

        int selectB = solve(AY, AX, nowIncident.y, nowIncident.x, A, nowIndex, nowIndex + 1) + distanceB;

        cache[A][B] = Math.min(cache[A][B], selectB);

        return cache[A][B];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int road = Integer.parseInt(br.readLine());

        // 사건 데이터 삽입
        int N = Integer.parseInt(br.readLine());
        incident = new Unit[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            incident[i] = new Unit(y, x);
        }

        // 캐시 초기화

        cache = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                cache[i][j] = -1;
            }
        }

        // 결과 출력

        bw.write(solve(1, 1, road, road, 0, 0, 1) + "\n");

        // 경로 역추적
        int indexA = 0;
        int indexB = 0;
        int nowIncident = 1;

        //System.out.println(cache[indexA][indexB]);

        while (indexA < N && indexB < N) {
            // 경찰차 B가 출동한 것이 총 거리가 더 짧을 경우


            int ay = (indexA == 0) ? 1 : incident[indexA].y;
            int ax = (indexA == 0) ? 1 : incident[indexA].x;

            int by = (indexB == 0) ? road : incident[indexB].y;
            int bx = (indexB == 0) ? road : incident[indexB].x;

            int distanceA = calDistance(ay, ax, incident[nowIncident]);
            int distanceB = calDistance(by, bx, incident[nowIncident]);

           // System.out.println(cache[indexA][nowIncident]+".."+distanceA + "---" + cache[nowIncident][indexB]+".."+distanceB);

            if (cache[indexA][nowIncident] + distanceB < cache[nowIncident][indexB] + distanceA) {
                indexB = nowIncident;
                bw.write("2" + "\n");
            }
            // 경찰차 A가 출동한 것이 거리가 더 짧을 경우
            else {
                indexA = nowIncident;
                bw.write("1" + "\n");
            }

            nowIncident++;
        }

        br.close();
        bw.close();
    }
}
