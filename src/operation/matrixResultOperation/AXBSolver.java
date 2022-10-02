package operation.matrixResultOperation;

import matrix.Column;
import matrix.Matrices;
import matrix.Matrix;

public class AXBSolver extends MatrixResultOperation {
    private Matrix ab;

    @Override
    public boolean isReadyToOperate() {
        return ab != null && ab.getWidth() - ab.getHeight() == 1;
    }

    @Override
    public Matrix operate() throws Exception {
        int chosenColumnIndex = ab.getWidth() - 1;
        Matrix a = Matrices.subMatrixWithoutColumns(ab, new int[]{chosenColumnIndex});
        Column b = Matrices.getColumn(ab, chosenColumnIndex);
        return Matrices.solveAXb(a, b);
    }

    public Matrix getAb() {
        return ab;
    }

    public void setAb(Matrix ab) {
        this.ab = ab;
    }
}
