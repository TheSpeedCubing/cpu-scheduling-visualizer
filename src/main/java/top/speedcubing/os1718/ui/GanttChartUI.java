package top.speedcubing.os1718.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import top.speedcubing.os1718.algorithms.AlgorithmResult;
import top.speedcubing.os1718.gantt.Gantt;
import top.speedcubing.os1718.ui.gantt.GanttChartStyler;
import top.speedcubing.os1718.ui.gantt.GanttDatasetBuilder;

public class GanttChartUI extends ChartPanel {

    public GanttChartUI(AlgorithmResult result) {
        super(createChart(result));
        setMouseWheelEnabled(false);
        setMouseZoomable(true);
    }

    private static JFreeChart createChart(AlgorithmResult result) {

        GanttDatasetBuilder builder = new GanttDatasetBuilder();
        GanttDatasetBuilder.BuildResult build = builder.build(result);

        JFreeChart chart = ChartFactory.createXYLineChart(
                result.getAlgorithm().getName(),
                "Time",
                "Process",
                build.dataset(),
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        GanttChartStyler.apply(
                chart,
                result.getProcList(),
                build.maxStopTime()
        );

        XYPlot plot = chart.getXYPlot();

        Set<Integer> endTimes = new HashSet<>();
        for (Gantt.GanttProcState s : result.getGantt().getProcState()) {
            if (!endTimes.add(s.getStopTime())) continue;

            int t = s.getStopTime();

            ValueMarker m = new ValueMarker(t);
            m.setPaint(new Color(180, 180, 180));
            m.setStroke(new BasicStroke(1.0f));
            m.setAlpha(0.8f);

            m.setLabel(String.valueOf(t));
            m.setLabelFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));

            m.setLabelAnchor(RectangleAnchor.TOP_LEFT);
            m.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
            m.setLabelOffset(new RectangleInsets(15, 0, 0, 0));
            plot.addDomainMarker(m);
        }

        return chart;
    }
}
