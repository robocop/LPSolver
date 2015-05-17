package fr.enslyon.LinearCombination;

import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.DivisionRing.RationalNumber;
import org.junit.Assert;
import org.junit.Test;

public class DictionaryEntryTest {

    @Test
    public void testSwap_variable() throws Exception {

        RationalDivisionRing ring = new RationalDivisionRing();

        DictionaryEntry<RationalNumber> equ1 = new DictionaryEntry<RationalNumber>(3, 5, ring, 2);
        equ1.setConstant(ring.fromString("5"));
        equ1.setConstants(new RationalNumber[]{ring.fromString("1"), ring.fromString("2"), ring.fromString("3")});
        equ1.setVariables(new int[]{0, 1, 3});
        equ1.swap_variable(1);

        Assert.assertEquals(1, equ1.getVariable());
        Assert.assertEquals(new RationalNumber(-5, 2), equ1.getConstant());

        Assert.assertArrayEquals("failure - variables not the same",
                new int[]{0, 2, 3},
                new int[] {equ1.getVariableById(0), equ1.getVariableById(1), equ1.getVariableById(2)});

        Assert.assertArrayEquals("failure - constants not the same",
                new RationalNumber[]{new RationalNumber(-1, 2), new RationalNumber(1, 2), new RationalNumber(-3, 2)},
                new RationalNumber[]{equ1.getConstantById(0), equ1.getConstantById(1), equ1.getConstantById(2)});

    }

    @Test
    public void testSwapVariableAndSubstitute() throws Exception {
        RationalDivisionRing ring = new RationalDivisionRing();

        // x_3 = 5 - 2x_0 - 3x_1 -x_2
        DictionaryEntry<RationalNumber> equ1 = new DictionaryEntry<RationalNumber>(3, 5, ring, 3);
        equ1.setConstant(ring.fromString("5"));
        equ1.setConstants(new RationalNumber[]{ring.fromString("-2"), ring.fromString("-3"), ring.fromString("-1")});
        equ1.setVariables(new int[]{0, 1, 2});

        equ1.swap_variable(0);

        // x_4 = 11-4x_0-x_1 - 2x_2
        DictionaryEntry<RationalNumber> equ2 = new DictionaryEntry<RationalNumber>(3, 6, ring, 4);
        equ2.setConstant(ring.fromString("11"));
        equ2.setConstants(new RationalNumber[]{ring.fromString("-4"), ring.fromString("-1"), ring.fromString("-2")});
        equ2.setVariables(new int[]{0, 1, 2});

        equ2.substitute(equ1);

        //v_4 = 1.0  + 2.0 * v_3  + 5.0 * v_1  + 0.0 * v_2

        Assert.assertArrayEquals("failure - variables not the same", new int[] {3, 1, 2},
                new int[] {equ2.getVariableById(0), equ2.getVariableById(1), equ2.getVariableById(2)});


        Assert.assertArrayEquals("failure - constants not the same",
                new RationalNumber[] {ring.fromString("2"), ring.fromString("5"), ring.fromString("0")},
                new RationalNumber[]{equ2.getConstantById(0), equ2.getConstantById(1), equ2.getConstantById(2)});


        Assert.assertEquals(ring.fromString("1"), equ2.getConstant());

        Assert.assertEquals(4, equ2.getVariable());


    }

}