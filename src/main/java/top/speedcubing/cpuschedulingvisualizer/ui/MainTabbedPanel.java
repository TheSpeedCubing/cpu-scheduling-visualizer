package top.speedcubing.cpuschedulingvisualizer.ui;

import javax.swing.JTabbedPane;

public class MainTabbedPanel extends JTabbedPane {
    public void removeAll() {
        while (getTabCount() != 1) {
            remove(1);
        }
    }

    public void add() {

    }
}
