package top.speedcubing.os1718.algorithms;

import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import top.speedcubing.os1718.gantt.Gantt;
import top.speedcubing.os1718.process.Proc;
import top.speedcubing.os1718.process.ProcResult;

@Getter
@ToString
public class AlgorithmResult {
    private final Algorithm algorithm;
    private final List<Proc> procList;
    private final HashMap<Proc, ProcResult> procResultMap = new HashMap<>();
    private final Gantt gantt = new Gantt();

    public AlgorithmResult(Algorithm algorithm, List<Proc> procList) {
        this.algorithm = algorithm;
        this.procList = procList;
    }

    public ProcResult getProcResult(Proc proc) {
        return procResultMap.computeIfAbsent(proc, k -> new ProcResult());
    }
}
