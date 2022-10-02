package operation.matrixResultOperation;

import matrix.Matrix;
import matrix.Matrices;

public class Times extends MatrixResultOperation {
    private double lambda;
    private Matrix matrix;

    @Override
    public boolean isReadyToOperate() {
        return matrix != null;
    }

    @Override
    public Matrix operate() throws Exception {
        return Matrices.times(lambda, matrix);
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
