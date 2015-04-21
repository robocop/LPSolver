package fr.enslyon;

import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.DivisionRing.RationalNumber;
import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombinationException;
import fr.enslyon.Parser.LinearProgram;
import fr.enslyon.Parser.LinearProgramToSimplexEncapsulation;
import fr.enslyon.Parser.ParserVisitor;
import fr.enslyon.Parser.gen.InputLexer;
import fr.enslyon.Parser.gen.InputParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {
    public static void main(String[] args)
        throws DictionaryEntryException, LinearCombinationException
    {
        try {
            InputLexer lexer = new InputLexer(new ANTLRFileStream("/Users/quentin/Projet/LPSolver/ComplexExample.lp"));
            InputParser parser = new InputParser(new CommonTokenStream(lexer));
            parser.setBuildParseTree(true);
            ParseTree tree = parser.linearSystem();

            RationalDivisionRing ring = new RationalDivisionRing();

            ParserVisitor<RationalNumber> visitor = new ParserVisitor<RationalNumber>(ring);
            visitor.visit(tree);

            LinearProgram<RationalNumber> lp = visitor.getLinearProgram();
            LinearProgramToSimplexEncapsulation<RationalNumber> lpConverter =
                    new LinearProgramToSimplexEncapsulation<RationalNumber>(lp, ring);

            lpConverter.makeUniform();
            lpConverter.computeDictionary();
            SimplexEncapsulation<RationalNumber> simplexEncapsulation = lpConverter.getLinearProgramEncapsulation();

            //simplexEncapsulation.setDebug(true);

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