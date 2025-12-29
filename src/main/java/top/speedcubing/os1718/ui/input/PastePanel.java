package top.speedcubing.os1718.ui.input;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PastePanel extends JScrollPane {

    private final JTextArea area = new JTextArea();

    public PastePanel() {
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        area.setBorder(BorderFactory.createTitledBorder("Paste processes here"));
        setViewportView(area);
    }

    public String getText() {
        return area.getText();
    }
}