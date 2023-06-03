package 동적_계획법_테크닉;

import java.util.List;

public class 숫자_게임 {
    final static int EMPTY = -987654321;
    // left, right 일 때 점수차의 최대치 저장
    static int cache[][];

    static int board[];

    // 현재 게임판에 남은 수들이 state일 때 점수차의 최대치
    static int dp(int left, int right) {
        // 수가 없을 때
        if (left > right) {
            return 0;
        }

        if (cache[left][right] != EMPTY) {
            return cache[left][right];
        }

        // 수를 가져가는 경우
        // 왼쪽에서 가져가거나, 오른쪽에서 가져가거나
        cache[left][right] = Math.max(board[left] - dp(left + 1, right), board[right] - dp(left, right - 1));

        // 수를 없애는 경우
        // 2개 이상 있어야 함
        if (right - left + 1 >= 2) {
            // 왼쪽 2개 지우기
            cache[left][right] = Math.max(cache[left][right], -dp(left + 2, right));

            // 오른쪽 2개 지우기
            cache[left][right] = Math.max(cache[left][right], -dp(left, right - 2));
        }

        return cache[left][right];

    }

    public static void main(String[] args) {
        // 1. 왼쪽 or 오른쪽 숫자 가져가기
        // 2. 왼쪽 끝에서 2개, 오른쪽 끝에서 2개 지우기
        // 최종 점수 차이?
    }
}
