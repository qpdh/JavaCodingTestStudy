package 최단_경로_알고리즘;

import java.util.Arrays;
import java.util.List;

public class 플로이드 {
    // u에서 v까지 가는 가중치, 간선이 없으면 INF
    static int board[][];

    // u에서 v까지 가는 최단 경로를 경유하는 점 중 가장 번호가 큰 정점
    static int via[][];

    static void floyd() {
        // 자기 자신의 거리는 0
        for (int i = 0; i < board.length; i++) {
            board[i][i] = 0;
            Arrays.fill(via[i], -1);
        }

        // 거리 계산하기
        for (int k = 0; k < board.length; k++) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    via[i][j] = k;
                    board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
                }
            }
        }
    }

    static void reconstruct(int u, int v, List<Integer> path) {
        // 직접 가는게 가장 빠른 경우
        if (via[u][v] == -1) {
            path.add(u);
            if (u != v) {
                path.add(v);
            }
        }

        else {
            // u->v 경로 중 번호가 가장 큰 정점
            int w = via[u][v];
            // u->w 의 경로 찾기
            reconstruct(u, w, path);

            // w가 겹치므로 한 번 제거
            path.remove(path.size() - 1);

            // w->v 의 경로 찾기
            reconstruct(w, v, path);
        }
    }

    public static void main(String[] args) {

    }
}
