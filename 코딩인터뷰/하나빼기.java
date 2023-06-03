package 코딩인터뷰;

public class 하나빼기 {
    static boolean checkEdit(String before, String after) {
        int editCount = 0;

        for (int i = 0; i < before.length(); i++) {
            if (before.charAt(i) != after.charAt(i)) {
                editCount++;
            }
        }

        return editCount < 2;
    }

    static boolean checkInsertOrDelete(String before, String after) {
        int beforeIndex = 0;
        int afterIndex = 0;

        // 삽입, 삭제, 교체가 이루어졌는가?
        boolean flag = false;

        while (beforeIndex < before.length() && afterIndex < after.length()) {
            // 문자 비교
            if (before.charAt(beforeIndex) == after.charAt(afterIndex)) {
                beforeIndex++;
                afterIndex++;
                continue;
            }

            // 문자가 다르다면 -> after만 index 1 추가
            if (!flag) {
                flag = true;
                afterIndex++;
                continue;
            }

            // 이미 삽입이 이루어 진 경우
            return false;
        }
        return true;
    }

    static boolean oneAway(String before, String after) {
        // 삽입, 삭제의 경우
        // 문자열의 길이 차이 = 1

        // 1. 삽입의 경우 before - after == -1
        // 2. 삭제의 경우 before - after == 1
        if (Math.abs(before.length() - after.length()) == 1) {
            return checkInsertOrDelete(before, after);
        }

        // 3. 교체의 경우 before == after
        if (before.length() == after.length()) {
            return checkEdit(before, after);
        }

        // 4. 그 외의 경우 - 일치하는 경우
        return false;
    }

    public static void main(String[] args) {

    }
}
