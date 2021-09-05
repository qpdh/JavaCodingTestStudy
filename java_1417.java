import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class java_1417 {
    static class Person {
        int number;
        int vote;

        public Person(int number, int vote) {
            this.number = number;
            this.vote = vote;
        }
    }

    static PriorityQueue<Person> people;
    static Person dasom;

    public static int commitee() {
        int count = 0;

        // 기저사례 : 1명 밖에 없으면 매수할 필요가 없음
        if (people.size() == 0) {
            return count;
        }

        // 조건을 만족할 때 까지 계속 매수하기
        while (true) {
            Person nowPerson = people.poll();

            // 유일한 득표수를 찍어야 함
            if (dasom.vote > nowPerson.vote) {
                break;
            }

            ++count;
            nowPerson.vote -= 1;
            // 다솜이 득표 수 1 증가
            dasom.vote += 1;

            people.add(nowPerson);

        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 투표수의 내림차순 우선순위 큐
        people = new PriorityQueue<>(new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                return o2.vote - o1.vote;
            }

        });

        // 다솜 데이터 삽입
        dasom = new Person(1, Integer.parseInt(br.readLine()));

        // 데이터 삽입
        for (int i = 0; i < N - 1; i++) {
            int vote = Integer.parseInt(br.readLine());
            people.add(new Person(i + 2, vote));
        }

        // 메소드 호출
        int ret = commitee();

        // 결과 출력
        bw.write(ret + "\n");

        br.close();
        bw.close();
    }
}
