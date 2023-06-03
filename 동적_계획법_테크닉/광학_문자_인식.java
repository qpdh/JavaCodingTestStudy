package 동적_계획법_테크닉;

public class 광학_문자_인식 {
    // m : 원문에 출현할 수 있는 단어의 수
    // q : 처리해야 할 문장의 수
    static int m, q;

    // 분류기가 반환한 문장 corpus[R[i]] : 실제 들어있는 단어
    static int R[];

    // B : 각 단어가 문장 처음에 등장할 확률
    // sum(B) == 1
    static double B[];

    // T[i][j] : i번 단어 다음 단어가 j단어일 확률
    static double T[][];

    // M[i][j] : i번 단어가 적힌 조각을 j번 단어로 분류할 확률
    static double M[][];

    // 문장 내 단어의 수
    static int n;

    static int choice[][];

    // 1로 초기화
    // 로그값을 저장하므로 소수는 무조건 음수임
    static double cache[][];

    // Q[index] 이후를 채워서 얻을 수 있는 최대 g()의 로그 곱을 반환
    // Q[index-1] == previousMatch라고 가정
    static double dp(int index, int previousMatch) {
        // 배열의 끝까지 돌았다면 확률은 100% -> 로그값은 0
        if (index == n) {
            return 0;
        }

        if (cache[index][previousMatch] != 1.0) {
            return cache[index][previousMatch];
        }

        // 캐시 초기화
        cache[index][previousMatch] = Double.MIN_VALUE;

        // 현재 단어를 thisMatch로 판별할 경우
        for (int thisMatch = 0; thisMatch < m; thisMatch++) {
            double cand = T[previousMatch][thisMatch]
                    + M[thisMatch][R[index]]
                    + dp(index + 1, thisMatch);

            if (cache[index][previousMatch] < cand) {
                cache[index][previousMatch] = cand;
                choice[index][previousMatch] = thisMatch;
            }
        }

        return cache[index][previousMatch];
    }

    // 입력받은 단어들의 목록
    String corpus[];

    String reconstruct(int index, int previousMatch) {
        int choose = choice[index][previousMatch];
        String result = corpus[choose];
        if (index < n - 1) {
            result = result + " " + reconstruct(index + 1, choose);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
