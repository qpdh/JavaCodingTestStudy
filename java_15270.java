import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class java_15270 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 반 친구 수 N
        int N = Integer.parseInt(st.nextToken());
        // 관계도 수 M
        int M = Integer.parseInt(st.nextToken());

        boolean friendRelation[][] = new boolean[N][N];
        int pareCount[][] = new int[N][2];

        // i의 친구수 초기화
        for (int i = 0; i < N; i++) {
            pareCount[i][0] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            friendRelation[u - 1][v - 1] = true;
            friendRelation[v - 1][u - 1] = true;

            pareCount[u - 1][1] += 1;
            pareCount[v - 1][1] += 1;
        }

        boolean isPare[] = new boolean[N];

        int result = 0;

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        while (true) {
            // // 연결된 친구가 적은 순으로 정렬
            // Arrays.sort(pareCount, (num1, num2) -> {
            //     return Integer.compare(num1[1], num2[1]);
            // });

            int nowIndex = -1;

            for (int i = 0; i < N; i++) {
                if (pareCount[i][1] <= 0)
                    continue;

                nowIndex = i;
                break;
            }

            // 연결이 더이상 없으면 종료
            if (nowIndex == -1)
                break;

            // 연결된 친구 수가 적은 순서대로 비교
            int iPerson = pareCount[nowIndex][0];

            for (int j = 0; j < N; j++) {
                // jPerson 친구가 짝궁이 없고, iPerson과 jPerson가 친구일 경우
                int jPerson = pareCount[j][0];
                if (isPare[jPerson] == false && friendRelation[iPerson][jPerson] == true) {
                    if (isPare[iPerson] == true)
                        continue;

                    // System.out.println(iPerson + "과 " + jPerson + "가 이어짐");

                    pareCount[nowIndex][1] = 0;
                    pareCount[j][1] = 0;

                    // 연결 줄이기
                    for (int k = 0; k < N; k++) {
                        if (friendRelation[k][jPerson]) {
                            friendRelation[k][jPerson] = false;
                            pareCount[k][1] -= 1;
                        }

                        if (friendRelation[k][iPerson]) {
                            friendRelation[k][iPerson] = false;
                            pareCount[k][1] -= 1;
                        }
                    }

                    isPare[jPerson] = true;
                    isPare[iPerson] = true;

                    result += 2;
                    // bw.write(result+"\n");
                    break;
                }

                // System.out.println(minPare);
            }
        }

        // 로봇춤 1명 더할지 말지
        result = (N > result) ? result + 1 : result;

        bw.write(result + "\n");
        bw.flush();

        bw.close();
        br.close();

    }
}
