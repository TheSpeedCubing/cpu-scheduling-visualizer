package top.speedcubing.os1718;


import java.util.Scanner;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Proc {

    @Getter
    private final int burstTime;

    @Getter
    private final int timeArrival;

    @Getter
    private final int id;



    public Proc() {
        Scanner s = new Scanner(System.in);
        System.out.println("timeArrival (what time is it?):");
        timeArrival = s.nextInt();
        System.out.println("pid:");
        id = s.nextInt();
        System.out.println("burstTime:");
        burstTime = s.nextInt();
    }
}
