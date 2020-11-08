package masmar.home.jba.matrixprocessor;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m1 = scanner.nextInt();
        int n1 = scanner.nextInt();
        int[][] matrixOne = new int[m1][n1];
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                matrixOne[i][j] = scanner.nextInt();
            }
        }

        Matrix A = new Matrix(matrixOne);

        int m2 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int[][] matrixTwo = new int[m2][n2];
        for (int i = 0; i < m2; i++) {
            for (int j = 0; j < n2; j++) {
                matrixTwo[i][j] = scanner.nextInt();
            }
        }

        Matrix B = new Matrix(matrixTwo);

        try {
            A.add(B);
            System.out.println(A.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR");
        }
    }
}
