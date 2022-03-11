public class 거리두기 {
    public final static int[][] dydx1 = { { 1, 0 }, { 0, 1 } };
    public final static int[][] dydx2 = { { 2, 0 }, { 0, 2 }, { 1, 1 }, { -1, 1 } };

    // 자리 사이에 파티션이 있는가?
    // 두 사람 사이의 거리가 2 이하인가?

    // 사람이 있는 공간, 좌표를 받아옴
    public static int check(String[] place, int y, int x) {
        int result = 1;

        // 바로 옆에 있는 지 확인
        for (int i = 0; i < dydx1.length; i++) {
            int toY = y + dydx1[i][0];
            int toX = x + dydx1[i][1];

            // 배열의 범위가 벗어나는지 확인
            if (toY >= place.length || toX >= place[0].length()) {
                continue;
            }

            // 바로 옆에 있으면 거리두기 실패
            if (place[toY].charAt(toX) == 'P') {
                return 0;
            }

        }

        // 2 거리지만 옆에 가림막이 있는지 확인
        for (int i = 0; i < dydx2.length; i++) {
            int toY = y+dydx2[i][0];
            int toX = x+dydx2[i][1];

            // 배열의 범위가 벗어나는지 확인
            if (toY >= place.length || toY < 0 || toX >= place[0].length()) {
                continue;
            }

            // 거리가 2 이하라면.
            if (place[toY].charAt(toX) == 'P') {
                // 사이에 가림막이 있는지 확인
                switch (i) {
                    case 0: // | 의 경우 : y+1 에 가림막이 있어야 함

                        if (place[y + 1].charAt(x) != 'X') {
                            return 0;
                        }

                        break;
                    case 1: // ㅡ 의 경우 : x+1 에 가림막이 있어야 함

                        if (place[y].charAt(x + 1) != 'X') {
                            return 0;
                        }

                        break;
                    case 2: // \ 의 경우 : y+1, x+1 에 가림막이 있어야 함

                        if (place[y].charAt(x + 1) != 'X' || place[y + 1].charAt(x) != 'X') {
                            return 0;
                        }

                        break;
                    case 3: // / 의 경우 : y-1, x+1 에 가림막이 있어야 함
                        if (place[y - 1].charAt(x) != 'X' || place[y].charAt(x + 1) != 'X') {
                            return 0;
                        }
                        break;

                }
            }
        }

        return result;
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        // 케이스 만큼 돌리기
        for (int i = 0; i < places.length; i++) {
            // 한 케이스 내 2차원 배열 돌리기
            int result = 1;
            for (int j = 0; j < places[i].length; j++) {
                for (int k = 0; k < places[i][j].length(); k++) {
                    if (places[i][j].charAt(k) == 'P') {
                        result = check(places[i], j, k);

                        if (result == 0) {
                            break;
                        }
                    }

                }
                if (result == 0) {
                    break;
                }
            }
            answer[i] = result;
        }

        return answer;
    }

    public static void main(String[] args) {
        String places[][] = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
                { "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
                { "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };
        int result[] = solution(places);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

    }
}
