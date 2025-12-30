package top.speedcubing.cpuschedulingvisualizer.ui;

import javax.swing.JFrame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.speedcubing.cpuschedulingvisualizer.algorithms.AlgorithmResult;
import top.speedcubing.cpuschedulingvisualizer.ui.output.AlgorithmTabsPanel;

@Getter
@AllArgsConstructor
public class ResultUI {

    private final JFrame frame;

    public void display(AlgorithmResult... results) {

        AlgorithmTabsPanel algorithmTabsPanel = new AlgorithmTabsPanel(results);
        frame.setContentPane(algorithmTabsPanel);
        frame.revalidate();
        frame.repaint();
    }
}