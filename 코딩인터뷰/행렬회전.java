package 코딩인터뷰;

public class 행렬회전 {
    static int[][] rotateMatrix(int[][] matrix) {
        int result[][] = new int[matrix.length][matrix.length];

        // 1. transpose
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // 행
        for (int i = 0; i < matrix.length; i++) {
            // 열
            for (int j = 0; j < matrix[i].length / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[i].length - j - 1];
                matrix[j][i] = tmp;
            }

        }

        return result;
    }

    public static void main(String[] args) {

    }
}
