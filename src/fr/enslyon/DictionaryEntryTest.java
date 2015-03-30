package fr.enslyon;

public class DictionaryEntryTest {

    @org.junit.Test
    public void testChange_variable() throws Exception {
        DictionaryEntry equ1 = new DictionaryEntry(3, 2, 2);
        equ1.setConstant(5);
        equ1.setConstants(new double[] {1, 2, 3});
        equ1.setVariables(new int[] {0, 1, 3});
        equ1.change_variable(1);

        org.junit.Assert.assertEquals(1, equ1.getVariable());
        org.junit.Assert.assertEquals(-2.5, equ1.getConstant(), 0.01);

        org.junit.Assert.assertArrayEquals("failure - variables not the same",
                new int[] {0, 2, 3}, equ1.getVariablesLinearCombination());

        org.junit.Assert.assertArrayEquals("failure - constants not the same",
                new double[] {-0.5, 0.5, -1.5}, equ1.getConstantsLinearCombination(), 0.01);

    }

    @org.junit.Test
    public void testChangeVariableAndSubstitute() throws Exception {
        // x_3 = 5 - 2x_0 - 3x_1 -x_2
        DictionaryEntry equ1 = new DictionaryEntry(3, 3, 3);
        equ1.setConstant(5);
        equ1.setConstants(new double[]{-2, -3, -1});
        equ1.setVariables(new int[]{0, 1, 2});

        equ1.change_variable(0);

        // x_4 = 11-4x_0-x_1 - 2x_2
        DictionaryEntry equ2 = new DictionaryEntry(3, 3, 4);
        equ2.setConstant(11);
        equ2.setConstants(new double[]{-4, -1, -2});
        equ2.setVariables(new int[]{0, 1, 2});

        equ2.substitute(equ1);

        //v_4 = 1.0  + 2.0 * v_3  + 5.0 * v_1  + 0.0 * v_2

        org.junit.Assert.assertArrayEquals("failure - variables not the same", new int[] {3, 1, 2},
                equ2.getVariablesLinearCombination());

        org.junit.Assert.assertArrayEquals("failure - constants not the same", new double[] {2, 5, 0},
                equ2.getConstantsLinearCombination(), 0.01);

        org.junit.Assert.assertEquals(1.0, equ2.getConstant(), 0.01);

        org.junit.Assert.assertEquals(4, equ2.getVariable());


    }
}