package top.speedcubing.os1718;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class GanttChartUI extends JFrame {

    public GanttChartUI() {
        super("Gantt Chart");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultXYDataset dataset = new DefaultXYDataset();

        dataset.addSeries("p_1", new double[][]{
                {0, 3, Double.NaN, 4, 5},
                {1, 1, Double.NaN, 1, 1}
        });
        dataset.addSeries("p_2", new double[][]{
                {3, 6}, {2, 2}
        });
        dataset.addSeries("p_3", new double[][]{
                {6, 12}, {3, 3}
        });

        JFreeChart chart = ChartFactory.createXYLineChart(
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
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private void customizePlot(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.BLUE);

        renderer.setSeriesStroke(0, new BasicStroke(6.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        renderer.setSeriesStroke(1, new BasicStroke(6.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        renderer.setSeriesStroke(2, new BasicStroke(6.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        renderer.setDefaultShapesVisible(false);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setRange(0, 12);
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setAutoRangeIncludesZero(false);

        // y axis
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setRange(0, 6);
        yAxis.setInverted(true);

        // sets plot background
        plot.setBackgroundPaint(Color.DARK_GRAY);

        // grid line
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        // grid line
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        plot.setRenderer(renderer);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GanttChartUI().setVisible(true);
            }
        });
    }
}