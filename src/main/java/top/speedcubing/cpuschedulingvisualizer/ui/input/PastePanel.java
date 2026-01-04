package top.speedcubing.cpuschedulingvisualizer.ui.input;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PastePanel extends JScrollPane {

    private final JTextArea area = new JTextArea();

    public PastePanel() {
        area.setBorder(BorderFactory.createTitledBorder("Paste processes here (<pid> <at> <bt> <priority (optional)>)"));
        setViewportView(area);
    }

    public String getText() {
        return area.getText();
    }
}