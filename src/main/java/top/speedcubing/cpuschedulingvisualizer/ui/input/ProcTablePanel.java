package top.speedcubing.cpuschedulingvisualizer.ui.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;

@Getter
public class ProcTablePanel extends JScrollPane {

    private final JFrame frame;

    private final DefaultTableModel model;

    public ProcTablePanel(JFrame frame) {
        this.frame = frame;
        this.model = new DefaultTableModel(
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
        try {
            int lineNumber = 0;
            Set<Integer> pidSet = new HashSet<>();
            for (String line : text.split("\n")) {
                int textLength = line.length();
                line = line.trim();

                lineNumber++;
                if (line.isEmpty())
                    continue;

                // let it mutable
                List<String> list = new ArrayList<>(Arrays.stream(line.split("\\s+")).toList());

                if (list.size() < 3) {
                    throw new IllegalArgumentException("Missing arguments at " + lineNumber + ":" + textLength);
                }

                if (list.size() > 4) {
                    throw new IllegalArgumentException("Exceeding arguments at " + lineNumber + ":" + textLength);
                }

                int pid = Integer.parseInt(list.get(0));
                if (!pidSet.add(pid)) {
                    throw new IllegalArgumentException("Duplicate PID: " + pid);
                }

                int at = Integer.parseInt(list.get(1));
                if (at < 0) {
                    throw new IllegalArgumentException("Arrival time must be >= 0 (PID=" + pid + ")");
                }

                if (list.size() == 3) {
                    list.add("0");
                }

                model.addRow(list.toArray(new String[]{}));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Invalid data", JOptionPane.ERROR_MESSAGE);
        }
    }
}