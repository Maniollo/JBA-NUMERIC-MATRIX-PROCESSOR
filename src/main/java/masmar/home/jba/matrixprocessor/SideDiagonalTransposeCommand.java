package masmar.home.jba.matrixprocessor;

class SideDiagonalTransposeCommand implements Command {
    private final Matrix A;

    public SideDiagonalTransposeCommand(Matrix A) {
        this.A = A;
    }

    @Override
    public void execute() {
        try {
            Matrix B = A.transposeAlongSideDiagonal();
            System.out.println("The result is:");
            System.out.println(B.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed.");
        }
    }
}
