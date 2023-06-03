public class test {

    final static String test = "test";

    // + 연산자를 이용한 문자열 합치기
    public static void plusOperation(int loop) {
        String result = "";

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < loop; i++) {
            result += test;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("plusOperation::걸린 시간" + (endTime - startTime));
    }

    // concat 메소드를 이용한 문자열 합치기
    public static void concatMethod(int loop) {
        String result = "";

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < loop; i++) {
            result.concat(test);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("concatMethod::걸린 시간" + (endTime - startTime));
    }

    // format 메소드를 이용한 문자열 합치기
    public static void formatMethod(int loop) {
        String result = "";

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < loop; i++) {
            result = String.format("%s%s", result, test);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("formatMethod::걸린 시간" + (endTime - startTime));
    }

    // append 메소드를 이용한 문자열 합치기
    public static void appendMethod(int loop) {
        StringBuffer result = new StringBuffer();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < loop; i++) {
            result.append(test);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("appendMethod::걸린 시간" + (endTime - startTime));
    }

    public static void main(String[] args) {
        String a = "     abcddssd    ";
        System.out.println(a);
        System.out.println(a.trim());
        System.out.println(a);
    }
}
