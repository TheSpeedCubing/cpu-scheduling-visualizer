package top.speedcubing.os1718.algorithms;

import java.util.Comparator;
import top.speedcubing.os1718.process.Proc;

public class SJF extends Priority {

    public SJF() {
        super("SJF", Comparator.comparingInt(Proc::getBurstTime));
    }
}