package view.common;

import javax.swing.*;
import java.awt.*;

/**
 * @author $(bilal belhaj)
 **/
public class Button extends JButton {
    public Button(String text) {
        this.setText(text);
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(52, 73, 94));
        this.setFocusPainted(false);
        this.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
}
