package top.speedcubing.cpuschedulingvisualizer.ui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import top.speedcubing.cpuschedulingvisualizer.algorithms.AlgorithmResult;
import top.speedcubing.cpuschedulingvisualizer.process.Proc;

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

        for (Proc p : result.getProcList()) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getTimeArrival(),
                    p.getBurstTime(),
                    p.getPriority(),
                    result.getProcResult(p).getWaitTime(),
                    result.getProcResult(p).getTurnaroundTime()
            });
        }
        return model;
    }
}