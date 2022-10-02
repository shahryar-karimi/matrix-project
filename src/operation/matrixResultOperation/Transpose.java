package operation.matrixResultOperation;

import matrix.Matrix;
import matrix.Matrices;

public class Transpose extends MatrixResultOperation {
    private Matrix matrix;

    @Override
    public Matrix operate() throws Exception {
        return Matrices.transpose(matrix);
    }

    @Override
    public boolean isReadyToOperate() {
        return matrix != null;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
