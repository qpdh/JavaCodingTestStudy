import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class java_14564 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // [i]모는 몇번째 사람인가?
    static int tofu[];

    static int N, M, A;

    // 호출된 모 수에서 몇번 째 사람인지 체크하는 메소드
    // number : 호출된 모 수
    public static void numbering(int number) {
        // A -> M/2+1 + A-A
        // 0모에 해당하는 인덱스 -> A - (M/2+1)
        // number모에 해당하는 인덱스 -> A-(M/2+1) + number;
        // 오버플로우, 언더플로우 처리해야함
        int nowIndex = A - (M / 2 + 1) + number;

        if (nowIndex <= 0) {
            nowIndex += N;

        }
        if (nowIndex > N) {
            nowIndex -= N;
        }

        A = nowIndex;
    }

    public static void game() throws IOException {
        boolean gameOver = false;

        // 자신이 해당하는 모 수가 입력되는 경우 종료
        while (!gameOver) {
            // X모 라고 말함
            int number = Integer.parseInt(br.readLine());

            // M/2+1 -> 자기 번호를 말한 경우 -> 게임 종료
            if (number == M / 2 + 1) {
                gameOver = true;
                bw.write("0\n");
                break;
            }

            // 그 외 번호를 말한 경우 -> 그 번호에 맞게 다시 넘버링
            numbering(number);

            bw.write(A + "\n");
        }

    }

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N명이서 게임을 함
        N = Integer.parseInt(st.nextToken());
        // M모 게임
        M = Integer.parseInt(st.nextToken());
        // 주최자
        A = Integer.parseInt(st.nextToken());

        game();

        br.close();
        bw.close();
    }
}