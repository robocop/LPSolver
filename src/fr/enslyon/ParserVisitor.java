package fr.enslyon;

import fr.enslyon.DivisionRing.RationalDivisionRing;
import fr.enslyon.DivisionRing.RationalNumber;
import fr.enslyon.gen.InputBaseVisitor;
import fr.enslyon.gen.InputParser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;


/**
 * Created by quentin on 16/04/15.
 */
public class ParserVisitor extends InputBaseVisitor<Value> {
    private RationalDivisionRing ring = new RationalDivisionRing();
    LinearProgram<RationalNumber> linearProgram = new LinearProgram<RationalNumber>();

    public LinearProgram<RationalNumber> getLinearProgram() {
        return linearProgram;
    }
    private RationalNumber readFloat(ParseTree ctx) {
        return new RationalNumber(Double.valueOf(ctx.getText()));
    }

    @Override public Value visitLinearSystem(InputParser.LinearSystemContext ctx) {
        Boolean isMaximize = ctx.objective().getText().equals("MAXIMIZE");

        SyntacticLinearCombination<RationalNumber> linearObj =
                visitLinearCombination(ctx.linearCombination()).asSyntacticLinearCombination();

        Objective<RationalNumber> objective = new Objective<RationalNumber>(ring, isMaximize, linearObj);

        linearProgram.setObjective(objective);

        visitInequalities(ctx.inequalities());
        visitBounds(ctx.bounds());
        visitVariablesList(ctx.variablesList());


        return new Value(null);
    }


    @Override public Value visitLinearCombination(InputParser.LinearCombinationContext ctx) {
        SyntacticLinearCombination<RationalNumber> l = new SyntacticLinearCombination<RationalNumber>(ring);
        Boolean isPlus = true;
        for(ParseTree child: ctx.children) {
            if(child.getText().equals("+")) {
                isPlus = true;
            }
            else if(child.getText().equals("-")) {
                isPlus = false;
            }
            else {
                Value v = visit(child);
                Item<RationalNumber> item = v.asItem();
                if(!isPlus) {
                    item.setConstant(ring.opposite(item.getConstant()));
                }
                l.setOrUpdateVariable(item.getVariable(), item.getConstant());
            }
        }
        return new Value(l);
    }

    @Override public Value visitConstantAndVariableItem(@NotNull InputParser.ConstantAndVariableItemContext ctx) {
        RationalNumber value = this.readFloat(ctx.Float());
        return new Value(new Item<RationalNumber>(ctx.Variable().getText(), value));
    }
    @Override public Value visitConstantItem(@NotNull InputParser.ConstantItemContext ctx) {
        RationalNumber value = this.readFloat(ctx.Float());
        return new Value(new Item<RationalNumber>(value));
    }
    @Override public Value visitVariableItem(@NotNull InputParser.VariableItemContext ctx) {
        return new Value(new Item<RationalNumber>(ctx.Variable().getText(), ring.fromInteger(1)));
    }
    @Override public Value visitFirstItem(InputParser.FirstItemContext ctx) {
        Item<RationalNumber> item = visit(ctx.item()).asItem();
        if(ctx.getText().charAt(0) == '-' ) {
            item.setConstant(ring.opposite(item.getConstant()));
        }
        return new Value(item);
    }


    @Override public Value visitInequalities(InputParser.InequalitiesContext ctx) {
        Inequalities<RationalNumber> inequalities = new Inequalities<RationalNumber>();
        linearProgram.setInequalities(inequalities);
        for(ParseTree ctxChild: ctx.children) {
            visit(ctxChild);
        }
        return new Value(null);
    }

    @Override public Value visitInequality(InputParser.InequalityContext ctx) {
        SyntacticLinearCombination<RationalNumber> l =
                visitLinearCombination(ctx.linearCombination()).asSyntacticLinearCombination();
        boolean isGreater = (ctx.comparisonOrEqual().getText().equals(">="));


        RationalNumber constant = this.readFloat(ctx.Float());
        if(ctx.operator() != null) {
            if (ctx.operator().getText().equals("-")) {
                constant = ring.opposite(constant);
            }
        }

        Inequality<RationalNumber> inequality = new Inequality<RationalNumber>(ring, l, isGreater, constant);
        linearProgram.getInequalities().add(inequality);

        if(ctx.comparisonOrEqual().getText().equals("=")) {
            SyntacticLinearCombination<RationalNumber> l2 =
                    visitLinearCombination(ctx.linearCombination()).asSyntacticLinearCombination();
            Inequality<RationalNumber> inequality2 = new Inequality<RationalNumber>(ring, l2, !isGreater, constant);
            linearProgram.getInequalities().add(inequality2);
        }

        return new Value(null);

    }

    @Override public Value visitBounds(InputParser.BoundsContext ctx) {
        Bounds<RationalNumber> bounds = new Bounds<RationalNumber>();
        linearProgram.setBounds(bounds);
        for(InputParser.BoundContext bound: ctx.bound()) {
            visit(bound);
        }
        return new Value(null);
    }

    @Override public Value visitUpperBound(InputParser.UpperBoundContext ctx) {
        String v = ctx.Variable().getText();
        RationalNumber upper = this.readFloat(ctx.Float());
        Bound<RationalNumber> bound = new Bound<RationalNumber>(v);
        if(ctx.comparison().getText().equals("<="))
            bound.setUpperBound(upper);
        else
            bound.setLowerBound(upper);
        linearProgram.getBounds().add(bound);
        return new Value(null);
    }
    @Override public Value visitLowerBound(InputParser.LowerBoundContext ctx) {
        String v = ctx.Variable().getText();
        RationalNumber lower = this.readFloat(ctx.Float());
        Bound<RationalNumber> bound = new Bound<RationalNumber>(v);
        if(ctx.comparison().getText().equals("<="))
            bound.setLowerBound(lower);
        else
            bound.setUpperBound(lower);

        linearProgram.getBounds().add(bound);
        return new Value(null);
    }
    @Override public Value visitLowerAndUpperBound(InputParser.LowerAndUpperBoundContext ctx) {
        String v = ctx.Variable().getText();
        RationalNumber lower = this.readFloat(ctx.Float(0));
        RationalNumber upper = this.readFloat(ctx.Float(1));
        Bound<RationalNumber> bound = new Bound<RationalNumber>(v);
        if(ctx.comparison(0).getText().equals("<=")) {
            bound.setLowerBound(lower);
            bound.setUpperBound(upper);
        }
        else {
            bound.setLowerBound(upper);
            bound.setUpperBound(lower);
        }

        linearProgram.getBounds().add(bound);
        return new Value(null);
    }


    @Override public Value visitVariablesList(InputParser.VariablesListContext ctx) {
        Variables vars = new Variables();
        for(ParseTree variable: ctx.children) {
            vars.add(variable.getText());
        }
        linearProgram.setVariables(vars);

        return new Value(null);
    }
}
