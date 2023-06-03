package 정수론;

public class 소수_판별 {

    // 자연수 n이 소수인지 판별한다.
    static boolean isPrime(int n) {
        // 1과 2는 예외처리
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }

        // 2를 제외한 짝수는 소수가 아니다.
        if (n % 2 == 0) {
            return false;
        }

        // 3 이상의 모든 홀수로 나눠보기
        int lastNumber = (int) Math.sqrt(n);

        // 짝수는 이미 처리되므로 2씩 증가시킨다.
        for (int div = 3; div <= lastNumber; div += 2) {
            // 합성수의 경우
            if (n % div == 0) {
                return false;
            }
        }

        // 소수의 경우
        return true;
    }

    public static void main(String[] args) {

    }
}
