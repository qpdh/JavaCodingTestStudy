import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import java.util.StringTokenizer;
import java.util.Map.Entry;

public class java_21942 {
    static int fine;
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static long rentalMinute;

    static class Robot {
        String name;
        String rentalDate;
        String returnDate;
        Date d;

        public Robot(String name, String rentalDate) {
            this.name = name;
            this.rentalDate = rentalDate;
            try {
                d = sdf.parse(rentalDate);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // 반납 날짜가 들어오면
        // 벌금을 계산하는 메소드
        public long calFine(String returnDate) {
            // 벌금 계산
            Date rd = null;

            try {
                rd = sdf.parse(returnDate);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            long diffMin = (rd.getTime() - d.getTime()) / 60000;

            // System.out.println("분 차이 : " + diffMin);

            if (diffMin - rentalMinute > 0) {
                return (diffMin - rentalMinute) * fine;
            }

            return 0;
        }
    }

    static class Person {
        String personName;
        long personFine;
        HashMap<String, Robot> rentalList;

        public Person(String name) {
            personName = name;
            personFine = 0;
            rentalList = new HashMap<>();
        }

        // HashMap 안에 데이터가 없다면
        // 데이터 삽입

        // 데이터가 존재한다면 반납
        public void inputOrReturnRobot(String name, String date) {
            // 있다면 반납
            if (rentalList.containsKey(name)) {
                personFine += rentalList.get(name).calFine(date);
                rentalList.remove(name);
            }
            // 없다면 대여, 추가
            else {
                rentalList.put(name, new Robot(name, date));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer rentalFormat = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(rentalFormat.nextToken());

        // 대여 기간을 분단위로 전환
        String rentalDate = rentalFormat.nextToken();
        rentalMinute = Long.parseLong(rentalDate.substring(0, 3)) * 24 * 60;
        rentalMinute += Long.parseLong(rentalDate.substring(4, 6)) * 60;
        rentalMinute += Long.parseLong(rentalDate.substring(7));

        //System.out.println("빌릴 수 있는 분 : " + rentalMinute);

        fine = Integer.parseInt(rentalFormat.nextToken());

        HashMap<String, Person> personList = new HashMap<>();

        // 데이터 삽입
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String ymd = st.nextToken();
            String hm = st.nextToken();
            String robotName = st.nextToken();
            String personName = st.nextToken();

            // 사람이 없다면 사람 추가, 대여 처리
            // 사람이 있다면 대여/반납 처리
            if (!personList.containsKey(personName)) {
                personList.put(personName, new Person(personName));
            }

            personList.get(personName).inputOrReturnRobot(robotName, ymd + " " + hm);
        }

        // 결과 데이터 정리
        ArrayList<Person> resultList = new ArrayList<>();
        for (Entry<String, Person> e : personList.entrySet()) {
            if (e.getValue().personFine > 0) {
                resultList.add(e.getValue());
            }
        }

        Collections.sort(resultList, new Comparator<Person>(){

            @Override
            public int compare(Person o1, Person o2) {
                return o1.personName.compareTo(o2.personName);
                
            }
            
        });

        // 결과 배열이 없다면...
        if (resultList.isEmpty()) {
            bw.write("-1\n");
        }

      // 결과가 존재 한다면...
        else {
            for (int i = 0; i < resultList.size(); i++) {
                bw.write(resultList.get(i).personName + " " + resultList.get(i).personFine + "\n");
            }
        }

        br.close();
        bw.close();
    }

}
