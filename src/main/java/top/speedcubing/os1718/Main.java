package top.speedcubing.os1718;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import top.speedcubing.os1718.algorithms.FCFS;
import top.speedcubing.os1718.algorithms.Priority;
import top.speedcubing.os1718.algorithms.RR;
import top.speedcubing.os1718.algorithms.SJF;
import top.speedcubing.os1718.algorithms.SRJF;
import top.speedcubing.os1718.process.Proc;
import top.speedcubing.os1718.ui.InputUI;
import top.speedcubing.os1718.ui.MainUI;
import top.speedcubing.os1718.ui.ResultUI;

public class Main {
    public static void main(String[] args) {
        MainUI ui = new MainUI();

        CompletableFuture<List<Proc>> future = new InputUI(ui).display();
        List<Proc> processes;
        try {
            processes = future.get();
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
            return;
        }

        new ResultUI(ui).display(
                new FCFS().handle(processes),
                new SJF().handle(processes),
                new SRJF().handle(processes),
                new Priority().handle(processes),
                new RR().handle(processes, 20));
    }
}
