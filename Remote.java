import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Remote {
    static int min;
    static String n;
    static ArrayList<Integer> broken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = br.readLine();
        int count = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (count == 0) {
            min = Math.min(n.length(), Math.abs(Integer.parseInt(n) - 100));
            System.out.println(min);
            return;
        }
        broken = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {
            broken.add(Integer.parseInt(st.nextToken()));
        }
        if (n.equals("100")) {
            System.out.println(0);
            return;
        }

        min = Math.abs(Integer.parseInt(n) - 100);

        search(0, "");
        System.out.println(min);
    }

    public static void search(int index, String number) {
        for (int i = 0; i < 10; i++) {
            if (!broken.contains(i)) {
                String newNum = number + Integer.toString(i);
                int gap = Math.abs(Integer.parseInt(n) - Integer.parseInt(newNum)) + newNum.length();
                min = min > gap ? gap : min;
                if (index < 6) {
                    search(index + 1, newNum);
                }
            }
        }
    }
}