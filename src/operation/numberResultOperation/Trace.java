package operation.numberResultOperation;

import matrix.Matrix;
import matrix.Matrices;

public class Trace extends NumberResultOperation{
    private Matrix matrix;
    @Override
    public boolean isReadyToOperate() {
        return matrix != null;
    }

    @Override
    public Double operate() throws Exception {
        return Matrices.trace(matrix);
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
