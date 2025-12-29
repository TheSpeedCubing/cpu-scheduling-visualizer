package top.speedcubing.os1718.ui.input;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

    public final JButton parseBtn = new JButton("Bulk Input →");
    public final JButton runBtn   = new JButton("Run");

    public ControlPanel() {
        add(parseBtn);
        add(runBtn);
    }
}