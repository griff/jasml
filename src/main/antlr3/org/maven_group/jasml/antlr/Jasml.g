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
    STAR='*';

    TRUE='true';
    FALSE='false';

    // Builtin types
    TUPLE;
    LIST_LITERAL;
    CALL;
    TYPED_EXP;
    LOOKUP;
    LOOKUP_NONFIX;
    EXPRESSIONS;
}

scope InfixScope {
        Map operators;
}

@header {
    package org.maven_group.jasml.antlr;

    import org.maven_group.jasml.*; 
}

@lexer::header {
    package org.maven_group.jasml.antlr;
}

@members {
    private int level = 0;

    int findPivot( List operators, int startIndex, int stopIndex )
    {
        int pivot = startIndex;
        int pivotRank = prec( (Token)operators.get(pivot) );
        for( int i = startIndex + 1; i <= stopIndex; i++ )
        {
            Token token = (Token)operators.get(i);
            InfixDefinition def = findInfixDefinition(token);;
            int current = def.getPrecedence();
            boolean rtl = def.isLeftAssociative();
            if ( current < pivotRank || (current == pivotRank && rtl) )
            {
                pivot = i;
                pivotRank = current;
            }
        }
        return pivot;
    }
    Tree createPrecedenceTree( List expressions, List operators, int startIndex, int stopIndex )
    {
        if ( stopIndex == startIndex )
            return (Tree)expressions.get(startIndex);

        int pivot = findPivot( operators, startIndex, stopIndex - 1 );
        //System.out.println("createPrecedenceTree start="+startIndex +" stop="+stopIndex+" pivot="+pivot);
        Tree root = (Tree)adaptor.nil();
        Object call = (Object)adaptor.nil();
        call = (Object)adaptor.becomeRoot( (Object)adaptor.create(CALL, "CALL"), call );

        Object lookup = (Object)adaptor.nil();
        lookup = (Object)adaptor.becomeRoot( (Object)adaptor.create(LOOKUP, "LOOKUP"), lookup );
        adaptor.addChild( lookup, (Object)adaptor.create( (Token)operators.get(pivot) ) );
        adaptor.addChild( call, lookup );

        Object tuple = (Object)adaptor.nil();
        tuple = (Object)adaptor.becomeRoot( (Object)adaptor.create(TUPLE, "TUPLE"), tuple );
        adaptor.addChild( tuple, createPrecedenceTree( expressions, operators, startIndex, pivot ) );
        adaptor.addChild( tuple, createPrecedenceTree( expressions, operators, pivot + 1, stopIndex ) );
        adaptor.addChild( call, tuple );
        adaptor.addChild( root, call );
        return root;
    }
    Tree createPrecedenceTree( List expressions, List operators )
    {
        return createPrecedenceTree( expressions, operators, 0, expressions.size() - 1 );
    }

    boolean isInfixToken(Token t)
    {
        return t.getType()==IDENTIFIER || t.getType() == DBLCOLON || t.getType() == STAR || t.getType() == EQ;
    }


    boolean isInfixDefined(Token t)
    {
        return isInfixToken(t) && findInfixDefinition(t.getText()) != null;
    }

    InfixDefinition findInfixDefinition(Token token)
    {
        return isInfixToken(token) ? findInfixDefinition(token.getText()) : null;
    }

    InfixDefinition findInfixDefinition(String name)
    {
        //System.out.println("Infix lookup at level "+level+" with name="+name);
        for(int i = level-1; i>=0; i--)
        {
            if($InfixScope[i]::operators.containsKey(name))
            {
                InfixDefinition def = (InfixDefinition)$InfixScope[i]::operators.get(name);
                //System.out.println("Found key at level " + i + " with value " + def.getPrecedence());
                return def;
            }
        }
        return null;
    }

    void defineInfix(String name, String precedenceStr, boolean leftAssociative)
    {
        int precedence = precedenceStr == null ? 0 : Integer.parseInt(precedenceStr); 
        $InfixScope::operators.put(name, new InfixDefinition(name, precedence, leftAssociative));
        //System.out.println("Defined infix at level "+level+" with name="+name+", precedence="+precedence+" and left="+leftAssociative);
    }

    void undefineInfix(String name)
    {
        $InfixScope::operators.put(name, null);
        //System.out.println("Undefined infix at level "+level+" with name="+name);
    }

    int prec(Token t)
    {
        if(!isInfixToken(t)) return -1;
        InfixDefinition def = findInfixDefinition(t.getText());
        if(def == null) return -1;
        //System.out.println("prec="+def.getPrecedence());
        return def.getPrecedence();
    }

    int nextp(int p)
    {
        Token prevOpToken = input.LT(-1);
        if(!isInfixToken(prevOpToken)) return 0;
        InfixDefinition def = findInfixDefinition(prevOpToken.getText());
        if(def == null) return 0;
        int pr = def.isLeftAssociative() ? def.getPrecedence()+1 : def.getPrecedence();
        //System.out.println("nextp="+pr +" p="+p);
        return pr;
    }
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

TYPE_VARIABLE : '\'' (Letter|AlphaNumDigit)+
    ;

RECORD : '#' '1'..'9' ('0'..'9')*
    ;

SELECTOR : ':' Letter (Letter|AlphaNumDigit)*
    ;

fragment
Letter : 'a'..'z' | 'A'..'Z'
    ;

fragment
AlphaNumDigit : '\'' | '_' | '0'..'9'
    ;

fragment
Symbol : '!'|'#'|'$'|'%'|'&'|'*'|'+'|'-'|'/'|':'|'<'|'='|'>'|'?'|'@'|'\\'|'^'|'#1230'|'|'|'~'
    ;


WS  :  (' '|'\r'|'\t'|'\n') {$channel=HIDDEN;}
    ;


COMMENT
    :   '(*' (( options {greedy=false;} : ~'(' )*|COMMENT) '*)' {$channel=HIDDEN;}
    ;



/*------------------------------------------------------------------
 * PARSER RULES
 *-----------------------------------------------------------------*/

startRule
    : declarations SEMI!? EOF
    ;

declarations
	scope InfixScope;
    @init {
        $InfixScope::operators = new HashMap();
        level++;
    }
    @after {
        level--;
    }
    : letDeclaration ((SEMI! declaration)|letDeclaration)*
    | expression SEMI! declarations
    ;

declaration
    : letDeclaration
    | expression
    ;

letDeclarations
    : letDeclaration*
    ;

letDeclaration
    : VAL^ REC? atomicPattern EQ! expression
    | FUN^ name fvalbind
    /*| TYPE name EQ typebind */
    | EXCEPTION^ name
    | OPEN^ name
    | INFIX lprec=DECIMAL_LITERAL? il=name {defineInfix($il.text, $lprec.text, true);} ->
    | INFIXR rprec=DECIMAL_LITERAL? ir=name {defineInfix($ir.text, $rprec.text, false);} ->
    | NONFIX nf=name {undefineInfix($nf.text);} ->
    ;

fvalbind
    : (atomicPattern+ EQ expression -> ^(EQ atomicPattern+ expression)) (PIPE fvalbindExp -> $fvalbind fvalbindExp)*
    ;

fvalbindExp
    : name! atomicPattern+ EQ^ expression
    ;

expression
    : andalsoExpression ((COLON type)=> COLON^ type)*
    ;

andalsoExpression
    : orelseExpression ((ANDALSO orelseExpression)=> ANDALSO^ orelseExpression)*
    ;

orelseExpression
	: preInfixExpression ((ORELSE preInfixExpression)=> ORELSE^ preInfixExpression)*
    ;

preInfixExpression
    : infixExpression
    | RAISE^ expression
    | IF cond=expression THEN t=expression ELSE e=expression -> ^(IF $cond $t $e)
    | FN^ match
    ;


infixExpressionHack
	: infixExpression
	;

infixExpression
    @init {
        List expressions = new ArrayList();
        List operators = new ArrayList();
    }
    :  ( left=appliedExpression { expressions.add($left.tree); } )
        (
            {isInfixDefined(input.LT(1))}?=> name
            right=appliedExpression
            {
                operators.add($name.start);
                expressions.add($right.tree);
            }
        )*
        -> {createPrecedenceTree(expressions,operators)}
    ;


appliedExpression
    : (e1=atomicExpression -> $e1) (e2=atomicExpression -> ^(CALL $appliedExpression $e2))*
    ;

atomicExpression
    : literal
    | OP name -> ^(LOOKUP name)
    | {!isInfixDefined(input.LT(1))}?=> name -> ^(LOOKUP_NONFIX name)
    | RECORD^ atomicExpression
    | tuple
    | list
    | letExpression
    ;

letExpression
	scope InfixScope;
    @init {
        $InfixScope::operators = new HashMap();
        level++;
    }
    @after {
        level--;
    }
    : LET letDeclarations IN expression END  -> ^(LET letDeclarations? expression)
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
    | SELECTOR
    ;

name
    : IDENTIFIER
    | DBLCOLON
    | STAR
    | EQ
    ;

tuple
    : (LPAREN expression (SEMI expression)+ RPAREN)=>
        LPAREN expression (SEMI expression)+ RPAREN -> ^(EXPRESSIONS expression+)
    | (LPAREN expression (COMMA expression)+ RPAREN)=> 
        LPAREN expression (COMMA expression)+ RPAREN -> ^(TUPLE expression+)
    | (LPAREN expression RPAREN)=> LPAREN expression RPAREN -> expression
    | LPAREN RPAREN -> ^(TUPLE)
    ;

list
    : LBRACKET (expression (COMMA expression)*)? RBRACKET  -> ^(LIST_LITERAL expression*)
    ;

type
    : tupleType (TYPE_ARROW^ type)?
    ;

tupleType
    : listType (STAR listType)*
    ;

listType
    : atomicType (LIST^)*
    ;

atomicType
    : name
    | TYPE_VARIABLE
    | LPAREN type RPAREN -> type
    ;

atomicPattern
    : atomicListPattern ((DBLCOLON atomicPattern)=> DBLCOLON^ atomicPattern)?
    ;

atomicListPattern
    : atomicTupleLiteralPattern
    | LBRACKET (atomicPattern (COMMA atomicPattern)*)? RBRACKET -> ^(LIST_LITERAL atomicPattern*)
    | atomicPatternName
    | literal
    | UNDER
    ;

atomicTupleLiteralPattern
    : (LPAREN atomicPattern (COMMA atomicPattern)+ RPAREN)=>
        LPAREN atomicPattern (COMMA atomicPattern)+ RPAREN -> ^(TUPLE atomicPattern+)
    | (LPAREN atomicPattern RPAREN)=> LPAREN atomicPattern RPAREN -> atomicPattern
    | LPAREN RPAREN -> ^(TUPLE) 
    ;

atomicPatternName
    : IDENTIFIER
    | DBLCOLON
    | STAR
    ;