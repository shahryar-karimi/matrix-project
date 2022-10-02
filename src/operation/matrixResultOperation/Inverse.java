package operation.matrixResultOperation;

import matrix.Matrices;
import matrix.Matrix;

public class Inverse extends MatrixResultOperation{
    private Matrix matrix;
    @Override
    public boolean isReadyToOperate() {
        return matrix != null;
    }

    @Override
    public Matrix operate() throws Exception {
        return Matrices.inverse(matrix);
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
