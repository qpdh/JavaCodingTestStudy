import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 버킷_브리지 {
    final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static char board[][];
    static int isVisited[][];

    static int dfs(int y, int x, int length) {
        int result = Integer.MAX_VALUE;
        if (board[y][x] == 'B') {
            return length;
        }

        for (int i = 0; i < dydx.length; i++) {
            try {
                int toY = y + dydx[i][0];
                int toX = x + dydx[i][1];
                if (length + 1 < isVisited[toY][toX]) {
                    if (board[toY][toX] != 'R') {
                        isVisited[toY][toX] = length + 1;
                        result = Math.min(result, dfs(toY, toX, length + 1));
                    }

                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }

        return result;
    }

    static int dfsAll() {

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < lakeList.size(); i++) {
            isVisited = new int[10][10];
            for (int j = 0; j < 10; j++) {
                Arrays.fill(isVisited[j], Integer.MAX_VALUE);
            }

            Pair here = lakeList.get(i);
            isVisited[here.y][here.x] = 0;

            int tmp = dfs(here.y, here.x, 0);
            result = Math.min(result, tmp);
        }

        return result;
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static List<Pair> lakeList;
    static Pair fire;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        lakeList = new ArrayList<>();
        board = new char[10][10];

        for (int i = 0; i < 10; i++) {
            String tmp = scanner.nextLine();
            for (int j = 0; j < 10; j++) {
                board[i][j] = tmp.charAt(j);
                if (tmp.charAt(j) == 'L') {
                    lakeList.add(new Pair(i, j));
                }
                if (tmp.charAt(j) == 'B') {
                    fire = new Pair(i, j);
                }
            }
        }

        System.out.println(dfsAll() - 1);

        scanner.close();
    }
}
