package view.panel;

import javax.swing.*;
import java.awt.*;

public class MatrixButtonPanel extends JPanel {
    private JButton clearButton;
    private JButton determinantButton;
    private JButton multipleButton;
    private JButton inverseButton;
    private JButton sumButton;
    private JButton traceButton;
    private JButton transposeButton;
    private JButton innerDotButton;
    private JButton resultButton;
    private JButton timesButton;
    private JButton luDecompositionButton;
    private JButton axbSolverButton;
    private JButton reduceRowEchelonFormButton;

    public MatrixButtonPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setBackground(new java.awt.Color(0, 153, 153));
        this.setLayout(new java.awt.GridLayout(4, 4));
        sumButton = new javax.swing.JButton("Sum");
        multipleButton = new javax.swing.JButton("Multiple");
        transposeButton = new javax.swing.JButton("Transpose");
        inverseButton = new javax.swing.JButton("Inverse");
        traceButton = new javax.swing.JButton("Trace");
        determinantButton = new javax.swing.JButton("Determinant");
        clearButton = new javax.swing.JButton("Clear");
        innerDotButton = new JButton("Inner Dot");
        resultButton = new javax.swing.JButton("Result");
        timesButton = new JButton("Times");
        luDecompositionButton = new JButton("LU");
        axbSolverButton = new JButton("Ax=b");
        reduceRowEchelonFormButton = new JButton("RREF");
        JButton[] buttons = new JButton[]{sumButton, multipleButton, timesButton, transposeButton,
                inverseButton, traceButton, clearButton, innerDotButton,
                determinantButton, axbSolverButton, luDecompositionButton, resultButton,
                reduceRowEchelonFormButton};
        for (JButton button : buttons) {
            button.setFont(new java.awt.Font("Helvetica Neue", Font.PLAIN, 18));
            this.add(button);
        }
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getDeterminantButton() {
        return determinantButton;
    }

    public JButton getMultipleButton() {
        return multipleButton;
    }

    public JButton getResultButton() {
        return resultButton;
    }

    public JButton getSumButton() {
        return sumButton;
    }

    public JButton getTraceButton() {
        return traceButton;
    }

    public JButton getTransposeButton() {
        return transposeButton;
    }

    public JButton getInnerDotButton() {
        return innerDotButton;
    }

    public JButton getTimesButton() {
        return timesButton;
    }

    public JButton getLuDecompositionButton() {
        return luDecompositionButton;
    }

    public JButton getAxbSolverButton() {
        return axbSolverButton;
    }

    public JButton getInverseButton() {
        return inverseButton;
    }

    public JButton getReduceRowEchelonFormButton() {
        return reduceRowEchelonFormButton;
    }
}
