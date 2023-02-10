import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Predicate;

public class java_20055 {
    static int N, K;

    static Belt beltList[];
    static List<Robot> robotList;

    static int beltCount;

    static class Belt {
        boolean robot;
        int durability;

        public Belt(int durability) {
            this.robot = false;
            this.durability = durability;
        }
    }

    static class Robot {
        int index;

        public Robot() {
            this.index = 0;
        }
    }

    static void beltMove() {
        // 벨트 회전하기
        Belt tmp = beltList[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            beltList[i] = beltList[i - 1];
        }
        beltList[0] = tmp;

        // 로봇도 같이 회전하기
        for (Robot robot : robotList) {
            robot.index = (robot.index + 1) % (2 * N);

            // 로봇의 현재 위치가 N-1 이라면 내리기
            if (robot.index == N - 1) {
                beltList[N - 1].robot = false;
            }
        }

        // 로봇 내리기
        robotList.removeIf(new Predicate<Robot>() {
            @Override
            public boolean test(Robot t) {
                if (t.index == N - 1) {
                    return true;
                }
                return false;
            }
        });
    }

    static void robotMove() {
        // 로봇 이동하기
        for (int i = 0; i < robotList.size(); i++) {
            // 이동할 인덱스
            int moveIndex = (robotList.get(i).index + 1) % (2 * N);

            // 벨트의 내구도가 1 이상 이여야 하며
            if (1 <= beltList[moveIndex].durability) {
                // 그 위치에 로봇이 없어야 한다.
                if (!beltList[moveIndex].robot) {
                    // 현재 위치 로봇 없어짐
                    beltList[robotList.get(i).index].robot = false;

                    // 이동할 위치 로봇 생김
                    beltList[moveIndex].robot = true;
                    // 내구도 감소
                    beltList[moveIndex].durability -= 1;

                    // 로봇의 위치 조정
                    robotList.get(i).index = moveIndex;

                    // 내구도가 0이 된다면.. 0카운트 증가
                    if (beltList[moveIndex].durability == 0) {
                        beltCount++;
                    }

                    // 현재 인덱스가 N-1 이라면.. 로봇 내리기
                    if (moveIndex == N - 1) {
                        beltList[moveIndex].robot = false;
                    }
                }
            }
        }

        // 로봇 내리기
        robotList.removeIf(new Predicate<Robot>() {
            @Override
            public boolean test(Robot t) {
                if (t.index == N - 1) {
                    return true;
                }
                return false;
            }
        });
    }

    public static void loadRobot() {
        // 로봇 올리기
        // 내구도가 1 이상이여야 함
        if (1 <= beltList[0].durability) {
            // 로봇이 없어야 함
            if (!beltList[0].robot) {
                // 로봇 올라감
                beltList[0].robot = true;
                robotList.add(new Robot());

                // 내구도 감소
                beltList[0].durability -= 1;

                // 내구도가 0이 된다면.. 0카운트 증가
                if (beltList[0].durability == 0) {
                    beltCount++;
                }
            }
        }
    }

    public static boolean stopCheck() {
        // 내구도가 0인 칸의 갯수가 K개 이상이면 .. 종료
        if (K <= beltCount) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        beltList = new Belt[2 * N];

        st = new StringTokenizer(br.readLine());

        // 내구도가 0인 벨트의 수
        beltCount = 0;

        for (int i = 0; i < 2 * N; i++) {
            beltList[i] = new Belt(Integer.parseInt(st.nextToken()));
            if (beltList[i].durability == 0) {
                beltCount++;
            }
        }

        // 로봇 리스트 생성
        robotList = new ArrayList<>();

        int round = 1;

        while (true) {

            beltMove();

            robotMove();

            loadRobot();

            if (stopCheck()) {
                break;
            } else {
                // 단계 증가
                round++;
            }
        }

        System.out.println(round);

        br.close();
    }
}
