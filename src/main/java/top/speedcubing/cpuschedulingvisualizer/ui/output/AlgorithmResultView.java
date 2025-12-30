package top.speedcubing.cpuschedulingvisualizer.ui.output;

import javax.swing.JSplitPane;
import top.speedcubing.cpuschedulingvisualizer.algorithms.AlgorithmResult;
import top.speedcubing.cpuschedulingvisualizer.ui.GanttChartUI;
import top.speedcubing.cpuschedulingvisualizer.ui.TableUI;

public class AlgorithmResultView extends JSplitPane {

    public AlgorithmResultView(AlgorithmResult result) {
        super(JSplitPane.VERTICAL_SPLIT,
                new GanttChartUI(result),
                new TableUI(result));

        setResizeWeight(0.7);
    }
}