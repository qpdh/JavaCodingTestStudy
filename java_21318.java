import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_21318 {
    static int N;
    static int musicSheet[];

    // [0] 부터 [i] 까지 연주했을 때 실패하는 갯수
    static int failList[];

    static int Q;

    static void findFail() {
        failList = new int[N];

        // [0] 부터 [0] 까지는 실수하지 않는다.
        failList[0] = 0;

        // [0] 부터 [i] 까지 연주한다.
        for (int i = 1; i < musicSheet.length; i++) {
            // 맨 마지막은 실수하지 않는다.
                // 이번에 연주할 것이 이전에 연주했던 것 보다 쉬우면..
                if (musicSheet[i] < musicSheet[i - 1]) {
                    failList[i] = failList[i - 1] + 1;
                } else {
                    failList[i] = failList[i - 1];
                }
            }
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // [0] [4] 까지 실패하는 갯수 2개
        // [1,2,3,2]
        // [0] [6] 까지 실패하는 갯수 3개
        // [1,2,3,2,1,5,6]

        // [4] [6] 까지 실패하는 갯수?
        // [0] [6] 까지 실패하는 갯수 - [0] [4] 까지 실패하는 갯수 - 1

        // N개의 악보
        // 1번 부터 N번까지 번호로 부른다.

        // 각 악보는 1 이상 10^9 이하 난이도를 가짐

        // x번 부터 y번 까지 악보를 순서대로 연주한다.

        // musicSheet[i] > musicSheet[i+1] 라면 실수
        // y번에서는 실수 X

        // 1 부터 x 번까지 연주했을 때 실수를 하는 횟수 저장하자

        N = Integer.parseInt(br.readLine());

        musicSheet = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            musicSheet[i] = Integer.parseInt(st.nextToken());
        }

        findFail();

        Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            int result = failList[y] - failList[x];

            System.out.println(result);

        }

        br.close();
    }
}
