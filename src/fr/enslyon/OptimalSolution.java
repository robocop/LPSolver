package fr.enslyon;

import java.util.List;

/**
 * Created by quentin on 30/03/15.
 */
public class OptimalSolution<T> implements SimplexOutput<T> {
    T value;
    List<ResultVariable<T>> solution;

    OptimalSolution(T value, List<ResultVariable<T>> solution) {
        this.value = value;
        this.solution = solution;
    }
    @Override
    public void print() {
        System.out.println("Optimal solution: z = " + this.value.toString());
        for(ResultVariable e: solution) {
            System.out.printf("x_%d = %s\n", e.getVariable(), e.getValue().toString());
        }
    }

    public T getValue() {
        return value;
    }

    public List<ResultVariable<T>> getSolution() {
        return this.solution;
    }
}
