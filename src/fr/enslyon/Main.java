package fr.enslyon;

import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.DivisionRing.RationalNumber;
import fr.enslyon.LinearCombination.DictionaryEntry;
import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombination;
import fr.enslyon.LinearCombination.LinearCombinationException;
import fr.enslyon.SimplexAlgorithm.Simplex;
import fr.enslyon.SimplexAlgorithm.SimplexOutput;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {

            RationalDivisionRing ring = new RationalDivisionRing();

            // Max 2x_0 + x_1
            LinearCombination<RationalNumber> objective = new LinearCombination<RationalNumber>(2, 5, ring);
            objective.setConstant(ring.fromInteger(0));
            objective.setConstants(new RationalNumber[]{ring.fromInteger(2), ring.fromInteger(1)});
            objective.setVariables(new int[]{0, 1});

            //st -2x_0 + x_1 <= -2
            // x_2 = -2 + 2x_0 - x_1
            DictionaryEntry<RationalNumber> equ1 = new DictionaryEntry<RationalNumber>(2, 5, ring, 2);
            equ1.setConstant(ring.fromInteger(-2));
            equ1.setConstants(new RationalNumber[]{ring.fromInteger(2), ring.fromInteger(-1)});
            equ1.setVariables(new int[]{0, 1});

            //st x_0 - 2 x_1 <= -2
            // x_3 = -2 - x_0 + 2x_1
            DictionaryEntry<RationalNumber> equ2 = new DictionaryEntry<RationalNumber>(2, 5, ring, 3);
            equ2.setConstant(ring.fromInteger(-2));
            equ2.setConstants(new RationalNumber[]{ring.fromInteger(-1), ring.fromInteger(2)});
            equ2.setVariables(new int[]{0, 1});

            //st x_0 + x_1 <= 7
            // x_3 = 7 - x_0 - x_1
            DictionaryEntry<RationalNumber> equ3 = new DictionaryEntry<RationalNumber>(2, 5, ring, 4);
            equ3.setConstant(ring.fromInteger(7));
            equ3.setConstants(new RationalNumber[]{ring.fromInteger(-1), ring.fromInteger(-1)});
            equ3.setVariables(new int[]{0, 1});



            System.out.println(objective.toString());
            System.out.println(equ1.toString());
            System.out.println(equ2.toString());
            System.out.println(equ3.toString());

            ArrayList<DictionaryEntry<RationalNumber>> eqs = new ArrayList<DictionaryEntry<RationalNumber>>(3);
            eqs.add(0, equ1); eqs.add(1, equ2); eqs.add(2, equ3);

            Simplex<RationalNumber> s = new Simplex<RationalNumber>(objective, eqs, ring, true);
            SimplexOutput<RationalNumber> solution = s.solve();

            solution.print();

        }
        catch (DictionaryEntryException e) {

        }
        catch (LinearCombinationException e) {

        }
    }
}
