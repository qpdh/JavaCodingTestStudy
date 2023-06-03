package SKPlanet;

import java.util.ArrayList;
import java.util.List;

public class p1 {

    class Schedule {
        int minute;
        int breadCount;

        public Schedule(int minute, int breadCount) {
            this.minute = minute;
            this.breadCount = breadCount;
        }
    }

    List<Integer> list = new ArrayList<>();


    // XX:YY 를 분단위로 바꿔주는 메소드
    public int changeTime(String time) {
        try{

        }catch(ArrayIndexOutOfBoundsException)
        int resultMinute = 0;

        String timeList[] = time.split(":");
        resultMinute += Integer.parseInt(timeList[0]) * 60;
        resultMinute += Integer.parseInt(timeList[1]);

        return resultMinute;
    }

    public static void main(String[] args) {

    }
}
