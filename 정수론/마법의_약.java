package 정수론;

import java.util.ArrayList;
import java.util.List;

public class 마법의_약 {

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static int[] solve(List<Integer> recipe, List<Integer> put) {
        int n = recipe.size();

        // 모든 recipe의 최대 공약수 구하기
        // 모든 레시피의 갯수가 b로 나누어 떨어지기 위해
        int b = recipe.get(0);
        for (int i = 1; i < n; i++) {
            b = gcd(b, recipe.get(i));
        }

        // 적어도 b/b 1개는 만들어야 한다.
        int a = b;

        // (레시피에 들어가는 재료의 수 / 넣은 재료의 수) 의 소수부분 올림 값이 가장 큰 것 찾기
        // -> 그 갯수만큼 만들면 된다.
        for (int i = 0; i < n; i++) {
            a = Math.max(a, (int) Math.ceil((double) put.get(i) * b / recipe.get(i)));
        }

        // a/b배 분량을 만들면 된다.
        int result[] = new int[n];

        // 넣어야 하는 갯수 == 레시피에 들어가야하는 재료 수 * 만들어야 하는 갯수(a/b) - 이미 넣은 재료 수
        for (int i = 0; i < n; i++) {
            result[i] = recipe.get(i) * (a / b) - put.get(i);
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
