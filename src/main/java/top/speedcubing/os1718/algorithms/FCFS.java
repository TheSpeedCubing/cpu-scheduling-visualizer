package top.speedcubing.os1718.algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import top.speedcubing.os1718.Gantt;
import top.speedcubing.os1718.Proc;
import top.speedcubing.os1718.ProcInfo;

public class FCFS {

    public Map<Proc, ProcInfo> info = new HashMap<>();

    public FCFS(List<Proc> processList) {

        Gantt gantt = new Gantt();

        int[] finish = new int[processList.size()];
        for (int i = 0; i < processList.size(); i++) {
            Proc p = processList.get(i);
            if (i == 0) {
                finish[i] = p.getTimeArrival() + p.getBurstTime();
                gantt.addProcState(p, finish[i]);
            } else {
                finish[i] = finish[i - 1] + p.getBurstTime();
                gantt.addProcState(p, finish[i]);
            }

            info.put(p, new ProcInfo(finish[i] - p.getTimeArrival() - p.getBurstTime(), finish[i] - p.getTimeArrival()));
        }
    }
}
