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

    Matrix multiplyByMatrix(Matrix matrix) throws IllegalArgumentException {
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
        double[][] transposed = new double[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                transposed[i][j] = data[j][i];
            }
        }
        return new Matrix(transposed);
    }

    Matrix transposeAlongHorizontalLine() {
        double[][] transposed = new double[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                transposed[-i + m - 1][j] = data[i][j];
            }
        }
        return new Matrix(transposed);
    }

    Matrix transposeAlongVerticalLine() {
        double[][] transposed = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                transposed[i][-j + n - 1] = data[i][j];
            }
        }
        return new Matrix(transposed);
    }

    Matrix transposeAlongSideDiagonal() {
        double[][] transposed = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transposed[-j + m - 1][-i + n - 1] = data[i][j];
            }
        }
        return new Matrix(transposed);
    }

    int calculateDeterminant() throws IllegalStateException{
        if (m != n) {
            throw new IllegalStateException();
        }
        return -1;
    }
}
