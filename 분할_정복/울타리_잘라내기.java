package 분할_정복;

public class 울타리_잘라내기 {
    // 무식하게 풀기
    // (end-start+1)*min([end,start])
    static int bruteForce(int height[]) {
        int result = 0;

        int N = height.length;

        for (int left = 0; left < N; left++) {
            int minHeight = height[left];
            for (int right = left; right < N; right++) {
                minHeight = Math.min(minHeight, height[right]);
                result = Math.max(result, (right - left + 1) * minHeight);
            }
        }

        return result;
    }

    // 분할 정복을 이용하여 풀기
    static int heights[];

    static int solve(int left, int right) {
        // 판자가 1개 밖에 없는 경우
        // 너비 1, 높이 height[left] -> 넓이 height[left] 반환
        if (left == right) {
            return heights[left];
        }

        // [left, mid], [mid+1, right] 두 구간으로 나누어 계산한다
        int mid = (left + right) / 2;

        // 왼쪽 판자에만 있는 경우, 오른쪽 판자에만 있는 경우 계산하기
        int result = Math.max(solve(left, mid), solve(mid + 1, right));

        // 걸쳐있는 경우 계산하기
        int low = mid;
        int high = mid + 1;
        int height = Math.min(heights[low], heights[high]);

        result = Math.max(result, 2 * height);

        // 너비가 right-left+1 가 될 때 까지 반복한다.
        while (left < low || high < right) {
            // 높이가 더 높은 쪽으로 확장한다.
            if (high < right && (low == left || heights[low - 1] < heights[high + 1])) {
                high++;
                height = Math.min(height, heights[high]);
            } else {
                low--;
                height = Math.min(height, heights[low]);
            }

            // 확장한 후 사각형의 넓이
            result = Math.max(result, (high - low + 1) * height);
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
