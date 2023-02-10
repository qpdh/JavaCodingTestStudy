import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class java_1822 {
    static Set<Integer> aSet, bSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int nA = Integer.parseInt(st.nextToken());
        int nB = Integer.parseInt(st.nextToken());

        // A 집합 입력받기
        aSet = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nA; i++) {
            aSet.add(Integer.parseInt(st.nextToken()));
        }

        // B 집합 입력받기
        bSet = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nB; i++) {
            bSet.add(Integer.parseInt(st.nextToken()));
        }

        // A-B 를 구하자
        aSet.removeAll(bSet);

        // A-B의 크기
        int size = aSet.size();
        // 크기 출력
        System.out.println(size);
        if (size != 0) {
            for (int number : aSet) {
                System.out.print(number+" ");
            }
            System.out.println();
        }

        br.close();
    }
}
