package masmar.home.jba.matrixprocessor;

class Matrix {
    private final int[][] data;
    private final int m;
    private final int n;

    Matrix(int[][] data) {
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
}
