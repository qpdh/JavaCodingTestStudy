package 정수론;

import java.util.ArrayList;
import java.util.List;

public class 소인수_분해 {

    // 주어진 정수 n을 소인수분해하는 알고리즘
    List<Integer> factorSimple(int n) {
        List<Integer> result = new ArrayList<>();

        int lastNumber = (int) Math.sqrt(n);
        for (int div = 2; div <= lastNumber; div++) {
            while (n % div == 0) {
                n /= div;
                result.add(div);
            }
        }

        if (n > 1) {
            result.add(n);
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
