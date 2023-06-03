import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class java_2517 {
    static int N;
    static int tree[];

    static class Pair {
        int first, second;

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public Pair() {
        }

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    // (i & -i) 가장 우측 비트의 값 저장
    static int sum(int i) {
        int result = 0;
        while (i > 0) {
            result += tree[i];
            i -= (i & -i);
        }
        return result;
    }

    static void update(int i, int num) {
        while (i <= N) {
            tree[i] += num;
            i += (i & -i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // N명 데이터 생성
        Pair people[] = new Pair[N];

        // 데이터 저장
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(br.readLine());

            people[i] = new Pair(k, i + 1);
        }

        // 능력순으로 정렬
        Arrays.sort(people, new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                // TODO Auto-generated method stub
                return o1.first - o2.first;
            }

        });

        // 값을 압축
        for (int i = 0; i < people.length; i++) {
            people[i].first = i + 1;
        }

        // 실제 등수대로 정렬
        // => 압축된 실제 등수
        Arrays.sort(people, new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                // TODO Auto-generated method stub
                return o1.second - o2.second;
            }

        });

        // for (Pair p : people) {
        // System.out.println("능력치 : " + p.first + "등수 : " + p.second);
        // }

        // 최종 등수 = 내 등수 - 앞에 있는 사람 중 실력이 낮은 사람

        // 앞에 있는 사람 중 실력이 낮은 사람의 수를 저장하는 트리
        tree = new int[500005];
        for (int i = 0; i < people.length; i++) {
            int tmp = people[i].first;

            int result = i + 1 - sum(tmp - 1);

            System.out.println(result);

            update(tmp, 1);
        }

        // 브루트 포스
        // for (int i = 1; i < N; i++) {
        // // 몇 명을 재낄 수 있는가?
        // int count = 0;
        // for (int j = 0; j < i; j++) {
        // if (player[i] > player[j]) {
        // count += 1;
        // }
        // }
        // result[i] -= count;
        // }

        // for (int p : result) {
        // System.out.println(p);
        // }

        br.close();
        bw.close();
    }
}
