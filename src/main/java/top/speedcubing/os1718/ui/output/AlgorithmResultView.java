package top.speedcubing.os1718.ui.output;

import javax.swing.JSplitPane;
import top.speedcubing.os1718.algorithms.AlgorithmResult;
import top.speedcubing.os1718.ui.GanttChartUI;
import top.speedcubing.os1718.ui.TableUI;

public class AlgorithmResultView extends JSplitPane {

    public AlgorithmResultView(AlgorithmResult result) {
        super(JSplitPane.VERTICAL_SPLIT,
                new GanttChartUI(result),
                new TableUI(result));

        setResizeWeight(0.7);
    }
}