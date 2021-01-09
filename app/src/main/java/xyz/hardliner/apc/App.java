package xyz.hardliner.apc;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var solver = new Solver();

        System.out.println("Enter machines number:");
        final int machinesNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter machine's production:");
        final List<Integer> production = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println("Enter target production:");
        final int target = Integer.parseInt(scanner.nextLine());

        final var input = new Input(machinesNumber, production, target);
        final var solutions = solver.solve(input);

        System.out.println("Nr solutions=" + solutions.size());
        for (Solution solution : solutions) {
            System.out.println(solution.picks);
        }
        System.out.println("Waste=" + solutions.iterator().next().waste);
    }
}
