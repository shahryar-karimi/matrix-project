package operation.matrixListResultOperation;

import matrix.Matrices;
import matrix.Matrix;

import java.util.List;

public class ReduceRowEchelonForm extends MatrixListResultOperation {
    private Matrix matrix;

    @Override
    public boolean isReadyToOperate() {
        return matrix != null;
    }

    @Override
    public List<Matrix> operate() throws Exception {
        return Matrices.reduceRowEchelonForm(matrix);
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
