package fr.enslyon;

import org.junit.Test;

public class SimplexBaseTest {

    @Test
    public void testGetOptimalSolution() throws Exception {

    }

    @Test
    public void testGetUnboundedSolution() throws Exception {
        // Max 6 + x_1 - x_3
        LinearCombination objective = new LinearCombination(2, 4);
        objective.setConstant(6);
        objective.setConstants(new double[]{1, -1});
        objective.setVariables(new int[]{1, 3});

        // x_2 = 4 + 2x_1 + x_3
        DictionaryEntry e0 = new DictionaryEntry(2, 4, 2);
        e0.setConstant(4);
        e0.setConstants(new double[]{2, 1});
        e0.setVariables(new int[]{1, 3});

        // x_0 = 4 + 2x_1 + x_3
        DictionaryEntry e1 = new DictionaryEntry(2, 4, 0);
        e1.setConstant(2);
        e1.setConstants(new double[]{0, -1});
        e1.setVariables(new int[]{1, 3});

        DictionaryEntry[] dictionary = new DictionaryEntry[] {e0, e1};
        SimplexBase s = new SimplexBase(objective, dictionary);

        SimplexOutput solution = s.solve();

        org.junit.Assert.assertTrue(solution instanceof UnboundedSolution);

    }

    @Test
    public void testSolve() throws Exception {
        // Max 5x_0 + 4x_1 + 3x_2
        LinearCombination objective = new LinearCombination(3, 6);
        objective.setConstant(0);
        objective.setConstants(new double[]{5, 4, 3});
        objective.setVariables(new int[]{0, 1, 2});

        //st 2x_0 + 3_x_1 + x_2 <= 5
        // x_3 = 5 - 2x_0 - 3x_1 -x_2
        DictionaryEntry equ1 = new DictionaryEntry(3, 6, 3);
        equ1.setConstant(5);
        equ1.setConstants(new double[]{-2, -3, -1});
        equ1.setVariables(new int[]{0, 1, 2});

        //st 4x_0+x_1+2x_2 <= 11
        // x_4 = 11-4x_0-x_1 - 2x_2
        DictionaryEntry equ2 = new DictionaryEntry(3, 6, 4);
        equ2.setConstant(11);
        equ2.setConstants(new double[]{-4, -1, -2});
        equ2.setVariables(new int[]{0, 1, 2});

        //st 3x_0+4x_1+2x_2 <= 8
        // x_5 = 8-3x_0-4x_1 - 2x_2
        DictionaryEntry equ3 = new DictionaryEntry(3, 6, 5);
        equ3.setConstant(8);
        equ3.setConstants(new double[]{-3, -4, -2});
        equ3.setVariables(new int[]{0, 1, 2});

        DictionaryEntry[] dictionary = new DictionaryEntry[] {equ1, equ2, equ3};

        SimplexBase s = new SimplexBase(objective, dictionary);
        SimplexOutput solution = s.solve();

        org.junit.Assert.assertTrue(solution instanceof OptimalSolution);
        double value = ((OptimalSolution) solution).getValue();

        org.junit.Assert.assertEquals(13.0, value, 0.01);


    }
}