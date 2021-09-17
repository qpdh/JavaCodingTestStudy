import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_1045 {
    static Vector<Vector<Integer>> adj;

    public static void solve() {
        for(int i=0;i<adj.size();i++){
            if(adj.get(i).size()==2){
                System.out.println(i+"는 2개의 도로를 갖고 있음");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시의 개수
        int N = Integer.parseInt(st.nextToken());

        adj = new Vector<>();
        for (int i = 0; i < N; i++) {
            adj.add(new Vector<>());
        }

        // M개의 도로를 가진 도로의 집합 중 연결되어 있으면서 우선 순위가 가장 높은 것 찾기
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String data = br.readLine();

            for (int j = 0; j < N; j++) {
                // Y값이면 연결 되어있다는 의미
                if (data.charAt(j) == 'Y') {
                    adj.get(i).add(j);
                }
            }
        }

        solve();

        bw.close();
        br.close();

    }
}
