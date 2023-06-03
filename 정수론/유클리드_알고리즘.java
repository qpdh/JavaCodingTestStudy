package 정수론;

public class 유클리드_알고리즘 {
    int gcd(int p, int q) {
        if (p < q) {
            int tmp = q;
            q = p;
            p = tmp;
        }

        if (q == 0) {
            return p;
        }

        return gcd(p - q, q);
    }

    int gcd2(int p, int q) {
        if (q == 0) {
            return p;
        }

        return gcd2(q, p % q);
    }

}
