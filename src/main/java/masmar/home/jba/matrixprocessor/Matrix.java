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

    void multiplyByNumber(double factor) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double newValue = data[i][j] * factor;
                data[i][j] = newValue == 0 ? 0.0 : newValue;
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

    double calculateDeterminant() throws IllegalStateException {
        if (m != n) {
            throw new IllegalStateException();
        }
        return calculateDeterminantRecursive(data);
    }

    Matrix inverse() {
        double determinant = this.calculateDeterminant();
        if (determinant == 0) {
            throw new IllegalStateException();
        }
        Matrix cofactorMatrix = getCofactorMatrix();
        Matrix transposedCofators = cofactorMatrix.transposeAlongMainDiagonal();
        transposedCofators.multiplyByNumber(1.0 / determinant);
        return transposedCofators;
    }

    private double calculateDeterminantRecursive(double[][] data) {
        if (data.length == 2) {
            return data[0][0] * data[1][1] - data[1][0] * data[0][1];
        }

        double sum = 0;

        for (int i = 0; i < data.length; i++) {
            double[][] subMatrix = createSubMatrix(data, 0, i);
            sum = sum + data[0][i] * Math.pow(-1.0, 2 + i) * calculateDeterminantRecursive(subMatrix);
        }

        return sum;
    }

    private double[][] createSubMatrix(double[][] data, int skipRowIdx, int skipColumnIdx) {
        int newSubMatrixLength = data.length - 1;
        double[][] subMatrix = new double[newSubMatrixLength][newSubMatrixLength];
        int y;
        int x = 0;
        for (int i = 0; i < newSubMatrixLength; i++) {
            y = 0;
            if (x == skipRowIdx) {
                x += 1;
            }
            for (int j = 0; j < newSubMatrixLength; j++) {
                if (y == skipColumnIdx) {
                    y += 1;
                }
                subMatrix[i][j] = data[x][y++];
            }
            x += 1;
        }
        return subMatrix;
    }

    private Matrix getCofactorMatrix() {
        double[][] cofactors = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                double determinant = new Matrix(createSubMatrix(data, i, j)).calculateDeterminant();
                cofactors[i][j] = determinant == 0 ? 0.0 : Math.pow(-1.0, 2 + i + j) * determinant;
            }
        }
        return new Matrix(cofactors);
    }
}
