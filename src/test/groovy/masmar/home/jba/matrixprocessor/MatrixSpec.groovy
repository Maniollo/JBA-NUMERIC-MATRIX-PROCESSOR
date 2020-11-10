package masmar.home.jba.matrixprocessor

import spock.lang.Specification


class MatrixSpec extends Specification {
    def "should return matrix as string"() {
        when:
        Matrix A = new Matrix([[1, 2, 3, 4], [3, 2, 3, 5], [8, 0, 9, 10]] as double[][])

        then:
        A.asString() == "1.0 2.0 3.0 4.0\n3.0 2.0 3.0 5.0\n8.0 0.0 9.0 10.0"
    }

    def "should throw IllegalArgument Exception when added matrix has different size"() {
        given:
        Matrix A = new Matrix([[1, 2],
                               [3, 2]] as double[][])

        Matrix B = new Matrix([[1.0, 1.0, 4.0, 4.0, 5.0],
                               [4.0, 4.0, 5.0, 7.0, 8.0],
                               [1.0, 2.0, 3.0, 9.0, 8.0],
                               [1.0, 0.0, 0.0, 0.0, 1.0]] as double[][])

        when:
        A.add(B)

        then:
        thrown IllegalArgumentException
    }

    def "should add another matrix"() {
        given:
        Matrix A = new Matrix([[1.1, 2.2, 3.3, 4.4, 5.5],
                               [3.0, 2.0, 3.0, 2.0, 1.0],
                               [8.0, 0.0, 9.0, 9.0, 1.0],
                               [1.0, 3.0, 4.0, 5.0, 6.0]] as double[][])

        Matrix B = new Matrix([[1.0, 1.0, 4.0, 4.0, 5.0],
                               [4.0, 4.0, 5.0, 7.0, 8.0],
                               [1.0, 2.0, 3.0, 9.0, 8.0],
                               [1.0, 0.0, 0.0, 0.0, 1.0]] as double[][])

        when:
        A.add(B)

        then:
        A.asString() ==
                "2.1 3.2 7.3 8.4 10.5\n" +
                "7.0 6.0 8.0 9.0 9.0\n" +
                "9.0 2.0 12.0 18.0 9.0\n" +
                "2.0 3.0 4.0 5.0 7.0"
    }

    def "should multiply by factor = #factor"() {
        given:
        Matrix A = new Matrix([[1, 2, 3, 4, 5],
                               [3, 2, 3, 2, 1],
                               [8, 0, 9, 9, 1],
                               [1, 3, 4, 5, 6]] as double[][])

        when:
        A.multiplyByNumber(factor)

        then:
        A.asString() == output

        where:
        factor || output
        2      || "2.0 4.0 6.0 8.0 10.0\n6.0 4.0 6.0 4.0 2.0\n16.0 0.0 18.0 18.0 2.0\n2.0 6.0 8.0 10.0 12.0"
        0      || "0.0 0.0 0.0 0.0 0.0\n0.0 0.0 0.0 0.0 0.0\n0.0 0.0 0.0 0.0 0.0\n0.0 0.0 0.0 0.0 0.0"
    }

    def "should multiply by matrix"() {
        when:
        Matrix C = A.multiplyByMatrix(B)

        then:
        C.asString() == CasString

        where:
        A                                                           | B                                                            || CasString
        new Matrix([[1, 7, 7], [6, 6, 4], [4, 2, 1]] as double[][]) | new Matrix([[3, 2, 4], [5, 5, 9], [8, 0, 10]] as double[][]) || "94.0 37.0 137.0\n80.0 42.0 118.0\n30.0 18.0 44.0"
        new Matrix([[2, 4, 6], [1, 3, 5]] as double[][])            | new Matrix([[1, 2], [3, 4], [5, 6]] as double[][])           || "44.0 56.0\n35.0 44.0"
    }

    def "should throw IllegalArgumentException when matrices dimensions are invalid"() {
        given:
        Matrix A = new Matrix([[1, 7, 7], [6, 6, 4], [4, 2, 1]] as double[][])
        Matrix B = new Matrix([[1, 7, 7], [6, 6, 4]] as double[][])

        when:
        A.multiplyByMatrix(B)

        then:
        thrown IllegalArgumentException
    }

    def "should transpose matrix along the main diagonal"() {
        given:
        def matrix = new Matrix([[1, 1, 1, 1], [2, 2, 2, 2], [3, 3, 3, 3], [4, 4, 4, 4]] as double[][])

        expect:
        matrix.transposeAlongMainDiagonal().asString() == "1.0 2.0 3.0 4.0\n1.0 2.0 3.0 4.0\n1.0 2.0 3.0 4.0\n1.0 2.0 3.0 4.0"
    }

    def "should transpose matrix along the side diagonal"() {
        given:
        def matrix = new Matrix([[1, 1, 1, -1], [2, 2, 2, -2], [3, 3, 3, -3], [4, 4, 4, -4]] as double[][])

        expect:
        matrix.transposeAlongSideDiagonal().asString() == "-4.0 -3.0 -2.0 -1.0\n4.0 3.0 2.0 1.0\n4.0 3.0 2.0 1.0\n4.0 3.0 2.0 1.0"
    }

    def "should transpose matrix along the vertical line"() {
        when:
        def matrix = new Matrix([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]] as double[][])

        then:
        matrix.transposeAlongVerticalLine().asString() == "4.0 3.0 2.0 1.0\n8.0 7.0 6.0 5.0\n12.0 11.0 10.0 9.0\n16.0 15.0 14.0 13.0"
    }

    def "should transpose matrix along the horizontal line"() {
        when:
        def matrix = new Matrix([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]] as double[][])

        then:
        matrix.transposeAlongHorizontalLine().asString() == "13.0 14.0 15.0 16.0\n9.0 10.0 11.0 12.0\n5.0 6.0 7.0 8.0\n1.0 2.0 3.0 4.0"
    }

    def "should throw IllegalStateException when calculate not square matrix determinant"() {
        given:
        def matrix = new Matrix([[1, 2]] as double[][])

        when:
        matrix.calculateDeterminant()

        then:
        thrown IllegalStateException
    }

    def "should return #determinant as matrix determinant"() {
        expect:
        new Matrix(matrix as double[][]).calculateDeterminant() == determinant as double

        where:
        matrix                                                   || determinant
        [[1, -4], [-2, 5]]                                       || -3.0
        [[2, 1, 3], [4, 0, -1], [-2, 5, 6]]                      || 48.0
        [[1, 2, 3, 4], [1, 0, 2, 0], [0, 1, 2, 3], [2, 3, 0, 0]] || 7.0
    }

    def "should throw IllegalStateException when inverse not square matrix"() {
        given:
        def matrix = new Matrix([[1, 2]] as double[][])

        when:
        matrix.inverse()

        then:
        thrown IllegalStateException
    }

    def "should return inverted matrix"() {
        when:
        def A = new Matrix(matrix as double[][]).inverse()

        then:
        A.asString() == inverted

        where:
        matrix           || inverted
        [[2, -1, 0], [0, 1, 2], [1, 1, 0]] || "0.3333333333333333 0.0 0.3333333333333333\n-0.3333333333333333 0.0 0.6666666666666666\n0.16666666666666666 0.5 -0.3333333333333333"
    }
}
