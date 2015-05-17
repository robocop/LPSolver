package fr.enslyon;

import fr.enslyon.DivisionRing.DivisionRing;
import fr.enslyon.DivisionRing.DoubleDivisionRing;
import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombinationException;
import fr.enslyon.Parser.LinearProgram;
import fr.enslyon.Parser.LinearProgramToSimplexEncapsulation;
import fr.enslyon.Parser.Parser;

import java.io.IOException;

/**
 * Created by quentin on 21/04/15.
 * Factory method
 */
public class FactorySolver {
    public static void solve(String pathFile, boolean debug, boolean latex, String method)
            throws IOException, LinearCombinationException, DictionaryEntryException {

        if(method.equals("rationals")) {
            DivisionRing ring = new RationalDivisionRing();
            auxSolve(ring, pathFile, latex, debug);
        }

        else if(method.equals("double")) {
            DivisionRing ring = new DoubleDivisionRing();
            auxSolve(ring, pathFile, latex, debug);
        }
    }

    @SuppressWarnings("unchecked")
    private static void auxSolve(DivisionRing ring, String pathFile, boolean latex, boolean debug)
            throws IOException, LinearCombinationException, DictionaryEntryException {

        Parser parser = new Parser();
        LinearProgram lp = parser.parse(ring, pathFile);

        LinearProgramToSimplexEncapsulation lpConverter =
                new LinearProgramToSimplexEncapsulation(lp, ring);
        lpConverter.makeUniform();
        lpConverter.computeDictionary();
        SimplexEncapsulation simplexEncapsulation = lpConverter.getLinearProgramEncapsulation();

        if(latex)
            simplexEncapsulation.setLatex();
        else if(debug)
            simplexEncapsulation.setDebug();
        else
            simplexEncapsulation.setNormal();

        simplexEncapsulation.solve();
    }
}
