package 동적_계획법_테크닉;

// 반복적 동적 계획법
public class 회전초밥 {
    static int cache[];
    static int n, m;
    static int price[];
    static int pref[];

    static int sushi(int budget) {
        if (cache[budget] != -1) {
            return cache[budget];
        }

        cache[budget] = 0;
        for (int dish = 0; dish < n; dish++) {
            if (budget < price[dish]) {
                continue;
            }
            cache[budget] = Math.max(cache[budget], sushi(budget - price[dish]) + pref[dish]);
        }

        return cache[budget];
    }

    static int sushi2() {
        int result = 0;

        for (int budget = 1; budget <= m; budget++) {
            cache[budget] = 0;
            for (int dish = 0; dish < n; dish++) {
                if (budget >= price[dish]) {
                    cache[budget] = Math.max(cache[budget], cache[budget - price[dish]] + pref[dish]);
                }
            }
            result = Math.max(result, cache[budget]);
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
