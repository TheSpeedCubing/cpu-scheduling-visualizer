package top.speedcubing.os1718.ui.output;

import javax.swing.JTabbedPane;
import top.speedcubing.os1718.algorithms.AlgorithmResult;

public class AlgorithmTabsPanel extends JTabbedPane {

    public AlgorithmTabsPanel(AlgorithmResult... results) {
        for (AlgorithmResult r : results) {
            addTab(r.getAlgorithm().getName(),
                    new AlgorithmResultView(r));
        }
    }
}