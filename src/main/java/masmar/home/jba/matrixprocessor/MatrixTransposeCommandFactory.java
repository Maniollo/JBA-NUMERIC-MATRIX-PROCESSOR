package masmar.home.jba.matrixprocessor;

class MatrixTransposeCommandFactory {
    public Command create(int option, Matrix A) {
        switch (option) {
            case 1:
                return new MainDiagonalTransposeCommand(A);
            case 2:
                return new SideDiagonalTransposeCommand(A);
            case 3:
                return new VerticalLineTransposeCommand(A);
            case 4:
                return new HorizontalLineTransposeCommand(A);
            default:
                throw new IllegalArgumentException();
        }
    }
}
