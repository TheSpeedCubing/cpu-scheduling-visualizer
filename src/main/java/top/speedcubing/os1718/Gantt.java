package top.speedcubing.os1718;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Gantt {
    private final List<GanttProcState> procState = new ArrayList<>();

    public void addProcState(Proc proc, int stopTime) {
        procState.add(new GanttProcState(proc, stopTime));
    }

    @Override
    public String toString() {
        String b = "";
        for (GanttProcState s : procState) {
            b += (s.proc + " " + s.stopTime) + "\n";
        }
        return b;
    }

    @AllArgsConstructor
    static class GanttProcState {
        @Getter
        Proc proc;
        @Getter
        int stopTime;
    }
}
