package top.speedcubing.os1718.ui.input;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;

@Getter
public class ProcTablePanel extends JScrollPane {

    private final DefaultTableModel model;

    public ProcTablePanel() {
        model = new DefaultTableModel(
                new String[]{"PID", "Arrival", "Burst", "Priority"}, 0
        );

        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSelectionAllowed(true);
        table.setFocusable(false);

        setViewportView(table);
    }

    public void setRowsFromText(String text) {
        model.setRowCount(0);
        for (String line : text.split("\n")) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] t = line.split("\\s+");
            if (!t[0].matches("\\d+")) continue;

            model.addRow(new Object[]{t[0], t[1], t[2], t[3]});
        }
    }

}