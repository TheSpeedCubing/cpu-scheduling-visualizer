package top.speedcubing.cpuschedulingvisualizer.application.algorithm;

import java.util.List;
import java.util.function.Function;
import top.speedcubing.cpuschedulingvisualizer.algorithms.AlgorithmResult;
import top.speedcubing.cpuschedulingvisualizer.algorithms.FCFS;
import top.speedcubing.cpuschedulingvisualizer.algorithms.PriorityNP;
import top.speedcubing.cpuschedulingvisualizer.algorithms.PriorityP;
import top.speedcubing.cpuschedulingvisualizer.algorithms.RR;
import top.speedcubing.cpuschedulingvisualizer.algorithms.SJF;
import top.speedcubing.cpuschedulingvisualizer.algorithms.SRJF;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;

public class AlgorithmRegistry {
    public record AlgorithmExecutor(
            String name,
            Function<List<Proc>, AlgorithmResult> execute
    ) {
    }

    public static List<AlgorithmExecutor> all(int quantum) {
        return List.of(
                new AlgorithmExecutor("FCFS", p -> new FCFS().handle(p)),
                new AlgorithmExecutor("SJF", p -> new SJF().handle(p)),
                new AlgorithmExecutor("SRJF", p -> new SRJF().handle(p)),
                new AlgorithmExecutor("Priority (NP)", p -> new PriorityNP().handle(p)),
                new AlgorithmExecutor("Priority (P)", p -> new PriorityP().handle(p)),
                new AlgorithmExecutor("Round Robin", p -> new RR().handle(p, quantum))
        );
    }
}
