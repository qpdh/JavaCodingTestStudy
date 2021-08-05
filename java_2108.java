import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class java_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int intArray[] = new int[N];
        for (int i = 0; i < N; ++i) {
            intArray[i] = Integer.parseInt(br.readLine());
        }

        // 산술평균
        double arithmeticMean = 0.0;
        for (int i = 0; i < N; ++i) {
            arithmeticMean += intArray[i];
        }
        arithmeticMean = Math.round(arithmeticMean / N);
        bw.write((int) arithmeticMean + "\n");

        // 중앙값
        Arrays.sort(intArray);
        bw.write(intArray[N / 2] + "\n");

        // 최빈값
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            try {
                hashMap.put(intArray[i], hashMap.get(intArray[i]) + 1);
            } catch (Exception e) {
                hashMap.putIfAbsent(intArray[i], 1);
            }
        }

        ArrayList<Integer> keyArray = new ArrayList<>(hashMap.keySet());

        Collections.sort(keyArray, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (hashMap.get(o1) == hashMap.get(o2)) {
                    return o1 - o2;
                } else {
                    return hashMap.get(o2) - hashMap.get(o1);
                }
            }

        });

        if (hashMap.size() != 1 && hashMap.get(keyArray.get(0)) == hashMap.get(keyArray.get(1))) {
            bw.write(keyArray.get(1) + "\n");
        } else {
            bw.write(keyArray.get(0) + "\n");
        }

        // 범위
        int range = intArray[N - 1] - intArray[0];
        bw.write(range + "\n");

        bw.close();
        br.close();
    }
}
