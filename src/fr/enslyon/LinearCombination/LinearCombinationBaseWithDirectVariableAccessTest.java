package fr.enslyon.LinearCombination;

import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.DivisionRing.RationalNumber;
import org.junit.Assert;
import org.junit.Test;

public class LinearCombinationBaseWithDirectVariableAccessTest {
    @Test
    public void testGetIndexVariable() throws Exception {
        RationalDivisionRing ring = new RationalDivisionRing();

        LinearCombinationBaseWithDirectVariableAccess<RationalNumber> equ1 =
                new LinearCombinationBaseWithDirectVariableAccess<RationalNumber>(3, 5);
        equ1.setConstants(new RationalNumber[]{ring.fromString("1"), ring.fromString("2"), ring.fromString("3")});
        equ1.setVariables(new int[]{0, 1, 3});

        Assert.assertEquals(2, equ1.getIndexVariable(3));
    }

}