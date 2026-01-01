package top.speedcubing.cpuschedulingvisualizer.ui.output;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.List;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;

public class GanttChartStyler {

    public static void apply(JFreeChart chart, List<Proc> procList, int maxTime) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer r = new XYLineAndShapeRenderer();

        List<String> labels = procList.stream()
                .map(p -> "p_" + p.getId())
                .toList();

        SymbolAxis yAxis = new SymbolAxis("Process", labels.toArray(String[]::new));
        yAxis.setInverted(true);
        yAxis.setGridBandsVisible(false);
        plot.setRangeAxis(yAxis);

        Color[] colors = {
                Color.RED, Color.GREEN, Color.CYAN,
                Color.BLUE, Color.YELLOW
        };

        for (int i = 0; i < procList.size(); i++) {
            r.setSeriesPaint(i, colors[i % colors.length]);
            r.setSeriesStroke(i,
                    new BasicStroke(6f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        }
        r.setDefaultShapesVisible(false);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setRange(0, maxTime * 1.05);
        xAxis.setAutoTickUnitSelection(true);
        xAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        xAxis.setAutoRangeIncludesZero(false);

        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(false);
        plot.setDomainCrosshairLockedOnData(false);

        plot.setRenderer(r);
    }
}
