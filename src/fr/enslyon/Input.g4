// Define a grammar called Hello
grammar Input;
linearSystem  : objective linearCombination SUBJECTTO inequalities BOUNDS bounds VARIABLES variables END;         // match keyword hello followed by an identifier
WS : [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines


objective : MAXIMIZE | MINIMIZE;
MINIMIZE : 'MINIMIZE';
MAXIMIZE : 'MAXIMIZE';
SUBJECTTO : 'SUBJECT TO';
BOUNDS: 'BOUNDS';
VARIABLES: 'VARIABLES';
END: 'END';


linearCombination : firstItem (operator item)*;

item : Float Variable | Variable | Float;
firstItem: '-'? item;

inequalities:
     (linearCombination comparaison '-'? Float)*;

bounds: bound*;

bound:
     (Variable comparaison Float | Float comparaison Variable comparaison Float);

variables:
     Variable*;

operator : Plus | Minus;


Plus : '+';
Minus : '-';

comparaison: LessEqual | GreaterEqual;

LessEqual : '<=';
GreaterEqual : '>=';

Float : ('0'..'9')+ ('.' ('0'..'9')+ ( ('e' | 'E') ('0'..'9')+)*)?;
Variable : ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;