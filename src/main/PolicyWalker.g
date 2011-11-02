tree grammar PolicyWalker;

options {
   tokenVocab=Policy;
   ASTLabelType=PolicyNode;
}

@header {
    package dk.lisberg.entity.security.policy.antlr;

    import dk.lisberg.entity.security.policy.IfPermission;
    import dk.lisberg.entity.security.policy.PolicyNode;
    import dk.lisberg.entity.security.policy.ClassNode;
    import dk.lisberg.entity.security.policy.ConditionalPermissions;
    import dk.lisberg.entity.security.policy.walker.*;
    import java.security.Permission;
    import java.security.Principal;
    import dk.lisberg.entity.security.policy.walker.EqualExpr.EqualType;
    import dk.lisberg.entity.security.policy.walker.CalculusExpr.CalculusType;
}

@members {
    protected void createGrants( PolicyNode ref, List<Principal> principals, ConditionalPermissions perms )
    {
        throw new UnsupportedOperationException();
    }
    
    protected Permission createPermission(PolicyNode ref, Class clazz, String pname, String action)
    {
        throw new UnsupportedOperationException();
    }

    protected Principal createPrincipal(PolicyNode ref, Class clazz, String name, String property)
    {
        throw new UnsupportedOperationException();
    }

    protected ArgumentExpr createArgument(PolicyNode ref, Class cls, String name)
    {
        throw new UnsupportedOperationException();
    }

    protected Permission createIfPermission(PolicyNode ref, Permission p, Expression expr)
    {
        throw new UnsupportedOperationException();
    }

    protected String expandProperty(PolicyNode ref, String str)
    {
        throw new UnsupportedOperationException();
    }

    protected Expression findStatic(PolicyNode ref, Class clazz, String name)
    {
        throw new UnsupportedOperationException();
    }

    protected String prepareString(String str)
    {
        return str;
    }

    protected Expression createAdditionExpr(PolicyNode ref, Expression left, Expression right)
    {
        throw new UnsupportedOperationException();
    }

    protected Expression createAndOrExpr(PolicyNode ref, boolean and, Expression left, Expression right)
    {
        throw new UnsupportedOperationException();
    }

    protected Expression createCalculusExpr(PolicyNode ref, CalculusType type, Expression left, Expression right)
    {
        throw new UnsupportedOperationException();
    }

    protected Expression createEqualExpr(PolicyNode ref, EqualType type, Expression left, Expression right)
    {
        throw new UnsupportedOperationException();
    }

    protected Expression createInstanceOfExpr(PolicyNode ref, Class clazz, Expression target)
    {
        throw new UnsupportedOperationException();
    }

    protected Expression createNegateExpr(PolicyNode ref, Expression expr)
    {
        throw new UnsupportedOperationException();
    }
}

policy
	: grant*
	;
	
grant
    : ^(b=GRANT principal? permissions) {createGrants($b, $principal.p, $permissions.p);}
    ;
		
principal returns[List p]
@init {p = new ArrayList();}
	: ^(PRINCIPAL_OR p1=principal p2=principal) {p.addAll($p1.p); p.addAll($p2.p); }
	| ^(b=PRINCIPAL (classMatch name=propertyExpanded property=propertyExpanded?)?) {p.add(createPrincipal($b, $classMatch.clazz, $name.str, $property.str));}
	;

permissions returns [ConditionalPermissions p]
@init{ p = new ConditionalPermissions(); }
	: (conditionalPermission {p.add($conditionalPermission.p);})+
	;
	
conditionalPermission returns [Permission p]
	: ^(b=IF expression p3=permission) {p = createIfPermission($b, $p3.p, $expression.expr);}
	| p2=permission {p = $p2.p;}
	;

permission returns [Permission p]
	: ^(b=PERMISSION classMatch (pname=propertyExpanded action=propertyExpanded?)?)
	    {p=createPermission($b, $classMatch.clazz, $pname.str, $action.str);}
	;

expression returns [Expression expr]
	: ^(b=AND e1=expression e2=expression) {expr = createAndOrExpr($b, true, $e1.expr, $e2.expr);}
	| ^(b=OR e1=expression e2=expression)  {expr = createAndOrExpr($b, false, $e1.expr, $e2.expr);}
	| ^(b=INSTANCEOF e1=expression classMatch) {expr = createInstanceOfExpr($b, $classMatch.clazz, $e1.expr);}
	| ^(b=LTGT e1=expression e2=expression) {expr = createEqualExpr($b, EqualType.NEQ, $e1.expr, $e2.expr); }
  	| ^(b=EQ e1=expression e2=expression) {expr = createEqualExpr($b, EqualType.EQ, $e1.expr, $e2.expr); }
	| ^(b=LTEQ e1=expression e2=expression) {expr = createEqualExpr($b, EqualType.LTEQ, $e1.expr, $e2.expr); }
	| ^(b=GTEQ e1=expression e2=expression) {expr = createEqualExpr($b, EqualType.GTEQ, $e1.expr, $e2.expr); }
	| ^(b=LT e1=expression e2=expression) {expr = createEqualExpr($b, EqualType.LT, $e1.expr, $e2.expr); }
	| ^(b=GT e1=expression e2=expression) {expr = createEqualExpr($b, EqualType.GT, $e1.expr, $e2.expr); }
  	| ^(b=PLUS e1=expression e2=expression) {expr = createAdditionExpr($b, $e1.expr, $e2.expr); }
	| ^(b=SUB e1=expression e2=expression) {expr = createCalculusExpr($b, CalculusType.Subtract, $e1.expr, $e2.expr); }
	| ^(b=STAR e1=expression e2=expression) {expr = createCalculusExpr($b, CalculusType.Multiply, $e1.expr, $e2.expr); }
	| ^(b=SLASH e1=expression e2=expression) {expr = createCalculusExpr($b, CalculusType.Divide, $e1.expr, $e2.expr); }
	| ^(b=PERCENT e1=expression e2=expression) {expr = createCalculusExpr($b, CalculusType.Modulus, $e1.expr, $e2.expr); }
	| ^(b=NEGATIVE e1=expression) {expr = createNegateExpr($b, $e1.expr);}
	| ^(STATIC classMatch? i=IDENTIFIER) {expr=findStatic($i, $classMatch.clazz, $i.text);}
	| ^(b=ARGUMENT classMatch i=IDENTIFIER) {expr = createArgument($b, $classMatch.clazz, $i.text);}
	| literal {expr=$literal.value;}
	;
literal returns [Expression value]
@init{ Object lValue = null; }
@after { value = new LiteralExpr(lValue); }
	: s=STRING_LITERAL {lValue=prepareString($s.text);}
	| i=DECIMAL_LITERAL {lValue=Integer.valueOf( $i.text ); }
	| o=OCTAL_LITERAL {lValue=Integer.valueOf( $o.text,8 ); }
	| h=HEX_LITERAL {lValue=Integer.valueOf( $o.text,16 ); }
//    | TIME_LITERAL
	| f=FLOATING_POINT_LITERAL {lValue=Float.valueOf($f.text); }
	| TRUE {lValue=Boolean.TRUE;}
	| FALSE {lValue=Boolean.FALSE;}
	| NULL
	;

classMatch returns [Class clazz]
    : c=CLASS {clazz = ((ClassNode)$c).getRepresentedClass();}
    ;

propertyExpanded returns [String str]
    : ^(b=EXPAND s=STRING_LITERAL) {str=expandProperty($b, prepareString($s.text));}
    ;
