package operation.matrixListResultOperation;

import matrix.Matrix;
import matrix.Matrices;

import java.util.Arrays;
import java.util.List;

public class LUDecomposition extends MatrixListResultOperation {
    private Matrix matrix;

    @Override
    public boolean isReadyToOperate() {
        return matrix != null;
    }

    @Override
    public List<Matrix> operate() throws Exception {
        return Arrays.asList(Matrices.luDecomposition(matrix));
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
