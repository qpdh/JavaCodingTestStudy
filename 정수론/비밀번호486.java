package 정수론;

public class 비밀번호486 {
    final static int TM = 100_000_000;

    static int factors[];

    static void getFactorsBrute() {
        for (int div = 1; div <= TM; div++) {
            for (int multiple = div; multiple <= TM; multiple += div) {
                factors[multiple] += 1;
            }
        }
    }

    public static void main(String[] args) {

    }
}
