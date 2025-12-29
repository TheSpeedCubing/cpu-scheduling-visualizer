package top.speedcubing.os1718;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import top.speedcubing.os1718.algorithms.FCFS;
import top.speedcubing.os1718.algorithms.Priority;
import top.speedcubing.os1718.algorithms.RR;
import top.speedcubing.os1718.algorithms.SJF;
import top.speedcubing.os1718.algorithms.SRJF;
import top.speedcubing.os1718.process.Proc;
import top.speedcubing.os1718.ui.MainUI;

public class Main {
    public static void main(String[] args) {
        int n = 5;

        System.out.println("Please input your processes:");
        System.out.println("(format: 'pid arrivalTime burstTime priority')");

        List<Proc> processList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println(i);
            processList.add(new Proc(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }

        new MainUI(
                new FCFS().handle(processList),
                new SJF().handle(processList),
                new SRJF().handle(processList),
                new Priority().handle(processList),
                new RR().handle(processList, 20)
        ).view();
    }
}
