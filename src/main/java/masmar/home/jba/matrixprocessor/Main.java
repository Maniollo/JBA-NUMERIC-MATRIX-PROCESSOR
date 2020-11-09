package masmar.home.jba.matrixprocessor;

import java.util.Scanner;

class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            displayMenu();
            int option = prompt();
            switch (option) {
                case 1:
                    addMatrices();
                    break;
                case 2:
                    multiplyByConstant();
                    break;
                case 3:
                    multiplyByMatrix();
                    break;
                default:
                    return;
            }
        }
    }

    private static void multiplyByMatrix() {
        Matrix A = readMatrixFromInput();
        Matrix B = readMatrixFromInput();
        try {
            Matrix C = A.multiplyByMatrix(B);
            System.out.println("The result is:");
            System.out.println(C.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed.");
        }
    }

    private static void multiplyByConstant() {
        Matrix A = readMatrixFromInput();
        int factor = SCANNER.nextInt();

        try {
            System.out.println("The result is:");
            A.multiplyByNumber(factor);
            System.out.println(A.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed.");
        }
    }

    private static void addMatrices() {
        Matrix A = readMatrixFromInput();
        Matrix B = readMatrixFromInput();
        try {
            A.add(B);
            System.out.println("The result is:");
            System.out.println(A.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed.");
        }
    }

    private static int prompt() {
        System.out.println("Your choice: ");
        return SCANNER.nextInt();
    }

    private static void displayMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("0. Exit");
    }

    private static Matrix readMatrixFromInput() {
        int m1 = SCANNER.nextInt();
        int n1 = SCANNER.nextInt();
        double[][] matrixOne = new double[m1][n1];
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                matrixOne[i][j] = SCANNER.nextInt();
            }
        }
        return new Matrix(matrixOne);
    }
}
