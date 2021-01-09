package xyz.hardliner.apc;

import com.google.common.base.Objects;

import java.util.List;

public class Task {
    final int target;
    final List<Integer> options;

    public Task(int target, List<Integer> options) {
        this.target = target;
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return target == task.target &&
                Objects.equal(options, task.options);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(target, options);
    }

    @Override
    public String toString() {
        return "Task{" +
                "target=" + target +
                ", options=" + options +
                '}';
    }
}
