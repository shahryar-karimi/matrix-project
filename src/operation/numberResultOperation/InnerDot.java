package operation.numberResultOperation;

import matrix.Matrix;
import matrix.Matrices;

public class InnerDot extends NumberResultOperation{
    private Matrix matrix1;
    private Matrix matrix2;

    @Override
    public boolean isReadyToOperate() {
        return matrix1 != null && matrix2 != null;
    }

    @Override
    public Double operate() throws Exception {
        return Matrices.innerDot(matrix1, matrix2);
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
