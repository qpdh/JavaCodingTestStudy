import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_19238 {
    private final static int INF = 987654321;
    private final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N, M, fuel;
    private static int board[][];
    private static List<Person> people;
    private static Taxi taxi;

    static class Taxi {
        private int y, x;

        Taxi(int y, int x) {
            this.setY(y);
            this.setX(x);
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    static class Person {
        private int y, x, goalY, goalX;
        private boolean done;

        Person(int y, int x, int goalY, int goalX) {
            this.setY(y);
            this.setX(x);
            this.setGoalY(goalY);
            this.setGoalX(goalX);
            this.setDone(false);
        }

        public boolean isDone() {
            return done;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        public int getGoalX() {
            return goalX;
        }

        public void setGoalX(int goalX) {
            this.goalX = goalX;
        }

        public int getGoalY() {
            return goalY;
        }

        public void setGoalY(int goalY) {
            this.goalY = goalY;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    static class Pair {
        private int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

    }

    public static void bfs(int y, int x, int[][] weight) {
        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(y, x));

        int nowWeight = 0;
        while (!queue.isEmpty()) {
            Pair now = queue.poll();

            for (int i = 0; i < dydx.length; i++) {
                int toY = now.getY() + dydx[i][0];
                int toX = now.getX() + dydx[i][1];

                // 배열 범위를 벗어나면 처리 안함
                if (toY < 0 || N <= toY || toX < 0 || N <= toX) {
                    continue;
                }

                // 0이 아니면 방문한 적이 있는것임
                if (weight[toY][toX] != 0) {
                    continue;
                }

                // 1로는 갈 수 없음
                if (board[toY][toX] == 1) {
                    continue;
                }

                weight[toY][toX] = weight[now.getY()][now.getX()] + 1;
                queue.add(new Pair(toY, toX));
            }
        }

    }

    // 현재 위치로부터 거리 계산
    public static void calcWeight(int weight[][]) {
        bfs(taxi.getY(), taxi.getX(), weight);
        weight[taxi.getY()][taxi.getX()] = 0;
    }

    public static boolean calcFuel(Person person, int distance) {
        int[][] weight = new int[N][N];

        // 이동했다 치자
        taxi.setY(person.getY());
        taxi.setX(person.getX());

        // 손님 지점으로부터 목적지 거리 계산
        calcWeight(weight);

        // 손님을 픽업하러 간 뒤 목적지 까지 갈 수 있는가?
        int toGoalDistance = weight[person.getGoalY()][person.getGoalX()];

        // 손님 대려다주기 연료 소모
        fuel = fuel - (toGoalDistance + distance);

        // 중간에 연료가 없는 경우
        if (fuel < 0) {
            return false;
        }

        fuel += toGoalDistance * 2;

        taxi.setY(person.getGoalY());
        taxi.setX(person.getGoalX());

        return true;
    }

    // 가장 거리가 작은 손님 반환
    public static Person selectPerson(int weight[][]) {
        int minWeight = INF;
        int personIndex = -1;

        for (int i = 0; i < people.size(); i++) {
            Person now = people.get(i);
            int nowWeight = weight[now.getY()][now.getX()];

            if (now.isDone()) {
                continue;
            }

            // 더 가까운 손님을 찾았을 경우
            if (nowWeight < minWeight) {
                personIndex = i;
                minWeight = nowWeight;
            }

            // 가까운 손님이 여러명일 경우
            else if (nowWeight == minWeight) {
                Person pre = people.get(personIndex);

                // 1. 행 번호가 작은 승객
                if (now.getY() < pre.getY()) {
                    personIndex = i;
                    minWeight = weight[now.getY()][now.getX()];
                }

                // 행 번호가 같다면
                // 2. 열 번호가 작은 승객
                else if (now.getY() == pre.getY()) {
                    if (now.getX() < pre.getX()) {
                        personIndex = i;
                        minWeight = weight[now.getY()][now.getX()];
                    }
                }
            }
        }

        // 손님이 존재하지 않는다면
        if (personIndex == -1) {
            return null;
        }

        return people.get(personIndex);
    }

    public static int solve() {

        while (true) {
            // 현재 택시 위치에서 가장 가까운 승객 찾기
            int weight[][] = new int[N][N];
            calcWeight(weight);

            // for (int i = 0; i < weight.length; i++) {
            // for (int j = 0; j < weight.length; j++) {
            // System.out.print(weight[i][j] + " ");
            // }
            // System.out.println();
            // }

            for (int i = 0; i < people.size(); i++) {
                int y = people.get(i).getY();
                int x = people.get(i).getX();
                int gY = people.get(i).getGoalY();
                int gX = people.get(i).getGoalX();

                // 현재 택시 위치와 겹치지 않으면서 목적지 까지의 거리가 0이라면 -> 못감
                if (weight[y][x] == 0 && (taxi.getY() != y || taxi.getX() != x)) {
                    return -1;
                }

                // 현재 택시 위치와 겹치지 않으면서 목적지 까지의 거리가 0이라면 -> 못감
                if (weight[gY][gX] == 0 && (taxi.getY() != gY || taxi.getX() != gX)) {
                    return -1;
                }
            }

            // 최적 승객 찾기
            Person selectedPerson = selectPerson(weight);

            // 손님이 존재하지 않는다면
            // 전부 완료 했다면
            if (selectedPerson == null) {
                return fuel;
            }

            // 선택된 손님 연료 계산
            if (!calcFuel(selectedPerson, weight[selectedPerson.getY()][selectedPerson.getX()])) {
                return -1;
            }

            // 승객 완료 처리
            selectedPerson.setDone(true);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행, 열, 초기연료 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        // 벽, 지나갈 수 있는 길 표시
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 택시 데이터 삽입
        st = new StringTokenizer(br.readLine());
        int taxiY = Integer.parseInt(st.nextToken()) - 1;
        int taxiX = Integer.parseInt(st.nextToken()) - 1;
        taxi = new Taxi(taxiY, taxiX);

        // 손님 데이터 삽입
        people = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int goalY = Integer.parseInt(st.nextToken()) - 1;
            int goalX = Integer.parseInt(st.nextToken()) - 1;
            people.add(new Person(y, x, goalY, goalX));
        }

        int result = solve();

        bw.write(result + "");

        br.close();
        bw.close();
    }
}
