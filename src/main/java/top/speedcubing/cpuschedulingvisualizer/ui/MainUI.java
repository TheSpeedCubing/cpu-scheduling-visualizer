package top.speedcubing.cpuschedulingvisualizer.ui;

import javax.swing.JFrame;
import lombok.Getter;

@Getter
public final class MainUI extends JFrame {

    private final MainTabbedPanel tab = new MainTabbedPanel();

    public MainUI() {
        super("CPU Scheduling Visualizer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);
        setContentPane(tab);

        new InputPage(this).init();

        setVisible(true);
    }
}