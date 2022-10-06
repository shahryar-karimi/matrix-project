package operation.numberResultOperation;

import matrix.Matrices;
import matrix.Matrix;

public class Rank extends NumberResultOperation{
    private Matrix matrix;
    @Override
    public boolean isReadyToOperate() {
        return matrix != null;
    }

    @Override
    public Double operate() throws Exception {
        return (double) Matrices.rank(matrix);
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
