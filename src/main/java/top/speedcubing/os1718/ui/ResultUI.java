package top.speedcubing.os1718.ui;

import javax.swing.JFrame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.speedcubing.os1718.algorithms.AlgorithmResult;
import top.speedcubing.os1718.ui.output.AlgorithmTabsPanel;

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