package top.speedcubing.cpuschedulingvisualizer.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProcResult {

    private int waitTime;

    private int turnaroundTime;
}
