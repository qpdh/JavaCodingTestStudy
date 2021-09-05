import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class java_5585 {

    public static int calChange(int money) {
        int count = 0;

        int change = 1000 - money;

        // 500엔을 먼저 계산하지 않는 경우..
        // change/500 + change % 500/100 ->개 
        // <= 
        // 100엔을 계산하면 change/100 -> 개
        count += change / 500;
        count += change % 500 / 100;
        count += change % 100 / 50;
        count += change % 50 / 10;
        count += change % 10 / 5;
        count += change % 5 / 1;
        count += change % 1;

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int money = Integer.parseInt(br.readLine());

        //
        int ret = calChange(money);

        bw.write(ret+"\n");

        br.close();
        bw.close();
    }
}
