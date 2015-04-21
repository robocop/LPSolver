// Generated from /Users/quentin/Projet/LPSolver/src/fr/enslyon/Input.g4 by ANTLR 4.5

     package fr.enslyon.Parser.gen;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class InputLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, MINIMIZE=2, MAXIMIZE=3, SUBJECTTO=4, BOUNDS=5, VARIABLES=6, END=7, 
		Plus=8, Minus=9, LessEqual=10, GreaterEqual=11, Equal=12, Float=13, Variable=14;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "MINIMIZE", "MAXIMIZE", "SUBJECTTO", "BOUNDS", "VARIABLES", "END", 
		"Plus", "Minus", "LessEqual", "GreaterEqual", "Equal", "Float", "Variable"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'MINIMIZE'", "'MAXIMIZE'", "'SUBJECT TO'", "'BOUNDS'", "'VARIABLES'", 
		"'END'", "'+'", "'-'", "'<='", "'>='", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "MINIMIZE", "MAXIMIZE", "SUBJECTTO", "BOUNDS", "VARIABLES", 
		"END", "Plus", "Minus", "LessEqual", "GreaterEqual", "Equal", "Float", 
		"Variable"
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


	public InputLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Input.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\20\u0083\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\6\2!\n\2\r\2\16\2\"\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\6\16f\n\16\r\16\16"+
		"\16g\3\16\3\16\6\16l\n\16\r\16\16\16m\3\16\3\16\6\16r\n\16\r\16\16\16"+
		"s\7\16v\n\16\f\16\16\16y\13\16\5\16{\n\16\3\17\3\17\7\17\177\n\17\f\17"+
		"\16\17\u0082\13\17\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\3\2\6\5\2\13\f\17\17\"\"\4\2GGgg\5\2C\\aac|"+
		"\6\2\62;C\\aac|\u0089\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3 \3\2\2"+
		"\2\5&\3\2\2\2\7/\3\2\2\2\t8\3\2\2\2\13C\3\2\2\2\rJ\3\2\2\2\17T\3\2\2\2"+
		"\21X\3\2\2\2\23Z\3\2\2\2\25\\\3\2\2\2\27_\3\2\2\2\31b\3\2\2\2\33e\3\2"+
		"\2\2\35|\3\2\2\2\37!\t\2\2\2 \37\3\2\2\2!\"\3\2\2\2\" \3\2\2\2\"#\3\2"+
		"\2\2#$\3\2\2\2$%\b\2\2\2%\4\3\2\2\2&\'\7O\2\2\'(\7K\2\2()\7P\2\2)*\7K"+
		"\2\2*+\7O\2\2+,\7K\2\2,-\7\\\2\2-.\7G\2\2.\6\3\2\2\2/\60\7O\2\2\60\61"+
		"\7C\2\2\61\62\7Z\2\2\62\63\7K\2\2\63\64\7O\2\2\64\65\7K\2\2\65\66\7\\"+
		"\2\2\66\67\7G\2\2\67\b\3\2\2\289\7U\2\29:\7W\2\2:;\7D\2\2;<\7L\2\2<=\7"+
		"G\2\2=>\7E\2\2>?\7V\2\2?@\7\"\2\2@A\7V\2\2AB\7Q\2\2B\n\3\2\2\2CD\7D\2"+
		"\2DE\7Q\2\2EF\7W\2\2FG\7P\2\2GH\7F\2\2HI\7U\2\2I\f\3\2\2\2JK\7X\2\2KL"+
		"\7C\2\2LM\7T\2\2MN\7K\2\2NO\7C\2\2OP\7D\2\2PQ\7N\2\2QR\7G\2\2RS\7U\2\2"+
		"S\16\3\2\2\2TU\7G\2\2UV\7P\2\2VW\7F\2\2W\20\3\2\2\2XY\7-\2\2Y\22\3\2\2"+
		"\2Z[\7/\2\2[\24\3\2\2\2\\]\7>\2\2]^\7?\2\2^\26\3\2\2\2_`\7@\2\2`a\7?\2"+
		"\2a\30\3\2\2\2bc\7?\2\2c\32\3\2\2\2df\4\62;\2ed\3\2\2\2fg\3\2\2\2ge\3"+
		"\2\2\2gh\3\2\2\2hz\3\2\2\2ik\7\60\2\2jl\4\62;\2kj\3\2\2\2lm\3\2\2\2mk"+
		"\3\2\2\2mn\3\2\2\2nw\3\2\2\2oq\t\3\2\2pr\4\62;\2qp\3\2\2\2rs\3\2\2\2s"+
		"q\3\2\2\2st\3\2\2\2tv\3\2\2\2uo\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2"+
		"x{\3\2\2\2yw\3\2\2\2zi\3\2\2\2z{\3\2\2\2{\34\3\2\2\2|\u0080\t\4\2\2}\177"+
		"\t\5\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2"+
		"\2\u0081\36\3\2\2\2\u0082\u0080\3\2\2\2\n\2\"gmswz\u0080\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}