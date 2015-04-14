package fr.enslyon;

import org.junit.Test;

public class LinearCombinationTest {

    @Test
    public void testCopyLinearCombination() throws Exception {
        LinearCombination equ1 = new LinearCombination(3, 5);
        equ1.setConstant(5);
        equ1.setConstants(new double[]{1, 2, 3});
        equ1.setVariables(new int[]{0, 1, 3});

        LinearCombination equ2 = new LinearCombination(equ1);
        equ2.setConstant(2);
        equ2.setVariables(new int[] {0, 2, 3});
        equ2.setConstants(new double[] {2, 3, 4});

        org.junit.Assert.assertArrayEquals("failure - variables not the same",
                new int[] {0, 1, 3}, equ1.getVariablesLinearCombination());

        org.junit.Assert.assertArrayEquals("failure - variables not the same",
                new int[] {0, 2, 3}, equ2.getVariablesLinearCombination());

        org.junit.Assert.assertEquals(5, equ1.getConstant(), 0.01);
        org.junit.Assert.assertEquals(2, equ2.getConstant(), 0.01);

        equ1.setConstant(12);
        equ1.setConstants(new double[] {0, 1, 7});

        org.junit.Assert.assertEquals(12, equ1.getConstant(), 0.01);
        org.junit.Assert.assertEquals(2, equ2.getConstant(), 0.01);


    }
    @Test
    public void testGetIndexVariable() throws Exception {
        LinearCombination equ1 = new LinearCombination(3, 5);
        equ1.setConstant(5);
        equ1.setConstants(new double[]{1, 2, 3});
        equ1.setVariables(new int[]{0, 1, 3});

        org.junit.Assert.assertEquals(2, equ1.getIndexVariable(3));
    }

    @Test
    public void testAddVariable() throws Exception {
        LinearCombination equ1 = new LinearCombination(3, 5);
        equ1.setConstant(5);
        equ1.setConstants(new double[]{1, 2, 4});
        equ1.setVariables(new int[]{0, 1, 3});

        equ1.addVariable(-1.0);

        org.junit.Assert.assertArrayEquals("failure - variables not the same",
                new int[] {0, 1, 3, 5}, equ1.getVariablesLinearCombination());

        org.junit.Assert.assertArrayEquals("failure - constants not the same",
                new double[] {1, 2, 4, -1}, equ1.getConstantsLinearCombination(), 0.01);

    }

    @Test
    public void testSubstitute() throws Exception {
        //v_2 = 5.0  + 1.0 * v_0  + 2.0 * v_1  + 3.0 * v_3

        LinearCombination equ1 = new DictionaryEntry(3, 5, 2);
        equ1.setConstant(5);
        equ1.setConstants(new double[]{1, 2, 3});
        equ1.setVariables(new int[]{0, 1, 3});

        //v_3 = 7.0  + 2.0 * v_0  + 1.0 * v_1  + 1.0 * v_4
        DictionaryEntry equ2 = new DictionaryEntry(3, 5, 3);
        equ2.setConstant(7);
        equ2.setConstants(new double[]{2, 1, 1});
        equ2.setVariables(new int[]{0, 1, 4});

        equ1.substitute(equ2); // -> v_2 = 26.0  + 7.0 * v_0  + 5.0 * v_1  + 3.0 * v_4

        org.junit.Assert.assertArrayEquals("failure - variables not the same",
                new int[]{0, 1, 4}, equ1.getVariablesLinearCombination());

        org.junit.Assert.assertEquals(26.0, equ1.getConstant(), 0.01);

        org.junit.Assert.assertArrayEquals("failure - constants not the same",
                new double[]{7.0, 5.0, 3.0}, equ1.getConstantsLinearCombination(), 0.01);

    }

}