import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class java_1374 {
    static int N;
    static List<Lecture> lectureList;

    static class Lecture implements Comparable<Lecture> {
        int number;
        int startTime;
        int endTime;

        public Lecture(int number, int startTime, int endTime) {
            this.number = number;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lecture o) {
            return this.startTime - o.startTime;
        }
    }

    static int solution() {
        int result = 0;

        Collections.sort(lectureList);

        // 강의실에 강의를 추가하는 경우

        // 강의실을 추가하는 경우
        // -> 가장 빨리 끝나는 강의 시각 > 추가하려는 강의의 시작 시각

        PriorityQueue<Integer> lectureClassQueue = new PriorityQueue<>();
        lectureClassQueue.add(0);

        for (int i = 0; i < N; i++) {
            Lecture lecture = lectureList.get(i);

            int classEndTime = lectureClassQueue.peek();

            // 강의실에 강의를 추가하는 경우
            if (!(classEndTime > lecture.startTime)) {
                lectureClassQueue.poll();
            }

            lectureClassQueue.add(lecture.endTime);
        }

        result = lectureClassQueue.size();

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        lectureList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            lectureList.add(new Lecture(number, startTime, endTime));
        }

        int result = solution();

        System.out.println(result);

        br.close();
    }
}
