import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class java_2580 {

    final static Set<Integer> numberSet = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    static int board[][];

    static List<Pair> zeroList;

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Pair [y=" + y + ", x=" + x + "]";
        }

    }

    // [y,x] 가 주어질 때 3*3의 좌측 상단 좌표를 반환한다.
    static Pair findBase(int y, int x) {
        int resultY = y / 3 * 3;
        int resultX = x / 3 * 3;

        return new Pair(resultY, resultX);
    }

    // 가로, 세로, 3*3에서 될 수 있는 숫자 경우의 수를 반환한다.
    static Set<Integer> findNumber(int y, int x) {
        Set<Integer> result = new HashSet<>(numberSet);

        // ㅡ 숫자 체크
        Set<Integer> tmpSet = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board[y][i] == 0) {
                continue;
            }
            tmpSet.add(board[y][i]);
        }
        result.removeAll(tmpSet);

        // | 숫자 체크
        tmpSet = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board[i][x] == 0) {
                continue;
            }
            tmpSet.add(board[i][x]);
        }
        result.removeAll(tmpSet);

        Pair basePair = findBase(y, x);
        for (int i = basePair.y; i < basePair.y + 3; i++) {
            for (int j = basePair.x; j < basePair.x + 3; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                tmpSet.add(board[i][j]);
            }
        }
        result.removeAll(tmpSet);

        return result;
    }

    static boolean sudoku(int index) {
        if (zeroList.size() == index) {
            // 스도쿠가 맞는지 체크
            return true;
        }

        Pair here = zeroList.get(index);

        Set<Integer> set = findNumber(here.y, here.x);
        if (set.size() == 0) {
            return false;
        }

        for (int number : set) {
            board[here.y][here.x] = number;
            if (sudoku(index + 1)) {
                return true;
            }
            board[here.y][here.x] = 0;
        }

        return false;
    }

    static void solution() {
        // 0위치에서 될 수 있는 경우의 수가 작은 것 부터 처리하기
        // 각 위치에서 될 수 있는 숫자 찾아내기

        List<Pair> removeList = new ArrayList<>();
        for (Pair pair : zeroList) {
            Set<Integer> set = findNumber(pair.y, pair.x);
            // 무조건 삽입하자
            if (set.size() == 1) {
                removeList.add(pair);
                board[pair.y][pair.x] = set.iterator().next();
            }
        }

        // 숫자 채운 것 제거
        zeroList.removeAll(removeList);

        sudoku(0);
    }

    // 스도쿠 판 출력
    static void printBoard() {
        // System.out.println("=================");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];
        zeroList = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 0) {
                    zeroList.add(new Pair(i, j));
                }
            }
        }

        solution();

        printBoard();

        br.close();
    }
}
