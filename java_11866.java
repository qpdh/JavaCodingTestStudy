import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class java_11866 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arrayList.add(i + 1);
        }

        ArrayList<String> resultList = new ArrayList<>();
        int startIndex = 0;
        while (!arrayList.isEmpty()) {
            startIndex = (startIndex + K - 1) % arrayList.size();
            resultList.add(arrayList.remove(startIndex).toString());
        }

        System.out.print("<");
        System.out.print(String.join(", ", resultList));
        System.out.println(">");

        // 5개 남았을 때 5번째 찾기 0 1 2 3 4 -> 4 if arrayList.size()>=(K-1) return K-1
        // 4개 남았을 때 5번째 찾기 0 1 2 3 -> 0 (5)%4 == 1 -1
        // 3개 남았을 때 5번째 찾기 0 1 2 -> 1 (5)%3 == 2 -1
        // 2개 남았을 때 5번째 찾기 0 1 -> 0 (5)%2 == 1 -1

        br.close();
        bw.close();
    }
}
