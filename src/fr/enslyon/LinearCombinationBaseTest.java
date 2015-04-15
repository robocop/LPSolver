package fr.enslyon;

import org.junit.Test;

public class LinearCombinationBaseTest {

    @Test
    public void testCopyLinearCombination() throws Exception {
        RationalDivisionRing ring = new RationalDivisionRing();
        LinearCombinationBase<RationalNumber> equ1 = new LinearCombinationBase<RationalNumber>(3, 5);
        equ1.setConstant(ring.fromInteger(5));
        equ1.setConstants(new RationalNumber[]{ring.fromInteger(1), ring.fromInteger(2), ring.fromInteger(3)});
        equ1.setVariables(new int[]{0, 1, 3});

        LinearCombinationBase<RationalNumber> equ2 = new LinearCombinationBase<RationalNumber>(equ1);
        equ2.setConstant(ring.fromInteger(2));
        equ2.setVariables(new int[] {0, 2, 3});
        equ2.setConstants(new RationalNumber[]{ring.fromInteger(2), ring.fromInteger(3), ring.fromInteger(4)});

        org.junit.Assert.assertArrayEquals("failure - variables not the same",
                new int[] {0, 1, 3}, equ1.getVariablesLinearCombination());

        org.junit.Assert.assertArrayEquals("failure - variables not the same",
                new int[] {0, 2, 3}, equ2.getVariablesLinearCombination());

        org.junit.Assert.assertEquals(ring.fromInteger(5), equ1.getConstant());
        org.junit.Assert.assertEquals(ring.fromInteger(2), equ2.getConstant());
    }


    @Test
    public void testGetIndexVariable() throws Exception {
        RationalDivisionRing ring = new RationalDivisionRing();

        LinearCombinationBase<RationalNumber> equ1 = new LinearCombinationBase<RationalNumber>(3, 5);
        equ1.setConstant(ring.fromInteger(5));
        equ1.setConstants(new RationalNumber[]{ring.fromInteger(1), ring.fromInteger(2), ring.fromInteger(3)});
        equ1.setVariables(new int[]{0, 1, 3});

        org.junit.Assert.assertEquals(2, equ1.getIndexVariable(3));
    }

    @Test
    public void testAddVariable() throws Exception {
        RationalDivisionRing ring = new RationalDivisionRing();
        LinearCombinationBase<RationalNumber> equ1 = new LinearCombinationBase<RationalNumber>(3, 5);
        equ1.setConstant(ring.fromInteger(5));
        equ1.setConstants(new RationalNumber[]{ring.fromInteger(1), ring.fromInteger(2), ring.fromInteger(4)});
        equ1.setVariables(new int[]{0, 1, 3});

        equ1.addVariable(ring.fromInteger(-1));

        org.junit.Assert.assertArrayEquals("failure - variables not the same",
                new int[] {0, 1, 3, 5}, equ1.getVariablesLinearCombination());

        org.junit.Assert.assertArrayEquals("failure - constants not the same",
                new RationalNumber[] {ring.fromInteger(1), ring.fromInteger(2), ring.fromInteger(4), ring.fromInteger(-1)},
                equ1.getConstantsLinearCombination());
    }

}