package xyz.hardliner.apc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solver {

    private int minWaste;
    private Map<Task, List<Solution>> solutions;

    public List<Solution> solve(Input input) {
        minWaste = Integer.MAX_VALUE;
        solutions = new HashMap<>();
        final var initialTask = new Task(input.target, input.production);
        return solve(initialTask).stream().distinct().filter(s -> s.waste == minWaste).collect(Collectors.toList());
    }

    private List<Solution> solve(Task task) {
        if (solutions.containsKey(task)) {
            return solutions.get(task); // do nothing, this subtask already has been solved
        }

        if (task.target <= 0) { // no need to calculate subtask, required production rate reached
            final var waste = -task.target;
            if (minWaste > waste) {
                minWaste = waste; // new minimal waste solution found
            }
            return List.of(new Solution(waste, Collections.emptyList()));
        }

        if (task.options.size() == 1) { // end of machine picking options
            if (task.target - task.options.get(0) > 0) {
                return Collections.emptyList(); // no solutions here
            }
            return List.of(
                    // return the solution of single picking option
                    new Solution(task.options.get(0) - task.target, List.of(task.options.get(0)))
            );
        }

        List<Solution> subtaskSolutions = new ArrayList<>();
        for (int machineIndex = 0; machineIndex < task.options.size(); machineIndex++) {
            final var machineProduction = task.options.get(machineIndex);
            if (task.target - machineProduction < -minWaste) {
                continue; // no need to calculate, there are better solutions
            }

            final var subtask = new Task(
                    task.target - machineProduction,
                    cloneAndRemoveElementByIndex(task.options, machineIndex)
            );

            subtaskSolutions.addAll(solve(subtask).stream().map(
                    s -> {
                        // add current pick to subtask solution's pick stack
                        return new Solution(s.waste, insertAtTheBeginning(machineProduction, s.picks));
                    }).distinct().collect(Collectors.toList()));
        }

        solutions.put(task, subtaskSolutions); // All solutions for specific task have been found, remember them
        return subtaskSolutions;
    }

    private List<Integer> cloneAndRemoveElementByIndex(List<Integer> originalList, int index) {
        final var list = new LinkedList<>(originalList);
        list.remove(index);
        return list;
    }

    private List<Integer> insertAtTheBeginning(int val, List<Integer> originalList) {
        final var list = new LinkedList<>(originalList);
        list.add(0, val);
        return list;
    }

}
