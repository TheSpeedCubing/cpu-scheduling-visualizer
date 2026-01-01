package top.speedcubing.cpuschedulingvisualizer.ui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import top.speedcubing.cpuschedulingvisualizer.algorithms.AlgorithmResult;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;

@Getter
public class TableUI extends JScrollPane {

    public TableUI(AlgorithmResult result) {
        JTable table = new JTable(createModel(result));
        table.setRowHeight(22);
        table.setFocusable(false);
        setViewportView(table);
    }

    private DefaultTableModel createModel(AlgorithmResult result) {

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{
                        "Process",
                        "Arrival Time",
                        "Burst Time",
                        "Priority",
                        "Waiting Time",
                        "Turnaround Time"
                },
                0
        ) {

            @Override public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        int totalWt = 0;
        int totalTt = 0;
        for (Proc p : result.getProcList()) {
            totalWt += result.getProcResult(p).getWaitTime();
            totalTt += result.getProcResult(p).getTurnaroundTime();
            model.addRow(new Object[]{
                    p.getId(),
                    p.getTimeArrival(),
                    p.getBurstTime(),
                    p.getPriority(),
                    result.getProcResult(p).getWaitTime(),
                    result.getProcResult(p).getTurnaroundTime()
            });
        }
        model.addRow(new Object[]{null, null, null, "Average: ",
                "%.3f".formatted((double) totalWt / result.getProcList().size()),
                "%.3f".formatted((double) totalTt / result.getProcList().size())});
        return model;
    }
}