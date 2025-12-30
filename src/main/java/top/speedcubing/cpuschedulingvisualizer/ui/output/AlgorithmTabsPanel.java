package top.speedcubing.cpuschedulingvisualizer.ui.output;

import javax.swing.JTabbedPane;
import top.speedcubing.cpuschedulingvisualizer.algorithms.AlgorithmResult;

public class AlgorithmTabsPanel extends JTabbedPane {

    public AlgorithmTabsPanel(AlgorithmResult... results) {
        for (AlgorithmResult r : results) {
            addTab(r.getAlgorithm().getName(),
                    new AlgorithmResultView(r));
        }
    }
}