package xyz.hardliner.apc;

import com.google.common.base.Objects;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    final int waste;
    final List<Integer> picks;

    public Solution(int waste, List<Integer> picks) {
        this.waste = waste;
        final var picksSorted = new LinkedList<>(picks);
        picksSorted.sort(Comparator.comparing(Integer::valueOf));
        this.picks = picksSorted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return waste == solution.waste &&
                Objects.equal(picks, solution.picks);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(waste, picks);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "waste=" + waste +
                ", picks=" + picks +
                '}';
    }
}
