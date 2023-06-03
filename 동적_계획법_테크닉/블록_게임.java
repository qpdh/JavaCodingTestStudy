package 동적_계획법_테크닉;

import java.util.ArrayList;
import java.util.List;

public class 블록_게임 {

    static char cache[];
    static List<Integer> moves;

    static int cell(int y, int x) {
        return 1 << (y * 5 + x);
    }

    static void precalc() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                List<Integer> cells = new ArrayList<>();
                for (int dy = 0; dy < 2; dy++) {
                    for (int dx = 0; dx < 2; dx++) {
                        cells.add(cell(y + dy, x + dx));
                    }
                }
                int squre = cells.get(0) + cells.get(1) + cells.get(2) + cells.get(3);
                for (int i = 0; i < 4; i++) {
                    moves.add(squre - cells.get(i));
                }
            }
        }

        // 두 칸 짜리 계산
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                moves.add(cell(i, j) + cell(i, j + 1));
                moves.add(cell(j, i) + cell(j + 1, i));
            }
        }
    }

    static char play(int board) {
        if (cache[board] != -1) {
            return cache[board];
        }

        cache[board] = 0;

        for (int i = 0; i < moves.size(); i++) {
            // 이 수를 게임판에 놓을 수 있는가 확인
            if ((moves.get(i) & board) == 0) {
                if (play(board | moves.get(i)) != 0) {
                    cache[board] = 1;
                    break;
                }
            }
        }

        return cache[board];
    }

    public static void main(String[] args) {

    }
}
