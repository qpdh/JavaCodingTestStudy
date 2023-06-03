package 동적_계획법_테크닉;

import java.util.ArrayList;
import java.util.List;

public class 최대_증가_부분_수열 {
    static int n;
    static int cache[], S[], choices[];

    // S[start]에서 시작하는 증가 부분 수열중 최대 길이를 반환한다.
    static int lis4(int start) {
        if (cache[start] != -1) {
            return cache[start];
        }

        // S[start]는 항상 포함하므로, 최소 길이는 1
        cache[start] = 1;

        //
        int bestNext = -1;
        for (int next = start + 1; next < n; next++) {
            // 최대 증가 부분 수열이므로 다음것이 더 커야 함
            if (start == -1 || S[start] < S[next]) {
                // 현재 위치 + next에서 시작하는 증가 부분 수열 중 최대 길이
                int cand = lis4(next) + 1;
                // 길이가 더 크면 갱신
                if (cand > cache[start]) {
                    cache[start] = cand;
                    // 다음 원소로 next 인덱스에 있는 원소가 최적이다.
                    bestNext = next;
                }
            }
        }

        //
        choices[start] = bestNext;
        return cache[start];

    }

    static void reconstruct(int start, List<Integer> seq) {
        if (start != -1) {
            seq.add(S[start]);
        }

        int next = choices[start];

        if (next != -1) {
            reconstruct(next, seq);
        }
    }

    static void solution() {
        cache = new int[100];
        choices = new int[100];

        int resultStartIndex = 0;

        for (int i = 0; i < n; i++) {
            if (lis4(resultStartIndex) < lis4(i)) {
                resultStartIndex = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        reconstruct(resultStartIndex, result);
    }

    public static void main(String[] args) {

    }
}
