package top.speedcubing.cpuschedulingvisualizer.application;

import java.util.*;
import javax.swing.table.DefaultTableModel;
import top.speedcubing.cpuschedulingvisualizer.model.Proc;

public final class ProcParser {

    public static List<Proc> fromTable(DefaultTableModel model) {
        List<Proc> list = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            int pid = Integer.parseInt(model.getValueAt(i, 0).toString());
            int at = Integer.parseInt(model.getValueAt(i, 1).toString());
            int bt = Integer.parseInt(model.getValueAt(i, 2).toString());
            int pr = Integer.parseInt(model.getValueAt(i, 3).toString());
            list.add(new Proc(pid, at, bt, pr));
        }

        if (list.isEmpty())
            throw new IllegalArgumentException("At least one process required");

        return list;
    }
}