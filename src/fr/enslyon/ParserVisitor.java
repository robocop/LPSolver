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

    private RationalNumber readFloat(ParseTree ctx) {
        return new RationalNumber(Double.valueOf(ctx.getText()));
    }

    @Override public Value visitLinearSystem(InputParser.LinearSystemContext ctx) {
        LinearProgram<RationalNumber> lp = new LinearProgram<RationalNumber>();
        Boolean isMaximize = ctx.objective().getText().equals("MAXIMIZE");

        SyntacticLinearCombination<RationalNumber> linearObj =
                visitLinearCombination(ctx.linearCombination()).asSyntacticLinearCombination();

        Objective<RationalNumber> objective = new Objective<RationalNumber>(ring, isMaximize, linearObj);

        Inequalities<RationalNumber> inequalities = visitInequalities(ctx.inequalities()).asInequalities();

        Bounds<RationalNumber> bounds = visitBounds(ctx.bounds()).asBounds();

        Variables variables = visitVariablesList(ctx.variablesList()).asVariables();

        lp.setObjective(objective);
        lp.setInequalities(inequalities);
        lp.setBounds(bounds);
        lp.setVariables(variables);

        return new Value(lp);
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
        for(ParseTree ctxChild: ctx.children) {
            inequalities.add(visit(ctxChild).asInequality());
        }
        return new Value(inequalities);
    }

    @Override public Value visitInequality(InputParser.InequalityContext ctx) {
        SyntacticLinearCombination<RationalNumber> l =
                visitLinearCombination(ctx.linearCombination()).asSyntacticLinearCombination();
        boolean isGreater = (ctx.comparison().getText().equals(">="));


        RationalNumber constant = this.readFloat(ctx.Float());
        if(ctx.operator() != null) {
            if (ctx.operator().getText().equals("-")) {
                constant = ring.opposite(constant);
            }
        }

        Inequality<RationalNumber> inequality = new Inequality<RationalNumber>(ring, l, isGreater, constant);

        return new Value(inequality);
    }

    @Override public Value visitBounds(InputParser.BoundsContext ctx) {
        Bounds<RationalNumber> bounds = new Bounds<RationalNumber>();
        for(InputParser.BoundContext bound: ctx.bound()) {
            Bound b = visit(bound).asBound();
            bounds.add(b);
        }
        return new Value(bounds);
    }

    @Override public Value visitUpperBound(InputParser.UpperBoundContext ctx) {
        String v = ctx.Variable().getText();
        RationalNumber upper = this.readFloat(ctx.Float());
        Bound<RationalNumber> bound = new Bound<RationalNumber>(v);
        if(ctx.comparison().getText().equals("<="))
            bound.setUpperBound(upper);
        else
            bound.setLowerBound(upper);
        return new Value(bound);
    }
    @Override public Value visitLowerBound(InputParser.LowerBoundContext ctx) {
        String v = ctx.Variable().getText();
        RationalNumber lower = this.readFloat(ctx.Float());
        Bound<RationalNumber> bound = new Bound<RationalNumber>(v);
        if(ctx.comparison().getText().equals("<="))
            bound.setLowerBound(lower);
        else
            bound.setUpperBound(lower);

        return new Value(bound);
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

        return new Value(bound);
    }


    @Override public Value visitVariablesList(InputParser.VariablesListContext ctx) {
        Variables vars = new Variables();
        for(ParseTree variable: ctx.children) {
            vars.add(variable.getText());
        }
        return new Value(vars);
    }

    @Override public Value visitOperator(InputParser.OperatorContext ctx) {
        System.out.println("operator: " + ctx.getText());
        return visitChildren(ctx);
    }

    @Override public Value visitComparison(@NotNull InputParser.ComparisonContext ctx) {
        System.out.println("comparison: " + ctx.toString());
        return visitChildren(ctx);
    }



}
