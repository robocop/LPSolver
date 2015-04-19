// Generated from /Users/quentin/Projet/LPSolver/src/fr/enslyon/Input.g4 by ANTLR 4.5

     package fr.enslyon.gen;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
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
		Plus=8, Minus=9, LessEqual=10, GreaterEqual=11, Float=12, Variable=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "MINIMIZE", "MAXIMIZE", "SUBJECTTO", "BOUNDS", "VARIABLES", "END", 
		"Plus", "Minus", "LessEqual", "GreaterEqual", "Float", "Variable"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17\177\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\6\2\37\n\2\r\2\16\2 \3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\r\6\rb\n\r\r\r\16\rc\3\r\3\r\6\rh\n\r\r"+
		"\r\16\ri\3\r\3\r\6\rn\n\r\r\r\16\ro\7\rr\n\r\f\r\16\ru\13\r\5\rw\n\r\3"+
		"\16\3\16\7\16{\n\16\f\16\16\16~\13\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\3\2\6\5\2\13\f\17\17\"\"\4\2GG"+
		"gg\5\2C\\aac|\6\2\62;C\\aac|\u0085\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\36\3\2\2"+
		"\2\5$\3\2\2\2\7-\3\2\2\2\t\66\3\2\2\2\13A\3\2\2\2\rH\3\2\2\2\17R\3\2\2"+
		"\2\21V\3\2\2\2\23X\3\2\2\2\25Z\3\2\2\2\27]\3\2\2\2\31a\3\2\2\2\33x\3\2"+
		"\2\2\35\37\t\2\2\2\36\35\3\2\2\2\37 \3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\""+
		"\3\2\2\2\"#\b\2\2\2#\4\3\2\2\2$%\7O\2\2%&\7K\2\2&\'\7P\2\2\'(\7K\2\2("+
		")\7O\2\2)*\7K\2\2*+\7\\\2\2+,\7G\2\2,\6\3\2\2\2-.\7O\2\2./\7C\2\2/\60"+
		"\7Z\2\2\60\61\7K\2\2\61\62\7O\2\2\62\63\7K\2\2\63\64\7\\\2\2\64\65\7G"+
		"\2\2\65\b\3\2\2\2\66\67\7U\2\2\678\7W\2\289\7D\2\29:\7L\2\2:;\7G\2\2;"+
		"<\7E\2\2<=\7V\2\2=>\7\"\2\2>?\7V\2\2?@\7Q\2\2@\n\3\2\2\2AB\7D\2\2BC\7"+
		"Q\2\2CD\7W\2\2DE\7P\2\2EF\7F\2\2FG\7U\2\2G\f\3\2\2\2HI\7X\2\2IJ\7C\2\2"+
		"JK\7T\2\2KL\7K\2\2LM\7C\2\2MN\7D\2\2NO\7N\2\2OP\7G\2\2PQ\7U\2\2Q\16\3"+
		"\2\2\2RS\7G\2\2ST\7P\2\2TU\7F\2\2U\20\3\2\2\2VW\7-\2\2W\22\3\2\2\2XY\7"+
		"/\2\2Y\24\3\2\2\2Z[\7>\2\2[\\\7?\2\2\\\26\3\2\2\2]^\7@\2\2^_\7?\2\2_\30"+
		"\3\2\2\2`b\4\62;\2a`\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2dv\3\2\2\2e"+
		"g\7\60\2\2fh\4\62;\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2js\3\2\2\2"+
		"km\t\3\2\2ln\4\62;\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2"+
		"qk\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tw\3\2\2\2us\3\2\2\2ve\3\2\2\2"+
		"vw\3\2\2\2w\32\3\2\2\2x|\t\4\2\2y{\t\5\2\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2"+
		"\2|}\3\2\2\2}\34\3\2\2\2~|\3\2\2\2\n\2 ciosv|\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}