    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayDeque;
    import java.util.ArrayList;
    import java.util.Deque;
    import java.util.StringTokenizer;

    public class java_23030 {
        // N개의 노선
        static int N;
        // M개의 환승역
        static int M;
        // K개의 사용자 요청 갯수
        static int K;

        static ArrayList<ArrayList<Pair>> transferInformation;

        static class Pair {
            // 환승 노선 : 없다면 -1
            int line;
            // 환승역 : 없다면 -1
            int station;
            int cost;

            public Pair(int line, int station, int cost) {
                this.line = line;
                this.station = station;
                this.cost = cost;
            }

            public Pair(int line, int station) {
                this.line = line;
                this.station = station;
            }
        }

        // 시작노선, 시작역, 환승비용이 주어지면 결과값을 반환
        static int findRoute(int startLine, int startStation, int endLine, int endStation, int transferCost) {
            // 경로 저장
            ArrayList<ArrayList<Integer>> dist = new ArrayList<>();
            for (int i = 0; i < transferInformation.size(); i++) {
                dist.add(new ArrayList<>());
                for (int j = 0; j < transferInformation.get(i).size(); j++) {
                    dist.get(i).add(Integer.MAX_VALUE);
                }
            }

            // 최초 위치 0 처리
            dist.get(startLine).set(startStation, 0);

            Deque<Pair> deque = new ArrayDeque<>();
            deque.add(new Pair(startLine, startStation, 0));

            while (!deque.isEmpty()) {
                Pair now = deque.poll();

                // 원하는 위치에 도착한 경우
                if (now.line == endLine && now.station == endStation) {
                    continue;
                }

                else {
                    // +1로 가는 경우
                    // +1로 갈 수 있는 지 확인
                    // +1로 가는게 비용이 더 저렴한지 확인
                    if (now.station + 1 < transferInformation.get(now.line).size()) {
                        if (now.cost + 1 < dist.get(now.line).get(now.station + 1)) {
                            dist.get(now.line).set(now.station + 1, now.cost + 1);
                            deque.add(new Pair(now.line, now.station + 1, now.cost + 1));
                        }
                    }

                    // -1로 가는 경우
                    // -1로 갈 수 있는 지 확인
                    // -1로 가는게 비용이 더 저렴한지 확인
                    if (0 <= now.station - 1) {
                        if (now.cost + 1 < dist.get(now.line).get(now.station - 1)) {
                            dist.get(now.line).set(now.station - 1, now.cost + 1);
                            deque.add(new Pair(now.line, now.station - 1, now.cost + 1));
                        }
                    }

                    // 환승하는 경우
                    if (transferInformation.get(now.line).get(now.station).line != -1) {
                        //
                        int transferLine = transferInformation.get(now.line).get(now.station).line;
                        int transferStation = transferInformation.get(now.line).get(now.station).station;

                        if (now.cost + transferCost < dist.get(transferLine).get(transferStation)) {
                            dist.get(transferLine).set(transferStation, now.cost + transferCost);
                            deque.add(new Pair(transferLine, transferStation, now.cost + transferCost));
                        }
                    }
                }

            }

            return dist.get(endLine).get(endStation);
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // 출발지, 도착지를 입력하면 최단경로로 이동했을 때 소요시간을 알려줌

            // N개의 노선
            N = Integer.parseInt(br.readLine());

            transferInformation = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                transferInformation.add(new ArrayList<>());
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                // 역의 갯수
                int countStation = Integer.parseInt(st.nextToken());
                for (int j = 0; j < countStation; j++) {
                    transferInformation.get(i).add(new Pair(-1, -1));
                }
            }
            // 인접한 역은 1의 시간이 걸림

            // M개의 환승역 존재
            M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int U1 = Integer.parseInt(st.nextToken()) - 1;
                int U2 = Integer.parseInt(st.nextToken()) - 1;

                int V1 = Integer.parseInt(st.nextToken()) - 1;
                int V2 = Integer.parseInt(st.nextToken()) - 1;

                transferInformation.get(U1).get(U2).line = V1;
                transferInformation.get(U1).get(U2).station = V2;

                transferInformation.get(V1).get(V2).line = U1;
                transferInformation.get(V1).get(V2).station = U2;
            }

            // K개의 요청
            K = Integer.parseInt(br.readLine());
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                // 환승하는데 걸리는 시간 T
                int T = Integer.parseInt(st.nextToken());

                int U1 = Integer.parseInt(st.nextToken()) - 1;
                int U2 = Integer.parseInt(st.nextToken()) - 1;

                int V1 = Integer.parseInt(st.nextToken()) - 1;
                int V2 = Integer.parseInt(st.nextToken()) - 1;

                int result = findRoute(U1, U2, V1, V2, T);
                System.out.println(result);
            }

            // 다익스트라 사용

            br.close();
        }
    }
