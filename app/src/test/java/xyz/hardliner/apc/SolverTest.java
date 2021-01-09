package xyz.hardliner.apc;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolverTest {

    @Test
    public void GivenTaskTest() {
        final var productions = List.of(1, 2, 4, 10, 5, 6);
        final var input = new Input(productions.size(), productions, 11);
        Solver solver = new Solver();

        final var solutions = solver.solve(input);

        assertEquals(solutions.size(), 4);
        assertTrue(solutions.contains(new Solution(0, List.of(1, 10))));
        assertTrue(solutions.contains(new Solution(0, List.of(2, 4, 5))));
        assertTrue(solutions.contains(new Solution(0, List.of(1, 4, 6))));
        assertTrue(solutions.contains(new Solution(0, List.of(5, 6))));
    }

    @Test
    public void NoSolutionsTest() {
        final var productions = List.of(1, 2, 4, 10, 5, 6);
        final var input = new Input(productions.size(), productions, 100);
        Solver solver = new Solver();

        final var solutions = solver.solve(input);

        assertEquals(solutions.size(), 0);
    }

    @Test
    public void NonZeroWasteTest() {
        final var productions = List.of(4, 10, 5, 6);
        final var input = new Input(productions.size(), productions, 17);
        Solver solver = new Solver();

        final var solutions = solver.solve(input);

        assertEquals(solutions.size(), 1);
        assertTrue(solutions.contains(new Solution(2, List.of(4, 5, 10))));
    }

    @Test
    public void NoMachinesTest() {
        final List<Integer> productions = Collections.emptyList();
        final var input = new Input(productions.size(), productions, 17);
        Solver solver = new Solver();

        final var solutions = solver.solve(input);

        assertEquals(solutions.size(), 0);
    }

    @Test
    public void EqualMachinesTest() {
        final List<Integer> productions = List.of(5, 5, 5, 5, 3, 3);
        final var input = new Input(productions.size(), productions, 17);
        Solver solver = new Solver();

        final var solutions = solver.solve(input);

        assertEquals(solutions.size(), 1);
        assertTrue(solutions.contains(new Solution(1, List.of(3, 5, 5, 5))));
    }
}