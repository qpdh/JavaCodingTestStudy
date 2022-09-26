import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_2458 {
    // 전체 학생의 수
    static int N;
    // 학생의 키를 비교한 횟수
    static int M;
    // 학생 테이블
    static boolean board[][];

    static void floydWarshall() {
        // [i] 를 경유하여
        for (int i = 0; i < N; i++) {
            // [j] 로부터 출발하여
            for (int j = 0; j < N; j++) {
                // [k] 로 가는 경우
                for (int k = 0; k < N; k++) {
                    if (board[j][k] == false) {
                        if (board[j][i] != false && board[i][k] != false) {
                            board[j][k] = true;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N명의 학생의 키는 전부 다르다
        // 1 -> 5
        // 3 -> 4
        // 5 -> 4
        // 4 -> 2
        // 4 -> 6
        // 5 -> 2

        // 데이터 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            board[from][to] = true;
        }

        // floyd warshall 알고리즘 사용
        floydWarshall();

        // [i] 번 학생이 비교 가능한 학생의 수
        // N-1이 되어야 몇 번째인지 알 수 있다.
        int studentResult[] = new int[N];

        // n 번 학생의 정확한 키를 알고 싶다면..
        // [i]행에서 갈 수 있는 학생의 수 == i 학생보다 큰 학생의 수
        // [i]열로 갈 수 있는 학생의 수 == i 학생보다 작은 학생의 수
        // 두 수의 합 == 전체 학생 수 - 1

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == true) {
                    studentResult[i] += 1;
                    studentResult[j] += 1;
                }
            }
        }

        int result = 0;
        for (int student : studentResult){
            if(student==N-1){
                result++;
            }
        }

        System.out.println(result);

        // 자신의 키가 몇 번째인지 알 수 있는 학생이 몇 명인가?

        br.close();
    }}
