package operation.matrixResultOperation;

import matrix.Matrix;
import matrix.Matrices;

public class Sum extends MatrixResultOperation {
    private Matrix matrix1;
    private Matrix matrix2;

    @Override
    public Matrix operate() throws Exception {
        return Matrices.sum(matrix1, matrix2);
    }

    @Override
    public boolean isReadyToOperate() {
        return matrix1 != null && matrix2 != null;
    }

    public Matrix getMatrix1() {
        return matrix1;
    }

    public void setMatrix1(Matrix matrix1) {
        this.matrix1 = matrix1;
    }

    public Matrix getMatrix2() {
        return matrix2;
    }

    public void setMatrix2(Matrix matrix2) {
        this.matrix2 = matrix2;
    }
}
