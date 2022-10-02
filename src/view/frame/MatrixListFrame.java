package view.frame;

import matrix.Matrix;
import view.panel.ShownResultPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MatrixListFrame extends JFrame {
    private ShownResultPanel mainPanel;

    public MatrixListFrame(List<Matrix> matrixList, List<String> names) {
        initComponents(matrixList, names);
    }

    private void initComponents(List<Matrix> matrixList, List<String> names) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainPanel = new ShownResultPanel(matrixList, names);
        this.setLayout(new GridLayout());
        add(mainPanel);
        pack();
        this.setVisible(true);
    }
}
