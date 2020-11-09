package masmar.home.jba.matrixprocessor;

class MultiplyByConstantCommand implements Command {
    private final Matrix A;
    private final int factor;

    public MultiplyByConstantCommand(Matrix A, int factor) {
        this.A = A;
        this.factor = factor;
    }

    @Override
    public void execute() {
        try {
            System.out.println("The result is:");
            A.multiplyByNumber(factor);
            System.out.println(A.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed.");
        }
    }
}
