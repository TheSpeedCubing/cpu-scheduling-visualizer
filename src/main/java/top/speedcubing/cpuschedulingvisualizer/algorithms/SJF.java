package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.Comparator;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;

public class SJF extends PriorityNP {

    // it's priority algorithm aswell
    public SJF() {
        super("SJF", Comparator
                .comparingInt(Proc::getBurstTime)
                .thenComparing(TieBreaker.FCFS)
        );
    }
}