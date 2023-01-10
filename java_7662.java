import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class java_7662 {
    static int T;
    static int k;

    // Key에 따라 정렬
    // 1 삽입, (1,1), 1 삽입(2), (1,2)
    // 삭제 -> (1,1)
    // Dict -> 삭제 O(1)
    // 삽입 -> O(lgN)
    static TreeMap<Integer, Integer> treeMap;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            treeMap = new TreeMap<>();

            k = Integer.parseInt(br.readLine());

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                int number = Integer.parseInt(st.nextToken());

                if (command == 'I') {
                    treeMap.put(number, treeMap.getOrDefault(number, 0) + 1);
                    continue;
                }

                if (command == 'D') {
                    if (treeMap.isEmpty()) {
                        continue;
                    }
                    if (number == 1) {
                        int maxValue = treeMap.lastKey();
                        treeMap.put(maxValue, treeMap.get(maxValue) - 1);

                        if (treeMap.get(maxValue) == 0) {
                            treeMap.remove(maxValue);
                        }
                        continue;
                    }

                    if (number == -1) {
                        int minValue = treeMap.firstKey();
                        treeMap.put(minValue, treeMap.get(minValue) - 1);

                        if (treeMap.get(minValue) == 0) {
                            treeMap.remove(minValue);
                        }
                        continue;
                    }
                }
            }

            if (treeMap.isEmpty()) {
                System.out.println("EMPTY");
            } else {

                System.out.printf("%d %d\n", treeMap.lastKey(), treeMap.firstKey());
            }
        }

        br.close();

    }
}
