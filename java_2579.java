import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_2579 {
    static int cache[][];
    static int stairList[];

    // index 위치에서 계단을 타기 시작할 때 가장 최대값
    public static int maxValue(int index, int continuityCount) {
        // 3연속 같은 계단을 탔다면?
        // 처리 안함
        if (continuityCount == 3) {
            return 0;
        }

        // index 가 마지막 위치를 넘어간다면?
        // 처리 안함
        if (index >= cache.length) {
            return 0;
        }

        if(index==cache.length-1){
            return stairList[index];
        }

        if(cache[index][continuityCount]!=-1){
            return cache[index][continuityCount];
        }

        // 마지막 계단은 무조건 밟아야 함
        if(index==cache.length-2){
            if(continuityCount==2){
                return 0;
            }
        }

        // 마지막 위치를 넘어가지 않고,
        // 3연속 되지 않게 계단 타기

        // 1계단 또는 2계단 타기
        cache[index][continuityCount] = Math.max(cache[index][continuityCount],
                stairList[index] + Math.max(maxValue(index + 1, continuityCount + 1), maxValue(index + 2, 1)));
        // System.out.println(index + "cache[index] : " + cache[index]);

        // return cache[index][continuityCount];
        return cache[index][continuityCount];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numStairs = Integer.parseInt(br.readLine());

        cache = new int[numStairs][3];
        stairList = new int[numStairs];

        for (int i = 0; i < numStairs; ++i) {
            cache[i][0] = -1;
            cache[i][1] = -1;
            cache[i][2] = -1;
            stairList[i] = Integer.parseInt(br.readLine());
        }

        // 처음에 1계단 2계단 처리
        int ret = Math.max(maxValue(0,1),maxValue(1,1));

        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
