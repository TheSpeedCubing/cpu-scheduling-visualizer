package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import top.speedcubing.cpuschedulingvisualizer.process.Proc;

public class SRJF extends Algorithm {

    public SRJF() {
        super("SRJF");
    }

    public AlgorithmResult handle(List<Proc> procList) {
        AlgorithmResult result = createResult(procList);

        List<Proc> pending = new ArrayList<>(procList);
        pending.sort(Comparator.comparingInt(Proc::getTimeArrival));

        Map<Proc, Integer> rem = new HashMap<>();
        for (Proc p : procList) rem.put(p, p.getBurstTime());

        Map<Proc, Integer> completion = new HashMap<>();

        PriorityQueue<Proc> ready = new PriorityQueue<>(
                Comparator.<Proc>comparingInt(rem::get)
                        .thenComparingInt(Proc::getTimeArrival)
                        .thenComparingInt(Proc::getId)
        );

        int time = 0;
        int idx = 0;

        Proc cur = null;
        int curStart = -1;

        while (idx < pending.size() || !ready.isEmpty() || cur != null) {

            if (cur == null && ready.isEmpty()) {
                time = Math.max(time, pending.get(idx).getTimeArrival());
            }

            while (idx < pending.size() && pending.get(idx).getTimeArrival() <= time) {
                ready.add(pending.get(idx));
                idx++;
            }

            if (cur == null) {
                if (ready.isEmpty()) continue;
                cur = ready.poll();
                curStart = time;
            }

            int nextArrival = (idx < pending.size()) ? pending.get(idx).getTimeArrival() : Integer.MAX_VALUE;
            int runFor = Math.min(rem.get(cur), nextArrival - time);

            if (runFor == 0) {
                while (idx < pending.size() && pending.get(idx).getTimeArrival() <= time) {
                    ready.add(pending.get(idx));
                    idx++;
                }

                Proc best = ready.peek();
                if (best != null && rem.get(best) < rem.get(cur)) {
                    if (curStart < time) {
                        result.getGantt().addProcState(cur, curStart, time);
                    }
                    ready.add(cur);
                    cur = ready.poll();
                    curStart = time;
                }
                continue;
            }

            rem.put(cur, rem.get(cur) - runFor);
            time += runFor;

            while (idx < pending.size() && pending.get(idx).getTimeArrival() <= time) {
                ready.add(pending.get(idx));
                idx++;
            }

            if (rem.get(cur) == 0) {
                result.getGantt().addProcState(cur, curStart, time);
                completion.put(cur, time);
                cur = null;
                curStart = -1;
                continue;
            }

            Proc best = ready.peek();
            if (best != null && rem.get(best) < rem.get(cur)) {
                result.getGantt().addProcState(cur, curStart, time);
                ready.add(cur);
                cur = null;
                curStart = -1;
            }
        }

        for (Proc p : procList) {
            int fin = completion.getOrDefault(p, p.getTimeArrival());
            int tat = fin - p.getTimeArrival();
            int wt = tat - p.getBurstTime();
            result.getProcResult(p).setTurnaroundTime(tat);
            result.getProcResult(p).setWaitTime(wt);
        }
        return result;
    }
}