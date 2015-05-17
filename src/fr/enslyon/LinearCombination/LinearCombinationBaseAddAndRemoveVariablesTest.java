package fr.enslyon.LinearCombination;

import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.DivisionRing.RationalNumber;
import org.junit.Assert;
import org.junit.Test;

public class LinearCombinationBaseAddAndRemoveVariablesTest {
    @Test
    public void testAddVariable() throws Exception {
        RationalDivisionRing ring = new RationalDivisionRing();
        LinearCombinationBaseAddAndRemoveVariables<RationalNumber> equ1 =
                new  LinearCombinationBaseAddAndRemoveVariables<RationalNumber>(3, 5);
        equ1.setConstants(new RationalNumber[]{ring.fromString("1"), ring.fromString("2"), ring.fromString("4")});
        equ1.setVariables(new int[]{0, 1, 3});

        equ1.addVariable(ring.fromString("-1"));

        Assert.assertArrayEquals("failure - variables not the same",
                new int[]{0, 1, 3, 5},
                new int[]{equ1.getVariableById(0), equ1.getVariableById(1),
                        equ1.getVariableById(2), equ1.getVariableById(3)});

        Assert.assertArrayEquals("failure - constants not the same",
                new RationalNumber[] {ring.fromString("1"), ring.fromString("2"),
                        ring.fromString("4"), ring.fromString("-1")},
                new RationalNumber[]{equ1.getConstantById(0), equ1.getConstantById(1),
                        equ1.getConstantById(2), equ1.getConstantById(3)});
    }
}