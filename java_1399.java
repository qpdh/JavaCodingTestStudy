import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class java_1399 {
    static int N;

    // 각 알파벳 대문자를 0~9까지 숫자중 하나로 바꿔서 합
    // 두 개 이상이 같은 숫자 X

    static StringBuffer words[];

    public static void main(String[] args) throws IOException {
        // GCF + ACDEB

        // 각 자릿수만큼 가중치를 두어 맵에 저장한다.
        // 맵에 값에서 가장 큰것부터 큰 값을 할당한다.
        // ex] ABBBB A:10000 B:3000
        // A가 9가 되는 것이 이득이다.
        // 최대값 100_000_000

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        words = new StringBuffer[N];

        for (int i = 0; i < N; i++) {
            words[i] = new StringBuffer(br.readLine()).reverse();
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }

    private static int solution() {
        Map<Character, Integer> alphaMap = new HashMap<>();

        for (StringBuffer word : words) {
            // 현재 단어를 더해주기
            int exp = 1;
            for (int i = 0; i < word.length(); i++) {
                // 현재 알파벳 추출
                char thisAlpha = word.charAt(i);

                // 자릿수에 따른 가중치 값 가산
                int alphaValue = alphaMap.getOrDefault(thisAlpha, 0);
                alphaMap.put(thisAlpha, alphaValue + exp);

                // 자릿수 증가
                exp *= 10;
            }
        }

        List<Entry<Character, Integer>> entryList = new ArrayList<Entry<Character, Integer>>(alphaMap.entrySet());

        // value 의 내림차순 정렬
        entryList.sort(Entry.comparingByValue((o1, o2) -> o2 - o1));

        int result = 0;
        int number = 9;
        for (Entry<Character, Integer> entry : entryList) {
            result += entry.getValue() * number;
            number--;
            // System.out.println(entryList);
        }

        return result;
    }
}
