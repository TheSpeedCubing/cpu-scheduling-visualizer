package top.speedcubing.cpuschedulingvisualizer.application;

import java.util.List;
import javax.swing.JOptionPane;
import lombok.AllArgsConstructor;
import top.speedcubing.cpuschedulingvisualizer.algorithms.AlgorithmResult;
import top.speedcubing.cpuschedulingvisualizer.application.service.SchedulerService;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;
import top.speedcubing.cpuschedulingvisualizer.ui.MainUI;
import top.speedcubing.cpuschedulingvisualizer.ui.input.ControlPanel;
import top.speedcubing.cpuschedulingvisualizer.ui.input.PastePanel;
import top.speedcubing.cpuschedulingvisualizer.ui.input.ProcTablePanel;
import top.speedcubing.cpuschedulingvisualizer.ui.output.AlgorithmResultPanel;

public class InputController {

    private final MainUI mainUI;
    private final PastePanel pastePanel;
    private final ProcTablePanel tablePanel;
    private final ControlPanel controlPanel;

    private final SchedulerService scheduler = new SchedulerService();

    public InputController(
            MainUI mainUI,
            PastePanel pastePanel,
            ProcTablePanel tablePanel,
            ControlPanel controlPanel
    ) {
        this.mainUI = mainUI;
        this.pastePanel = pastePanel;
        this.tablePanel = tablePanel;
        this.controlPanel = controlPanel;
        bind();
    }

    private void bind() {
        controlPanel.parseBtn.addActionListener(e ->
                tablePanel.setRowsFromText(pastePanel.getText())
        );

        controlPanel.runBtn.addActionListener(e -> run());
    }

    private void run() {
        try {
            int quantum = controlPanel.getQuantum();
            List<Proc> procList = ProcParser.fromTable(tablePanel.getModel());

            List<AlgorithmResult> results =
                    scheduler.runAll(procList, quantum);

            while (mainUI.getTab().getTabCount() != 1) {
                mainUI.getTab().remove(1);
            }

            for(AlgorithmResult r : results) {
                mainUI.getTab().add(r.getAlgorithm().getName(),
                        new AlgorithmResultPanel(r));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    mainUI, ex.getMessage(), "Invalid data", JOptionPane.ERROR_MESSAGE);
        }
    }
}
