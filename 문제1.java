import java.util.HashMap;

class Solution {

    static class Pair {
        int size;
        int capacity;

        public Pair(int size, int capacity) {
            this.size = size;
            this.capacity = capacity;
        }

    }

    // number를 포용하기 위한 가장 작은 2의 거듭제곱 수
    public int calcCapacity(int number) {
        if (number == 1) {
            return 1;
        }

        int result = 1;

        while (number != 0) {
            number /= 2;
            result *= 2;
        }

        return result;
    }

    public int solution(int[][] queries) {
        int copyCount = 0;

        // 배열의 크기
        HashMap<Integer, Pair> hashMap = new HashMap<>();

        // 복사 카운트
        // 가장 가까운 2의 거듭제곱수를 구하자
        for (int i = 0; i < queries.length; i++) {
            // 배열 번호와 추가할 원소의 수

            // 만약 배열의 크기가 존재하지 않으면...
            // 카운트는 그대로 둔다
            // 배열 번호
            int arrayNumber = queries[i][0];
            // 추가할 원소의 수
            int insertNumber = queries[i][1];

            // 만약 0이라면. 기본 배열이 없다면
            Pair pair = hashMap.getOrDefault(arrayNumber, new Pair(0, 0));
            if (pair.size == 0) {
                int capacity = calcCapacity(insertNumber);
                hashMap.put(arrayNumber, new Pair(insertNumber, capacity));
            }
            // 이미 존재한다면
            else {
                // 추가해야 하는 지 판단하자
                // 복사할 필요가 없는 경우
                if (pair.capacity - pair.size >= insertNumber) {
                    pair.size += insertNumber;
                    hashMap.put(arrayNumber, pair);
                }
                // 복사할 필요가 있는 경우
                else {
                    // 복사
                    copyCount += pair.size;
                    int capacity = calcCapacity(insertNumber + pair.size);

                    pair.capacity = capacity;
                    pair.size += insertNumber;

                    hashMap.put(arrayNumber, pair);

                }
            }

        }

        return copyCount;
    }
}

public class 문제1 {
    public static void main(String[] args) {
        Solution s = new Solution();

        // 처음 배열의 크기 0
        // 추가하더라도 크기가 작거나 같으면 기존 배열에 원소 추가
        // 원소를 추가하면 크기보다 커지는 경우 2 거듭제곱 중 가자 작은 값
    }
}
