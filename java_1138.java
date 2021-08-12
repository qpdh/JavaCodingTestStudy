import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int informList[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            informList[i] = Integer.parseInt(st.nextToken());
        }

        // 1번째 녀석은 그 인덱스로 삽입.
        int retList[] = new int[N];
        // 0 0 0 0 0
        // 0은 나보다 큰 사람 이거나 내가 들어갈 수 있는 위치

        // 0이 아니고, 자기보다 큰사람 카운트

        // 사람 번호 0 부터 N-1 까지 삽입
        for (int h = 0; h < N; h++) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                // 현재 위치가 0이고, 나보다 큰 사람의 수가 만족한다면
                if (count == informList[h]) {
                    if (retList[i] == 0) {
                        retList[i] = h + 1;
                        break;
                    }
                    continue;
                }

                // 0이거나 나보다 큰 사람이 있으면

                if (retList[i] == 0)
                    count++;

            }

        }

        for (int human : retList) {
            bw.write(human + " ");
        }
        bw.write("\n");

        bw.flush();
        br.close();
        bw.close();
    }
}