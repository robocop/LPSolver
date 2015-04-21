package fr.enslyon;

import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.DivisionRing.RationalNumber;
import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombinationException;
import fr.enslyon.Parser.LinearProgram;
import fr.enslyon.Parser.LinearProgramToSimplexEncapsulation;
import fr.enslyon.Parser.Parser;

import java.io.IOException;

public class Main {
    public static void printUsage() {
        System.out.println("Usage: ./toto.sh file.lp [-d | -h]");
    }
    public static void main(String[] args)
        throws DictionaryEntryException, LinearCombinationException
    {
        try {

            if(args.length == 0) {
                printUsage();
                return;
            }

            String pathFile = args[0];

            boolean debug = false;
            for(int j = 1; j < args.length; j++) {
                if (args[j].equals("-debug") || args[j].equals("-d") || args[j].equals("--debug")) {
                    debug = true;
                }
                if (args[j].equals("-help") || args[j].equals("-h") || args[j].equals("--help")) {
                    printUsage();
                    return;
                }
            }

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
        catch (IOException e) {
            System.err.println("IO error");
        }
        catch(java.lang.NumberFormatException e) {
            System.err.println("Syntax error");
            System.err.println(e.getMessage());
        }

    }
}