package xyz.hardliner.apc;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Input {
    final int machinesNumber;
    final List<Integer> production;
    final int target;

    public Input(int machinesNumber, List<Integer> production, int target) {
        if (machinesNumber != production.size()) {
            throw new IllegalArgumentException("Wrong machines number");
        }
        this.machinesNumber = machinesNumber;
        final var productionSorted = new LinkedList<>(production);
        productionSorted.sort(Comparator.comparing(Integer::valueOf));
        this.production = productionSorted;
        this.target = target;
    }
}
