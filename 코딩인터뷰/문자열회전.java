package 코딩인터뷰;

public class 문자열회전 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("abcd");
        System.out.println(sb.toString());
        sb.reverse();
        System.out.println(sb.toString());
    }
}
