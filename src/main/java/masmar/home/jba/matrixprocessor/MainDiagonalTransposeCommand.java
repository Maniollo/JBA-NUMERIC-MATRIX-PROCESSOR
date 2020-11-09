package masmar.home.jba.matrixprocessor;

class MainDiagonalTransposeCommand implements Command {
    private final Matrix A;

    public MainDiagonalTransposeCommand(Matrix A) {
        this.A = A;
    }

    @Override
    public void execute() {
        try {
            Matrix B = A.transposeAlongMainDiagonal();
            System.out.println("The result is:");
            System.out.println(B.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed.");
        }
    }
}
