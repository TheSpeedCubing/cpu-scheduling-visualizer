package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.*;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;

public class PriorityP extends Algorithm {

    private final Comparator<Proc> comparator;

    public PriorityP() {
        this("Priority (preemptive)", Comparator
                .comparingInt(Proc::getPriority)
                .thenComparingInt(Proc::getTimeArrival));
    }

    public PriorityP(String name, Comparator<Proc> comparator) {
        super(name);
        this.comparator = comparator;
    }

    public AlgorithmResult handle(List<Proc> procList) {
        AlgorithmResult result = createResult(procList);

        procList.sort(Comparator.comparingInt(Proc::getTimeArrival));

        List<Proc> notArrived = new ArrayList<>(procList);
        List<Proc> ready = new ArrayList<>();

        Map<Proc, Integer> remaining = new HashMap<>();
        for (Proc p : procList) {
            remaining.put(p, p.getBurstTime());
        }

        int time = 0;
        Proc current = null;
        int currentStart = -1;

        while (!ready.isEmpty() || !notArrived.isEmpty() || current != null) {
            for (int i = 0; i < notArrived.size(); ) {
                Proc p = notArrived.get(i);
                if (p.getTimeArrival() <= time) {
                    ready.add(p);
                    notArrived.remove(i);
                } else {
                    i++;
                }
            }

            Proc best = ready.stream()
                    .min(comparator)
                    .orElse(null);

            if (best != null && (current == null
                    || comparator.compare(best, current) < 0)) {

                if (current != null) {
                    result.getGantt().addProcState(current, currentStart, time);
                    ready.add(current);
                }

                ready.remove(best);
                current = best;
                currentStart = time;
            }

            if (current == null) {
                time = notArrived.stream()
                        .mapToInt(Proc::getTimeArrival)
                        .min()
                        .orElse(time);
                continue;
            }

            remaining.put(current, remaining.get(current) - 1);
            time++;

            if (remaining.get(current) == 0) {
                result.getGantt().addProcState(current, currentStart, time);

                int turnaround = time - current.getTimeArrival();
                result.getProcResult(current).setTurnaroundTime(turnaround);
                result.getProcResult(current)
                        .setWaitTime(turnaround - current.getBurstTime());

                current = null;
                currentStart = -1;
            }
        }

        return result;
    }
}
