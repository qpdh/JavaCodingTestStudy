import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class java_23254 {
    static class Pair {
        int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        int score;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public Pair(int index, int score) {
            this.index = index;
            this.score = score;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 24 * N 시간 공부
        int N = Integer.parseInt(st.nextToken()) * 24;
        // M 과목
        int M = Integer.parseInt(st.nextToken());

        // 공부 안하고도 받는 최저 점수
        HashMap<Integer, Integer> subjectMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            subjectMap.put(i, Integer.parseInt(st.nextToken()));
        }



        PriorityQueue<Pair> studyScoreQueue = new PriorityQueue<>(new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                // 순수 증가량이 가장 큰 것
                // 100 넘어가게 되면 순위를 낮춤
                //
                int o1Score = o1.score + subjectMap.get(o1.index);
                if (o1Score > 100) {
                    o1Score = 100 - subjectMap.get(o1.index);
                } else {
                    o1Score = o1.score;
                }

                int o2Score = o2.score + subjectMap.get(o2.index);
                if (o2Score > 100) {
                    o2Score = 100 - subjectMap.get(o2.index);
                } else {
                    o2Score = o2.score;
                }

                return o2Score - o1Score;
                // subjectMap.get(o1.index) + o1.score
                // 100 - (해당 과목의 점수 + 증가량) 이 가장 큰 것
            }
        });

        st = new StringTokenizer(br.readLine());

        // 1시간 공부할 때마다 얻는 점수
        for (int i = 0; i < M; i++) {
            studyScoreQueue.add(new Pair(i, Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < N; i++) {
            Pair pair = studyScoreQueue.poll();
            int newScore = subjectMap.get(pair.index) + pair.score;
            if (newScore >= 100) {
                subjectMap.put(pair.index, 100);
                if (studyScoreQueue.isEmpty()) {
                    break;
                }
            } else {
                subjectMap.put(pair.index, pair.score + subjectMap.get(pair.index));
                studyScoreQueue.add(pair);
            }

        }

        // 1. 가장 증가폭이 높은 순서대로 100점 초과되기 전까지 올리기
        // 2. 이후 분배하기

        int result = 0;
        for (int i = 0; i < M; i++) {
            result += subjectMap.get(i);
        }

        bw.write(result + "\n");

        // 더했을 때 100점이 넘어가면...
        // 우선순위 큐에서 제외하고 나중에 추가한다.
        br.close();
        bw.close();
    }
}
