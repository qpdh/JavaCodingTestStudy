import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
5 5
1 4
2 4
2 5
3 5
4 5
*/

public class java_6118 {
    final static int INF = 987654321;
    static int cache[];
    static Barn barnList[];
    static boolean isVisited[];

    /*
    // 0번노드에서 다른 노드로 가는 최소 거리 반환
    // -> 0번노드로 가는 최소값 반환
    // public static int bfs(int n) {
    // System.out.println("현재 노드" + (n + 1));

    // // 0번 노드 도착 시 종료
    // if (n == 0) {
    // return 0;
    // }

    // if (barnList[n].adjList.contains(0)) {
    // cache[n] = 1;
    // System.out.println(n + "종료");
    // return cache[n];
    // }

    // // 이미 값이 존재한다면...
    // if (cache[n] != -1) {
    // // System.out.println("기존 경로 있음 "+barnList[n].distance);
    // return cache[n];
    // }

    // int ret = INF;

    // for (int i = 0; i < barnList.length; i++) {
    // // 제자리 걸음 제외
    // if (i != n) {
    // // 길이 이어져 있어야 함
    // if (barnList[n].adjList.contains(i)) {
    // if (isVisited[i] == false) {
    // isVisited[i] = true;
    // // System.out.println("경우의 수 : "+i);
    // ret = Math.min(ret, 1 + bfs(i));
    // isVisited[i] = false;
    // }
    // }
    // }
    // }

    // System.out.println("======" + (n + 1) + "종료=====");
    // cache[n] = ret;
    // return cache[n];
    // }
    */

    public static int bfs2() {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        barnList[0].distance = 0;
        isVisited[0]  =true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < barnList[now].adjList.size(); i++) {
                if (isVisited[barnList[now].adjList.get(i)] == false) {
                    isVisited[barnList[now].adjList.get(i)] = true;
                    queue.add(barnList[now].adjList.get(i));
                    barnList[barnList[now].adjList.get(i)].distance = barnList[now].distance + 1;
                }
            }
        }

        return 0;
    }

    // 번호, 거리, 다른 헛간으로 가는 길을 가지는 클래스
    static class Barn {
        int number;
        int distance;
        ArrayList<Integer> adjList;

        public Barn(int number) {
            this.number = number;
            this.distance = 0;
            this.adjList = new ArrayList<>();
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ///////// 데이터 삽입 ////////////
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        barnList = new Barn[N];

        for (int i = 0; i < N; i++) {
            barnList[i] = new Barn(i + 1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            barnList[A - 1].adjList.add(B - 1);
            barnList[B - 1].adjList.add(A - 1);
        }
        ///////// 데이터 종료 ////////////


        isVisited = new boolean[N];
        bfs2();

        //////// 배열 정렬 /////////
        // 1. 거리 내림차순
        // 2. 헛간 번호 오름차순
        Arrays.sort(barnList, new Comparator<Barn>() {

            @Override
            public int compare(Barn o1, Barn o2) {
                if (o1.distance == o2.distance) {
                    return o1.number - o2.number;
                }
                return o2.distance - o1.distance;
            }

        });

        //////// 같은 거리의 헛간 갯수 확인 /////////
        int maxBarnNumber = 1;

        for (int i = 1; i < N; i++) {
            if (barnList[0].distance != barnList[i].distance) {
                break;
            }
            ++maxBarnNumber;
        }

        //////// 결과값 출력 ///////////
        bw.write(barnList[0].number + " " + barnList[0].distance + " " + maxBarnNumber + "\n");

        br.close();
        bw.close();
    }
}
