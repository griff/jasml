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
    TYPE_PREFIX='\'';

    TRUE='true';
    FALSE='false';

    // Builtin types
    TUPLE_LITERAL;
    LIST_LITERAL;
    CALL;
    TYPED_EXP;
    LOOKUP;
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
    : (typedExpression COLON type)=> typedExpression COLON type -> ^(TYPED_EXP typedExpression type)
    | typedExpression
    ;

typedExpression
    : (andOrExpression ANDALSO^ andOrExpression)=> andOrExpression ANDALSO^ andOrExpression
	| (andOrExpression ORELSE^ andOrExpression)=> andOrExpression ORELSE^ andOrExpression
    | andOrExpression
    ;

andOrExpression
    : infixExpression
    | RAISE^ expression
    | IF cond=expression THEN t=expression ELSE e=expression -> ^(IF $cond $t $e)
    | FN^ match
    ;

infixExpression
    : appliedExpression
     /*
     (e1=appliedExpression -> $e1)
      (name e2=appliedExpression -> ^(CALL ^(LOOKUP name) ^(TUPLE_LITERAL $infixExpression $e2)) )*
      */
    ;

appliedExpression
    : (e1=atomicExpression -> $e1) (e2=atomicExpression -> ^(CALL $e1 $e2))*
    ;

atomicExpression
    : literal
    | OP? name
    /*| record_selector*/
    | tuple
    | list
    | LET declaration IN expression END  -> ^(LET declaration expression)
    ;

match
    : (atomicPattern ARROW expression PIPE match)=>
        atomicPattern ARROW expression PIPE match -> ^(ARROW atomicPattern expression) match 
    | atomicPattern ARROW^ expression
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
    : (LPAREN expression (COMMA expression)+ RPAREN)=>
        LPAREN expression (COMMA expression)+ RPAREN -> ^(TUPLE_LITERAL expression+)
    | LPAREN expression RPAREN -> expression
    ;

list
    : LBRACKET (expression (COMMA expression)*)? RBRACKET  -> ^(LIST_LITERAL expression*)
    ;

type
    : listType (TYPE_ARROW^ type)?
    ;

listType
    : atomicType (LIST^)*
    ;

atomicType
    : name
    | TYPE_PREFIX^ name
    | LPAREN type RPAREN -> type
    ;

atomicPattern
    : atomicListPattern (DBLCOLON^ atomicPattern)?
    ;

atomicListPattern
    : atomicTupleLiteralPattern
    | LBRACKET (atomicPattern (COMMA atomicPattern)*)? RBRACKET -> ^(LIST_LITERAL atomicPattern*)
    | name
    | literal
    | UNDER
    ;

atomicTupleLiteralPattern
    : (LPAREN atomicPattern (COMMA atomicPattern)+ RPAREN)=>
        LPAREN atomicPattern (COMMA atomicPattern)+ RPAREN -> ^(TUPLE_LITERAL atomicPattern+)
    | LPAREN atomicPattern RPAREN -> atomicPattern
    ;