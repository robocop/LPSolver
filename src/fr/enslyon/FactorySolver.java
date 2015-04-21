package fr.enslyon;

import fr.enslyon.DivisionRing.DoubleDivisionRing;
import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.DivisionRing.RationalNumber;
import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombinationException;
import fr.enslyon.Parser.LinearProgram;
import fr.enslyon.Parser.LinearProgramToSimplexEncapsulation;
import fr.enslyon.Parser.Parser;

import java.io.IOException;

/**
 * Created by quentin on 21/04/15.
 */
public class FactorySolver {
    public static void solve(String pathFile, boolean debug, String method)
            throws IOException, LinearCombinationException, DictionaryEntryException {
        if(method.equals("rationals")) {
            RationalDivisionRing ring = new RationalDivisionRing();
            Parser<RationalNumber> parser = new Parser<RationalNumber>();
            LinearProgram<RationalNumber> lp = parser.parse(ring, pathFile);
            LinearProgramToSimplexEncapsulation<RationalNumber> lpConverter =
                    new LinearProgramToSimplexEncapsulation<RationalNumber>(lp, ring);
            lpConverter.makeUniform();
            lpConverter.computeDictionary();
            SimplexEncapsulation<RationalNumber> simplexEncapsulation = lpConverter.getLinearProgramEncapsulation();
            simplexEncapsulation.setDebug(debug);
            simplexEncapsulation.solve();
        }

        else if(method.equals("double")) {
            DoubleDivisionRing ring = new DoubleDivisionRing();
            Parser<Double> parser = new Parser<Double>();
            LinearProgram<Double> lp = parser.parse(ring, pathFile);
            LinearProgramToSimplexEncapsulation<Double> lpConverter =
                    new LinearProgramToSimplexEncapsulation<Double>(lp, ring);
            lpConverter.makeUniform();
            lpConverter.computeDictionary();
            SimplexEncapsulation<Double> simplexEncapsulation = lpConverter.getLinearProgramEncapsulation();
            simplexEncapsulation.setDebug(debug);
            simplexEncapsulation.solve();
        }
    }


}
