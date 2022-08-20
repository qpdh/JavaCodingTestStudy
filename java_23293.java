import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;

public class java_23293 {

    static class Player {
        // 플레이어의 현재 위치
        int place;
        // 플레이어가 갖고 있는 아이템 목록
        // <아이템 번호, 아이템 갯수>
        HashMap<Integer, Integer> item;

        public Player() {
            item = new HashMap<>();
            place = 1;
        }

        public int getPlace() {
            return place;
        }

        public void setPlace(int place) {
            this.place = place;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 로그의 줄 수
        int T = Integer.parseInt(st.nextToken());
        // 플레이어의 수
        int N = Integer.parseInt(st.nextToken());

        Vector<Player> players = new Vector<>();
        for (int i = 0; i < N; i++) {
            players.add(new Player());
        }

        // 벤할 사람 목록
        TreeSet<Integer> result = new TreeSet();

        //
        TreeSet<Integer> illegalLogCount = new TreeSet();

        // 로그의 줄 수(T) 만큼 반복
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            // 로그 번호
            int number = Integer.parseInt(st.nextToken());
            // 플레이어 번호
            int playerNumber = Integer.parseInt(st.nextToken()) - 1;
            // 행동 코드
            char actCode = st.nextToken().charAt(0);

            // craft의 경우 1개의 인자를 더 받음
            int craftParam = -1;
            if (actCode == 'C') {
                craftParam = Integer.parseInt(st.nextToken());
            }
            // 행동 인자
            int actParam = Integer.parseInt(st.nextToken());

            // 규칙 1 : 플레이어가 현재 위치한 지역에서 얻을 수 없는 소재 아이템을 획득한 경우
            // 행동 코드가 F인가?
            //
            Player nowPlayer = players.get(playerNumber);

            switch (actCode) {
                // 이동 처리
                case 'M':
                    // // 같은 곳으로 이동하면 오류 발생
                    // if (nowPlayer.getPlace() == actParam) {
                    // System.out.println("ERROR!");
                    // }
                    nowPlayer.setPlace(actParam);
                    break;
                case 'F':
                    // 현재 위치와 파밍하는 위치가 다르면 오류발생
                    if (nowPlayer.getPlace() != actParam) {
                        // result.add(playerNumber);
                        illegalLogCount.add(number);
                    }
                    // 이전에 획득한 적이 있으면
                    // 기존 값에서 + 1

                    if (nowPlayer.item.get(actParam) != null) {
                        int itemCount = nowPlayer.item.get(actParam) + 1;
                        nowPlayer.item.put(actParam, itemCount);
                    }
                    // 이전에 획득한 적이 없으면 1개 추가
                    else {
                        nowPlayer.item.put(actParam, 1);
                    }

                    break;
                case 'C':
                    // 1개 이상 있어야 함
                    if (nowPlayer.item.get(actParam) != null) {
                        if (nowPlayer.item.get(actParam) >= 1) {
                            nowPlayer.item.put(actParam, nowPlayer.item.get(actParam) - 1);

                        } else {
                            illegalLogCount.add(number);
                        }

                    } else {
                        illegalLogCount.add(number);
                    }
                    if (nowPlayer.item.get(craftParam) != null) {
                        if (nowPlayer.item.get(craftParam) >= 1) {
                            nowPlayer.item.put(craftParam, nowPlayer.item.get(craftParam) - 1);
                        }
                        // 갯수가 모자르면
                        else {
                            // result.add(playerNumber);
                            illegalLogCount.add(number);
                        }
                        // 아이템이 존재하지 않으면

                    } else {
                        // result.add(playerNumber);
                        illegalLogCount.add(number);
                    }

                    break;
                case 'A':
                    Player targetPlayer = players.get(actParam - 1);
                    // 현재 플레이어 위치와 상대 플레이어의 위치가 다르면 오류 발생
                    if (nowPlayer.getPlace() != targetPlayer.getPlace()) {
                        result.add(playerNumber);
                        illegalLogCount.add(number);
                    }

                    break;
            }

        }

        if (illegalLogCount.isEmpty()) {
            bw.write("0\n");
        } else {
            bw.write(illegalLogCount.size() + "\n");
            for (Integer logNumber : illegalLogCount) {
                bw.write(logNumber + " ");
            }
            bw.write("\n");
        }

        if (result.isEmpty()) {
            bw.write("0\n");
        } else {
            bw.write(result.size() + "\n");
            for (Integer playerNumber : result) {
                bw.write((playerNumber + 1) + " ");
            }
            bw.write("\n");
        }

        br.close();
        bw.close();
    }
}
