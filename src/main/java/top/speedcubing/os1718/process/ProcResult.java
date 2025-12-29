package top.speedcubing.os1718.process;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProcResult {

    private int waitTime;

    private int turnaroundTime;
}
