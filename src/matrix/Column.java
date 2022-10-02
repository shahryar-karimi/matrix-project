package matrix;

public class Column extends Matrix {

    public Column(int height) {
        super(height, 1);
    }

    public Column(double[] elements) {
        super(get2DElements(elements));
    }

    public static Column valueOf(Matrix matrix) throws Exception {
        if (matrix.getWidth() > 1) throw new Exception("This dimensions can not be a for a vector");
        return new Column(get1DElements(matrix.getElements()));
    }

    public static double[][] get2DElements(double[] elements) {
        double[][] result = new double[elements.length][1];
        for (int i = 0; i < elements.length; i++)
            result[i][0] = elements[i];
        return result;
    }

    public static double[] get1DElements(double[][] elements) {
        int length = elements.length;
        double[] result = new double[length];
        for (int i = 0; i < length; i++)
            result[i] = elements[i][0];
        return result;
    }

    public double[] get1DElements() {
        return get1DElements(elements);
    }

    public double element(int i) throws Exception {
        return element(i, 0);
    }
}
