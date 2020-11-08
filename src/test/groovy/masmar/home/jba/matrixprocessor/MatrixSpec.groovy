package masmar.home.jba.matrixprocessor

import spock.lang.Specification


class MatrixSpec extends Specification {
    def "should return matrix as string"() {
        when:
        Matrix A = new Matrix([[1, 2, 3, 4], [3, 2, 3, 5], [8, 0, 9, 10]] as int[][])

        then:
        A.asString() == "1 2 3 4\n3 2 3 5\n8 0 9 10"
    }

    def "should throw IllegalArgument Exception when added matrix has different size"() {
        given:
        Matrix A = new Matrix([[1, 2],
                               [3, 2]] as int[][])

        Matrix B = new Matrix([[1, 1, 4, 4, 5],
                               [4, 4, 5, 7, 8],
                               [1, 2, 3, 9, 8],
                               [1, 0, 0, 0, 1]] as int[][])

        when:
        A.add(B)

        then:
        IllegalArgumentException ex = thrown()
    }

    def "should add another matrix"() {
        given:
        Matrix A = new Matrix([[1, 2, 3, 4, 5],
                               [3, 2, 3, 2, 1],
                               [8, 0, 9, 9, 1],
                               [1, 3, 4, 5, 6]] as int[][])

        Matrix B = new Matrix([[1, 1, 4, 4, 5],
                               [4, 4, 5, 7, 8],
                               [1, 2, 3, 9, 8],
                               [1, 0, 0, 0, 1]] as int[][])

        when:
        A.add(B)

        then:
        A.asString() ==
                "2 3 7 8 10\n" +
                "7 6 8 9 9\n" +
                "9 2 12 18 9\n" +
                "2 3 4 5 7"
    }

    def "should multiply by factor = #factor"() {
        given:
        Matrix A = new Matrix([[1, 2, 3, 4, 5],
                               [3, 2, 3, 2, 1],
                               [8, 0, 9, 9, 1],
                               [1, 3, 4, 5, 6]] as int[][])

        when:
        A.multiplyByNumber(factor)

        then:
        A.asString() == output

        where:
        factor || output
        2      || "2 4 6 8 10\n6 4 6 4 2\n16 0 18 18 2\n2 6 8 10 12"
        0      || "0 0 0 0 0\n0 0 0 0 0\n0 0 0 0 0\n0 0 0 0 0"
    }

}
