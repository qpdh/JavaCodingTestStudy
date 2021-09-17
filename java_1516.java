import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

public class java_1516 {
    // 선행 건물 정보를 저장하는 인접리스트
    static Vector<Vector<Integer>> adj;

    static int buildTime[];
    static int cache[];

    public static void solve(int now) {
        // 각 건물이 완성되기 까지 걸리는 시간 저장



        // 이미 완성까지 걸리는 시간이 존재하면..
        if (cache[now] != -1) {
            return;
        }

        //
        int maxLength = 0;
        for (int i = 0; i < adj.get(now).size(); i++) {
            int next = adj.get(now).get(i);

            if (cache[next] == -1) {
                solve(next);
            }

            if (maxLength < cache[next]) {
                maxLength = cache[next];
            }
        }

        cache[now] = buildTime[now] + maxLength;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        adj = new Vector<>();
        buildTime = new int[N];
        cache = new int[N];
        Arrays.fill(cache, -1);

        for(int i=0;i<N;i++){
            adj.add(new Vector<>());
        }

        // i번째 건물을 짓기 위한 다른 선행 건물 정보
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            buildTime[i] = Integer.parseInt(st.nextToken());

            // 마지막은 -1 이므로 마지막 직전까지 반복
            int maxBuildCount = st.countTokens();
            for (int j = 0; j < maxBuildCount - 1; j++) {
                int next = Integer.parseInt(st.nextToken()) - 1;
                adj.get(i).add(next);
            }
        }

        for(int i=0;i<N;i++){
            solve(i);
        }

        for(int i=0;i<cache.length;i++){
            System.out.println(cache[i]);
        }

        // // 출력 테스트
        // for(int i=0;i<adj.size();i++){
        // System.out.print(buildTime[i]+" ");
        // for(int j=0;j<adj.get(i).size();j++){
        // System.out.print(adj.get(i).get(j)+" ");
        // }
        // System.out.println();
        // }

        br.close();
        bw.close();
    }
}
