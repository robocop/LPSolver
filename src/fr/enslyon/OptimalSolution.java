package fr.enslyon;

import java.util.List;

/**
 * Created by quentin on 30/03/15.
 */
public class OptimalSolution implements SimplexOutput {
    double value;
    List<ResultVariable> solution;

    OptimalSolution(double value, List<ResultVariable> solution) {
        this.value = value;
        this.solution = solution;
    }
    @Override
    public void print() {
        System.out.println("Optimal solution: z = " + this.value);
        for(ResultVariable e: solution) {
            System.out.printf("x_%d = %.02f\n", e.getVariable(), e.getValue());
        }
    }

    public double getValue() {
        return value;
    }
}
