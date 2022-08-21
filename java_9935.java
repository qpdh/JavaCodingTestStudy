import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class java_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder word = new StringBuilder(br.readLine());

        String bombWord = br.readLine();


        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < word.length(); i++) {
            stack.push(word.charAt(i));

            if (stack.size() >= bombWord.length()) {
                // 폭발이 일어나는가?
                // 폭발 단어가 존재해야 함
                boolean isBomb = true;
                for (int j = 0; j < bombWord.length(); j++) {
                    if (stack.get(stack.size() - bombWord.length() + j) != bombWord.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }
                if (isBomb) {
                    for (int j = 0; j < bombWord.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < stack.size(); i++) {
            result.append(stack.get(i));
        }

        if (result.toString().equals("")) {
            bw.write("FRULA\n");
        } else {
            bw.write(result + "\n");
        }

        br.close();
        bw.close();
    }
}
