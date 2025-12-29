package top.speedcubing.os1718.gantt;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import top.speedcubing.os1718.process.Proc;

@ToString
@Getter
public class Gantt {

    private final List<GanttProcState> procState = new ArrayList<>();

    public void addProcState(Proc proc, int startTime, int stopTime) {
        procState.add(new GanttProcState(proc, startTime, stopTime));
    }

    @ToString
    @Getter
    @AllArgsConstructor
    public static class GanttProcState {
        Proc proc;
        int startTime;
        int stopTime;
    }
}
