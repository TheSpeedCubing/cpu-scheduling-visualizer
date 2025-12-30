package top.speedcubing.cpuschedulingvisualizer.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.speedcubing.cpuschedulingvisualizer.process.Proc;

@Getter
@RequiredArgsConstructor
public abstract class Algorithm {

    private final String name;

    public final AlgorithmResult createResult(List<Proc> procList) {
        List<Proc> cloned = new ArrayList<>(procList);
        cloned.sort(Comparator.comparingInt(Proc::getId));
        return new AlgorithmResult(this, cloned);
    }
}
