package matrix;

import utils.SpecificMatrixName;

import java.util.ArrayList;
import java.util.Collections;

public class Matrices {
    public static Matrix createMatrix(int height, int width, double fillNumber, SpecificMatrixName comboSelect) {
        if (comboSelect == SpecificMatrixName.I)
            return createI(height);
        else if (comboSelect == SpecificMatrixName.fill)
            return createFilledMatrix(height, width, fillNumber);
        else
            return new Matrix(height, width);
    }

    private static Matrix createFilledMatrix(int height, int width, double fillNumber) {
        Matrix matrix = new Matrix(height, width);
        try {
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    matrix.changeElement(i, j, fillNumber);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return matrix;
    }

    private static Matrix createI(int length) {
        Matrix matrix = new Matrix(length, length);
        try {
            for (int i = 0; i < length; i++)
                matrix.changeElement(i, i, 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return matrix;
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) throws Exception {
        if (!hasEqualDimension(matrix1, matrix2))
            throw new Exception("Matrices are not equal in dimension");
        int height = matrix1.getHeight();
        int width = matrix1.getWidth();
        double[][] resultElements = new double[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                resultElements[i][j] = matrix1.element(i, j) + matrix2.element(i, j);
        return new Matrix(resultElements);
    }

    public static boolean hasEqualDimension(Matrix matrix1, Matrix matrix2) {
        return matrix1.getHeight() == matrix2.getHeight() && matrix1.getWidth() == matrix2.getWidth();
    }

    public static Matrix multiple(Matrix matrix1, Matrix matrix2) throws Exception {
        if (!isMultiplicativeTo(matrix1, matrix2))
            throw new Exception("Dimensions are not correct for multiplication");
        int height = matrix1.getHeight();
        int width = matrix2.getWidth();
        int length = matrix1.getWidth();
        double[][] resultElements = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                resultElements[i][j] = 0;
                for (int k = 0; k < length; k++) {
                    resultElements[i][j] += matrix1.element(i, k) * matrix2.element(k, j);
                }
            }
        }
        return new Matrix(resultElements);
    }

    public static boolean isMultiplicativeTo(Matrix matrix1, Matrix matrix2) {
        return matrix1.getWidth() == matrix2.getHeight();
    }

    public static double trace(Matrix matrix) throws Exception {
        if (!isSquare(matrix))
            throw new Exception("Matrix is not square");
        double result = 0;
        int length = matrix.getHeight();
        for (int i = 0; i < length; i++) {
            result += matrix.element(i, i);
        }
        return result;
    }

    public static double determinant(Matrix matrix) throws Exception {
        if (!isSquare(matrix))
            throw new Exception("Matrix is not square");
        if (matrix.getWidth() < 6)
            return simpleDeterminant(matrix);
        else
            return luDecompositionDeterminant(matrix);
    }

    private static double luDecompositionDeterminant(Matrix matrix) throws Exception {
        Matrix[] lup = luDecomposition(matrix);
        double lowerDet = triangleMatrixDeterminant(lup[0]);
        double upperDet = triangleMatrixDeterminant(lup[1]);
        double permutedDet = permutationMatrixDeterminant(lup[2]);
        return lowerDet * upperDet * permutedDet;
    }

    private static double permutationMatrixDeterminant(Matrix matrix) throws Exception {
        int upperOnesCounter = 0;
        int lowerOnesCounter = 0;
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix.element(i, j) > 0) {
                    if (i > j) lowerOnesCounter++;
                    else if (i < j) upperOnesCounter++;
                }
            }
        }
        return Math.pow(-1, Math.max(upperOnesCounter, lowerOnesCounter));
    }

    private static double triangleMatrixDeterminant(Matrix matrix) throws Exception {
        int length = matrix.getWidth();
        double result = 1;
        for (int i = 0; i < length; i++)
            result *= matrix.element(i, i);
        return result;
    }

    private static double simpleDeterminant(Matrix matrix) throws Exception {
        int length = matrix.getWidth();
        if (length == 1) return matrix.element(0, 0);
        double result = 0;
        for (int i = 0; i < length; i++) {
            Matrix subMatrix = subMatrixWithoutRows(matrix, new int[]{0});
            subMatrix = subMatrixWithoutColumns(subMatrix, new int[]{i});
            result += matrix.element(0, i) * Math.pow(-1, i) * simpleDeterminant(subMatrix);
        }
        return result;
    }

    public static Matrix[] luDecomposition(Matrix matrix) throws Exception {
        if (!isSquare(matrix))
            throw new Exception("Matrix is not square");
        int length = matrix.getHeight();
        Matrix permutationMatrix = createI(length);
        double[][] mat = matrix.getElements().clone();
        double[][] lower = createI(length).getElements();
        double[][] upper = new double[length][length];
        for (int i = 0; i < length; i++) {
            int[] permuteRows = permuteRows(mat, length, i);
            permutationMatrix.swapRows(permuteRows[0], permuteRows[1]);
            for (int k = i; k < length; k++) {
                double sum = 0;
                for (int j = 0; j < i; j++)
                    sum += (lower[i][j] * upper[j][k]);
                upper[i][k] = mat[i][k] - sum;
            }
            for (int k = i; k < length; k++) {
                if (i == k)
                    lower[i][i] = 1;
                else {
                    double sum = 0;
                    for (int j = 0; j < i; j++)
                        sum += (lower[k][j] * upper[j][i]);
                    lower[k][i] = (mat[k][i] - sum) / upper[i][i];
                }
            }
        }
        return new Matrix[]{new Matrix(lower), new Matrix(upper), permutationMatrix};
    }

    private static int[] permuteRows(double[][] mat, int length, int index) {
        double max = Math.abs(mat[index][index]);
        int maxIndex = index;
        for (int i = index; i < length; i++) {
            if (Math.abs(mat[i][index]) > max) {
                max = Math.abs(mat[i][index]);
                maxIndex = i;
            }
        }
        double[] temp = new double[length];
        System.arraycopy(mat[index], 0, temp, 0, length);
        System.arraycopy(mat[maxIndex], 0, mat[index], 0, length);
        System.arraycopy(temp, 0, mat[maxIndex], 0, length);
        return new int[]{index, maxIndex};
    }

    public static Matrix subMatrixWithoutRows(Matrix matrix, int[] removableRowsIndexes) {
        ArrayList<Integer> rowIndexes = new ArrayList<>();
        for (int removableRowsIndex : removableRowsIndexes)
            rowIndexes.add(removableRowsIndex);
        Collections.sort(rowIndexes);
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        double[][] resultElements = new double[height - rowIndexes.size()][width];
        try {
            int jump = 0;
            for (int i = 0; i < height; i++) {
                if (!rowIndexes.isEmpty() && i == rowIndexes.get(0)) {
                    jump++;
                    rowIndexes.remove(0);
                    continue;
                }
                for (int j = 0; j < width; j++) {
                    resultElements[i - jump][j] = matrix.element(i, j);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new Matrix(resultElements);
    }

    public static Matrix subMatrixWithoutColumns(Matrix matrix, int[] removableColumnsIndexes) {
        ArrayList<Integer> columnIndexes = new ArrayList<>();
        for (int removableColumnsIndex : removableColumnsIndexes)
            columnIndexes.add(removableColumnsIndex);
        Collections.sort(columnIndexes);
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        double[][] resultElements = new double[height][width - columnIndexes.size()];
        try {
            int jump = 0;
            for (int j = 0; j < width; j++) {
                if (!columnIndexes.isEmpty() && j == columnIndexes.get(0)) {
                    jump++;
                    columnIndexes.remove(0);
                    continue;
                }
                for (int i = 0; i < height; i++) {
                    resultElements[i][j - jump] = matrix.element(i, j);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new Matrix(resultElements);
    }

    public static Column getColumn(Matrix matrix, int col) throws Exception {
        int length = matrix.getHeight();
        double[] columnElements = new double[length];
        for (int i = 0; i < length; i++)
            columnElements[i] = matrix.element(i, col);
        return new Column(columnElements);
    }

    public static boolean isSquare(Matrix matrix) {
        return matrix.getWidth() == matrix.getHeight();
    }

    public static Matrix transpose(Matrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        double[][] resultElements = new double[width][height];
        try {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    resultElements[j][i] = matrix.element(i, j);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new Matrix(resultElements);
    }

    public static double innerDot(Matrix matrix1, Matrix matrix2) throws Exception {
        if (!hasEqualDimension(matrix1, matrix2))
            throw new Exception("Matrices are not equal in dimension");
        double result = 0;
        int height = matrix1.getHeight();
        int width = matrix1.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result += matrix1.element(i, j) * matrix2.element(i, j);
            }
        }
        return result;
    }

    public static Matrix times(double lambda, Matrix matrix) throws Exception {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        double[][] resultElements = new double[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                resultElements[i][j] = matrix.element(i, j) * lambda;
        return new Matrix(resultElements);
    }

    public static Column solveAXb(Matrix a, Column b) throws Exception {
        Matrix[] lup = luDecomposition(a);
        Matrix l = lup[0];
        Matrix u = lup[1];
        Matrix p = lup[2];
        Column b1 = Column.valueOf(multiple(p, b));
        Column y = solveLowerTriangleLXb(l, b1);
        return solveUpperTriangleUXb(u, y);
    }

    public static Column solveLowerTriangleLXb(Matrix l, Column b) throws Exception {
        int length = b.getHeight();
        double[] x = new double[length];
        for (int i = 0; i < length; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++)
                sum += l.element(i, j) * x[j];
            x[i] = (b.element(i) - sum) / l.element(i, i);
        }
        return new Column(x);
    }

    public static Column solveUpperTriangleUXb(Matrix u, Column b) throws Exception {
        int length = b.getHeight();
        double[] x = new double[length];
        for (int i = length - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = length - 1; j > i; j--)
                sum += u.element(i, j) * x[j];
            x[i] = (b.element(i) - sum) / u.element(i, i);
        }
        return new Column(x);
    }

    public static Matrix inverse(Matrix matrix) throws Exception {
        Matrix[] lup = luDecomposition(matrix);
        Matrix l = lup[0];
        Matrix u = lup[1];
        Matrix p = lup[2];
        if (triangleMatrixDeterminant(l) * triangleMatrixDeterminant(u) == 0)
            throw new Exception("Matrix is not invertible");
        Matrix lInverse = lowerTriangleMatrixInverse(l);
        Matrix uInverse = upperTriangleMatrixInverse(u);
        return multiple(multiple(uInverse, lInverse), p);
    }

    private static Matrix lowerTriangleMatrixInverse(Matrix l) throws Exception {
        int height = l.getHeight();
        int width = l.getWidth();
        double[][] inverseElements = new double[height][width];
        for (int j = 0; j < width; j++) {
            for (int i = j; i < height; i++) {
                if (i == j)
                    inverseElements[i][j] = 1 / l.element(i, j);
                else {
                    double sum = 0;
                    for (int r = j; r < i; r++)
                        sum += l.element(i, r) * inverseElements[r][j];
                    inverseElements[i][j] = -1 * sum / l.element(i, i);
                }
            }
        }
        return new Matrix(inverseElements);
    }

    private static Matrix upperTriangleMatrixInverse(Matrix u) throws Exception {
        int height = u.getHeight();
        int width = u.getWidth();
        double[][] inverseElements = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = i; j < width; j++) {
                if (i == j)
                    inverseElements[i][j] = 1 / u.element(i, j);
                else {
                    double sum = 0;
                    for (int r = i; r < j; r++)
                        sum += inverseElements[i][r] * u.element(r, j);
                    inverseElements[i][j] = -1 * sum / u.element(j, j);
                }
            }
        }
        return new Matrix(inverseElements);
    }
}
