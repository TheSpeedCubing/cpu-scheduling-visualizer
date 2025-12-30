package top.speedcubing.cpuschedulingvisualizer.process;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Proc {

    private final int id;

    private final int timeArrival;

    private final int burstTime;

    @Getter
    private int priority;
}
