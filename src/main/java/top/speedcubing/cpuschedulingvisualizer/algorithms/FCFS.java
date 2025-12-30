package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.Comparator;
import java.util.List;
import top.speedcubing.cpuschedulingvisualizer.process.Proc;

public class FCFS extends Algorithm {

    public FCFS() {
        super("FCFS");
    }

    public AlgorithmResult handle(List<Proc> procList) {
        AlgorithmResult result = createResult(procList);


        procList.sort(Comparator.comparingInt(Proc::getTimeArrival));

        int[] finish = new int[procList.size()];
        for (int i = 0; i < procList.size(); i++) {
            Proc p = procList.get(i);

            int prev = i == 0 ? p.getTimeArrival() : finish[i - 1];

            finish[i] = prev + p.getBurstTime();
            result.getGantt().addProcState(p, prev, finish[i]);

            result.getProcResult(p).setWaitTime(finish[i] - p.getTimeArrival() - p.getBurstTime());
            result.getProcResult(p).setTurnaroundTime(finish[i] - p.getTimeArrival());
        }
        return result;
    }
}
