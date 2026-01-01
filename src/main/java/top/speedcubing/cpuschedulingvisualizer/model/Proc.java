package top.speedcubing.cpuschedulingvisualizer.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Proc {

    private final int id;

    private final int timeArrival;

    private final int burstTime;

    @Getter
    private int priority;
}
