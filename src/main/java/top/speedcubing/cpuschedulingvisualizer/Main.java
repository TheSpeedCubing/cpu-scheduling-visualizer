package top.speedcubing.cpuschedulingvisualizer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import top.speedcubing.cpuschedulingvisualizer.algorithms.FCFS;
import top.speedcubing.cpuschedulingvisualizer.algorithms.Priority;
import top.speedcubing.cpuschedulingvisualizer.algorithms.RR;
import top.speedcubing.cpuschedulingvisualizer.algorithms.SJF;
import top.speedcubing.cpuschedulingvisualizer.algorithms.SRJF;
import top.speedcubing.cpuschedulingvisualizer.ui.InputUI;
import top.speedcubing.cpuschedulingvisualizer.ui.MainUI;
import top.speedcubing.cpuschedulingvisualizer.ui.ResultUI;

public class Main {
    public static void main(String[] args) {
        MainUI ui = new MainUI();

        CompletableFuture<InputUI.InputResult> future = new InputUI(ui).display();
        InputUI.InputResult inputResult;
        try {
            inputResult = future.get();
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
            return;
        }

        new ResultUI(ui).display(
                new FCFS().handle(inputResult.procList()),
                new SJF().handle(inputResult.procList()),
                new SRJF().handle(inputResult.procList()),
                new Priority().handle(inputResult.procList()),
                new RR().handle(inputResult.procList(), inputResult.quantum()));
    }
}
