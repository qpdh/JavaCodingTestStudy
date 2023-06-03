import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 현2 {
    static int maxRound;
    static int childCount;
    static String list[];

    static List<String> result = new ArrayList<>();

    // [start,end)
    static void zip(int start, int length, StringBuffer sb) {
        if (length == 1) {
            if (list[start].charAt(0) != '0') {
                return;
            } else {
                result.add(sb.toString());
                return;
            }
        }
        // 범위 내의 요소가 전부 0이라면
        boolean isZero = true;

        for (int i = start; i < start + length; i++) {
            if (list[i].charAt(0) != '0') {
                isZero = false;
                break;
            }
        }

        if (isZero) {
            result.add(sb.toString());
            return;
        }

        else {
            int nextLength = length / childCount;
            int count = 0;
            for (int nextStart = start; nextStart < start + length; nextStart += (length / childCount)) {
                zip(nextStart, nextLength, sb.append(count));
                sb.deleteCharAt(sb.length() - 1);
                count++;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        // 1
        // 2~5
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        maxRound = Integer.parseInt(st.nextToken());
        childCount = Integer.parseInt(st.nextToken());

        // 앞에서 childCount 갯수 만큼 계속 묶는다.
        // 0이면 계속 묶어서 올라감
        list = st.nextToken().split(",");

        zip(0, list.length, new StringBuffer("1"));

        for (String tmp : result) {
            System.out.println(tmp);
        }

        br.close();
    }
}
