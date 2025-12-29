package top.speedcubing.os1718.ui.gantt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lombok.Getter;
import org.jfree.data.xy.DefaultXYDataset;
import top.speedcubing.os1718.algorithms.AlgorithmResult;
import top.speedcubing.os1718.gantt.Gantt;

@Getter
public class GanttDatasetBuilder {

    public record BuildResult(DefaultXYDataset dataset, int maxStopTime) {}

    public BuildResult build(AlgorithmResult result) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        Map<Integer, List<Double>> data = new TreeMap<>();

        int maxStopTime = 0;
        for (Gantt.GanttProcState s : result.getGantt().getProcState()) {
            int id = s.getProc().getId();
            data.computeIfAbsent(id, k -> new ArrayList<>());

            maxStopTime = Math.max(maxStopTime, s.getStopTime());
            data.get(id).add((double) s.getStartTime());
            data.get(id).add((double) s.getStopTime());
            data.get(id).add(Double.NaN);
        }

        int y = 0;
        for (var e : data.entrySet()) {
            int n = e.getValue().size();
            double[] x = new double[n];
            double[] yArr = new double[n];

            for (int i = 0; i < n; i++) {
                x[i] = e.getValue().get(i);
                yArr[i] = y;
            }
            dataset.addSeries("p_" + e.getKey(), new double[][]{x, yArr});
            y++;
        }
        return new BuildResult(dataset, maxStopTime);
    }

}