package top.speedcubing.os1718.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartPanel;
import top.speedcubing.os1718.algorithms.AlgorithmResult;

public class MainUI extends JFrame {
    public MainUI(AlgorithmResult... result) {
        super("Visualization");
        setSize(800, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        for (AlgorithmResult r : result) {
            tabs.addTab(r.getAlgorithm().getName(), createView(r));
        }

        setContentPane(tabs);
    }

    private static JSplitPane createView(AlgorithmResult result) {
        ChartPanel chartPanel = new GanttChartUI(result).getPanel();
        JScrollPane tablePanel = new TableUI(result).getJScrollPane();

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                chartPanel,
                tablePanel
        );
        splitPane.setResizeWeight(0.7);
        return splitPane;
    }

    public void view() {
        MainUI ui = this;
        SwingUtilities.invokeLater(() -> ui.setVisible(true));
    }
}
