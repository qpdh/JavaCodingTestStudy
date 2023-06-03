package 탐욕법;

import java.util.PriorityQueue;

public class 문자열_합치기 {
    static PriorityQueue<Integer> priorityQueue;

    static int solution() {
        int result = 0;

        while (priorityQueue.size() > 1) {
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();

            result += a + b;
            priorityQueue.add(a + b);
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
