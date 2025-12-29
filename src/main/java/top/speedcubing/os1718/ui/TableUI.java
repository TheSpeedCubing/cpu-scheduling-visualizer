package top.speedcubing.os1718.ui;

import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import top.speedcubing.os1718.algorithms.AlgorithmResult;
import top.speedcubing.os1718.process.Proc;
import top.speedcubing.os1718.process.ProcResult;

@Getter
public class TableUI {

    private final JScrollPane jScrollPane;

    public TableUI(AlgorithmResult result) {

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
            @Override
            public Class<?> getColumnClass(int column) {
                return Integer.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        for (Proc p : result.getProcList()) {
            Integer waiting = result.getProcResult(p).getWaitTime();
            Integer turnaround = result.getProcResult(p).getTurnaroundTime();

            model.addRow(new Object[]{
                    p.getId(), p.getTimeArrival(), p.getBurstTime(), p.getPriority(), waiting, turnaround
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(22);

        this.jScrollPane = new JScrollPane(table);
    }
}
