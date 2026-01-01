package top.speedcubing.cpuschedulingvisualizer.ui;

import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import lombok.AllArgsConstructor;
import top.speedcubing.cpuschedulingvisualizer.application.InputController;
import top.speedcubing.cpuschedulingvisualizer.ui.input.ControlPanel;
import top.speedcubing.cpuschedulingvisualizer.ui.input.PastePanel;
import top.speedcubing.cpuschedulingvisualizer.ui.input.ProcTablePanel;

@AllArgsConstructor
public class InputPage {

    private final MainUI mainUI;

    public void init() {
        PastePanel pastePanel = new PastePanel();
        ProcTablePanel tablePanel = new ProcTablePanel(mainUI);
        ControlPanel controlPanel = new ControlPanel();

        new InputController(mainUI, pastePanel, tablePanel, controlPanel);

        JSplitPane hSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pastePanel, tablePanel);
        hSplit.setResizeWeight(0.5);

        JSplitPane vSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, hSplit, controlPanel);
        vSplit.setResizeWeight(0.9);

        mainUI.getTab().add("Input", vSplit);

        SwingUtilities.invokeLater(() -> {
            hSplit.setDividerLocation(0.5);
            vSplit.setDividerLocation(0.9);
        });
    }
}
