package fr.enslyon;

import fr.enslyon.DivisionRing.DoubleDivisionRing;
import fr.enslyon.gen.InputBaseVisitor;
import fr.enslyon.gen.InputParser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;


/**
 * Created by quentin on 16/04/15.
 */
public class ParserVisitor extends InputBaseVisitor<Value<Double>> {
    private DoubleDivisionRing ring = new DoubleDivisionRing();
    LinearProgram<Double> linearProgram = new LinearProgram<Double>();

    public LinearProgram<Double> getLinearProgram() {
        return linearProgram;
    }
    private Double readFloat(ParseTree ctx) {
        return Double.valueOf(ctx.getText());
    }

    @Override public Value<Double> visitLinearSystem(InputParser.LinearSystemContext ctx) {
        Boolean isMaximize = ctx.objective().getText().equals("MAXIMIZE");

        SyntacticLinearCombination<Double> linearObj =
                visitLinearCombination(ctx.linearCombination()).asSyntacticLinearCombination();

        Objective<Double> objective = new Objective<Double>(ring, isMaximize, linearObj);

        linearProgram.setObjective(objective);

        visitInequalities(ctx.inequalities());
        visitBounds(ctx.bounds());
        visitVariablesList(ctx.variablesList());


        return new Value<Double>(null);
    }


    @Override public Value<Double> visitLinearCombination(InputParser.LinearCombinationContext ctx) {
        SyntacticLinearCombination<Double> l = new SyntacticLinearCombination<Double>(ring);
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
                Item<Double> item = v.asItem();
                if(!isPlus) {
                    item.setConstant(ring.opposite(item.getConstant()));
                }
                l.setOrUpdateVariable(item.getVariable(), item.getConstant());
            }
        }
        return new Value<Double>(l);
    }

    @Override public Value<Double> visitConstantAndVariableItem(@NotNull InputParser.ConstantAndVariableItemContext ctx) {
        Double value = this.readFloat(ctx.Float());
        return new Value<Double>(new Item<Double>(ctx.Variable().getText(), value));
    }
    @Override public Value<Double> visitConstantItem(@NotNull InputParser.ConstantItemContext ctx) {
        Double value = this.readFloat(ctx.Float());
        return new Value<Double>(new Item<Double>(value));
    }
    @Override public Value<Double> visitVariableItem(@NotNull InputParser.VariableItemContext ctx) {
        return new Value<Double>(new Item<Double>(ctx.Variable().getText(), ring.fromInteger(1)));
    }
    @Override public Value<Double> visitFirstItem(InputParser.FirstItemContext ctx) {
        Item<Double> item = visit(ctx.item()).asItem();
        if(ctx.getText().charAt(0) == '-' ) {
            item.setConstant(ring.opposite(item.getConstant()));
        }
        return new Value<Double>(item);
    }


    @Override public Value<Double> visitInequalities(InputParser.InequalitiesContext ctx) {
        Inequalities<Double> inequalities = new Inequalities<Double>();
        linearProgram.setInequalities(inequalities);
        for(ParseTree ctxChild: ctx.children) {
            visit(ctxChild);
        }
        return new Value<Double>(null);
    }

    @Override public Value<Double> visitInequality(InputParser.InequalityContext ctx) {
        SyntacticLinearCombination<Double> l =
                visitLinearCombination(ctx.linearCombination()).asSyntacticLinearCombination();
        boolean isGreater = (ctx.comparisonOrEqual().getText().equals(">="));


        Double constant = this.readFloat(ctx.Float());
        if(ctx.operator() != null) {
            if (ctx.operator().getText().equals("-")) {
                constant = ring.opposite(constant);
            }
        }

        Inequality<Double> inequality = new Inequality<Double>(ring, l, isGreater, constant);
        linearProgram.getInequalities().add(inequality);

        if(ctx.comparisonOrEqual().getText().equals("=")) {
            SyntacticLinearCombination<Double> l2 =
                    visitLinearCombination(ctx.linearCombination()).asSyntacticLinearCombination();
            Inequality<Double> inequality2 = new Inequality<Double>(ring, l2, !isGreater, constant);
            linearProgram.getInequalities().add(inequality2);
        }

        return new Value<Double>(null);

    }

    @Override public Value<Double> visitBounds(InputParser.BoundsContext ctx) {
        Bounds<Double> bounds = new Bounds<Double>();
        linearProgram.setBounds(bounds);
        for(InputParser.BoundContext bound: ctx.bound()) {
            visit(bound);
        }
        return new Value<Double>(null);
    }

    @Override public Value<Double> visitUpperBound(InputParser.UpperBoundContext ctx) {
        String v = ctx.Variable().getText();
        Double upper = this.readFloat(ctx.Float());
        Bound<Double> bound = new Bound<Double>(v);
        if(ctx.comparison().getText().equals("<="))
            bound.setUpperBound(upper);
        else
            bound.setLowerBound(upper);
        linearProgram.getBounds().add(bound);
        return new Value<Double>(null);
    }
    @Override public Value<Double> visitLowerBound(InputParser.LowerBoundContext ctx) {
        String v = ctx.Variable().getText();
        Double lower = this.readFloat(ctx.Float());
        Bound<Double> bound = new Bound<Double>(v);
        if(ctx.comparison().getText().equals("<="))
            bound.setLowerBound(lower);
        else
            bound.setUpperBound(lower);

        linearProgram.getBounds().add(bound);
        return new Value<Double>(null);
    }
    @Override public Value<Double> visitLowerAndUpperBound(InputParser.LowerAndUpperBoundContext ctx) {
        String v = ctx.Variable().getText();
        Double lower = this.readFloat(ctx.Float(0));
        Double upper = this.readFloat(ctx.Float(1));
        Bound<Double> bound = new Bound<Double>(v);
        if(ctx.comparison(0).getText().equals("<=")) {
            bound.setLowerBound(lower);
            bound.setUpperBound(upper);
        }
        else {
            bound.setLowerBound(upper);
            bound.setUpperBound(lower);
        }

        linearProgram.getBounds().add(bound);
        return new Value<Double>(null);
    }


    @Override public Value<Double> visitVariablesList(InputParser.VariablesListContext ctx) {
        Variables vars = new Variables();
        for(ParseTree variable: ctx.children) {
            vars.add(variable.getText());
        }
        linearProgram.setVariables(vars);

        return new Value(null);
    }
}
