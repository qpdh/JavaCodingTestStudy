import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class java_15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 카드의 개수
        int n = Integer.parseInt(st.nextToken());
        // 카드 합체를 몇 번 하는지
        int m = Integer.parseInt(st.nextToken());

        // 카드 입력받기
        PriorityQueue<Long> cardList = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cardList.add(Long.parseLong(st.nextToken()));
        }

        // 카드 합치기
        for (int i = 0; i < m; i++) {
            long first = cardList.poll();
            long second = cardList.poll();
            long result = first + second;
            cardList.add(result);
            cardList.add(result);
        }

        // 점수 계산하기
        long result = 0;
        while (!cardList.isEmpty()) {
            result += cardList.poll();
        }

        bw.write(result + "\n");

        br.close();
        bw.close();
    }
}
