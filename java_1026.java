import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> A = new PriorityQueue<>(N);
        Vector<Integer> B = new Vector<>(N);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        int ret = 0;

        while(!B.isEmpty()){
            int BMaxIndex = 0;
            for(int i=1;i<B.size();i++){
                if(B.get(BMaxIndex) < B.get(i)){
                    BMaxIndex = i;
                }
            }

            ret += B.get(BMaxIndex) * A.poll();
            B.remove(BMaxIndex);
        }

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
