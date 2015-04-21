package fr.enslyon;

import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombinationException;

import java.io.IOException;

public class Main {
    public static void printUsage() {
        System.out.println("Usage: ./toto file.lp [-d | -h | -double]");
    }
    public static void main(String[] args)
        throws DictionaryEntryException, LinearCombinationException
    {
        try {

            if(args.length == 0) {
                printUsage();
                return;
            }
            String pathFile = "";
            String ring = "rationals";

            boolean debug = false;
            for(int j = 0; j < args.length; j++) {
                if (args[j].equals("-debug") || args[j].equals("-d") || args[j].equals("--debug")) {
                    debug = true;
                }
                else if (args[j].equals("-help") || args[j].equals("-h") || args[j].equals("--help")) {
                    printUsage();
                    return;
                }
                else if (args[j].equals("-double")|| args[j].equals("--double")) {
                    ring = "double";
                }
                else if (args[j].equals("-rationals")|| args[j].equals("--rationals") || args[j].equals("-r")) {
                    ring = "rationals";
                }
                else {
                    pathFile = args[j];
                }
            }

            FactorySolver.solve(pathFile, debug, ring);

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