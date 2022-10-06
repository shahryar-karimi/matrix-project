package view.panel;

import matrix.Matrix;
import view.textFields.DoubleTextField;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
    private Matrix matrix;
    private DoubleTextField[][] matrixTextFields;

    public DataPanel(Matrix matrix) {
        this.matrix = matrix;
        initComponents();
        update();
    }

    public DataPanel(int height, int width) {
        this(new Matrix(height, width));
    }

    private void initComponents() {
        setLayout();
    }

    private void setLayout() {
        removeAll();
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        setPreferredSize(new java.awt.Dimension(width * 37, height * 30));
        setLayout(new java.awt.GridLayout(height, width));
        matrixTextFields = new DoubleTextField[height][width];
        try {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    DoubleTextField textField = new DoubleTextField();
                    textField.setText(matrix.element(i, j) + "");
                    textField.setBackground(Color.LIGHT_GRAY);
                    matrixTextFields[i][j] = textField;
                    add(textField);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        matrix = new Matrix(matrix.getHeight(), matrix.getWidth());
        update();
    }

    public void update() {
        setLayout();
        revalidate();
        repaint();
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
        update();
    }

    public Matrix getMatrix() {
        updateMatrix();
        return matrix;
    }

    private void updateMatrix() {
        try {
            for (int i = 0; i < matrix.getHeight(); i++) {
                for (int j = 0; j < matrix.getWidth(); j++) {
                    String text = matrixTextFields[i][j].getText();
                    double element = Double.parseDouble(text);
                    matrix.changeElement(i, j, element);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
