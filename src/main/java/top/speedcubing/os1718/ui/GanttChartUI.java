package top.speedcubing.os1718.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import top.speedcubing.os1718.algorithms.AlgorithmResult;
import top.speedcubing.os1718.gantt.Gantt;
import top.speedcubing.os1718.process.Proc;

public class GanttChartUI {

    private final List<Proc> procList;
    private int maxStopTime = 0;

    private JFreeChart chart;

    public GanttChartUI(AlgorithmResult result) {
        this.procList = result.getProcList();

        DefaultXYDataset dataset = new DefaultXYDataset();

        Map<Integer, List<Double>> data = new TreeMap<>();

        for (Gantt.GanttProcState state : result.getGantt().getProcState()) {
            int id = state.getProc().getId();
            if (!data.containsKey(id)) {
                data.put(id, new ArrayList<>());
            }

            maxStopTime = Math.max(maxStopTime, state.getStopTime());

            data.get(id).add((double) state.getStartTime());
            data.get(id).add((double) state.getStopTime());
            data.get(id).add(Double.NaN);
        }

        int yIndex = 0;
        for (Map.Entry<Integer, List<Double>> e : data.entrySet()) {
            int n = e.getValue().size();

            double[] x = new double[n];
            double[] y = new double[n];

            for (int i = 0; i < n; i++) {
                x[i] = e.getValue().get(i);
                y[i] = yIndex;
            }
            yIndex++;

            dataset.addSeries(
                    "p_" + e.getKey(),
                    new double[][]{x, y}
            );
        }

        createFrame(dataset);
    }

    public ChartPanel getPanel() {
        return new ChartPanel(chart);
    }

    private void createFrame(DefaultXYDataset dataset) {
        chart = ChartFactory.createXYLineChart(
                "Gantt Chart",
                "Time",
                "Process",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        customizePlot(chart);
    }

    private void customizePlot(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        List<String> procNames = procList.stream()
                .map(p -> "p_" + p.getId())
                .toList();

        SymbolAxis yAxis = new SymbolAxis("Process", procNames.toArray(new String[]{}));
        plot.setRangeAxis(yAxis);
        yAxis.setGridBandsVisible(false);
        yAxis.setInverted(true);

        int yIndex = 0;

        Color[] colors = {Color.RED, Color.GREEN, Color.CYAN, Color.BLUE, Color.yellow};
        int index = 0;
        for(Proc ignored : procList) {
            renderer.setSeriesPaint(yIndex, colors[index++]);
            renderer.setSeriesStroke(yIndex++, new BasicStroke(6.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        }
        renderer.setDefaultShapesVisible(false);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setRange(0, maxStopTime);
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setAutoRangeIncludesZero(false);

        // sets plot background
        plot.setBackgroundPaint(Color.WHITE);

        // grid line
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        // grid line
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        plot.setRenderer(renderer);
    }
}