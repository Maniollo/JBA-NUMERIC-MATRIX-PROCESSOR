package masmar.home.jba.matrixprocessor;

import java.util.Scanner;

class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        CommandOperator commandOperator = new CommandOperator();
        MatrixTransposeCommandFactory transposeCommandFactory = new MatrixTransposeCommandFactory();
        int option;
        while (true) {
            displayMenu();
            option = prompt();
            switch (option) {
                case 1:
                    commandOperator.setCommand(new AddMatricesCommand(readMatrixFromInput(), readMatrixFromInput()));
                    break;
                case 2:
                    commandOperator.setCommand(new MultiplyByConstantCommand(readMatrixFromInput(), SCANNER.nextInt()));
                    break;
                case 3:
                    commandOperator.setCommand(new MultiplyMatrixByMatrixCommand(readMatrixFromInput(), readMatrixFromInput()));
                    break;
                case 4:
                    displayTransposeMenu();
                    option = prompt();
                    commandOperator.setCommand(transposeCommandFactory.create(option, readMatrixFromInput()));
                    break;
                default:
                    return;
            }
            commandOperator.execute();
        }
    }

    private static void displayTransposeMenu() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
    }

    private static int prompt() {
        System.out.println("Your choice: ");
        return SCANNER.nextInt();
    }

    private static void displayMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("0. Exit");
    }

    private static Matrix readMatrixFromInput() {
        int m1 = SCANNER.nextInt();
        int n1 = SCANNER.nextInt();
        double[][] matrixOne = new double[m1][n1];
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                matrixOne[i][j] = SCANNER.nextDouble();
            }
        }
        return new Matrix(matrixOne);
    }
}
