// Generated from /Users/quentin/Projet/LPSolver/src/fr/enslyon/Input.g4 by ANTLR 4.5

     package fr.enslyon.gen;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class InputParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, MINIMIZE=2, MAXIMIZE=3, SUBJECTTO=4, BOUNDS=5, VARIABLES=6, END=7, 
		Plus=8, Minus=9, LessEqual=10, GreaterEqual=11, Float=12, Variable=13;
	public static final int
		RULE_linearSystem = 0, RULE_objective = 1, RULE_linearCombination = 2, 
		RULE_item = 3, RULE_firstItem = 4, RULE_inequalities = 5, RULE_inequality = 6, 
		RULE_bounds = 7, RULE_bound = 8, RULE_variablesList = 9, RULE_operator = 10, 
		RULE_comparison = 11;
	public static final String[] ruleNames = {
		"linearSystem", "objective", "linearCombination", "item", "firstItem", 
		"inequalities", "inequality", "bounds", "bound", "variablesList", "operator", 
		"comparison"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'MINIMIZE'", "'MAXIMIZE'", "'SUBJECT TO'", "'BOUNDS'", "'VARIABLES'", 
		"'END'", "'+'", "'-'", "'<='", "'>='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "MINIMIZE", "MAXIMIZE", "SUBJECTTO", "BOUNDS", "VARIABLES", 
		"END", "Plus", "Minus", "LessEqual", "GreaterEqual", "Float", "Variable"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Input.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public InputParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class LinearSystemContext extends ParserRuleContext {
		public ObjectiveContext objective() {
			return getRuleContext(ObjectiveContext.class,0);
		}
		public LinearCombinationContext linearCombination() {
			return getRuleContext(LinearCombinationContext.class,0);
		}
		public TerminalNode SUBJECTTO() { return getToken(InputParser.SUBJECTTO, 0); }
		public InequalitiesContext inequalities() {
			return getRuleContext(InequalitiesContext.class,0);
		}
		public TerminalNode BOUNDS() { return getToken(InputParser.BOUNDS, 0); }
		public BoundsContext bounds() {
			return getRuleContext(BoundsContext.class,0);
		}
		public TerminalNode VARIABLES() { return getToken(InputParser.VARIABLES, 0); }
		public VariablesListContext variablesList() {
			return getRuleContext(VariablesListContext.class,0);
		}
		public TerminalNode END() { return getToken(InputParser.END, 0); }
		public LinearSystemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linearSystem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterLinearSystem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitLinearSystem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitLinearSystem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinearSystemContext linearSystem() throws RecognitionException {
		LinearSystemContext _localctx = new LinearSystemContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_linearSystem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24); 
			objective();
			setState(25); 
			linearCombination();
			setState(26); 
			match(SUBJECTTO);
			setState(27); 
			inequalities();
			setState(28); 
			match(BOUNDS);
			setState(29); 
			bounds();
			setState(30); 
			match(VARIABLES);
			setState(31); 
			variablesList();
			setState(32); 
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectiveContext extends ParserRuleContext {
		public TerminalNode MAXIMIZE() { return getToken(InputParser.MAXIMIZE, 0); }
		public TerminalNode MINIMIZE() { return getToken(InputParser.MINIMIZE, 0); }
		public ObjectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterObjective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitObjective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitObjective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectiveContext objective() throws RecognitionException {
		ObjectiveContext _localctx = new ObjectiveContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_objective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_la = _input.LA(1);
			if ( !(_la==MINIMIZE || _la==MAXIMIZE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LinearCombinationContext extends ParserRuleContext {
		public FirstItemContext firstItem() {
			return getRuleContext(FirstItemContext.class,0);
		}
		public List<OperatorContext> operator() {
			return getRuleContexts(OperatorContext.class);
		}
		public OperatorContext operator(int i) {
			return getRuleContext(OperatorContext.class,i);
		}
		public List<ItemContext> item() {
			return getRuleContexts(ItemContext.class);
		}
		public ItemContext item(int i) {
			return getRuleContext(ItemContext.class,i);
		}
		public LinearCombinationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linearCombination; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterLinearCombination(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitLinearCombination(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitLinearCombination(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinearCombinationContext linearCombination() throws RecognitionException {
		LinearCombinationContext _localctx = new LinearCombinationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_linearCombination);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); 
			firstItem();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Plus || _la==Minus) {
				{
				{
				setState(37); 
				operator();
				setState(38); 
				item();
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ItemContext extends ParserRuleContext {
		public ItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_item; }
	 
		public ItemContext() { }
		public void copyFrom(ItemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VariableItemContext extends ItemContext {
		public TerminalNode Variable() { return getToken(InputParser.Variable, 0); }
		public VariableItemContext(ItemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterVariableItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitVariableItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitVariableItem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstantAndVariableItemContext extends ItemContext {
		public TerminalNode Float() { return getToken(InputParser.Float, 0); }
		public TerminalNode Variable() { return getToken(InputParser.Variable, 0); }
		public ConstantAndVariableItemContext(ItemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterConstantAndVariableItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitConstantAndVariableItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitConstantAndVariableItem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstantItemContext extends ItemContext {
		public TerminalNode Float() { return getToken(InputParser.Float, 0); }
		public ConstantItemContext(ItemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterConstantItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitConstantItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitConstantItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ItemContext item() throws RecognitionException {
		ItemContext _localctx = new ItemContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_item);
		try {
			setState(49);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new ConstantAndVariableItemContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(45); 
				match(Float);
				setState(46); 
				match(Variable);
				}
				break;
			case 2:
				_localctx = new VariableItemContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(47); 
				match(Variable);
				}
				break;
			case 3:
				_localctx = new ConstantItemContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(48); 
				match(Float);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FirstItemContext extends ParserRuleContext {
		public ItemContext item() {
			return getRuleContext(ItemContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public FirstItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_firstItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterFirstItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitFirstItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitFirstItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FirstItemContext firstItem() throws RecognitionException {
		FirstItemContext _localctx = new FirstItemContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_firstItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(51); 
				operator();
				}
			}

			setState(54); 
			item();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InequalitiesContext extends ParserRuleContext {
		public List<InequalityContext> inequality() {
			return getRuleContexts(InequalityContext.class);
		}
		public InequalityContext inequality(int i) {
			return getRuleContext(InequalityContext.class,i);
		}
		public InequalitiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inequalities; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterInequalities(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitInequalities(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitInequalities(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InequalitiesContext inequalities() throws RecognitionException {
		InequalitiesContext _localctx = new InequalitiesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_inequalities);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << Float) | (1L << Variable))) != 0)) {
				{
				{
				setState(56); 
				inequality();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InequalityContext extends ParserRuleContext {
		public LinearCombinationContext linearCombination() {
			return getRuleContext(LinearCombinationContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode Float() { return getToken(InputParser.Float, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public InequalityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inequality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterInequality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitInequality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitInequality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InequalityContext inequality() throws RecognitionException {
		InequalityContext _localctx = new InequalityContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_inequality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(62); 
			linearCombination();
			setState(63); 
			comparison();
			setState(65);
			_la = _input.LA(1);
			if (_la==Plus || _la==Minus) {
				{
				setState(64); 
				operator();
				}
			}

			setState(67); 
			match(Float);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoundsContext extends ParserRuleContext {
		public List<BoundContext> bound() {
			return getRuleContexts(BoundContext.class);
		}
		public BoundContext bound(int i) {
			return getRuleContext(BoundContext.class,i);
		}
		public BoundsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bounds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterBounds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitBounds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitBounds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoundsContext bounds() throws RecognitionException {
		BoundsContext _localctx = new BoundsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bounds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Float || _la==Variable) {
				{
				{
				setState(69); 
				bound();
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoundContext extends ParserRuleContext {
		public BoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bound; }
	 
		public BoundContext() { }
		public void copyFrom(BoundContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LowerBoundContext extends BoundContext {
		public TerminalNode Float() { return getToken(InputParser.Float, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode Variable() { return getToken(InputParser.Variable, 0); }
		public LowerBoundContext(BoundContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterLowerBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitLowerBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitLowerBound(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LowerAndUpperBoundContext extends BoundContext {
		public List<TerminalNode> Float() { return getTokens(InputParser.Float); }
		public TerminalNode Float(int i) {
			return getToken(InputParser.Float, i);
		}
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public TerminalNode Variable() { return getToken(InputParser.Variable, 0); }
		public LowerAndUpperBoundContext(BoundContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterLowerAndUpperBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitLowerAndUpperBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitLowerAndUpperBound(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UpperBoundContext extends BoundContext {
		public TerminalNode Variable() { return getToken(InputParser.Variable, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode Float() { return getToken(InputParser.Float, 0); }
		public UpperBoundContext(BoundContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterUpperBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitUpperBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitUpperBound(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoundContext bound() throws RecognitionException {
		BoundContext _localctx = new BoundContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_bound);
		try {
			setState(89);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new UpperBoundContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(75); 
				match(Variable);
				setState(76); 
				comparison();
				setState(77); 
				match(Float);
				}
				break;
			case 2:
				_localctx = new LowerBoundContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(79); 
				match(Float);
				setState(80); 
				comparison();
				setState(81); 
				match(Variable);
				}
				break;
			case 3:
				_localctx = new LowerAndUpperBoundContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(83); 
				match(Float);
				setState(84); 
				comparison();
				setState(85); 
				match(Variable);
				setState(86); 
				comparison();
				setState(87); 
				match(Float);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariablesListContext extends ParserRuleContext {
		public List<TerminalNode> Variable() { return getTokens(InputParser.Variable); }
		public TerminalNode Variable(int i) {
			return getToken(InputParser.Variable, i);
		}
		public VariablesListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variablesList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterVariablesList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitVariablesList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitVariablesList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariablesListContext variablesList() throws RecognitionException {
		VariablesListContext _localctx = new VariablesListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variablesList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Variable) {
				{
				{
				setState(91); 
				match(Variable);
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode Plus() { return getToken(InputParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(InputParser.Minus, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_la = _input.LA(1);
			if ( !(_la==Plus || _la==Minus) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonContext extends ParserRuleContext {
		public TerminalNode LessEqual() { return getToken(InputParser.LessEqual, 0); }
		public TerminalNode GreaterEqual() { return getToken(InputParser.GreaterEqual, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InputListener ) ((InputListener)listener).exitComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InputVisitor ) return ((InputVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_la = _input.LA(1);
			if ( !(_la==LessEqual || _la==GreaterEqual) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17h\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\7\4+\n\4\f\4\16\4.\13\4\3\5\3\5\3\5\3\5\5\5\64\n\5\3\6\5\6\67"+
		"\n\6\3\6\3\6\3\7\7\7<\n\7\f\7\16\7?\13\7\3\b\3\b\3\b\5\bD\n\b\3\b\3\b"+
		"\3\t\7\tI\n\t\f\t\16\tL\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\5\n\\\n\n\3\13\7\13_\n\13\f\13\16\13b\13\13\3\f\3\f\3\r"+
		"\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\5\3\2\4\5\3\2\n\13\3\2"+
		"\f\re\2\32\3\2\2\2\4$\3\2\2\2\6&\3\2\2\2\b\63\3\2\2\2\n\66\3\2\2\2\f="+
		"\3\2\2\2\16@\3\2\2\2\20J\3\2\2\2\22[\3\2\2\2\24`\3\2\2\2\26c\3\2\2\2\30"+
		"e\3\2\2\2\32\33\5\4\3\2\33\34\5\6\4\2\34\35\7\6\2\2\35\36\5\f\7\2\36\37"+
		"\7\7\2\2\37 \5\20\t\2 !\7\b\2\2!\"\5\24\13\2\"#\7\t\2\2#\3\3\2\2\2$%\t"+
		"\2\2\2%\5\3\2\2\2&,\5\n\6\2\'(\5\26\f\2()\5\b\5\2)+\3\2\2\2*\'\3\2\2\2"+
		"+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\7\3\2\2\2.,\3\2\2\2/\60\7\16\2\2\60\64"+
		"\7\17\2\2\61\64\7\17\2\2\62\64\7\16\2\2\63/\3\2\2\2\63\61\3\2\2\2\63\62"+
		"\3\2\2\2\64\t\3\2\2\2\65\67\5\26\f\2\66\65\3\2\2\2\66\67\3\2\2\2\678\3"+
		"\2\2\289\5\b\5\29\13\3\2\2\2:<\5\16\b\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2"+
		"=>\3\2\2\2>\r\3\2\2\2?=\3\2\2\2@A\5\6\4\2AC\5\30\r\2BD\5\26\f\2CB\3\2"+
		"\2\2CD\3\2\2\2DE\3\2\2\2EF\7\16\2\2F\17\3\2\2\2GI\5\22\n\2HG\3\2\2\2I"+
		"L\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\21\3\2\2\2LJ\3\2\2\2MN\7\17\2\2NO\5\30"+
		"\r\2OP\7\16\2\2P\\\3\2\2\2QR\7\16\2\2RS\5\30\r\2ST\7\17\2\2T\\\3\2\2\2"+
		"UV\7\16\2\2VW\5\30\r\2WX\7\17\2\2XY\5\30\r\2YZ\7\16\2\2Z\\\3\2\2\2[M\3"+
		"\2\2\2[Q\3\2\2\2[U\3\2\2\2\\\23\3\2\2\2]_\7\17\2\2^]\3\2\2\2_b\3\2\2\2"+
		"`^\3\2\2\2`a\3\2\2\2a\25\3\2\2\2b`\3\2\2\2cd\t\3\2\2d\27\3\2\2\2ef\t\4"+
		"\2\2f\31\3\2\2\2\n,\63\66=CJ[`";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}