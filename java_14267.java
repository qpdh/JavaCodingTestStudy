import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class java_14267 {
    static int n, m;
    static List<List<Integer>> tree;

    static int result[];

    static void order(int index, int score) {
        result[index] += score;

        for (int i = 0; i < tree.get(index).size(); i++) {
            order(tree.get(index).get(i), result[index]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        // 루트는 없애기
        st.nextToken();
        for (int i = 1; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken()) - 1;
            tree.get(parent).add(i);
        }

        result = new int[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            result[index] += w;
        }

        order(0, 0);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();

        br.close();
    }
}
