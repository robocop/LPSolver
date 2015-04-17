package fr.enslyon;

    import fr.enslyon.gen.InputLexer;
        import fr.enslyon.gen.InputParser;
        import org.antlr.v4.runtime.ANTLRFileStream;
        import org.antlr.v4.runtime.CommonTokenStream;
        import org.antlr.v4.runtime.tree.ParseTree;

    import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            InputLexer lexer = new InputLexer(new ANTLRFileStream("/Users/quentin/Projet/LPSolver/pl_example.lp"));
            InputParser parser = new InputParser(new CommonTokenStream(lexer));
            parser.setBuildParseTree(true);
            ParseTree tree = parser.linearSystem();
            ParserVisitor visitor = new ParserVisitor();
            visitor.visit(tree);
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