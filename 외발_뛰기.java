public class 외발_뛰기 {

    static boolean jump(int y, int x) {
        // 기저 사례 - 배열의 범위를 벗어난 경우
        if (y >= n || x >= n)
            return false;

        // 기저 사례 - 마지막 칸에 도착한 경우
        if (y == n - 1 && x == n - 1)
            return true;

        int jumpSize = board[y][x];

        return jump(y + jumpSize, x) || jump(y, x + jumpSize);
    }

    static int n, board[][];
    static int cache[][];

    static int jump2(int y, int x) {
        // 기저 사례 - 배열의 범위를 벗어난 경우
        if (y >= n || x >= n)
            return 0;

        // 기저 사례 - 마지막 칸에 도착한 경우
        if (y == n - 1 && x == n - 1)
            return 1;

        if (cache[y][x] != -1) {
            return cache[y][x];
        }

        int jumpSize = board[y][x];

        cache[y][x] = (jump2(y + jumpSize, x) | jump2(y, x + jumpSize));

        return cache[y][x];
    }

    public static void main(String[] args) {

    }
}
