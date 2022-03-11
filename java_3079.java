import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
public class java_3079 {
    
    public static final long INF = 987654321;
    // 만들 수 있는지 체크하는 함수
    public static boolean canMake(long v, int n, int[] times) {
        long count = 0;

        for (int i = 0; i < times.length; i++) {
            count += v / times[i];
            if (count >= n)
                break;
        }

        if (count >= n) {
            return true;
        }

        return false;
    }

    public static long bin(long low, long high, int n, int[] times, long res) {
        if (low > high) {
            return res;
        }
        // low + high / 2 를 만들 수 있는가?
        // 가능하다면
        // high 값을 low+high/2 로 바꾼다
        // 불가능하다면
        // min 값을 low+high/2로 바꾼다
        // 종료는 언제?
        // low == high 라면
        //

        long mid = (low + high) / 2;

        if (canMake(mid, n, times)) {
            return bin(low, mid-1, n, times,mid);
        } else {
            return bin(mid + 1, high, n, times,res);
        }
    }

    public static long solution(int n, int[] times) {
        Arrays.sort(times);

        long answer = bin(1, (long)times[times.length - 1] * n, n, times,INF);

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] times = new int[N];
        for(int i=0;i<N;i++){
            times[i] = Integer.parseInt(br.readLine());
        }

        bw.write(solution(M, times)+"");

        br.close();
        bw.close();
        
        
    }
}
