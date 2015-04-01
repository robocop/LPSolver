package fr.enslyon;

import java.util.List;

/**
 * Created by quentin on 31/03/15.
 */
public class UnboundedSolution implements SimplexOutput {
    private int enteringVariable;
    private List<ResultVariable<List<Double>>> solution;

    UnboundedSolution(int enteringVariable, List<ResultVariable<List<Double>>> solution) {
        this.enteringVariable = enteringVariable;
        this.solution = solution;
    }
    @Override
    public void print() {
        System.out.println("Unbounded solution");
        System.out.printf("x_%d = t, t >=0\n", enteringVariable);
        for(ResultVariable<List<Double>> v: this.solution) {
            System.out.printf("x_%d = %.01f + %.01f t\n", v.getVariable(), v.getValue().get(0), v.getValue().get(1));
        }
    }
}
