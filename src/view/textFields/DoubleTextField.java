package view.textFields;

import utils.NumbersProtocol;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DoubleTextField extends MyTextField{
    public DoubleTextField() {
        super();
        setText("0.0");
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                banPuttingAnythingButDouble();
            }

            @Override
            public void keyTyped(KeyEvent e) {
                deleteFirstZero(e);
                savePreviousText();
            }
        });
    }

    private void banPuttingAnythingButDouble() {
        String text = getText();
        if (!NumbersProtocol.isDouble(text))
            setText(getPreviousText());
        text = getText();
        if (text.isBlank()) setText("0.0");
    }
}
