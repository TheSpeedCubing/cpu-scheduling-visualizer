package top.speedcubing.os1718;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import top.speedcubing.os1718.algorithms.FCFS;

public class Main {
    public static void main(String[] args) {
        int n = 3;
        System.out.println("Please input your processes:");

        List<Proc> processList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            processList.add(new Proc());
        }
        processList.sort(Comparator.comparingInt(Proc::getTimeArrival));

        FCFS fcfs = new FCFS(processList);
        System.out.println(fcfs.info);
    }
}
