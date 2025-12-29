package top.speedcubing.os1718.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import top.speedcubing.os1718.gantt.Gantt;
import top.speedcubing.os1718.process.Proc;

public class Priority extends Algorithm {

    private final Comparator<Proc> comparator;

    public Priority() {
        this("Priority", Comparator
                .comparingInt(Proc::getPriority)
                .thenComparingInt(Proc::getTimeArrival));
    }

    public Priority(String name, Comparator<Proc> comparator) {
        super(name);
        this.comparator = comparator;
    }

    public AlgorithmResult handle(List<Proc> procList) {
        AlgorithmResult result = createResult(procList);

        procList.sort(Comparator.comparingInt(Proc::getTimeArrival));

        List<Proc> ready = new ArrayList<>();
        List<Proc> notArrived = new ArrayList<>(procList);

        int time = 0;

        while (!ready.isEmpty() || !notArrived.isEmpty()) {

            // move those proc which arrival_time < current time into ready q
            for (int i = 0; i < notArrived.size(); ) {
                Proc p = notArrived.get(i);
                if (p.getTimeArrival() <= time) {
                    ready.add(p);
                    notArrived.remove(i);
                } else {
                    i++;
                }
            }

            // if there's no proc in ready queue, jump to the next time
            if (ready.isEmpty()) {
                time = notArrived.stream()
                        .mapToInt(Proc::getTimeArrival)
                        .min()
                        .orElse(time);
                continue;
            }

            // shorest job first
            ready.sort(comparator);
            Proc p = ready.remove(0);

// update times
            int start = time;
            int finish = start + p.getBurstTime();
            time = finish;

// update state
            result.getGantt().addProcState(p, start, finish);

            result.getProcResult(p).setWaitTime(start - p.getTimeArrival());
            result.getProcResult(p).setTurnaroundTime(finish - p.getTimeArrival());
        }
        return result;
    }
}