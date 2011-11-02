grammar Policy;

options { 
   output=AST;
   ASTLabelType=PolicyNode;
}

tokens {
  ARGUMENT='argument';
  FALSE='false';
  TRUE='true';
  NULL='null';
  IMPORT='import';
  STATIC='static';
  INSTANCEOF='instanceof';
  IF='if';
  COMMA=',';
  NOT='!';
  POUND='#';
  LPAREN='(';
  LBRACKET='[';
  PLUSPLUS='++';
  SUBSUB='--';
  PIPE='|';
  RPAREN=')';
  RBRACKET=']';
  SEMI=';';
  COLON=':';
  DOT='.';
  DOTDOT='..';
  EQ='==';
  GT='>';
  LT='<';
  LTGT='!=';
  LTEQ='<=';
  GTEQ='>=';
  PLUS='+';
  SUB='-';
  STAR='*';
  SLASH='/';
  PERCENT='%';
  LBRACE='{';
  RBRACE='}';
  
  // Builtin types
  DOUBLE='double';
  FLOAT='float';
  LONG='long';
  INT='int';
  SHORT='short';
  BYTE='byte';
  CHAR='char';
  BOOLEAN='boolean';
  
  
  PRINCIPAL_AND;
  PRINCIPAL_OR;
  PRINCIPAL_ALIAS;
  NEGATIVE;
  EXPAND;
  CLASS;
  IMPORT_STATIC;
}

@header {
    package dk.lisberg.entity.security.policy.antlr;
    import dk.lisberg.entity.security.policy.ClassNode;
    import dk.lisberg.entity.security.policy.PolicyNode;
}

@lexer::header {
    package dk.lisberg.entity.security.policy.antlr;
}

@members {
    protected void setRepresentedClass( PolicyNode classNode, String name ) {
        throw new UnsupportedOperationException();
    }

    protected void addStaticImport( PolicyNode imp, String pck, String name ) {
        throw new UnsupportedOperationException();
    }
    
    protected void addStaticWildcardImport( PolicyNode imp, String pck ) {
        throw new UnsupportedOperationException();
    }

    protected void addImport( PolicyNode imp, String pck, String name ) {
        throw new UnsupportedOperationException();
    }
    
    protected void addWildcardImport( PolicyNode imp, String pck ) {
        throw new UnsupportedOperationException();
    }

    protected boolean isStatic(PolicyNode imp, String t) {
        throw new UnsupportedOperationException();
    }

    protected void addArgumentType( PolicyNode ref, String name)
    {
        throw new UnsupportedOperationException();
    }

    protected ClassNode createClassNode(Token token, Class cls)
    {
        throw new UnsupportedOperationException();
    }

    protected PolicyNode createArgument(PolicyNode nameNode, String name)
    {
        throw new UnsupportedOperationException();
    }
}

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
NONE:	'NONE' | '"NONE"';
KEYSTORE:	('k'|'K')('e'|'E')('y'|'Y')('s'|'S')('t'|'T')('o'|'O')('r'|'R')('e'|'E');
KEYSTORE_PASSWORD_URL:	('k'|'K')('e'|'E')('y'|'Y')('s'|'S')('t'|'T')('o'|'O')('r'|'R')('e'|'E')('p'|'P')('a'|'A')('s'|'S')('s'|'S')('w'|'W')('o'|'O')('r'|'R')('d'|'D')('u'|'U')('r'|'R')('l'|'L');
GRANT:	('g'|'G')('r'|'R')('a'|'A')('n'|'N')('t'|'T');
SIGNED_BY:	('s'|'S')('i'|'I')('g'|'G')('n'|'N')('e'|'E')('d'|'D')('b'|'B')('y'|'Y');
CODE_BASE:	('c'|'C')('o'|'O')('d'|'D')('e'|'E')('b'|'B')('a'|'A')('s'|'S')('e'|'E');
PRINCIPAL:	('p'|'P')('r'|'R')('i'|'I')('n'|'N')('c'|'C')('i'|'I')('p'|'P')('a'|'A')('l'|'L');
PERMISSION:	('p'|'P')('e'|'E')('r'|'R')('m'|'M')('i'|'I')('s'|'S')('s'|'S')('i'|'I')('o'|'O')('n'|'N');

STRING_LITERAL  
	: '"' DoubleQuoteBody '"'
	| '\'' SingleQuoteBody '\''
	;

fragment
DoubleQuoteBody  :	 (~('"'|'\\')|'\\' .)* ;

fragment
SingleQuoteBody  :	 (~('\''|'\\')|'\\' .)* ;

TIME_LITERAL : (DECIMAL_LITERAL | Digits '.' (Digits)? (Exponent)? | '.' Digits (Exponent)?) ( 'ms' | 'm' | 's' | 'h' ) ;

