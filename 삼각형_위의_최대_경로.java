public class 삼각형_위의_최대_경로 {
    static int n, triangle[][];
    static int cache[][][];
    static int cache2[][];

    static int path1(int y, int x, int sum) {
        // 맨 아래줄 까지 도달했을 경우
        if (y == n - 1) {
            return sum + triangle[y][x];
        }

        // 메모이제이션
        if (cache[y][x][sum] != -1) {
            return cache[y][x][sum];
        }

        // 현재 값 저장
        sum += triangle[y][x];

        // 아래로 가거나, 오른쪽으로 가거나
        cache[y][x][sum] = Math.max(path1(y + 1, x, sum), path1(y, x + 1, sum));

        return cache[y][x][sum];
    }

    static int path2(int y, int x) {
        // 맨 아래줄 까지 도달했을 경우
        if (y == n - 1) {
            return triangle[y][x];
        }

        // 메모이제이션
        if (cache2[y][x] != -1) {
            return cache2[y][x];
        }

        // 아래로 가거나, 오른쪽으로 가거나
        cache2[y][x] = Math.max(path2(y + 1, x), path2(y, x + 1)) + triangle[y][x];

        return cache2[y][x];
    }

    public static void main(String[] args) {

    }
}
