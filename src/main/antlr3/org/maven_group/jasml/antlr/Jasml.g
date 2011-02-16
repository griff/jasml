grammar Jasml;

options {
    output=AST;
}

tokens {
    ABSTRACTION='abstraction';
    ABSTYPE='abstype';
    AND='and';
    ANDALSO='andalso';
    AS='as';
    CASE='case';
    DO='do';
    DATATYPE='datatype';
    ELSE='else';
    END='end';
    EXCEPTION='exception';
    FN='fn';
    FUN='fun';
    HANDLE='handle';
    IF='if';
    IN='in';
    INFIX='infix';
    INFIXR='infixr';
    LET='let';
    LIST='list';
    LOCAL='local';
    NONFIX='nonfix';
    OF='of';
    OP='op';
    OPEN='open';
    ORELSE='orelse';
    RAISE='raise';
    REC='rec';
    SIG='sig';
    SIGNATURE='signature';
    STRUCT='struct';
    STRUCTURE='structure';
    THEN='then';
    TYPE='type';
    VAL='val';
    WITH='with';
    WITHTYPE='withtype';
    WHILE='while';
    COLON=':';
    COLONGT=':>';
    PIPE='|';
    EQ='=';
    ARROW='=>';
    TYPE_ARROW='->';
    HASH='#';
    LPAREN='(';
    RPAREN=')';
    LBRACKET='[';
    RBRACKET=']';
    LCURLY='{';
    RCURLY='}';
    COMMA=',';
    SEMI=';';
    DOTDOTDOT='...';
    UNDER='_';
    DBLCOLON='::';

    TRUE='true';
    FALSE='false';

    // Builtin types
    TUPLE_LITERAL;
    LIST_LITERAL;
    CALL;
    TYPED_EXP;
}

@header {
    package org.maven_group.jasml.antlr;
}

@lexer::header {
    package org.maven_group.jasml.antlr;
}


/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

STRING_LITERAL  : '"' DoubleQuoteBody* '"'
    ;

CHAR_LITERAL    : '#"' DoubleQuoteBody '"'
    ;
fragment
DoubleQuoteBody : ~('"'|'\\')|'\\' .
    ;

DECIMAL_LITERAL : '~'? '0' | '1'..'9' '0'..'9'*
    ;

FLOATING_POINT_LITERAL
    :/*     d=DECIMAL_LITERAL RangeDots
            {
                $d.setType(DECIMAL_LITERAL);
                emit($d);
                $RangeDots.setType(DOTDOT);
                emit($RangeDots);
            }
    |*/     Digits '.' Digits (Exponent)?
    |     Digits Exponent
    ;

fragment
RangeDots : '..'
    ;
fragment
Digits    : ('0'..'9')+
    ;
fragment
Exponent  : ('e'|'E') '~'? Digits
    ;

IDENTIFIER
    : Letter (Letter|AlphaNumDigit)*
    | Symbol+
    ;

fragment
Letter : 'a'..'z' | 'A'..'Z'
    ;

fragment
AlphaNumDigit : '\'' | UNDER | '0'..'9'
    ;

fragment
Symbol : '!'|HASH|'$'|'%'|'&'|'*'|'+'|'-'|'/'|COLON|'<'|EQ|'>'|'?'|'@'|'\\'|'^'|'#1230'|'|'|'~'
    ;


WS  :  (' '|'\r'|'\t'|'\n') {$channel=HIDDEN;}
    ;


COMMENT
    :   '(*' (( options {greedy=false;} : ~'(' )*|COMMENT) '*)' {$channel=HIDDEN;}
    ;



/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

startRule
    : declarations EOF
    ;

declarations
    : declaration (SEMI declaration)*
    ;

declaration
    : VAL REC? atomicPattern EQ expression
    /*| FUN fvalbind */
    /*| TYPE typebind */
    | EXCEPTION^ name
    | OPEN^ name
    | expression
    ;

fvalbind
    : fvalbindExp (PIPE^ fvalbindExp)*
    ;

fvalbindExp
    : name atomicPattern+ EQ expression
    ;

expression
    : typedExpression COLON type -> ^(TYPED_EXP typedExpression type)
    ;

typedExpression
    : andOrExpression
        ( ANDALSO^ andOrExpression
        | ORELSE^ andOrExpression
        )*
    ;

andOrExpression
    : infixExpression
    | RAISE^ expression
    | IF cond=expression THEN t=expression ELSE e=expression -> ^(IF $cond $t $e)
    | FN^ match
    ;

infixExpression
    : (e1=appliedExpression -> $e1)
      (name e2=appliedExpression -> ^(CALL name $infixExpression $e2) )*
    ;

appliedExpression
    : atomicExpression /*(expression)*  use function */
    ;

atomicExpression
    : literal
    | OP? name
    /*| record_selector*/
    | tuple
    | list
    | LET declaration IN expression END  -> ^(LET declaration expression)
    /*| LPAREN expression RPAREN   -> expression*/
    ;

match
    : atomicPattern ARROW expression (PIPE match)?
    ;

literal
    : DECIMAL_LITERAL
    | FLOATING_POINT_LITERAL
    | STRING_LITERAL
    | CHAR_LITERAL
    | TRUE
    | FALSE
    ;

name
    : IDENTIFIER
    ;

record_selector
    :
    ;

tuple
    : LPAREN (e1=expression -> $e1) (COMMA e2=expression -> ^(TUPLE_LITERAL $tuple $e2))* RPAREN
    ;

list
    : LBRACKET e1=expression (COMMA e2=expression)+ RBRACKET  -> ^(LIST_LITERAL $e1 $e2)
    ;

type
    : listType (TYPE_ARROW^ listType)*
    ;

listType
    : atomicType (LIST^)*
    ;

atomicType
    : name
    | '\'' name
    | LPAREN type RPAREN -> type
    ;

atomicPattern
    : atomicPattern2 (DBLCOLON^ atomicPattern2)*
    ;

atomicPattern2
    : name
    | LPAREN atomicPattern (COMMA atomicPattern)+ RPAREN
    | LBRACKET atomicPattern (COMMA atomicPattern)+ LBRACKET
    | UNDER
    ;
