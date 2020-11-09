package masmar.home.jba.matrixprocessor;

class VerticalLineTransposeCommand implements Command {
    private final Matrix A;

    public VerticalLineTransposeCommand(Matrix A) {
        this.A = A;
    }

    @Override
    public void execute() {
        try {
            Matrix B = A.transposeAlongVerticalLine();
            System.out.println("The result is:");
            System.out.println(B.asString());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed.");
        }
    }
}
