import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Fish {
    int size;
    int y, x;
    int distance;

    public Fish(int y, int x, int size) {
        this.y = y;
        this.x = x;
        this.size = size;
        distance = 0;
    }
}

class Shark extends Fish {
    int hungry;
    int sharkSize;

    public Shark(int y, int x, int size) {
        super(y, x, 0);
        sharkSize = 2;
        this.hungry = 0;
    }

    public void eatFish(Fish fish) {
        hungry += 1;
        // 같은 수의 물고기를 먹으면 성장!
        if (hungry == sharkSize) {
            hungry = 0;
            sharkSize += 1;
            // System.out.println("성장!");
        }

        y = fish.y;
        x = fish.x;

        // 먹었으니까 빈칸 만들기
        fish.size = 0;
    }
}

public class java_16236 {
    static Fish board[][];
    static Shark shark;
    final static int dydx[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    // y, x 위치에서 4방향으로 이동했을 때
    // 물고기가 있는 지 확인

    static ArrayList<Fish> findFish;

    public static void insertCanEatFish() {
        int N = board.length;
        Queue<Fish> fishQueue = new LinkedList<>();
        fishQueue.add(shark);

        boolean isChecked[][] = new boolean[N][N];

        isChecked[shark.y][shark.x] = true;
        //System.out.println(shark.y + ",,," + shark.x);

        // 거리 0으로 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j].distance = 0;
            }
        }

        Fish f;

