import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 현6 {
    static ArrayList<Integer> seatList;

    static void solution() {
        int preNumber = seatList.get(0);
        int nowNumber = seatList.get(1);

        int minDiff = nowNumber - preNumber;
        for (int i = 2; i < seatList.size(); i++) {

            int checkDiff = seatList.get(i) - seatList.get(i - 1);

            // 최소값 갱신
            if (checkDiff < minDiff) {
                preNumber = seatList.get(i - 1);
                nowNumber = seatList.get(i);
                minDiff = checkDiff;
            }
        }

        System.out.println(preNumber + " " + nowNumber);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        seatList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            seatList.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(seatList);

        solution();

        br.close();
    }
}
