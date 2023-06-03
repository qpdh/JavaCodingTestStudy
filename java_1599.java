import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class java_1599 {
    final static Map<Character, Integer> minsikMap = Map.ofEntries(
            Map.entry('a', 1),
            Map.entry('b', 2),
            Map.entry('c', 3),
            Map.entry('k', 4),
            Map.entry('d', 5),
            Map.entry('e', 6),
            Map.entry('g', 7),
            Map.entry('h', 8),
            Map.entry('i', 9),
            Map.entry('l', 10),
            Map.entry('m', 11),
            Map.entry('n', 12),
            Map.entry('-', 13),
            Map.entry('o', 14),
            Map.entry('p', 15),
            Map.entry('r', 16),
            Map.entry('s', 17),
            Map.entry('t', 18),
            Map.entry('u', 19),
            Map.entry('w', 20),
            Map.entry('y', 21));

    static int N;

    static String words[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        solution();

        printWords();

        br.close();

    }

    // words 배열을 출력한다.
    private static void printWords() {
        // System.out.println("========");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
        // System.out.println("========");
    }

    // words 배열을 민식어에 따라서 정렬한다.
    private static void solution() {
        Arrays.sort(words, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                int o1Index = 0, o2Index = 0;

                while (o1Index < o1.length() && o2Index < o2.length()) {
                    // 현재 인덱스 알파벳 추출
                    char o1Character = o1.charAt(o1Index++);
                    char o2Character = o2.charAt(o2Index++);

                    // 현재 알파벳이 ng인지 판단하기
                    if (o1Index < o1.length() && 'n' == o1Character) {
                        // 다음 알파벳이 g라면..
                        if (o1.charAt(o1Index) == 'g') {
                            o1Character = '-';
                            o1Index++;
                        }
                    }

                    if (o2Index < o2.length() && 'n' == o2Character) {
                        // 다음 알파벳이 g라면..
                        if (o2.charAt(o2Index) == 'g') {
                            o2Character = '-';
                            o2Index++;
                        }
                    }

                    // 현재 알파벳이 같다면.. 계속 비교
                    if (o1Character == o2Character) {
                        continue;
                    }

                    // 다르면 우선순위 판별
                    return minsikMap.get(o1Character) - minsikMap.get(o2Character);
                }

                // 반복문을 빠져나온 경우

                // 1. o1이 짧은 문자열인 경우
                if (o1Index == o1.length()) {
                    return -1;
                }

                // 2. o2가 짧은 문자열인 경우
                // 두 문자열이 같은 경우는 존재하지 않음
                else {
                    return 1;
                }
            }

        });
    }
}
