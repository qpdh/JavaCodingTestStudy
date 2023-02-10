import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_1783 {
    static int N, M;

    static int solution() {
        // 세로의 크기가 1일 때
        // -> 이동하지 못함, 1칸
        if (N == 1) {
            return 1;
        }

        // 세로의 크기가 2일 때
        // -> UDU(최대 4번) 또는 (가로+1/2) 값
        if (N == 2) {
            return Math.min(4, (M + 1) / 2);
        }

        if (M < 7) {
            return Math.min(4, M);
        }

        // 가로가 7미만일 때
        // 4가지 방법 전부 쓰기 불가능
        // 최대 4번 또는 가로값
        // 가로가 1인 경우 -> 세로가 2인 경우 2번 // 위에서 걸러짐
        // 가로가 1인 경우 -> 세로가 1인 경우 1번 // 위에서 걸러짐
        // 가로가 1인 경우 -> 세로가 3인 경우 // 1칸 우측으로못감
        // 가로가 2인 경우 -> 세로가 3인 경우 // 2칸
        // 가로가 3인 경우 -> 세로가 3인 경우 // 3칸

        // 4가지 전부 쓰는 경우
        // 쓴 뒤 위치는 [N-1][6]
        // 5칸을 방문한 상태
        return M - 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 무조건 오른쪽으로 간다.

        // 4가지 움직일 수 있는 방법
        // 1423 순서로 갈 때 최소 크기 계산하자
        // -> 세로 :: 3 // 가로 :: 7
        //

        // 그 다음부터는 1칸씩만 오른쪽으로 이동하면 된다. (2칸 위로, 2칸 아래로)

        // 세로의 크기가 1일 때
        // -> 이동하지 못함, 1칸

        // 세로의 크기가 2일 때
        // -> UDUD(최대 4번) 또는 가로/2 값

        // 가로가 7미만일 때
        // 4가지 방법 전부 쓰기 불가능
        // 최대 4번 또는 가로값
        // 가로가 1인 경우 -> 세로가 2인 경우 2번 // 위에서 걸러짐
        // 가로가 1인 경우 -> 세로가 1인 경우 1번 // 위에서 걸러짐
        // 가로가 1인 경우 -> 세로가 3인 경우 // 1칸 우측으로못감
        // 가로가 2인 경우 -> 세로가 3인 경우 // 2칸
        // 가로가 3인 경우 -> 세로가 3인 경우 // 3칸
        int result = solution();

        System.out.println(result);

        br.close();
    }
}
