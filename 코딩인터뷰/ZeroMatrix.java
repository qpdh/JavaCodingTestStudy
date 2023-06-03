package 코딩인터뷰;

import java.util.HashSet;
import java.util.Set;

public class ZeroMatrix {

    static int[][] zeroMatrix(int[][] matrix) {
        // 0이 있는 행렬 위치를 저장하자
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();

        // 0이 있는 위치 저장
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }

        // 0이 있는 위치 기반으로 속한 행와 열의 모든 원소를 0으로 만든다.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rowSet.contains(i) || colSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int arr[] : matrix) {
            for (int number : arr) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] tmp = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        zeroMatrix(tmp);
    }
}
