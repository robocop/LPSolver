package fr.enslyon.SimplexAlgorithm;

import java.util.List;

/**
 * Created by quentin on 31/03/15.
 */
public class UnboundedSolution<T> implements SimplexOutput<T> {
    private int enteringVariable;
    private List<ResultVariable<List<T>>> solution;

    UnboundedSolution(int enteringVariable, List<ResultVariable<List<T>>> solution) {
        this.enteringVariable = enteringVariable;
        this.solution = solution;
    }
    @Override
    public void print() {
        System.out.println("Unbounded solution");
        System.out.printf("x_%d = t, t>=0\n", enteringVariable);
        for(ResultVariable<List<T>> v: this.solution) {
            System.out.printf("x_%d = %s + %s t\n", v.getVariable(), v.getValue().get(0), v.getValue().get(1));
        }
    }
}
