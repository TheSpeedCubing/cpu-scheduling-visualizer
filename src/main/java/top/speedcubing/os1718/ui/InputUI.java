package top.speedcubing.os1718.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import lombok.AllArgsConstructor;
import top.speedcubing.os1718.process.Proc;
import top.speedcubing.os1718.ui.input.ControlPanel;
import top.speedcubing.os1718.ui.input.PastePanel;
import top.speedcubing.os1718.ui.input.ProcTablePanel;

@AllArgsConstructor
public class InputUI {

    private final JFrame frame;

    public CompletableFuture<List<Proc>> display() {

        CompletableFuture<List<Proc>> future = new CompletableFuture<>();

        PastePanel pastePanel = new PastePanel();
        ProcTablePanel tablePanel = new ProcTablePanel();
        ControlPanel controlPanel = new ControlPanel();

        controlPanel.parseBtn.addActionListener(e ->
                tablePanel.setRowsFromText(pastePanel.getText())
        );

        controlPanel.runBtn.addActionListener(e -> {
            try {
                List<Proc> result = new ArrayList<>();
                Set<Integer> pidSet = new HashSet<>();

                var model = tablePanel.getModel();

                for (int i = 0; i < model.getRowCount(); i++) {
                    int pid = Integer.parseInt(model.getValueAt(i, 0).toString());
                    int at = Integer.parseInt(model.getValueAt(i, 1).toString());
                    int bt = Integer.parseInt(model.getValueAt(i, 2).toString());
                    int prior = Integer.parseInt(model.getValueAt(i, 3).toString());

                    if (!pidSet.add(pid)) {
                        throw new IllegalArgumentException("Duplicate PID: " + pid);
                    }
                    if (at < 0) {
                        throw new IllegalArgumentException("Arrival time must be >= 0 (PID=" + pid + ")");
                    }

                    result.add(new Proc(pid, at, bt, prior));
                }

                SwingUtilities.invokeLater(() -> {
                    future.complete(result);
                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Invalid data", JOptionPane.ERROR_MESSAGE);
            }
        });

        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                pastePanel,
                tablePanel
        );
        split.setDividerLocation(350);

        frame.setLayout(new BorderLayout());
        frame.add(split, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        return future;
    }
}