        while (!fishQueue.isEmpty()) {

            f = fishQueue.poll();
            //System.out.println("현재 위치 : "+f.y+" "+f.x);

            // 4방향 넣기
            // 한 칸 씩 이동
            for (int i = 0; i < dydx.length; i++) {
                int toY = f.y + dydx[i][0];
                int toX = f.x + dydx[i][1];

                // 배열 범위 안에 들어온다면?
                if (toY < 0 || toY >= board.length) {
                    continue;
                }
                if (toX < 0 || toX >= board.length) {
                    continue;
                }

                // 이미 방문한 곳이라면?
                if (isChecked[toY][toX])
                    continue;

                isChecked[toY][toX] = true;

                // 현재 위치가 지나갈 수 없는 곳이라면?
                if (board[toY][toX].size > shark.sharkSize) {
                    continue;
                }

                //System.out.println(toY + " " + toX + "방문");

                // 0보다 커야하고, 상어보다 작다면?
                // 먹을 수 있는 물고기라면?
                if (board[toY][toX].size > 0 && board[toY][toX].size < shark.sharkSize) {
                    board[toY][toX].distance = f.distance + 1;
                    findFish.add(board[toY][toX]);
                    // System.out.println(toY + "," + toX + " 거리 : " + board[toY][toX].distance + "
                    // 물고기 삽입");
                } else { 
                    // 거리는 한 칸 증가
                    //System.out.println(toY + " " + toX + "삽입");
                    // Fish inputFish = board[toY][toX];
                    // inputFish.distance = f.distance + 1;
                    board[toY][toX].distance = f.distance + 1;
                    // 이동 경로 삽입
                    fishQueue.add(board[toY][toX]);
                }
            }
        }
    }

    // y,x로부터 물고기 까지 최단거리 구하기
    // 큰 물고기는 지나칠 수 없음
    // static int cache[][];

    // public static int fromSharkToFish(Fish fish, int y, int x) {
    // int length = 987654321;

    // if (y < 0 || y >= board.length) {
    // return length;
    // }

    // if (x < 0 || x >= board.length) {
    // return length;
    // }

    // if (cache[y][x] != -1)
    // return cache[y][x];

    // // 큰 물고기를 넘어갈 수 없음
    // if (board[y][x].size > shark.sharkSize) {
    // return length;
    // }

    // // 도착했다면,
    // if (fish.y == y && fish.x == x) {
    // return 0;
    // }

    // cache[y][x] = length;

    // // 도착하지 않았다면
    // for (int i = 0; i < dydx.length; i++) {
    // cache[y][x] = Math.min(cache[y][x], fromSharkToFish(fish, y + dydx[i][0], x +
    // dydx[i][1]) + 1);
    // }

    // // 빙글빙글 도는 경우 어떻게 처리???
    // // System.out.println("캐시값 : "+cache[y][x]);
    // return cache[y][x];
    // }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        board = new Fish[N][N];

        // 데이터 삽입
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int size = Integer.parseInt(st.nextToken());
                // 상어인 경우
                switch (size) {
                    // 아무것도 없는 경우
                    case 0:
                        board[i][j] = new Fish(i, j, 0);
                        break;

                    // 상어의 경우
                    case 9:
                        board[i][j] = new Fish(i, j, 0);
                        shark = new Shark(i,j,0);
                        break;

                    // 물고기의 경우
                    default:
                        board[i][j] = new Fish(i, j, size);
                        break;
                }
            }
        }

        // 몇 초 걸리는가?
        int second = 0;

        // 먹을 수 있는 물고기가 없으면 종료
        while (true) {
            //bw.write("먹기 전 시간 : " + second + "\n");
            findFish = new ArrayList<>();

            // 먹을 수 있는 물고기 찾기
            insertCanEatFish();

            // 먹을 수 있는 물고기가 없다면 종료
            if (findFish.isEmpty()) {
                break;
            }

            // for (Fish f : findFish) {
            //     System.out.println("(" + f.y + ", " + f.x + ")" + " distance : " + f.distance);
            // }

            Collections.sort(findFish, new Comparator<Fish>() {

                @Override
                public int compare(Fish o1, Fish o2) {
                    // 거리가 같으면 y값이 가장 작은 것
                    if (o1.distance == o2.distance) {
                        // y값이 같으면 x가 가장 작은 것
                        if (o1.y == o2.y) {
                            return o1.x - o2.x;
                        }
                        return o1.y - o2.y;
                    }
                    return o1.distance - o2.distance;
                }
            });

            // for (Fish f : findFish) {
            //     System.out.println(f.y + ", " + f.x + "");
            // }

            // 시간 더하기
            //bw.write(findFish.get(0).distance + "인 물고기 먹기\n");
            second += findFish.get(0).distance;

            // 물고기 먹기
            shark.eatFish(findFish.get(0));

            // System.out.println("===========");
            // for (Fish[] f : board) {
            //     for (Fish f2 : f) {
            //         System.out.print(f2.size + " ");
            //     }
            //     System.out.println();
            // }

        }

        bw.write(second + "\n");
        br.close();
        bw.close();
    }
}

// // 거리가 1일 때
// // 먹을 수 있는 물고기 판단
// for (int i = 0; i < dxdy.length; i++) {
// // 보드에 있는 사이즈가 상어보다 작아야하고,
// if (board[shark.y + dxdy[i][0]][shark.x + dxdy[i][1]].size < shark.size) {
// // 보드에 있는 사이즈가 0보다 커야 함
// if (board[shark.y + dxdy[i][0]][shark.x + dxdy[i][1]].size > 0) {
// canEatFish.add((Fish) board[shark.y + dxdy[i][0]][shark.x + dxdy[i][1]]);
// }
// }
// }

// // 거리가 2이상일 때
// // dxdy 절댓값 2 모든 경우의 수
// int dy = 2;
// int dx = 0;

// // -2 0, -1 1, 0 2
// // 2 0, 1 -1, 0 -2
// // 1 1, -1 -1

// // 거리 탐색?
// // 1일때
// // 2일때
// // 3일때
// // ...반복문 처리
// // 재귀보다 반복문이 효율적.

// // 거리가 1일때 넣기
// // 없다면 거리가 2일때 넣기
// // ... N일 때
// // 그래도 없다면 종료

// // 전부 다 돌기?
// // 400 연산
// // 별로 안큼
// // -> 전부 다 돌기