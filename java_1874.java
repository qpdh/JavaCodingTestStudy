import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class java_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int numList[] = new int[n];

        // 수열
        for (int i = 0; i < n; i++) {
            numList[i] = Integer.parseInt(br.readLine());
        }

        // 현재 수열을 만들기 위한 수가 스택에 맨 끝에 있는가?
        // -> pop
        // 아니라면 현재 수 push

        StringBuffer result = new StringBuffer();
        int numIndex = 0;

        for (int i = 1; i <= n; i++) {
            // 현재 숫자 넣기
            stack.push(i);
            result.append("+\n");

            // 스택이 비어있는 상태가 아니라면..
            // pop 연산 시작
            while (!stack.isEmpty() && stack.peek() == numList[numIndex]) {
                result.append("-\n");
                // 큐 스택 둘다 빼내기
                stack.pop();
                numIndex++;
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("NO");
        } else {
            System.out.println(result);
        }

        br.close();
        bw.close();
    }
}
