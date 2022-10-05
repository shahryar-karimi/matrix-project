package view.panel;

import matrix.Matrix;
import matrix.Matrices;
import operation.matrixListResultOperation.LUDecomposition;
import operation.matrixListResultOperation.MatrixListResultOperation;
import operation.matrixListResultOperation.ReduceRowEchelonForm;
import operation.matrixResultOperation.*;
import operation.numberResultOperation.Determinant;
import operation.numberResultOperation.InnerDot;
import operation.numberResultOperation.NumberResultOperation;
import operation.numberResultOperation.Trace;
import utils.SpecificMatrixName;
import view.frame.MatrixListFrame;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class MatrixPanel extends JPanel {
    private NumberResultOperation numberResultOperation;
    private MatrixResultOperation matrixResultOperation;
    private MatrixListResultOperation matrixListResultOperation;
    private MatrixButtonPanel buttonPanel;
    private JScrollPane scrollPane;
    private DataPanel dataPanel;

    public MatrixPanel(int height, int width) {
        initComponents(height, width);
    }

    private void initComponents(int height, int width) {
        buttonPanel = new MatrixButtonPanel();
        addActionListeners();
        scrollPane = new javax.swing.JScrollPane();
        dataPanel = new DataPanel(height, width);
        setButtonsEnable();

        setBackground(new java.awt.Color(0, 153, 153));

        scrollPane.setViewportView(dataPanel);

        setLayout();
    }

    private void setButtonsEnable() {
        boolean matrixResultOperatorEnabler = matrixResultOperation == null &&
                (numberResultOperation == null || !(numberResultOperation instanceof InnerDot));
        buttonPanel.getMultipleButton().setEnabled(matrixResultOperatorEnabler);
        buttonPanel.getSumButton().setEnabled(matrixResultOperatorEnabler);
        buttonPanel.getInnerDotButton().setEnabled(matrixResultOperatorEnabler);
        Matrix matrix = dataPanel.getMatrix();
        boolean isSquare = Matrices.isSquare(matrix);
        buttonPanel.getTraceButton().setEnabled(isSquare);
        buttonPanel.getDeterminantButton().setEnabled(isSquare);
        buttonPanel.getLuDecompositionButton().setEnabled(isSquare);
        buttonPanel.getInverseButton().setEnabled(isSquare);
        buttonPanel.getAxbSolverButton()
                .setEnabled(matrix.getWidth() - matrix.getHeight() == 1);
    }

    private void addActionListeners() {
        buttonPanel.getClearButton().addActionListener(e -> clearButtonActionPerformed());
        buttonPanel.getResultButton().addActionListener(e -> {
            try {
                resultButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getMultipleButton().addActionListener(e -> {
            try {
                multipleButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getSumButton().addActionListener(e -> {
            try {
                sumButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getTransposeButton().addActionListener(e -> {
            try {
                transposeButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getTraceButton().addActionListener(e -> {
            try {
                traceButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getDeterminantButton().addActionListener(e -> {
            try {
                determinantButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getInnerDotButton().addActionListener(e -> {
            try {
                innerDotButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getLuDecompositionButton().addActionListener(e -> {
            try {
                luDecompositionButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getAxbSolverButton().addActionListener(e -> {
            try {
                axbSolverActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getInverseButton().addActionListener(e -> {
            try {
                inverseButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
        buttonPanel.getReduceRowEchelonFormButton().addActionListener(e -> {
            try {
                reduceRowEchelonFormButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
    }

    private void clearButtonActionPerformed() {
        dataPanel.clear();
        matrixResultOperation = null;
    }

    public void resultButtonActionPerformed() throws Exception {
        if (matrixResultOperation == null && numberResultOperation == null) {
            throw new Exception("Hasn't set an operation yet");
        } else if (matrixResultOperation != null) {
            if (matrixResultOperation instanceof Multiply)
                ((Multiply) matrixResultOperation).setMatrix2(dataPanel.getMatrix());
            else if (matrixResultOperation instanceof Sum)
                ((Sum) matrixResultOperation).setMatrix2(dataPanel.getMatrix());
            if (matrixResultOperation.isReadyToOperate()) {
                Matrix result = matrixResultOperation.operate();
                setMatrix(result);
                matrixResultOperation = null;
            }
        } else {
            if (numberResultOperation instanceof InnerDot)
                ((InnerDot) numberResultOperation).setMatrix2(dataPanel.getMatrix());
            operateNumberResultOperations();
        }
        setButtonsEnable();
    }

    private void multipleButtonActionPerformed() throws Exception {
        if (matrixResultOperation == null) {
            matrixResultOperation = new Multiply();
            ((Multiply) matrixResultOperation).setMatrix1(dataPanel.getMatrix());
            dataPanel.clear();
            setButtonsEnable();
        } else {
            throw new Exception("An operation is operating right now");
        }
    }

    private void sumButtonActionPerformed() throws Exception {
        if (matrixResultOperation == null) {
            matrixResultOperation = new Sum();
            ((Sum) matrixResultOperation).setMatrix1(dataPanel.getMatrix());
            dataPanel.clear();
            setButtonsEnable();
        } else {
            throw new Exception("An operation is operating right now");
        }
    }

    private void transposeButtonActionPerformed() throws Exception {
        if (matrixResultOperation == null) {
            matrixResultOperation = new Transpose();
            ((Transpose) matrixResultOperation).setMatrix(dataPanel.getMatrix());
            resultButtonActionPerformed();
        } else {
            throw new Exception("An operation is operating right now");
        }
    }

    private void axbSolverActionPerformed() throws Exception {
        if (matrixResultOperation == null) {
            matrixResultOperation = new AXBSolver();
            ((AXBSolver) matrixResultOperation).setAb(dataPanel.getMatrix());
            resultButtonActionPerformed();
        } else {
            throw new Exception("An operation is operating right now");
        }
    }

    private void inverseButtonActionPerformed() throws Exception {
        if (matrixResultOperation == null) {
            matrixResultOperation = new Inverse();
            ((Inverse) matrixResultOperation).setMatrix(dataPanel.getMatrix());
            resultButtonActionPerformed();
        } else {
            throw new Exception("An operation is operating right now");
        }
    }

    private void operateNumberResultOperations() throws Exception {
        if (numberResultOperation.isReadyToOperate()) {
            double result = numberResultOperation.operate();
            JOptionPane.showMessageDialog(null, "Result is: " + result);
            numberResultOperation = null;
        }
    }

    private void traceButtonActionPerformed() throws Exception {
        numberResultOperation = new Trace();
        ((Trace) numberResultOperation).setMatrix(dataPanel.getMatrix());
        operateNumberResultOperations();
    }

    private void determinantButtonActionPerformed() throws Exception {
        numberResultOperation = new Determinant();
        ((Determinant) numberResultOperation).setMatrix(dataPanel.getMatrix());
        operateNumberResultOperations();
    }

    private void innerDotButtonActionPerformed() {
        numberResultOperation = new InnerDot();
        ((InnerDot) numberResultOperation).setMatrix1(dataPanel.getMatrix());
        dataPanel.clear();
        setButtonsEnable();
    }

    private void luDecompositionButtonActionPerformed() throws Exception {
        matrixListResultOperation = new LUDecomposition();
        ((LUDecomposition) matrixListResultOperation).setMatrix(dataPanel.getMatrix());
        operateMatrixListResultOperation();
    }

    private void reduceRowEchelonFormButtonActionPerformed() throws Exception {
        matrixListResultOperation = new ReduceRowEchelonForm();
        ((ReduceRowEchelonForm) matrixListResultOperation).setMatrix(dataPanel.getMatrix());
        operateMatrixListResultOperation();
    }

    private void operateMatrixListResultOperation() throws Exception {
        if (matrixListResultOperation.isReadyToOperate()) {
            List<Matrix> matrixList = matrixListResultOperation.operate();
            if (matrixListResultOperation instanceof LUDecomposition)
                showMatrixList(matrixList, Arrays.asList("L", "U", "Permutation"));
            else if (matrixListResultOperation instanceof ReduceRowEchelonForm) {
                showMatrixList(matrixList, Arrays.asList("RREF", "Permutation"));
            }
            matrixListResultOperation = null;
        }
    }

    private void showMatrixList(List<Matrix> matrixList, List<String> names) {
        new MatrixListFrame(matrixList, names);
    }

    private void setLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane)
                                        .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    public void createMatrix(int height, int width, double fillNumber, int comboSelect) {
        SpecificMatrixName matrixName = getSpecificMatrixName(comboSelect);
        Matrix matrix = Matrices.createMatrix(height, width, fillNumber, matrixName);
        setMatrix(matrix);
    }

    public void setMatrix(Matrix matrix) {
        dataPanel.setMatrix(matrix);
        setButtonsEnable();
        update();
    }

    public void update() {
        dataPanel.update();
    }

    private SpecificMatrixName getSpecificMatrixName(int comboSelect) {
        SpecificMatrixName matrixName;
        switch (comboSelect) {
            case 1 -> matrixName = SpecificMatrixName.I;
            case 2 -> matrixName = SpecificMatrixName.fill;
            default -> matrixName = SpecificMatrixName.O;
        }
        return matrixName;
    }

    public MatrixButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public DataPanel getDataPanel() {
        return dataPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setNumberResultOperation(NumberResultOperation numberResultOperation) {
        this.numberResultOperation = numberResultOperation;
    }

    public void setMatrixResultOperation(MatrixResultOperation matrixResultOperation) {
        this.matrixResultOperation = matrixResultOperation;
    }

    public NumberResultOperation getNumberResultOperation() {
        return numberResultOperation;
    }

    public MatrixResultOperation getMatrixResultOperation() {
        return matrixResultOperation;
    }
}
