package 코딩인터뷰;

public class 숫자뒤집기 {
    static int reverseNumber(int number) {
        boolean isNegative = false;
        if (number < 0) {
            isNegative = true;
            number *= -1;
        }

        StringBuffer stringNumber = new StringBuffer(Integer.toString(number));

        stringNumber.reverse();

        int result = Integer.parseInt(stringNumber.toString());
        if (isNegative) {
            result *= -1;
        }

        return result;

    }

    public static void main(String[] args) {
        System.out.println(reverseNumber(-12345600));
    }
}
