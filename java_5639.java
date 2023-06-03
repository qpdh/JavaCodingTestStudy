import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class java_5639 {
    static List<Integer> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nodes = new ArrayList<>();

        while (true) {
            String stringNumber = br.readLine();

            if (stringNumber == null || stringNumber.equals("")) {
                break;
            }

            int number = Integer.parseInt(stringNumber);

            nodes.add(number);
        }

        makeTree(0, nodes.size() - 1);

        br.close();
    }

    // [index,lastIndex] 까지 서브트리 중 후위순회하기
    // 50 | 1 2 3 | 51 52 53
    private static void makeTree(int index, int lastIndex) {
        if (index > lastIndex) {
            return;
        }
        // 왼쪽 자식과 오른쪽 자식을 찾아보자
        // 맨 앞 = 자기 자신
        int thisNumber = nodes.get(index);

        // 자기 자신보다 작은 것 = 왼쪽 자식
        // 자기 자신보다 큰 것 = 오른쪽 자식

        int mid = index + 1;
        for (; mid <= lastIndex; ++mid) {
            if (nodes.get(mid) < thisNumber) {
                continue;
            }

            // thisNumber 보다 커지는 경우
            break;
        }

        makeTree(index + 1, mid - 1);
        makeTree(mid, lastIndex);

        System.out.println(nodes.get(index));
    }
}
