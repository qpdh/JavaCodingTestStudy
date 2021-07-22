import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class java_2822 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int quizList[][] = new int[8][2];

        for (int i = 0; i < 8; i++) {
            int score = Integer.parseInt(br.readLine());
            quizList[i][0] = i + 1;
            quizList[i][1] = score;

        }

        Arrays.sort(quizList, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                // TODO Auto-generated method stub
                return o2[1] - o1[1];
            }

        });

        int retScore = 0;
        PriorityQueue<Integer> retNumber = new PriorityQueue<>();
        for (int i = 0; i < 5; i++) {
            retScore += quizList[i][1];
            retNumber.add(quizList[i][0]);
        }

        bw.write(retScore + "\n");
        while (!retNumber.isEmpty()) {
            bw.write(retNumber.poll()+" ");
        }

        br.close();
        bw.close();
    }

}
