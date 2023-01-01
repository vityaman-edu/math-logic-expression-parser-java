grammar Expression;

@header {
package ru.vityaman.itmo.math.logic.expression.parsing.antlr.generated;
import ru.vityaman.itmo.math.logic.expression.model.*;
}

file returns [Expression entireExpression]
: e1 = expression {
    $entireExpression = $e1.e;
};

expression returns [Expression e]
: d1 = disjunction { 
    $e = $d1.e; 
}
| d2 = disjunction IMPL s2 = expression {
    $e = new Implication($d2.e, $s2.e);
};

disjunction returns [Expression e]
: c1 = conjunction {
    $e = $c1.e;
}
| d2 = disjunction OR c2 = conjunction {
    $e = new Disjunction($d2.e, $c2.e);
};

conjunction returns [Expression e]
: n1 = negation {
    $e = $n1.e;
} | c2 = conjunction AND n2 = negation {
    $e = new Conjunction($c2.e, $n2.e);
};

negation returns [Expression e]
: NEG n1 = negation {
    $e = new Negation($n1.e);
}
| v2 = variable {
    $e = $v2.e;
}
| LBR s3 = expression RBR {
    $e = $s3.e;
};

variable returns [Expression e]
: VAR {
    $e = new Variable($VAR.text);
};

IMPL : '->';
OR   : '|';
AND  : '&';
NEG  : '!';
LBR  : '(';
RBR  : ')';
VAR  : [A-Z][A-Z0-9'`’]*;
END  : '\n';
