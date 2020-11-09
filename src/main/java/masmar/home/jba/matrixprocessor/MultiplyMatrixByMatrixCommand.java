package masmar.home.jba.matrixprocessor;

class MultiplyMatrixByMatrixCommand implements Command {
    private final Matrix A;
    private final Matrix B;

    public MultiplyMatrixByMatrixCommand(Matrix A, Matrix B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public void execute() {
        try {
            Matrix C = A.multiplyByMatrix(B);
            System.out.println("The result is:");
            System.out.println(C.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed.");
        }
    }
}
