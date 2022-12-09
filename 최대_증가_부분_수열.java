public class 최대_증가_부분_수열 {

    static int n;

    static int cache[], S[];

    static int lis(int start) {
        if (cache[start] != -1) {
            return cache[start];
        }

        // 자기 자신
        cache[start] = 1;

        for (int next = start + 1; next < n; next++) {
            // 증가한다면
            if (S[start] < S[next]) {
                cache[start] = Math.max(cache[start], lis(next) + 1);
            }
        }

        return cache[start];
    }

    static int dp() {
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, lis(i));
        }

        return maxLen;
    }

    public static void main(String[] args) {

    }
}
