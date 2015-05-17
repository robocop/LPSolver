package fr.enslyon.LinearCombination;

import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.DivisionRing.RationalNumber;
import org.junit.Assert;
import org.junit.Test;

public class LinearCombinationBaseTest {

    @Test
    public void testCopyLinearCombination() throws Exception {
        RationalDivisionRing ring = new RationalDivisionRing();
        LinearCombinationBase<RationalNumber> equ1 = new LinearCombinationBase<RationalNumber>(3, 5);
        equ1.setConstants(new RationalNumber[]{ring.fromString("1"), ring.fromString("2"), ring.fromString("3")});
        equ1.setVariables(new int[]{0, 1, 3});

        LinearCombinationBase<RationalNumber> equ2 = new LinearCombinationBase<RationalNumber>(equ1);
        equ2.setVariables(new int[]{0, 2, 3});
        equ2.setConstants(new RationalNumber[]{ring.fromString("2"), ring.fromString("3"), ring.fromString("4")});

        Assert.assertArrayEquals("failure - variables not the same",
                new int[]{0, 1, 3},
                new int[]{equ1.getVariableById(0), equ1.getVariableById(1), equ1.getVariableById(2)});

        Assert.assertArrayEquals("failure - variables not the same",
                new int[]{0, 2, 3},
                new int[]{equ2.getVariableById(0), equ2.getVariableById(1), equ2.getVariableById(2)});

    }

}