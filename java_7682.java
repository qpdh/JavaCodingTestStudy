import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class java_7682 {
    static char board[][];

    static void printBoard() {
        System.out.println("=================");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    static class Game {
        int xCount;
        int oCount;
        int xWinCount;
        int oWinCount;

        @Override
        public String toString() {
            return "Game [xCount=" + xCount + ", oCount=" + oCount + ", xWinCount=" + xWinCount + ", oWinCount="
                    + oWinCount + "]";
        }

        public Game() {
            xCount = 0;
            oCount = 0;
            xWinCount = 0;
            oWinCount = 0;
        }

        // valid X O Count
        // X와 O의 수가 같거나 -> O의 승리
        // X의 수가 O보다 1개 많아야 함 -> X의 승리
        public boolean checkValidCount() {
            if (xCount == oCount || xCount == oCount + 1) {
                return true;
            }
            return false;
        }

        //
        public boolean checkValidWinCount() {
            // X가 이기는 경우
            if (xWinCount > 0) {
                if (oWinCount > 0) {
                    return false;
                }
                if (xCount - oCount == 1) {
                    return true;
                }
            }

            // O가 이기는 경우
            else if (oWinCount > 0) {
                if (xCount - oCount == 0) {
                    return true;
                }
            }

            // 무승부인 경우
            else if (xCount + oCount == 9) {
                return true;
            }

            return false;
        }

        public void increaseWinCount(char stone) {
            if (stone == 'O') {
                oWinCount++;
                return;
            }

            if (stone == 'X') {
                xWinCount++;
                return;
            }

        }
    }

    // 돌의 갯수 검사
    static void countStone(Game game) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    game.xCount++;
                    continue;
                }
                if (board[i][j] == 'O') {
                    game.oCount++;
                }
            }
        }
    }

    // \/ 검사
    static void checkCross(Game game) {
        // 1. \ 검사

        char baseStone = board[0][0];
        if (baseStone != '.') {
            boolean isEqual = true;
            for (int i = 1; i < board.length; i++) {
                if (baseStone != board[i][i]) {
                    isEqual = false;
                    break;
                }
            }
            if (isEqual) {
                game.increaseWinCount(baseStone);
            }
        }

        // 2. / 검사
        baseStone = board[0][board[0].length - 1];

        if (baseStone != '.') {
            boolean isEqual = true;
            for (int i = 1; i < board.length; i++) {
                if (baseStone != board[i][board.length - i - 1]) {
                    isEqual = false;
                    break;
                }
            }
            if (isEqual) {
                game.increaseWinCount(baseStone);
            }
        }
    }

    // | 검사
    static void checkVertical(Game game) {
        for (int i = 0; i < board.length; i++) {
            char baseStone = board[0][i];

            if (baseStone != '.') {

                boolean isEqual = true;
                // 한 줄에 돌이 전부 같은지 확인
                for (int j = 1; j < board.length; j++) {
                    if (baseStone != board[j][i]) {
                        isEqual = false;
                        break;
                    }
                }

                if (isEqual) {
                    game.increaseWinCount(baseStone);
                }
            }
        }
    }

    // ㅡ 검사
    static void checkHorizontal(Game game) {
        for (int i = 0; i < board.length; i++) {
            char baseStone = board[i][0];

            if (baseStone != '.') {
                boolean isEqual = true;
                // 한 줄에 돌이 전부 같은지 확인
                for (int j = 1; j < board[i].length; j++) {
                    if (baseStone != board[i][j]) {
                        isEqual = false;
                        break;
                    }
                }

                if (isEqual) {
                    game.increaseWinCount(baseStone);
                }
            }
        }
    }

    static boolean solution() {
        boolean result = true;

        // 불가능한 경우
        // 1. .의 수 X의 수 O의 수가 맞지 않는 경우
        // X의 수 >= O 의 수 여야 한다.

        // 2. O의 승리와 X의 승리가 동시에 발생하는 경우
        // X의 승리수는 최대 2개까지 올라갈 수 있다
        // X X X
        // X O O
        // X O O
        // 갯수가 X = 5, O = 4 여야 가능하다.

        // | 3개
        // ㅡ 3개
        // / 1개
        // \ 1개

        Game game = new Game();

        // 갯수 세기
        countStone(game);
        // /\ 세기
        checkCross(game);
        // ㅡ 세기
        checkHorizontal(game);
        // | 세기
        checkVertical(game);

        // printBoard();
        // System.out.println(game);
        result = game.checkValidCount() & game.checkValidWinCount();

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 사람이 X
        // 두 번째 사람이 O

        // 3칸을 잇는데성공하면 끝남
        // 게임판이 가득차도 끝남

        while (true) {
            String line = br.readLine();

            // 종료
            if (line.equals("end")) {
                break;
            }

            board = new char[3][3];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = line.charAt(j + (i * 3));
                }
            }

            boolean result = solution();

            if (result == true) {
                System.out.println("valid");
                continue;
            }

            System.out.println("invalid");
        }

        br.close();
    }
}
