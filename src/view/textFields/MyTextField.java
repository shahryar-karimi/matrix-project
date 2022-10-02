package view.textFields;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyTextField extends JTextField {
    protected String previousText;

    public MyTextField() {
        this.previousText = "";
        this.setHorizontalAlignment(JTextField.CENTER);
    }

    protected void deleteFirstZero(KeyEvent e) {
        if (getText().equals("0") && e.getKeyCode() != '.') {
            setText("");
        }
    }

    public String getPreviousText() {
        return previousText;
    }

    public void setPreviousText(String previousText) {
        this.previousText = previousText;
    }

    protected void savePreviousText() {
        setPreviousText(getText());
    }
}
