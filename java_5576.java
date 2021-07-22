import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;



public class java_5576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 최대 힙 사용
        // 자동으로 맨 앞이 최댓값을 가짐
        PriorityQueue<Integer> WScores = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<10;i++){
            WScores.add(Integer.parseInt(br.readLine()));
        }

        PriorityQueue<Integer> KScores = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<10;i++){
            KScores.add(Integer.parseInt(br.readLine()));
        }

        int Wresult = 0;
        int Kresult = 0;

        for(int i=0;i<3;i++){
            Wresult += WScores.poll();
            Kresult += KScores.poll();
        }

        bw.write(Wresult+" "+Kresult);
        

        br.close();
        bw.close();
    
    }
}