package 동적_계획법_테크닉;

import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class 지니어스 {
    static int N, K;
    static int T[][], length[];

    static List<Double> getProb1() {
        // [time][song]=time분 후에 song번 노래가 시작할 확률
        double cache[][] = new double[5][50];
        cache[0][0] = 1.0;

        for (int time = 1; time <= K; time++) {
            for (int song = 0; song < N; song++) {
                double prob = cache[time % 5][song];
                prob = 0;
                for (int last = 0; last < N; last++) {
                    prob += cache[(time - length[last] + 5) % 5][last] * T[last][song];
                }

            }
        }

        List<Double> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            result.add(0d);
        }

        for (int song = 0; song < N; song++) {
            // song 번 노래가 재생되고 있을 확률을 계산한다.
            for (int start = K - length[song] + 1; start <= K; start++) {
                result.set(song, result.get(song) + cache[start % 5][song]);
            }
        }

    }

    public static void main(String[] args) {

    }
}
