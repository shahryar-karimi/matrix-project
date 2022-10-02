package view.textFields;

import utils.NumbersProtocol;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntegerTextField extends MyTextField{
    public IntegerTextField() {
        super();
        setText("0");
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                banPuttingAnythingButInteger();
            }

            @Override
            public void keyTyped(KeyEvent e) {
                deleteFirstZero(e);
                savePreviousText();
            }
        });
    }

    private void banPuttingAnythingButInteger() {
        String text = getText();
        if (!NumbersProtocol.isUnsignedInteger(text))
            setText(getPreviousText());
        text = getText();
        if (text.isBlank()) setText("0");
    }
}
