package 동적_계획법_테크닉;

import java.util.Arrays;
import java.util.List;

public class 여행_짐_싸기 {
    // C : 테스트 케이스
    static int C;

    // N : 가져가고 싶은 물건의 수
    // W : 캐리어의 용량
    static int N, W;

    // 현재 부피가 capacity이고
    // index 이후의 물건을 선택할 때, 최대 절박도를 저장
    static int cache[][];

    // 수하물 배열
    static Baggage[] baggages;

    static class Baggage {
        int volume;
        int urgency;
        String name;

        public Baggage(int volume, int urgency, String name) {
            this.volume = volume;
            this.urgency = urgency;
            this.name = name;
        }
    }

    // 현재 부피가 capacity 이고
    // index 이후의 물건을 선택할 때, 최대 절박도 계산
    static int dp(int capacity, int index) {
        // 배열의 끝에 도달했다면.. 종료
        if (N <= index) {
            return 0;
        }

        // 이미 값이 존재한다면.. 종료
        if (cache[capacity][index] != -1) {
            return cache[capacity][index];
        }

        // 캐시 초기화
        cache[capacity][index] = 0;

        // 1. 현재 물건을 선택하는 경우
        // 현재 물건을 선택할 수 있는지 체크
        if (capacity + baggages[index].volume <= W) {
            cache[capacity][index] = Math.max(cache[capacity][index],
                    dp(capacity + baggages[index].volume, index + 1) + baggages[index].urgency);
        }

        // 2. 현재 물건을 선택하지 않는 경우
        cache[capacity][index] = Math.max(cache[capacity][index],
                dp(capacity, index + 1));

        return cache[capacity][index];
    }

    static int solution() {
        cache = new int[W + 1][N + 1];
        for (int i = 0; i < W + 1; i++) {
            Arrays.fill(cache[i], -1);
        }

        int result = dp(0, 0);

        return result;
    }

    static void reconstruct(int capacity, int index, List<String> picked) {
        // 배열의 끝 까지 진행했다면..
        if (index == N) {
            return;
        }

        // 현재 인덱스의 물건을 선택하는가?
        if (dp(capacity, index) != dp(capacity, index + 1)) {
            picked.add(baggages[index].name);
            reconstruct(capacity + baggages[index].volume, index + 1, picked);
        }
        // 선택하지 않은 경우
        else {
            reconstruct(capacity, index + 1, picked);
        }
    }

    public static void main(String[] args) {

    }
}
