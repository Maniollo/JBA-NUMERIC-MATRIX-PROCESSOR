package masmar.home.jba.matrixprocessor;

import java.util.Scanner;

class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Matrix A = readMatrixFromInput();
        int factor = SCANNER.nextInt();

        try {
            A.multiplyByNumber(factor);
            System.out.println(A.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR");
        }
    }

    private static Matrix readMatrixFromInput() {
        int m1 = SCANNER.nextInt();
        int n1 = SCANNER.nextInt();
        int[][] matrixOne = new int[m1][n1];
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                matrixOne[i][j] = SCANNER.nextInt();
            }
        }
        return new Matrix(matrixOne);
    }
}
