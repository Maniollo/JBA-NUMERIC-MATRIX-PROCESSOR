package masmar.home.jba.matrixprocessor;

class InverseMatrixCommand implements Command {
    private final Matrix A;

    public InverseMatrixCommand(Matrix A) {
        this.A = A;
    }

    @Override
    public void execute() {
        try {
            Matrix B = A.inverse();
            System.out.println("The result is:");
            System.out.println(B.asString());
            System.out.println();
        } catch (IllegalStateException e) {
            System.out.println("This matrix doesn't have an inverse.");
        }
    }
}
