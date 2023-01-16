import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class java_9019 {
    // 테스트 케이스의 수
    static int T;

    // A : 초깃값
    // B : 목표값
    static int A, B;

    // 2배
    // 단, n*2 > 9999면 n*2%10000 반환
    static int commandD(int number) {
        int result = number;

        result *= 2;

        if (result > 9999) {
            result %= 10000;
        }

        return result;
    }

    // n-1연산. 단, n-1==0이면 9999반환
    static int commandS(int number) {
        int result = number;

        result -= 1;

        if (result < 0) {
            result = 9999;
        }

        return result;
    }

    // 왼쪽으로 돌리기
    static int commandL(int number) {
        int result = number;

        // 맨 앞자리 추출
        int first = number / 1000;

        // 원래 숫자 %1000 *10
        result = result % 1000 * 10;

        // 맨 앞자리 뒤에 더하기
        result += first;

        return result;
    }

    // 오른쪽으로 돌리기
    static int commandR(int number) {
        int result = number;

        // 맨 뒷자리 추출
        int last = number % 10;

        // 원래 숫자 /10
        result = result / 10;

        // 맨 앞자리 뒤에 더하기
        result += last * 1000;

        return result;
    }

    // 지금까지의 명령어, 레지스터값을 저장하는 클래스
    static class Pair {
        StringBuffer command;
        int number;

        public Pair(StringBuffer command, int number) {
            this.command = command;
            this.number = number;
        }
    }

    // 가장 먼저 B가 만들어지는 명령어를 반환하는 메소드
    static StringBuffer solution() {
        // 중복 방문 처리
        boolean isVisited[] = new boolean[10000];

        StringBuffer result = new StringBuffer();

        // 너비 우선 탐색 시작
        Queue<Pair> queue = new ArrayDeque<>();

        queue.add(new Pair(new StringBuffer(), A));

        while (true) {
            Pair here = queue.poll();

            // 목표값이 만들어지면 종료
            if (here.number == B) {
                result = here.command;
                break;
            }

            // D,S,L,R 한 번 씩 다 해보기
            // D
            int nextNumber = commandD(here.number);
            if (!isVisited[nextNumber]) {
                isVisited[nextNumber] = true;
                queue.add(new Pair(new StringBuffer(here.command).append('D'), nextNumber));
            }

            // S
            nextNumber = commandS(here.number);
            if (!isVisited[nextNumber]) {
                isVisited[nextNumber] = true;
                queue.add(new Pair(new StringBuffer(here.command).append('S'), nextNumber));
            }

            // L
            nextNumber = commandL(here.number);
            if (!isVisited[nextNumber]) {
                isVisited[nextNumber] = true;
                queue.add(new Pair(new StringBuffer(here.command).append('L'), nextNumber));
            }

            // R
            nextNumber = commandR(here.number);
            if (!isVisited[nextNumber]) {
                isVisited[nextNumber] = true;
                queue.add(new Pair(new StringBuffer(here.command).append('R'), nextNumber));
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            StringBuffer result = solution();
            System.out.println(result.toString());
        }

        br.close();
    }
}
