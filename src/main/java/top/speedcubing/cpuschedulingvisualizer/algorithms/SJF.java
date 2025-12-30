package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.Comparator;
import top.speedcubing.cpuschedulingvisualizer.process.Proc;

public class SJF extends Priority {

    // it's priority algorithm aswell
    public SJF() {
        super("SJF", Comparator.comparingInt(Proc::getBurstTime));
    }
}