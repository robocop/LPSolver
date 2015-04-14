package fr.enslyon;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SimplexTest {

    @Test
    public void testSolve() throws Exception {
        // Max 2x_0 + x_1
        LinearCombination objective = new LinearCombination(2, 5);
        objective.setConstant(0);
        objective.setConstants(new double[]{2, 1});
        objective.setVariables(new int[]{0, 1});

        //st -2x_0 + x_1 <= -2
        // x_2 = -2 + 2x_0 - x_1
        DictionaryEntry equ1 = new DictionaryEntry(2, 5, 2);
        equ1.setConstant(-2);
        equ1.setConstants(new double[]{2, -1});
        equ1.setVariables(new int[]{0, 1});

        //st x_0 - 2 x_1 <= -2
        // x_3 = -2 - x_0 + 2x_1
        DictionaryEntry equ2 = new DictionaryEntry(2, 5, 3);
        equ2.setConstant(-2);
        equ2.setConstants(new double[]{-1, 2});
        equ2.setVariables(new int[]{0, 1});

        //st x_0 + x_1 <= 7
        // x_3 = 7 - x_0 - x_1
        DictionaryEntry equ3 = new DictionaryEntry(2, 5, 4);
        equ3.setConstant(7);
        equ3.setConstants(new double[]{-1, -1});
        equ3.setVariables(new int[]{0, 1});

        DictionaryEntry[] eqs = new DictionaryEntry[] {equ1, equ2, equ3};

        Simplex s = new Simplex(objective, eqs);
        SimplexOutput solution = s.solve();

        org.junit.Assert.assertTrue(solution instanceof OptimalSolution);
        double value = ((OptimalSolution) solution).getValue();
        org.junit.Assert.assertEquals(11., value, 0.01);

        List<ResultVariable> solutionValues = ((OptimalSolution) solution).getSolution();
        List<ResultVariable> expectedSolution = new LinkedList<ResultVariable>();
        expectedSolution.add(new ResultVariable(0, new Double(4.0)));
        expectedSolution.add(new ResultVariable(1, new Double(3.0)));

        int variablesCompared = 0;
        for(ResultVariable v1: expectedSolution) {
            for(ResultVariable v2: solutionValues) {
                if(v1.getVariable() == v2.getVariable()) {
                    org.junit.Assert.assertEquals((Double) v1.getValue(), (Double) v2.getValue(), 0.01);
                    variablesCompared++;
                }
            }
        }

        org.junit.Assert.assertEquals(2, variablesCompared);


    }

    @Test
    public void testComputeAVertex() throws Exception {
        // Max 2x_0 + x_1
        LinearCombination objective = new LinearCombination(2, 5);
        objective.setConstant(0);
        objective.setConstants(new double[]{2, 1});
        objective.setVariables(new int[]{0, 1});

        //st -2x_0 + x_1 <= -2
        // x_2 = -2 + 2x_0 - x_1
        DictionaryEntry equ1 = new DictionaryEntry(2, 5, 2);
        equ1.setConstant(-2);
        equ1.setConstants(new double[]{2, -1});
        equ1.setVariables(new int[]{0, 1});

        //st x_0 - 2 x_1 <= -2
        // x_3 = -2 - x_0 + 2x_1
        DictionaryEntry equ2 = new DictionaryEntry(2, 5, 3);
        equ2.setConstant(-2);
        equ2.setConstants(new double[]{-1, 2});
        equ2.setVariables(new int[]{0, 1});

        //st x_0 + x_1 <= 7
        // x_3 = 7 - x_0 - x_1
        DictionaryEntry equ3 = new DictionaryEntry(2, 5, 4);
        equ3.setConstant(7);
        equ3.setConstants(new double[]{-1, -1});
        equ3.setVariables(new int[]{0, 1});

        DictionaryEntry[] eqs = new DictionaryEntry[] {equ1, equ2, equ3};

        Simplex s = new Simplex(objective, eqs);
        SimplexOutput vertex = s.computeAVertex();

        org.junit.Assert.assertTrue(vertex instanceof OptimalSolution);

        org.junit.Assert.assertEquals(0.0, ((OptimalSolution) vertex).getValue(), 0.01);

        org.junit.Assert.assertEquals(2.0, ((OptimalSolution) vertex).getSolution().get(0).getValue());
        org.junit.Assert.assertEquals(2.0, ((OptimalSolution) vertex).getSolution().get(1).getValue());

    }
}