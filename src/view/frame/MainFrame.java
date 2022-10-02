package view.frame;

import operation.matrixResultOperation.Times;
import view.panel.MainFrameHeaderPanel;
import view.panel.MatrixPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    private MatrixPanel matrixPanel;
    private MainFrameHeaderPanel headerPanel;
    private static final int DEFAULT_MATRIX_HEIGHT = 3;
    private static final int DEFAULT_MATRIX_WIDTH = 3;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        matrixPanel = new MatrixPanel(DEFAULT_MATRIX_HEIGHT, DEFAULT_MATRIX_WIDTH);
        headerPanel = new MainFrameHeaderPanel();
        addActionListeners();
        setLayout();
        pack();
        setVisible(true);
    }

    private void addActionListeners() {
        headerPanel.getRefreshButton().addActionListener(e -> headerGetButtonActionPerformed());
        matrixPanel.getButtonPanel().getTimesButton().addActionListener(e -> {
            try {
                timesButtonActionPerformed();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
    }

    private void timesButtonActionPerformed() throws Exception {
        matrixPanel.setMatrixResultOperation(new Times());
        Times times = (Times) matrixPanel.getMatrixResultOperation();
        times.setMatrix(matrixPanel.getDataPanel().getMatrix());
        double lambda = Double.parseDouble(headerPanel.getFillNumberTextField().getText());
        times.setLambda(lambda);
        matrixPanel.resultButtonActionPerformed();
    }

    private void headerGetButtonActionPerformed() {
        int height = Integer.parseInt(headerPanel.getHeightTextField().getText());
        int width = Integer.parseInt(headerPanel.getWidthTextField().getText());
        double fillNumber = Double.parseDouble(headerPanel.getFillNumberTextField().getText());
        int selectedIndex = headerPanel.getSelectionComboBox().getSelectedIndex();
        matrixPanel.createMatrix(height, width, fillNumber, selectedIndex);
    }

    private void setLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(matrixPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(matrixPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
