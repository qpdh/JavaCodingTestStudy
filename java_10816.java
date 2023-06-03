import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.HashMap;
import java.util.StringTokenizer;

public class java_10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());

            if (hashMap.get(number) == null) {
                hashMap.put(number, 1);
            } else {
                hashMap.put(number, hashMap.get(number) + 1);
            }
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int searchNumber = Integer.parseInt(st.nextToken());
            bw.write(hashMap.getOrDefault(searchNumber, 0) + " ");

        }
        bw.write("\n");

        br.close();
        bw.close();
    }
}
