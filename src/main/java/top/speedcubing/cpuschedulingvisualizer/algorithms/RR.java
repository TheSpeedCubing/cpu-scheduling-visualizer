package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import top.speedcubing.cpuschedulingvisualizer.process.Proc;

public class RR extends Algorithm {

    public RR() {
        super("RR");
    }

    public AlgorithmResult handle(List<Proc> procList, int quantum) {
        if (quantum <= 0) {
            throw new IllegalArgumentException("quantum must be > 0");
        }

        AlgorithmResult result = createResult(procList);

        Map<Proc, Integer> completion = new HashMap<>();

        List<Proc> pending = new ArrayList<>(procList);
        pending.sort(Comparator.comparingInt(Proc::getTimeArrival).thenComparingInt(Proc::getId));

        // ready queue (FIFO)
        Deque<Proc> ready = new ArrayDeque<>();

        // remaining burst time
        Map<Proc, Integer> remaining = new HashMap<>();
        for (Proc p : procList) remaining.put(p, p.getBurstTime());

        int time = 0;
        int idx = 0;

        while (idx < pending.size() || !ready.isEmpty()) {

            if (ready.isEmpty()) {
                time = Math.max(time, pending.get(idx).getTimeArrival());
                while (idx < pending.size()
                        && pending.get(idx).getTimeArrival() <= time) {
                    ready.addLast(pending.get(idx++));
                }
            }

            Proc cur = ready.removeFirst();

            int exec = Math.min(quantum, remaining.get(cur));
            int start = time;
            time += exec;

            result.getGantt().addProcState(cur, start, time);

            remaining.put(cur, remaining.get(cur) - exec);

            while (idx < pending.size()
                    && pending.get(idx).getTimeArrival() <= time) {
                ready.addLast(pending.get(idx++));
            }

            if (remaining.get(cur) > 0) {
                ready.addLast(cur);
            } else {
                completion.put(cur, time);
            }
        }

        for (Proc p : procList) {
            int ct = completion.get(p);
            int tat = ct - p.getTimeArrival();
            int wt = tat - p.getBurstTime();

            result.getProcResult(p).setTurnaroundTime(tat);
            result.getProcResult(p).setWaitTime(wt);
        }
        return result;
    }
}
