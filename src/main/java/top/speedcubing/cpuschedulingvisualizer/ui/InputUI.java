package top.speedcubing.cpuschedulingvisualizer.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import lombok.AllArgsConstructor;
import top.speedcubing.cpuschedulingvisualizer.process.Proc;
import top.speedcubing.cpuschedulingvisualizer.ui.input.ControlPanel;
import top.speedcubing.cpuschedulingvisualizer.ui.input.PastePanel;
import top.speedcubing.cpuschedulingvisualizer.ui.input.ProcTablePanel;

@AllArgsConstructor
public class InputUI {

    public static record InputResult(List<Proc> procList, int quantum){}

    private final JFrame frame;

    public CompletableFuture<InputResult> display() {

        CompletableFuture<InputResult> future = new CompletableFuture<>();

        PastePanel pastePanel = new PastePanel();
        ProcTablePanel tablePanel = new ProcTablePanel(frame);
        ControlPanel controlPanel = new ControlPanel();

        controlPanel.parseBtn.addActionListener(e ->
                tablePanel.setRowsFromText(pastePanel.getText())
        );

        controlPanel.runBtn.addActionListener(e -> {
            try {
                List<Proc> procList = new ArrayList<>();

                int quantum = controlPanel.getQuantum();
                if (quantum <= 0) {
                    throw new IllegalArgumentException("Quantum must be > 0");
                }

                var model = tablePanel.getModel();

                for (int i = 0; i < model.getRowCount(); i++) {
                    int pid = Integer.parseInt(model.getValueAt(i, 0).toString());
                    int at = Integer.parseInt(model.getValueAt(i, 1).toString());
                    int bt = Integer.parseInt(model.getValueAt(i, 2).toString());
                    int prior = Integer.parseInt(model.getValueAt(i, 3).toString());

                    procList.add(new Proc(pid, at, bt, prior));
                }

                SwingUtilities.invokeLater(() -> {
                    future.complete(new InputResult(procList, quantum));
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