DECIMAL_LITERAL : ('0' | '1'..'9' '0'..'9'*) ;

OCTAL_LITERAL : '0' ('0'..'7')+ ;

HEX_LITERAL : '0' ('x'|'X') HexDigit+    			{ setText(getText().substring(2, getText().length())); };

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

FLOATING_POINT_LITERAL
    :     d=DECIMAL_LITERAL RangeDots
    	  	{
    	  		$d.setType(DECIMAL_LITERAL);
    	  		emit($d);
          		$RangeDots.setType(DOTDOT);
    	  		emit($RangeDots);
    	  	}
    |     d=OCTAL_LITERAL RangeDots
    	  	{
    	  		$d.setType(OCTAL_LITERAL);
    	  		emit($d);
          		$RangeDots.setType(DOTDOT);
    	  		emit($RangeDots);
    	  	}
    |	  Digits '.' (Digits)? (Exponent)? 
    | '.' Digits (Exponent)? 
    |     Digits Exponent
    ;

fragment
RangeDots 
	:	DOTDOT
	;
fragment
Digits	:	('0'..'9')+ 
 	;
fragment
Exponent : 	('e'|'E') ('+'|'-')? Digits
 	;

IDENTIFIER 
	: Letter (Letter|JavaIDDigit)*
	| '<<' (~'>'| '>' ~'>')* '>'* '>>'			{ setText(getText().substring(2, getText().length()-2)); }
	;

fragment
Letter
    :  '\u0024' |
       '\u0041'..'\u005a' |
       '\u005f' |
       '\u0061'..'\u007a' |
       '\u00c0'..'\u00d6' |
       '\u00d8'..'\u00f6' |
       '\u00f8'..'\u00ff' |
       '\u0100'..'\u1fff' |
       '\u3040'..'\u318f' |
       '\u3300'..'\u337f' |
       '\u3400'..'\u3d2d' |
       '\u4e00'..'\u9fff' |
       '\uf900'..'\ufaff'
    ;

fragment
JavaIDDigit
    :  '\u0030'..'\u0039' |
       '\u0660'..'\u0669' |
       '\u06f0'..'\u06f9' |
       '\u0966'..'\u096f' |
       '\u09e6'..'\u09ef' |
       '\u0a66'..'\u0a6f' |
       '\u0ae6'..'\u0aef' |
       '\u0b66'..'\u0b6f' |
       '\u0be7'..'\u0bef' |
       '\u0c66'..'\u0c6f' |
       '\u0ce6'..'\u0cef' |
       '\u0d66'..'\u0d6f' |
       '\u0e50'..'\u0e59' |
       '\u0ed0'..'\u0ed9' |
       '\u1040'..'\u1049'
   ;

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;

COMMENT
    :   '/*' .* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? ('\n'|EOF) {$channel=HIDDEN;}
    ;

AND : '&&' | 'and';
OR 	: '||' | 'or';

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

policy
	: policyItems EOF
	;
	
policyItems
	: (importDecl SEMI)* (argumentDefinition SEMI)* grant* -> grant*
	/*
	( keystoreDecl SEMI
	| keystorePassword SEMI
	| grant )* -> grant* //keystoreDecl* keystorePassword* grant*
	*/
	;

expressionTest
    : expressionTestItems EOF
    ;

expressionTestItems
    : (argumentDefinition SEMI)* expression -> expression
    ;

importDecl
	: IMPORT (pck=importId DOT)? name {addImport($pck.tree, $pck.text, $name.text);} 	->
	| IMPORT pck=importId DOT STAR  {addWildcardImport($pck.tree, $pck.text);} 	->
	| IMPORT STATIC pck=importId DOT name {addStaticImport($pck.tree, $pck.text, $name.text);} ->
	| IMPORT STATIC pck=importId DOT STAR {addStaticWildcardImport($pck.tree, $pck.text);} ->
	;

importId
	: name ( DOT^ name )*  
	;

argumentDefinition
    : ARGUMENT typeMatch name {addArgumentType( $typeMatch.tree, $name.text);} ->
    ;

typeMatch
    : classMatch
    | t=DOUBLE -> {createClassNode($t, Double.class)}
    | t=FLOAT -> {createClassNode($t, Float.class)}
    | t=LONG -> {createClassNode($t, Long.class)}
    | t=INT -> {createClassNode($t, Integer.class)}
    | t=SHORT -> {createClassNode($t, Short.class)}
    | t=BYTE -> {createClassNode($t, Byte.class)}
    | t=CHAR -> {createClassNode($t, Character.class)}
    | t=BOOLEAN -> {createClassNode($t, Boolean.class)}
    ;

