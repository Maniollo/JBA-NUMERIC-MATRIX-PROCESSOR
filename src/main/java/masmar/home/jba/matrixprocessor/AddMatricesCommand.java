package masmar.home.jba.matrixprocessor;

class AddMatricesCommand implements Command {
    private final Matrix A;
    private final Matrix B;

    public AddMatricesCommand(Matrix A, Matrix B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public void execute() {
        try {
            A.add(B);
            System.out.println("The result is:");
            System.out.println(A.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed.");
        }
    }
}
