import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_11058 {
    static long cache[];

    // N번 눌러서 A의 최대 개수 반환

    public static long krill(int N) {
        // // 횟수를 넘길경우 -> 제외
        // if (N < 0) {
        // return 0;
        // }

        // // 딱 맞아 떨어지면 반환
        // if (N == 0) {
        // return countA;
        // }

        // int ret = 0;

        // // A를 하나 출력하는 경우
        // ret = Math.max(ret, krill(N - 1, buffer, countA + 1));
        // // 버퍼에 넣는 경우
        // ret = Math.max(ret, krill(N - 2, countA, countA));
        // // 붙여넣기 하는 경우
        // ret = Math.max(ret, krill(N - 1, buffer, countA + buffer));

        // 어느 순간부터는 복사 붙여넣기가 이득이 됨
        // 복사 붙여 넣기 -> 3공정 -> 3개 이상 A가 출력이 된다면...

        // 버퍼를 갱신을 할 필요?
        // 기존 N개 A가 있다 버퍼에도 N개이다
        // 복사 2공정 -> 단순 3공정 -> 3N

        // 복사 2공정 -> 단순 5공정 -> 6N
        // 복사 2공정 -> 2공정 -> 복붙3공정(총 7공정) -> 6N

        // 복사 2공정 -> 붙여넣기 6공정(총 8공정) -> 7N
        // 복사 2공정 -> 붙여넣기 3공정(4N, 총 5공정) -> 복붙3공정(총 8공정) -> 8N

        // 복사 2공정 -> 붙여넣기 5공정(총 7공정) -> 7N
        // 복사 2공정 -> 붙여넣기 2공정(3N, 총 4공정) -> 복붙3공정(총 7공정) -> 6N
        // long ret = 0;

        // // System.out.println(N);

        // if (buffer == 0) {
        // // AAAAA 가 이득임
        // if (N < 6) {
        // ret = countA + N;
        // }
        // // 최초 복사 공정
        // // AAA(3) 복사(2) 5공정
        // else {
        // ret = krill(N - 5, 3, 3);
        // }
        // }
        // // 복사 공정이 이득인가?
        // else if (countA >= 3) {
        // // 복사공정을 한번 더 할 수 있는가?

        // // V V A C
        // if (N >= 5) {
        // ret = krill(N - 4, countA + (buffer * 2), countA + (buffer * 2));
        // }

        // // N == 4
        // // V V V V
        // // V A C V

        // // N == 3
        // // V V V == A C V
        // // A C V

        // // N == 2 or 1
        // // V V | V

        // // ...V...
        // // 나머지 붙여넣기
        // else {
        // ret = countA + (buffer * N);
        // }
        // }
        // // // 단순 A 출력이 이득인가?
        // // // A의 개수가 적으면 단순 A 출력이 이득
        // // else {
        // // ret = krill(N - 1, buffer, countA + 1);
        // // }

        // return ret;

        // 버퍼가 0인 경우
        if (N <= 6) {
            return N;
        }

        if (N < 0) {
            return 0;
        }

        if (cache[N - 1] != -1) {
            return cache[N - 1];
        }

        long ret = 0;

        ret = Math.max(ret, krill(N - 1) + 1);

        // 붙여넣기 횟수 AC=>(V*(i+1)) 개
        for (int i = 1; i <= N - 3; i++) {
            ret = Math.max(ret, krill(N - 2 - i) * (i + 1));
        }

        cache[N - 1] = ret;

        return ret;

        // // 단순 추가
        // if (countA < 3) {
        // ret = Math.max(ret, krill(N - 1, buffer, countA + buffer));
        // }
        // // 붙여넣기
        // else {
        // // A C V V
        // if (N >= 4) {
        // ret = Math.max(ret, krill(N - 4, countA, countA * 3));
        // }
        // // }
        // // // A C V == V V V
        // // else if (N >= 3) {
        // // ret = Math.max(ret, krill(N - 3, countA, countA * 2));
        // // }

        // // 붙여넣기
        // else {
        // ret = countA + (buffer * N);
        // }

        // }

        // cache[N - 1] = ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        cache = new long[100];

        for (int i = 0; i < 100; i++) {
            cache[i] = -1;
        }

        // for (int i = 1; i <= 100; i++) {
        //     bw.write("i : " + i + " ==>\t" + krill(i) + "\n");
        // }
        bw.write(krill(N) + "\n");

        br.close();
        bw.close();
    }
}
