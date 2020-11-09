package masmar.home.jba.matrixprocessor;

class Matrix {
    private final double[][] data;
    private final int m;
    private final int n;

    Matrix(double[][] data) {
        this.data = data;
        m = data.length;
        n = data[0].length;
    }

    String asString() {
        StringBuilder matrixBuilder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrixBuilder.append(data[i][j]);
                if (j == n - 1) {
                    matrixBuilder.append("\n");
                } else {
                    matrixBuilder.append(" ");
                }
            }
        }
        matrixBuilder.deleteCharAt(matrixBuilder.length() - 1);
        return matrixBuilder.toString();
    }

    void add(Matrix matrix) throws IllegalArgumentException {
        if (m != matrix.m || n != matrix.n) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] += matrix.data[i][j];
            }
        }
    }

    void multiplyByNumber(int factor) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] *= factor;
            }
        }
    }

    Matrix multiplyByMatrix(Matrix matrix) throws IllegalArgumentException{
        if (this.n != matrix.m) {
            throw new IllegalArgumentException();
        }

        double[][] result = new double[m][matrix.n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < matrix.n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += data[i][k] * matrix.data[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    Matrix transposeAlongMainDiagonal() {
        return null;
    }

    Matrix transposeAlongHorizontalLine() {
        return null;
    }

    Matrix transposeAlongVerticalLine() {
        return null;
    }

    Matrix transposeAlongSideDiagonal() {
        return null;
    }
}