keystoreDecl
	: KEYSTORE url=propertyExpanded 
		(
			type=STRING_LITERAL 
			provider=STRING_LITERAL? 
		)? 
		-> ^(KEYSTORE $url $type? $provider?)
	| KEYSTORE NONE type=STRING_LITERAL provider=STRING_LITERAL? -> ^(KEYSTORE NONE $type $provider?)
	;
	
keystorePassword
	: KEYSTORE_PASSWORD_URL^ propertyExpanded
	;

grant
	: GRANT orPrincipal? LBRACE permissions RBRACE -> ^(GRANT orPrincipal? permissions)
	| GRANT orPrincipal? LBRACE RBRACE ->
	;

grantDefinition
	: signed_by COMMA! code_base COMMA! principals
	| signed_by COMMA! code_base
	| signed_by COMMA! principals
	| code_base COMMA! principals
	| signed_by
	| code_base
	| principals
	;

signed_by
	: SIGNED_BY^ propertyExpanded
	;

code_base
	: CODE_BASE^ propertyExpanded
	;

principals
	: (p1=orPrincipal 			-> $p1 ) 
	  ( COMMA p2=orPrincipal 	-> ^(PRINCIPAL_AND $principals $p2) )*
	;

orPrincipal
	: PRINCIPAL (p1=principalItem 	->  $p1 ) 
	  (COMMA p2=principalItem 		-> ^(PRINCIPAL_OR $orPrincipal $p2) )*
	;
	
principalItem
	: clazz=classMatch principal_name=propertyExpanded -> ^(PRINCIPAL $clazz $principal_name)
	| clazz=classMatch principal_role=propertyExpanded principal_name=propertyExpanded -> ^(PRINCIPAL $clazz $principal_role $principal_name)
	//| principal_name=propertyExpanded -> ^(PRINCIPAL_ALIAS $principal_name)
	//| clazz=classMatch STAR -> ^(PRINCIPAL $clazz)
	| STAR STAR -> ^(PRINCIPAL)	
	;


permissions
	: conditionalPermission+
	;

conditionalPermission
	: permission IF expression SEMI -> ^(IF expression permission)
	| permission SEMI!
	;
	
// Permission name can be expanded with ${{self}} or ${{alias:alias_name}}
permission
	: PERMISSION clazz=classMatch
		( pname=propertyExpanded 
			(COMMA action=propertyExpanded)? 
		)? 
		//(COMMA signed_by)?  -> ^(PERMISSION $clazz $pname? $action? signed_by?)
		 -> ^(PERMISSION $clazz $pname? $action?)
	; 
	
expression
	: andExpression
	;

andExpression  
	: orExpression ( AND^ orExpression )*
	;
orExpression  
	: typeExpression ( OR^ typeExpression )*
	;

typeExpression 
	: relationalExpression		
	   (   INSTANCEOF itn=classMatch	-> ^(INSTANCEOF relationalExpression $itn)
	   | 							-> relationalExpression
	   )
	;
	
relationalExpression  
	: additiveExpression
	   (   LTGT^  additiveExpression
	   |   EQ^    additiveExpression
	   |   LTEQ^  additiveExpression
	   |   GTEQ^  additiveExpression
	   |   LT^    additiveExpression
	   |   GT^    additiveExpression
	   ) * 
	;

additiveExpression 
	: multiplicativeExpression
	   (   PLUS^  multiplicativeExpression
	   |   SUB^   multiplicativeExpression
	   ) * 
	;

multiplicativeExpression
	: unaryExpression
	   (   STAR^    unaryExpression
	   |   SLASH^   unaryExpression
	   |   PERCENT^ unaryExpression
	   ) * 
	;

unaryExpression 
	: primaryExpression
	| SUB      e=unaryExpression	-> ^(NEGATIVE[$SUB] $e)
	;

primaryExpression  
	: n=name -> {isStatic($n.tree, $n.text)}? ^(STATIC name)
	         -> {createArgument($n.tree, $n.text)}
	| q=classMatch DOT name -> ^(STATIC $q name)
    | literal
    | LPAREN expression RPAREN	-> expression
	;

propertyExpanded
	: STRING_LITERAL -> ^(EXPAND STRING_LITERAL)
	;

literal  
	: STRING_LITERAL
	| DECIMAL_LITERAL
	| OCTAL_LITERAL
	| HEX_LITERAL
//    | TIME_LITERAL
	| FLOATING_POINT_LITERAL
	| TRUE
	| FALSE
	| NULL
	;

classMatch 
@after {
    setRepresentedClass($tree, $q.text);
}
    :	q=qualname -> ^(CLASS)
    ;

qualname 
	: name ( (DOT)=> DOT^ name ) *  
	;
name 
	: IDENTIFIER						
	;
