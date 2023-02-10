import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class java_10830 {
    static int N;
    static long B;

    static int original[][];

    final static int MOD = 1000;

    static int[][] matrixMultiply(int matrixA[][], int matrixB[][]) {
        int nextMatrix[][] = new int[N][N];

        // 제곱하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;

                for (int k = 0; k < N; k++) {
                    int tmp = (matrixA[i][k] * matrixB[k][j]) % MOD;
                    sum += tmp;
                }

                nextMatrix[i][j] = sum % MOD;
            }
        }

        return nextMatrix;
    }

    static void printResult(int result[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(result[i][j] % MOD + " ");
            }
            System.out.println();
        }
    }

    // 분할정복을 이용한 행렬제곱
    static int[][] calcMatrix(int matrix[][], long exp) {
        // 1은 그냥 반환
        if (exp == 1) {
            return matrix;
        }

        // 2는 그냥 제곱하기
        if (exp == 2) {
            return matrixMultiply(matrix, matrix);
        }

        int resultMatrix[][] = calcMatrix(matrix, exp / 2);
        resultMatrix = matrixMultiply(resultMatrix, resultMatrix);

        if (exp % 2 != 0) {
            resultMatrix = matrixMultiply(resultMatrix, original);
        }

        return resultMatrix;
    }

    static void solution() {
        int result[][] = calcMatrix(original, B);

        printResult(result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        original = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                original[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 제곱하는 메소드
        solution();

        br.close();
    }
}
