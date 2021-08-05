import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class java_2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringBuffer sb = new StringBuffer();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 1; i < N + 1; ++i) {
            linkedList.add(i);
        }

        while (true) {
            if (linkedList.size() == 1) {
                break;
            }
            // 맨 앞 지우기
            linkedList.pollFirst();

            // 맨 앞 뒤로 보내기
            linkedList.addLast(linkedList.pollFirst());
        }

        bw.write(linkedList.get(0)+"\n");

        br.close();
        bw.close();
    }
}
