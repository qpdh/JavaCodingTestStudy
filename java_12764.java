import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class java_12764 {
    static int N;
    static List<Person> personList;

    static class Person implements Comparable<Person> {
        int startTime;
        int endTime;

        public Person(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Person o) {
            return this.startTime - o.startTime;
        }
    }

    static class Computer implements Comparable<Computer> {
        static int globalIndex = 0;
        int endTime;
        int index;

        @Override
        public int compareTo(Computer o) {
            // 끝나는 시간 대비
            // 인덱스가 작은 것 우선

            return endTime - o.endTime;
        }

        public Computer(int endTime) {
            this.endTime = endTime;
            this.index = globalIndex++;

        }

    }

    static int solution() {
        int result = 0;

        List<Integer> countList = new ArrayList<>();

        Collections.sort(personList);

        PriorityQueue<Computer> computerQueue = new PriorityQueue<>();
        PriorityQueue<Computer> selectedQueue = new PriorityQueue<>(
                (o1, o2) -> ((Computer) o1).index - ((Computer) o2).index);

        computerQueue.add(new Computer(0));
        countList.add(0);

        for (int i = 0; i < N; i++) {
            Person person = personList.get(i);

            // 기존 컴퓨터에 추가
            while (!computerQueue.isEmpty()) {
                Computer selectedComputer = computerQueue.peek();
                if (!(selectedComputer.endTime > person.startTime)) {
                    selectedQueue.add(computerQueue.poll());
                    continue;
                }
                break;
            }

            if (!selectedQueue.isEmpty()) {
                Computer selectedComputer = selectedQueue.poll();
                selectedComputer.endTime = person.endTime;
                computerQueue.add(selectedComputer);
                countList.set(selectedComputer.index, countList.get(selectedComputer.index) + 1);
                continue;
            }

            countList.add(1);
            computerQueue.add(new Computer(person.endTime));
        }


        System.out.println(countList.size());

        for (int i = 0; i < countList.size(); i++) {
            System.out.print(countList.get(i) + " ");
        }
        System.out.println();

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        personList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            Person person = new Person(startTime, endTime);

            personList.add(person);

        }
        solution();

        br.close();
    }
}
