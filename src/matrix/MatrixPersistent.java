package matrix;

import java.util.ArrayList;

public class MatrixPersistent {
    private ArrayList<Matrix> matrices;

    public void add(Matrix matrix) {
        matrices.add(matrix);
    }

    public void remove(Matrix matrix) {
        matrices.remove(matrix);
    }

    public void remove(int index) {
        matrices.remove(index);
    }


}
