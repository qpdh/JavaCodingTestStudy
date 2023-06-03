package SKPlanet;

import java.util.*;

class Solution {
    final static int dydx[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static class Pos {
        int y, x;

        public Pos() {
        }

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    // (y,x)에서 dydx[delta] 방향으로 갈 때 가는 경로
    public static List<Pos> move(int y, int x, int delta, int[][] board) {
        List<Pos> moveList = new ArrayList<>();

        // 현재 위치가 꽃이라면 바로 반환
        if (board[y][x] == 3) {
            return moveList;
        }

        // 현재 위치 포함해서 이동하자
        moveList.add(new Pos(y, x));
        int toY = y;
        int toX = x;

        while (true) {
            toY += dydx[delta][0];
            toX += dydx[delta][1];
            try {
                // 더 가면 길이 아닌 경우 -> 종료
                if (board[toY][toX] != 1) {
                    break;
                }

                moveList.add(new Pos(toY, toX));
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }

        return moveList;
    }

    public static int checkAble(int y, int x, int roadCount, int[][] board) {
        // 이동할 수 없다면, 남은 길의 갯수가 0인지 체크 -> 판별
        if (roadCount == 0) {
            return 1;
        }

        // 심을 수 있는지 체크
        for (int i = 0; i < dydx.length; i++) {
            // dydx[i] 방향으로 가기
            // (y,x)에서 i 방향으로 가는 경로 리스트 반환 move
            List<Pos> moveList = move(y, x, i, board);

            // 더이상 갈 수 없는 경우
            if (moveList.size() == 0) {
                continue;
            }

            Pos lastPos = new Pos();

            lastPos = moveList.get(moveList.size() - 1);
            if (moveList.size() != 1) {
                moveList.remove(moveList.size() - 1);
            }

            for (Pos moveToken : moveList) {
                board[moveToken.y][moveToken.x] = 3;
            }

            // 더 이동할 수 있다면 이동하기
            if (checkAble(lastPos.y, lastPos.x, roadCount - moveList.size(), board) == 1) {
                return 1;
            }

            // 되돌리기
            for (Pos moveToken : moveList) {
                board[moveToken.y][moveToken.x] = 1;
            }

        }

        return 0;
    }

    // board가 주어졌을 때 모든 공간에 꽃을 둘 수 있는가?
    public static int gameStart(String[] stringBoard) {
        // 캐릭터 위치 찾기
        Pos character = new Pos();
        // 길의 갯수 계산하기
        // 캐릭터 위치 1추가한 상태로
        int roadCount = 1;

        int board[][] = new int[stringBoard.length][stringBoard[0].length()];

        for (int i = 0; i < stringBoard.length; i++) {
            for (int j = 0; j < stringBoard[0].length(); j++) {
                board[i][j] = stringBoard[i].charAt(j) - '0';

                if (board[i][j] == 1) {
                    roadCount++;
                    continue;
                }

                if (board[i][j] == 2) {
                    board[i][j] = 1;
                    character = new Pos(i, j);
                }
            }
        }

        int answer = checkAble(character.y, character.x, roadCount, board);

        System.out.println(answer);

        return answer;
    }

    public static int[] solution(String[][] boards) {
        int[] answer = new int[boards.length];

        for (int i = 0; i < boards.length; i++) {
            int result = gameStart(boards[i]);
            answer[i] = result;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] boards = { { "00011", "01111", "21001", "11001", "01111" }, { "1111", "1121", "1001", "1111" },
                { "0000", "0000", "0000", "0002" },
                { "0000", "0100", "0000", "0002" },
                { "0000", "0010", "0121", "0010" }
        };

        solution(boards);
    }

}
