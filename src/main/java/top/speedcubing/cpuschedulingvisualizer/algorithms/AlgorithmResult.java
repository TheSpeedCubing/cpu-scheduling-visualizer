package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.speedcubing.cpuschedulingvisualizer.model.Gantt;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;
import top.speedcubing.cpuschedulingvisualizer.model.ProcResult;

@Getter
@AllArgsConstructor
public class AlgorithmResult {
    private final Algorithm algorithm;
    private final List<Proc> procList;
    private final HashMap<Proc, ProcResult> procResultMap = new HashMap<>();
    private final Gantt gantt = new Gantt();

    public ProcResult getProcResult(Proc proc) {
        return procResultMap.computeIfAbsent(proc, k -> new ProcResult());
    }
}
