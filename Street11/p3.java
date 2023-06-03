package Street11;

import java.util.Stack;

public class p3 {
    public static int solution(String S) {
        // Implement your solution here
        // 여는 횟수와 닫는 횟수가 같게 된다면 대칭 부분 끝

        // 문자를 돌면서 체크하기

        int result = symmetric(S, 0) * 2;

        return result;
    }

    public static int countSubTask(Stack<Character> stack, int openCount, int closeCount, int wildCount) {
        int result = 0;
        if (wildCount == 0) {
            result = closeCount;
        }
        // 1. ? 만 있는 경우
        else if (openCount == 0 && closeCount == 0) {
            result = wildCount / 2;
        }
        // 2. 중간에 ?가 있는 경우 <?<?>>>>
        else {
            result = Math.max(Math.min(openCount + wildCount, closeCount),
                    Math.min(openCount, closeCount + wildCount));
        }
        return result;
    }

    // startIndex를 시작으로하는 대칭의 수 찾기
    // <><><>
    public static int symmetric(String S, int startIndex) {
        // s.charAt(startIndex) == '<'
        int openCount = 0;
        int closeCount = 0;
        int wildCount = 0;

        Stack<Character> stack = new Stack<>();

        for (; startIndex < S.length(); startIndex++) {
            if (S.charAt(startIndex) == '<') {
                stack.add('<');
                openCount++;
                break;
            }
            if (S.charAt(startIndex) == '?') {
                stack.add('?');
                wildCount++;
                break;
            }
        }

        // ?가 나오는 경우
        for (int i = startIndex + 1; i < S.length(); i++) {
            // < 를 만나는 경우
            // 1. >를 만나지 않았던 경우 -> 계속 추가
            // 2. >를 만난 경우 -> 새로운 symmetric 시작하기
            if (S.charAt(i) == '<') {
                stack.add('<');
                // 이미 닫는 것이 나왔다면...새로운 symmetric 시작해야 함
                if (closeCount != 0) {

                    int subResult = countSubTask(stack, openCount, closeCount, wildCount);

                    // 직전에 wildCount가 나왔다면..wildCard 위치부터 시작해도 무방
                    int lastWildCardIndex = -1;
                    for (lastWildCardIndex = i - 1; S.charAt(lastWildCardIndex) == '?'
                            && lastWildCardIndex >= 0; lastWildCardIndex--) {

                    }

                    return Math.max(subResult, symmetric(S, lastWildCardIndex + 1));
                }
                openCount++;
            }
            // > 를 만나는 경우
            else if (S.charAt(i) == '>') {
                stack.add('>');
                closeCount++;
                // if (openCount == closeCount) {
                // return Math.max(closeCount, symmetric(S, i + 1));
                // }
            }
            // ? 인 경우
            else {
                // <?<?<?>>> 인 경우
                // 이미 닫은 적이 있는 경우 -> 닫기로 고정됨
                if (closeCount != 0) {
                    closeCount++;
                } else {
                    wildCount++;
                }
                stack.add('?');
            }
        }

        // 문자열이 끝나버린 경우
        int subResult = countSubTask(stack, openCount, closeCount, wildCount);

        return subResult;
    }

    public static void main(String[] args) {
        System.out.println(solution(""));
    }
}
