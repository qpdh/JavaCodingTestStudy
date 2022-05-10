import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class java_2251 {
    static int A, B, C;

    static class Pair {
        int z, y, x;

        public Pair(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }

    }

    public static boolean isOutOfRange(int z, int y, int x) {
        if (z < 0 || A <= z) {
            return true;
        }
        if (y < 0 || B <= y) {
            return true;
        }
        if (x < 0 || C <= x) {
            return true;
        }

        return false;
    }

    public static TreeSet<Integer> solve() {
        TreeSet<Integer> hashSet = new TreeSet<>();

        // C 물통을 -> A 물통으로 전부 주기, B 물통으로 전부 주기
        // B 물통을 -> A 물통으로 전부 주기, C 물통으로 전부 주기
        // A 물통을 -> B 물통으로 전부 주기, C 물통으로 전부 주기

        // 종료는 언제 판단하는가?

        // A가 전부 비었을 때 이므로...
        // C -> A

        // A <= B 인 경우

        // A > B 인 경우

        boolean isVisited[][][] = new boolean[A + 1][B + 1][C + 1];

        isVisited[0][0][C] = true;

        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(0, 0, C));

        while (!queue.isEmpty()) {
            Pair now = queue.poll();

            if (now.z == 0) {
                hashSet.add(now.x);
            }

            /////////////////////
            // A가 옮기는 경우 //
            /////////////////////
            if (now.z > 0) {
                // B 로 옮기는 경우

                // B-1. A가 비워져 버리는 경우
                if (now.z <= B - now.y) {
                    if (!isVisited[0][now.y + now.z][now.x]) {
                        isVisited[0][now.y + now.z][now.x] = true;
                        queue.add(new Pair(0, now.y + now.z, now.x));
                    }
                }
                // B-2. B가 다 넣기전에 꽉 차버리는 경우
                else if (now.z > B - now.y) {
                    if (!isVisited[now.z - B + now.y][B][now.x]) {
                        isVisited[now.z - B + now.y][B][now.x] = true;
                        queue.add(new Pair(now.z - B + now.y, B, now.x));
                    }
                }

                // C 로 옮기는 경우
                // C-1. A가 비워져 버리는 경우
                if (now.z <= C - now.x) {
                    if (!isVisited[0][now.y][now.z + now.x]) {
                        isVisited[0][now.y][now.z + now.x] = true;
                        queue.add(new Pair(0, now.y, now.z + now.x));
                    }
                }
                // C-2. B를 다 넣기전에 C가 꽉 차버리는 경우
                else if (now.z > C - now.x) {
                    if (!isVisited[now.z - C + now.x][now.y][C]) {
                        isVisited[now.z - C + now.x][now.y][C] = true;
                        queue.add(new Pair(now.z - C + now.x, now.y, C));
                    }
                }
            }

            /////////////////////
            // B가 옮기는 경우 //
            /////////////////////
            if (now.y > 0) {
                // A 로 옮기는 경우

                // A-1. B가 비워져 버리는 경우
                if (now.y <= A - now.z) {
                    if (!isVisited[now.z + now.y][0][now.x]) {
                        isVisited[now.z + now.y][0][now.x] = true;
                        queue.add(new Pair(now.z + now.y, 0, now.x));
                    }
                }
                // A-2. B를 다 넣기전에 A가 꽉 차버리는 경우
                else if (now.y > A - now.z) {
                    if (!isVisited[A][now.y - A + now.z][now.x]) {
                        isVisited[A][now.y - A + now.z][now.x] = true;
                        queue.add(new Pair(A, now.y - A + now.z, now.x));
                    }
                }

                // C 로 옮기는 경우
                // C-1. B가 비워져 버리는 경우
                if (now.y <= C - now.x) {
                    if (!isVisited[now.z][0][now.x + now.y]) {
                        isVisited[now.z][0][now.x + now.y] = true;
                        queue.add(new Pair(now.z, 0, now.x + now.y));
                    }
                }
                // C-2. B가 다 넣기전에 C가 꽉 차버리는 경우
                else if (now.y > C - now.x) {
                    if (!isVisited[now.z][now.y - C + now.x][C]) {
                        isVisited[now.z][now.y - C + now.x][C] = true;
                        queue.add(new Pair(now.z, now.y - C + now.x, C));
                    }
                }
            }

            /////////////////////
            // C가 옮기는 경우 //
            /////////////////////
            if (now.x > 0) {
                // A 로 옮기는 경우

                // A-1. C가 비워져 버리는 경우
                if (now.x <= A - now.z) {
                    if (!isVisited[now.z + now.x][now.y][0]) {
                        isVisited[now.z + now.x][now.y][0] = true;
                        queue.add(new Pair(now.z + now.x, now.y, 0));
                    }
                }
                // A-2. C가 다 넣기전에 A가 꽉 차버리는 경우
                else if (now.x > A - now.z) {
                    if (!isVisited[A][now.y][now.x - A + now.z]) {
                        isVisited[A][now.y][now.x - A + now.z] = true;
                        queue.add(new Pair(A, now.y, now.x - A + now.z));
                    }
                }

                // B 로 옮기는 경우
                // B-1. C가 비워져 버리는 경우
                if (now.x <= B - now.y) {
                    if (!isVisited[now.z][now.x + now.y][0]) {
                        isVisited[now.z][now.x + now.y][0] = true;
                        queue.add(new Pair(now.z, now.x + now.y, 0));
                    }
                }
                // B-2. C가 다 넣기전에 B가 꽉 차버리는 경우
                else if (now.x > B - now.y) {
                    if (!isVisited[now.z][B][now.x - B + now.y]) {
                        isVisited[now.z][B][now.x - B + now.y] = true;
                        queue.add(new Pair(now.z, B, now.x - B + now.y));
                    }
                }
            }
        }

        return hashSet;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        TreeSet<Integer> resultList = solve();

        Iterator<Integer> iter = resultList.iterator();

        while (iter.hasNext()) {
            int res = iter.next();
            bw.write(res + " ");
        }
        bw.write("\n");

        br.close();
        bw.close();
    }
}
