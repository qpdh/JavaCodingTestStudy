import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class 조이스틱 {
    final static int INF = 987654321;
    static int index = 0;
    static int count = 0;

    public static int solution(String name) {
        index = 0;
        count = 0;
        char[] checkName = new char[name.length()];
        for (int i = 0; i < name.length(); i++) {
            checkName[i] = 'A';
        }

        // 방향을 결정하기
        // 단 한번 역순이면 방향을 결정하지 않는다.
        count += checkDirection(name);

        // 현재위치에서 알파벳 맞추기
        for (int i = 0; i < name.length(); i++) {
            count += vertical(name, i, checkName);
        }

        return count;
    }

    public static boolean isFinish(String name, char[] checkName) {
        if (name.equals(String.valueOf(checkName))) {
            return true;
        } else {
            return false;
        }
    }

    public static int checkDirection(String name) {

        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != 'A') {
                vector.add(i);
            }
        }

        int minCount = INF;

        if (vector.size() > 0) {
            for (int i = 0; i < vector.size() - 1; i++) {

                int tempCount = vector.get(i) * 2 + name.length() - vector.get(i + 1);

                if (tempCount < minCount) {
                    minCount = tempCount;
                }
            }

            for(int i=vector.size()-1;i>=1;i--){
                int tempCount = (name.length()-vector.get(i))*2 + vector.get(i-1);
                if (tempCount < minCount) {
                    minCount = tempCount;
                }

            }

            if (vector.get(vector.size() - 1) < minCount) {
                minCount = vector.get(vector.size() - 1);
            }

            if (name.length() - vector.get(0) < minCount) {
                minCount = name.length() - vector.get(0);
            }
        } else {
            minCount = 0;
        }

        // 1. 오른쪽으로 쭉 가기
        // 2. 처음부터 왼쪽으로 가기
        // 3. 오른쪽 찍고 돌리기
        // 4. 왼쪽으로 가다가 오른쪽으로 돌리기

        return minCount;

    }

    public static int vertical(String name, int index, char[] checkName) {
        // 왼쪽이 이득인가? 오른쪽이 이득인가?
        // 알파벳 26개 13까지는 이득임
        // A - N 까지는 정방향
        // M - Z 까지는 역방향

        checkName[index] = name.charAt(index);

        // 정방향으로 돌리기
        if ('N' - name.charAt(index) >= 0) {
            return name.charAt(index) - 'A';
        }
        // 역방향으로 돌리기
        else {
            return 'Z' - name.charAt(index) + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        bw.write(solution("JEROEN") + " : 56\n");
        bw.write(solution("JAN") + " : 23\n");
        bw.write(solution("BBBAAAB") + " : 9\n");
        bw.write(solution("ABABAAAAABA") + " : 11\n");
        bw.write(solution("BABAAAAAAAAAB") + " : 8\n");
        bw.write(solution("AAB") + " : 2\n");
        bw.write(solution("BBBBAAAABA") + " : 13\n");
        bw.write(solution("BBBBAAAAAB") + " : 12\n");

        bw.write(solution("AABAAAAAAABBB") + " : 11\n");

        bw.write(solution("AABAAAAAAABBB") + " : 11\n");

        bw.write(solution("ABABAAAAAAABA") + " : 11\n");

        bw.write(solution("ABAAAAAAABA") + " : 6\n");
        bw.write(solution("AAAAAAAAAAAAAAAAAA") + " : 0\n");


        bw.write(solution("ABBAAAAAAAAAB") + " : 7\n");
        bw.write(solution("BABAAAAAAAAAAAABAAAB") + " : 13\n");
        br.close();
        bw.close();
    }
}
