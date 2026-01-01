package top.speedcubing.cpuschedulingvisualizer;

import javax.swing.SwingUtilities;
import top.speedcubing.cpuschedulingvisualizer.ui.MainUI;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainUI::new);
    }
}
