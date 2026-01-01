package top.speedcubing.cpuschedulingvisualizer.application.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import top.speedcubing.cpuschedulingvisualizer.algorithms.AlgorithmResult;
import top.speedcubing.cpuschedulingvisualizer.application.algorithm.AlgorithmRegistry;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;

@AllArgsConstructor
public class SchedulerService {

    public List<AlgorithmResult> runAll(List<Proc> procs, int quantum) {
        List<AlgorithmResult> results = new ArrayList<>();

        AlgorithmRegistry.all(quantum).forEach(a ->
                results.add(a.execute().apply(procs)));

        return results;
    }
}