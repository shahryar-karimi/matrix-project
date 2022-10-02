package view.panel;

import matrix.Matrix;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShownResultPanel extends JPanel {
    private DataPanel dataPanel;
    private JScrollPane scrollPane;
    private JPanel footerPanel;
    private JLabel matrixName;
    private JButton nextButton;
    private JButton previousButton;
    private List<Matrix> matrixList;
    private List<String> names;
    private int shownIndex;

    public ShownResultPanel(List<Matrix> matrixList, List<String> names) {
        this.matrixList = matrixList;
        this.names = names;
        initComponents(matrixList);
    }

    private void initComponents(List<Matrix> matrixList) {
        footerPanel = new JPanel();
        previousButton = new JButton();
        nextButton = new JButton();
        matrixName = new JLabel();
        shownIndex = 0;
        dataPanel = new DataPanel(matrixList.get(shownIndex));
        scrollPane = new JScrollPane();

        footerPanel.setBackground(new java.awt.Color(51, 204, 255));

        previousButton.setText("<<");
        previousButton.addActionListener(e -> previousButtonActionPerformed());

        nextButton.setText(">>");
        nextButton.addActionListener(e -> nextButtonActionPerformed());

        matrixName.setText(names.get(shownIndex));

        setFooterPanelLayout();

        dataPanel.setBackground(new java.awt.Color(102, 204, 0));
        dataPanel.setPreferredSize(new Dimension(538, 444));

        scrollPane.setViewportView(dataPanel);
        setLayout();
    }

    private void previousButtonActionPerformed() {
        if (shownIndex > 0) {
            shownIndex--;
        }
        if (shownIndex == 0) {
            previousButton.setEnabled(false);
        }
        nextButton.setEnabled(true);
        update();
    }

    private void nextButtonActionPerformed() {
        if (shownIndex < names.size()) {
            shownIndex++;
        }
        if (shownIndex == names.size() - 1) {
            nextButton.setEnabled(false);
        }
        previousButton.setEnabled(true);
        update();
    }

    private void update() {
        dataPanel.setMatrix(matrixList.get(shownIndex));
        dataPanel.update();
        matrixName.setText(names.get(shownIndex));
    }

    private void setLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(footerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }

    private void setFooterPanelLayout() {
        GroupLayout footerPanelLayout = new GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
                footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(footerPanelLayout.createSequentialGroup()
                                .addComponent(previousButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                                .addComponent(matrixName)
                                .addGap(181, 181, 181)
                                .addComponent(nextButton))
        );
        footerPanelLayout.setVerticalGroup(
                footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(footerPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(previousButton)
                                        .addComponent(nextButton)
                                        .addComponent(matrixName))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
