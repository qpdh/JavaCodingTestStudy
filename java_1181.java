import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class java_1181 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TreeSet<String> treeSet = new TreeSet<>();

        int N = Integer.parseInt(br.readLine());

        // N 개 만큼 데이터 받기
        // set을 이용, 동시에 중복 제거
        for (int i = 0; i < N; i++) {
            treeSet.add(br.readLine());
        }

        Iterator it = treeSet.iterator();
        String stringArray[] = new String[treeSet.size()];

        int i = 0;
        while (it.hasNext()) {
            stringArray[i++] = it.next().toString();
        }

        Arrays.sort(stringArray, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                // TODO Auto-generated method stub
                return o1.length() - o2.length();
            }

        });

        for (int j = 0; j < stringArray.length; j++) {
            bw.write(stringArray[j] + "\n");
        }

        bw.close();
        br.close();
    }
}
