package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.Comparator;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;

public class TieBreaker {
    public static final Comparator<Proc> FCFS = Comparator.comparingInt(Proc::getTimeArrival);
}
