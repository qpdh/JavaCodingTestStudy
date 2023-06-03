import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class java_1484 {
    // G 킬로그램
    static int G;

    public static void main(String[] args) throws IOException {
        // G = weightReal^2 - weightRemember^2

        // 실제 몸무게가 항상 더 크거나 같아야한다.

        // weightReal^2 >= G+1 인 최초 시점부터 시작

        // G+weightRemember^2 = weightReal^2
        // 15 + 1 = 4^2
        // 15 + 10 = 5^2
        // 15 + 21 = 6^2

        // G = (weightReal+weightRemember)(weightReal-weightRemember)

        // weightRemember 1부터
        // weightReal 은 weightReal^2>=G 인 최초 시점 부터
        // 만약 G값과 일치하다면 결과값에 추가
        // G값 보다 작다면 weightReal 늘리기
        // G값 보다 크다면 weightRemember 늘리기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());

        List<Integer> result = solution();

        for (int weight : result) {
            System.out.println(weight);
        }

        br.close();
    }

    private static List<Integer> solution() {
        List<Integer> result = new ArrayList<>();

        int weightRemember = 1;
        
        // weightReal 은 weightReal^2>=G+1 인 최초 시점 부터
        int weightReal = (int) Math.ceil(Math.sqrt(G + 1));

        // G는 자연수이므로 weightReal == weightRemember 인 경우는 존재하지 않음
        while (weightReal > weightRemember) {
            int calcG = (weightReal + weightRemember) * (weightReal - weightRemember);

            // 일치하는 경우 result에 추가
            if (calcG == G) {
                result.add(weightReal);
                ++weightReal;
                continue;
            }

            // G가 더 작은 경우 -> weightRemember 늘리기
            if (G < calcG) {
                ++weightRemember;
                continue;
            }

            // G가 더 큰 경우 -> weightReal 늘리기
            ++weightReal;
            continue;
        }

        // 아무것도 없다면 -1 추가
        if (result.size() == 0) {
            result.add(-1);
        }

        return result;
    }
}
