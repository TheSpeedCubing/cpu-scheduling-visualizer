package top.speedcubing.cpuschedulingvisualizer.ui.input;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {

    public final JButton parseBtn = new JButton("Bulk Input →");
    public final JButton runBtn   = new JButton("Run");

    public final JTextField quantumField = new JTextField("20", 4);

    public ControlPanel() {
        add(parseBtn);

        add(runBtn);

        add(new JLabel("RR Quantum:"));
        add(quantumField);
    }

    public int getQuantum() {
        return Integer.parseInt(quantumField.getText());
    }
}