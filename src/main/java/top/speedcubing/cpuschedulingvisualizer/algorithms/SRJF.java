package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.Comparator;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;

public class SRJF extends PriorityP {

    public SRJF() {
        super("SRJF", Comparator
                .comparingInt(Proc::getBurstTime)
                .thenComparing(TieBreaker.FCFS)
        );
    }
}