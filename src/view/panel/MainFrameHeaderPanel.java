package view.panel;

import view.textFields.DoubleTextField;
import view.textFields.IntegerTextField;

import javax.swing.*;

public class MainFrameHeaderPanel extends JPanel {
    private JButton refreshButton;
    private JLabel heightLabel;
    private IntegerTextField heightTextField;
    private JLabel widthLabel;
    private IntegerTextField widthTextField;
    private JLabel fillNumberLabel;
    private DoubleTextField fillNumberTextField;
    private JComboBox<String> selectionComboBox;
    private JLabel selectionLabel;

    public MainFrameHeaderPanel() {
        initComponents();
    }

    private void initComponents() {
        heightLabel = new JLabel();
        heightTextField = new IntegerTextField();
        widthLabel = new JLabel();
        widthTextField = new IntegerTextField();
        refreshButton = new JButton();
        selectionComboBox = new JComboBox<>();
        selectionLabel = new JLabel();
        fillNumberLabel = new JLabel();
        fillNumberTextField = new DoubleTextField();

        this.setBackground(new java.awt.Color(204, 55, 0));

        heightLabel.setText("Height:");

        heightTextField.setText("3");

        widthLabel.setText("Width:");

        widthTextField.setText("3");

        refreshButton.setText("Get");

        selectionComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"0", "I", "fill number"}));
        selectionComboBox.setPreferredSize(new java.awt.Dimension(100, 23));

        selectionLabel.setText("Select specified matrix");

        fillNumberLabel.setText("Fill with:");

        setHeaderLayout();
    }

    private void setHeaderLayout() {
        GroupLayout headerPanelLayout = new GroupLayout(this);
        this.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
                headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(headerPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(widthLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(heightLabel, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(heightTextField, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(widthTextField))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(selectionLabel)
                                        .addComponent(fillNumberLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(selectionComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fillNumberTextField))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refreshButton)
                                .addContainerGap())
        );
        headerPanelLayout.setVerticalGroup(
                headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(headerPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(heightLabel)
                                        .addComponent(heightTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectionLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(widthLabel)
                                        .addComponent(widthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(refreshButton)
                                        .addComponent(fillNumberLabel)
                                        .addComponent(fillNumberTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JLabel getHeightLabel() {
        return heightLabel;
    }

    public JTextField getHeightTextField() {
        return heightTextField;
    }

    public JLabel getFillNumberLabel() {
        return fillNumberLabel;
    }

    public JTextField getFillNumberTextField() {
        return fillNumberTextField;
    }

    public JComboBox<String> getSelectionComboBox() {
        return selectionComboBox;
    }

    public JLabel getSelectionLabel() {
        return selectionLabel;
    }

    public JLabel getWidthLabel() {
        return widthLabel;
    }

    public JTextField getWidthTextField() {
        return widthTextField;
    }
}
