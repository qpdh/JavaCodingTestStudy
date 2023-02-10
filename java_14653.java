import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class java_14653 {

    static int N, K, Q;

    static class Message {
        int notReadCount;
        char sendPerson;

        public Message(int notReadCount, char sendPerson) {

            this.notReadCount = notReadCount;
            this.sendPerson = sendPerson;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사람의 수
        N = Integer.parseInt(st.nextToken());
        // 메시지의 수
        K = Integer.parseInt(st.nextToken());
        // 정보를 알고 싶은 메시지의 번호
        Q = Integer.parseInt(st.nextToken());

        // 1. B ~ ? 까지 Set에 추가
        // 송신한 인원 모두 제거

        // <person, 가장 최근에 읽은 메시지 인덱스>
        Map<Character, Integer> readIndexMap = new HashMap<>();

        Message messageList[] = new Message[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int notReadCount = Integer.parseInt(st.nextToken());
            char sendPerson = st.nextToken().charAt(0);

            messageList[i] = new Message(notReadCount, sendPerson);

            // 송신자는 현재 메시지 이전에 있는 건 모두 읽음
            readIndexMap.put(sendPerson, i);

            // 전부 읽었으면, 모두가 현재 메시지 이전에 있는건 모두 읽음
            if (notReadCount == 0) {
                for (int j = 0; j < N; j++) {
                    readIndexMap.put((char) (j + 'A'), i);
                }
            }

            // 이전 메시지와 읽지 않은 사람이 동일한 경우
            if (i > 0) {
                if (messageList[i - 1].notReadCount == notReadCount) {
                    // 이전 송신자도 현재 메시지를 읽음
                    readIndexMap.put(messageList[i - 1].sendPerson, i);
                }
            }
        }

        readIndexMap.put('A', K + 1);

        TreeSet<Character> result = new TreeSet<>();

        for (char i = 'A'; i < N + 'A'; i++) {
            if (readIndexMap.getOrDefault(i, -1) < Q - 1) {
                result.add(i);
            }
        }

        if (result.size() == 0) {
            System.out.println(-1);
        } else {
            for (char person : result) {
                System.out.print(person + " ");
            }
            System.out.println();
        }

        br.close();
    }
}
