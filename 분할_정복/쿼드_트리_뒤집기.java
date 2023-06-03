package 분할_정복;

public class 쿼드_트리_뒤집기 {
    static String quadTree;
    static int index;

    static String reverse() {
        char head = quadTree.charAt(index);

        index++;
        // 압축된 상태라면 -> 바로 반환
        if (head == 'w' || head == 'b') {
            return Character.toString(head);
        }

        // 뒤집기
        String upperLeft = reverse();
        String upperRight = reverse();
        String lowerLeft = reverse();
        String lowerRight = reverse();

        // 위 아래 뒤집은 결과 반환
        return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
    }

    public static void main(String[] args) {
        System.out.println("xbwwb");
        quadTree = "xxwwwbxwxwbbbwwxxxwwbbbwwwwbb";
        String result = reverse();
        System.out.println(result);
    }
}
