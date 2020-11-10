package masmar.home.jba.matrixprocessor;

class CalculateDeterminantCommand implements Command {
    private final Matrix A;

    public CalculateDeterminantCommand(Matrix A) {
        this.A = A;
    }

    @Override
    public void execute() {
        try {
            int determinant = A.calculateDeterminant();
            System.out.println("The result is:");
            System.out.println(determinant);
        } catch (IllegalStateException e) {
            System.out.println("The operation cannot be performed.");
        }
    }
}
