import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class java_16472 {
    static int N;
    static String word;

    public static void main(String[] args) throws IOException {
        /*
         * indexA, indexB
         * 현재 저장된 알파벳의 수 체크
         * N개가 넘어간다면 indexA부터 제거 해 나감
         * Max값 찾기
         *
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        word = br.readLine();

        int result = solution();

        System.out.println(result);

        br.close();
    }

    private static int solution() {
        int result = 1;

        Map<Character, Integer> alphaCountMap = new HashMap<>();

        int indexA = 0, indexB = 1;
        alphaCountMap.put(word.charAt(indexA), 1);
        int nowAlphaCount = 1;

        while (indexB < word.length()) {
            char bAlpha = word.charAt(indexB);

            // Map에 존재하지 않는다면..
            if (alphaCountMap.getOrDefault(bAlpha, -1) == -1) {
                // 더 추가할 수 있는 경우 -> 더 추가하기
                if (nowAlphaCount < N) {
                    ++nowAlphaCount;
                    alphaCountMap.put(bAlpha, 1);
                }
                // 더 추가할 수 없는 경우 -> 앞에서부터 지워나가기
                else {
                    alphaCountMap.put(bAlpha, 1);
                    ++nowAlphaCount;
                    while (N < nowAlphaCount) {
                        // indexA 를 증가시키면서 제거하자
                        int aCount = alphaCountMap.get(word.charAt(indexA));

                        // 1개 밖에 없으면 제거
                        if (aCount == 1) {
                            alphaCountMap.remove(word.charAt(indexA));
                            --nowAlphaCount;
                        }

                        // 1개가 넘는다면 1개 감소
                        else {
                            alphaCountMap.put(word.charAt(indexA), aCount - 1);
                        }

                        // indexA 증가
                        ++indexA;
                    }
                }
            }
            // Map에 존재한다면.. 추가
            else {
                alphaCountMap.put(bAlpha, alphaCountMap.get(bAlpha) + 1);
            }

            // 값 갱신
            result = Math.max(result, indexB - indexA + 1);

            // B 인덱스 증가
            ++indexB;
        }

        return result;
    }
}
