package matrix;

import java.util.Arrays;

public class Matrix {
    protected final int height;
    protected final int width;
    protected final double[][] elements;

    public Matrix(double[][] elements) {
        this.elements = elements;
        this.height = elements.length;
        if (height > 0) this.width = elements[0].length;
        else this.width = 0;
    }

    public Matrix(int height, int width) {
        this(new double[height][width]);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double[][] getElements() {
        return elements;
    }

    public double element(int i, int j) throws Exception {
        if (isIndexInMatrix(i, j))
            throw new Exception("Index out of bound for matrix");
        return elements[i][j];
    }

    protected boolean isIndexInMatrix(int i, int j) {
        return i < 0 && j < 0 && i >= getHeight() && j >= getWidth();
    }

    public void changeElement(int i, int j, double newDouble) throws Exception {
        if (isIndexInMatrix(i, j))
            throw new Exception("Index out of bound for matrix");
        elements[i][j] = newDouble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.deepEquals(getElements(), matrix.getElements());
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(getElements());
    }

    @Override
    public Matrix clone() {
        return new Matrix(elements.clone());
    }
}
