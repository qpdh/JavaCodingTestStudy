package 분할_정복;

public class 행렬의_거듭제곱 {

    // size 크기의 항등행렬 반환하는 메소드
    static int[][] identityMatrix(int size) {
        int matrix[][] = new int[size][size];

        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
        }

        return matrix;
    }

    // 두 행렬을 곱하는 메소드
    static int[][] multiplyMatrix(int[][] aMatrix, int[][] bMatrix) {
        int[][] resultMatrix = new int[aMatrix.length][aMatrix.length];

        // 두 행렬의 곱
        for (int i = 0; i < aMatrix.length; i++) {
            for (int j = 0; j < aMatrix.length; j++) {
                int sum = 0;

                for (int k = 0; k < aMatrix.length; k++) {
                    int tmp = (aMatrix[i][k] * bMatrix[k][j]);
                    sum += tmp;
                }

                resultMatrix[i][j] = sum;
            }
        }

        return resultMatrix;
    }

    // matrix 행렬을 m제곱한 값을 반환하는 메소드
    static int[][] matrixPow(int[][] matrix, int m) {
        if (m == 0) {
            return identityMatrix(matrix.length);
        }

        // m이 홀수인 경우
        if (m % 2 != 0) {
            return multiplyMatrix(matrix, matrixPow(matrix, m - 1));
        }

        // 문제를 반으로 나누기
        int halfMatrix[][] = matrixPow(matrix, m / 2);

        // 제곱하기
        return multiplyMatrix(halfMatrix, halfMatrix);
    }

    public static void main(String[] args) {

    }
}
