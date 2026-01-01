package top.speedcubing.cpuschedulingvisualizer.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Gantt {

    private final List<GanttProcState> procState = new ArrayList<>();

    public void addProcState(Proc proc, int startTime, int stopTime) {
        procState.add(new GanttProcState(proc, startTime, stopTime));
    }

    @Getter
    @AllArgsConstructor
    public static class GanttProcState {
        Proc proc;
        int startTime;
        int stopTime;
    }
}
