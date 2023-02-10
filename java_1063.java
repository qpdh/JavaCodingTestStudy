import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class java_1063 {

    final static Map<String, Pair> moveMap = Map.of(
            "L", new Pair(0, -1),
            "R", new Pair(0, 1),
            "B", new Pair(1, 0),
            "T", new Pair(-1, 0),
            "LT", new Pair(-1, -1),
            "RT", new Pair(-1, 1),
            "RB", new Pair(1, 1),
            "LB", new Pair(1, -1));

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return (char) (x + 'A') + "" + (8 - y);
        }

        @Override
        public boolean equals(Object obj) {
            Pair pairObj = (Pair) obj;
            // TODO Auto-generated method stub
            if (pairObj.y == this.y && pairObj.x == this.x) {
                return true;
            }
            return false;
        }

        public Pair sum(Pair pair) {
            return new Pair(this.y + pair.y, this.x + pair.x);
        }

    }

    static class Item {
        Pair yx;

        public Item(String xy) {

            int y = 8 - (xy.charAt(1) - '0');
            int x = xy.charAt(0) - 'A';

            this.yx = new Pair(y, x);
        }

        @Override
        public String toString() {
            return this.yx.toString();
        }

    }

    static boolean isOutOfRange(Pair yx) {
        int y = yx.y;
        int x = yx.x;

        if (y < 0 || 8 <= y) {
            return true;
        }
        if (x < 0 || 8 <= x) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 이동 시키기
        // 이동할 수 있는지 확인하기
        // 돌을 미는지 확인하기
        StringTokenizer st = new StringTokenizer(br.readLine());

        Item king = new Item(st.nextToken());
        Item stone = new Item(st.nextToken());

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            Pair to = moveMap.get(br.readLine());

            Pair kingMove = king.yx.sum(to);

            // 배열 밖으로 나가는지 체크 8*8
            // 킹이 배열 밖으로 나가지 않는다면...
            if (!isOutOfRange(kingMove)) {

                // 킹이 이동하는 위치에 돌이 있는지 확인
                if (kingMove.equals(stone.yx)) {
                    // 돌이 배열 밖으로 나가는지 체크
                    Pair stoneMove = stone.yx.sum(to);
                    // 아니라면 이동시키기
                    if (!isOutOfRange(stoneMove)) {
                        stone.yx = stoneMove;
                        king.yx = kingMove;
                    }
                }
                // 킹만 이동하는 경우
                else {
                    king.yx = kingMove;
                }
            }
        }

        System.out.println(king);
        System.out.println(stone);

    }
}
