// Generated from /Users/quentin/Projet/LPSolver/src/fr/enslyon/Input.g4 by ANTLR 4.5

     package fr.enslyon.gen;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InputParser}.
 */
public interface InputListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InputParser#linearSystem}.
	 * @param ctx the parse tree
	 */
	void enterLinearSystem(@NotNull InputParser.LinearSystemContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#linearSystem}.
	 * @param ctx the parse tree
	 */
	void exitLinearSystem(@NotNull InputParser.LinearSystemContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#objective}.
	 * @param ctx the parse tree
	 */
	void enterObjective(@NotNull InputParser.ObjectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#objective}.
	 * @param ctx the parse tree
	 */
	void exitObjective(@NotNull InputParser.ObjectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#linearCombination}.
	 * @param ctx the parse tree
	 */
	void enterLinearCombination(@NotNull InputParser.LinearCombinationContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#linearCombination}.
	 * @param ctx the parse tree
	 */
	void exitLinearCombination(@NotNull InputParser.LinearCombinationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantAndVariableItem}
	 * labeled alternative in {@link InputParser#item}.
	 * @param ctx the parse tree
	 */
	void enterConstantAndVariableItem(@NotNull InputParser.ConstantAndVariableItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantAndVariableItem}
	 * labeled alternative in {@link InputParser#item}.
	 * @param ctx the parse tree
	 */
	void exitConstantAndVariableItem(@NotNull InputParser.ConstantAndVariableItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableItem}
	 * labeled alternative in {@link InputParser#item}.
	 * @param ctx the parse tree
	 */
	void enterVariableItem(@NotNull InputParser.VariableItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableItem}
	 * labeled alternative in {@link InputParser#item}.
	 * @param ctx the parse tree
	 */
	void exitVariableItem(@NotNull InputParser.VariableItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantItem}
	 * labeled alternative in {@link InputParser#item}.
	 * @param ctx the parse tree
	 */
	void enterConstantItem(@NotNull InputParser.ConstantItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantItem}
	 * labeled alternative in {@link InputParser#item}.
	 * @param ctx the parse tree
	 */
	void exitConstantItem(@NotNull InputParser.ConstantItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#firstItem}.
	 * @param ctx the parse tree
	 */
	void enterFirstItem(@NotNull InputParser.FirstItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#firstItem}.
	 * @param ctx the parse tree
	 */
	void exitFirstItem(@NotNull InputParser.FirstItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#inequalities}.
	 * @param ctx the parse tree
	 */
	void enterInequalities(@NotNull InputParser.InequalitiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#inequalities}.
	 * @param ctx the parse tree
	 */
	void exitInequalities(@NotNull InputParser.InequalitiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#inequality}.
	 * @param ctx the parse tree
	 */
	void enterInequality(@NotNull InputParser.InequalityContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#inequality}.
	 * @param ctx the parse tree
	 */
	void exitInequality(@NotNull InputParser.InequalityContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#bounds}.
	 * @param ctx the parse tree
	 */
	void enterBounds(@NotNull InputParser.BoundsContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#bounds}.
	 * @param ctx the parse tree
	 */
	void exitBounds(@NotNull InputParser.BoundsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code upperBound}
	 * labeled alternative in {@link InputParser#bound}.
	 * @param ctx the parse tree
	 */
	void enterUpperBound(@NotNull InputParser.UpperBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code upperBound}
	 * labeled alternative in {@link InputParser#bound}.
	 * @param ctx the parse tree
	 */
	void exitUpperBound(@NotNull InputParser.UpperBoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lowerBound}
	 * labeled alternative in {@link InputParser#bound}.
	 * @param ctx the parse tree
	 */
	void enterLowerBound(@NotNull InputParser.LowerBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lowerBound}
	 * labeled alternative in {@link InputParser#bound}.
	 * @param ctx the parse tree
	 */
	void exitLowerBound(@NotNull InputParser.LowerBoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lowerAndUpperBound}
	 * labeled alternative in {@link InputParser#bound}.
	 * @param ctx the parse tree
	 */
	void enterLowerAndUpperBound(@NotNull InputParser.LowerAndUpperBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lowerAndUpperBound}
	 * labeled alternative in {@link InputParser#bound}.
	 * @param ctx the parse tree
	 */
	void exitLowerAndUpperBound(@NotNull InputParser.LowerAndUpperBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#variablesList}.
	 * @param ctx the parse tree
	 */
	void enterVariablesList(@NotNull InputParser.VariablesListContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#variablesList}.
	 * @param ctx the parse tree
	 */
	void exitVariablesList(@NotNull InputParser.VariablesListContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(@NotNull InputParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(@NotNull InputParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#comparisonOrEqual}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOrEqual(@NotNull InputParser.ComparisonOrEqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#comparisonOrEqual}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOrEqual(@NotNull InputParser.ComparisonOrEqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link InputParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(@NotNull InputParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link InputParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(@NotNull InputParser.ComparisonContext ctx);
}