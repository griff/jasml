
import org.antlr.gunit.gUnitBaseTest;

public class TestJasml extends gUnitBaseTest {
	
	public void setUp() {
		this.packagePath = "./org/maven_group/jasml/antlr";
		this.lexerPath = "org.maven_group.jasml.antlr.JasmlLexer";
		this.parserPath = "org.maven_group.jasml.antlr.JasmlParser";
	}

	public void testExpression1() throws Exception {
		// gunit test on line 8
		Object retval = execParser("expression", 8, "12 : int", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: 12 int)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression2() throws Exception {
		// gunit test on line 9
		Object retval = execParser("expression", 9, "12 : a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: 12 a)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression3() throws Exception {
		// gunit test on line 10
		Object retval = execParser("expression", 10, "12 : 'a -> 'b", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: 12 (-> 'a 'b))";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression4() throws Exception {
		// gunit test on line 11
		Object retval = execParser("expression", 11, "raise Muh : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (: (LOOKUP_NONFIX Muh) exn))";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression5() throws Exception {
		// gunit test on line 12
		Object retval = execParser("expression", 12, "raise Muh : exn : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (: (: (LOOKUP_NONFIX Muh) exn) exn))";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression6() throws Exception {
		// gunit test on line 13
		Object retval = execParser("expression", 13, "raise Muh : exn : exn : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (: (: (: (LOOKUP_NONFIX Muh) exn) exn) exn))";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression7() throws Exception {
		// gunit test on line 14
		Object retval = execParser("expression", 14, "(raise Muh) : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: (raise (LOOKUP_NONFIX Muh)) exn)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression8() throws Exception {
		// gunit test on line 15
		Object retval = execParser("expression", 15, "(raise Muh) : exn : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: (: (raise (LOOKUP_NONFIX Muh)) exn) exn)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression9() throws Exception {
		// gunit test on line 16
		Object retval = execParser("expression", 16, "(raise Muh) : exn : exn : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: (: (: (raise (LOOKUP_NONFIX Muh)) exn) exn) exn)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testAndalsoExpression1() throws Exception {
		// gunit test on line 19
		Object retval = execParser("andalsoExpression", 19, "true andalso false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso true false)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression2() throws Exception {
		// gunit test on line 20
		Object retval = execParser("andalsoExpression", 20, "1 andalso 2 andalso 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso (andalso 1 2) 3)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression3() throws Exception {
		// gunit test on line 21
		Object retval = execParser("andalsoExpression", 21, "1 andalso (2 andalso 3)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso 1 (andalso 2 3))";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression4() throws Exception {
		// gunit test on line 22
		Object retval = execParser("andalsoExpression", 22, "1 andalso 2 orelse 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso 1 (orelse 2 3))";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression5() throws Exception {
		// gunit test on line 23
		Object retval = execParser("andalsoExpression", 23, "1 orelse 2 andalso 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso (orelse 1 2) 3)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression6() throws Exception {
		// gunit test on line 24
		Object retval = execParser("andalsoExpression", 24, "(1 andalso 2) orelse 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse (andalso 1 2) 3)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression7() throws Exception {
		// gunit test on line 25
		Object retval = execParser("andalsoExpression", 25, "1 orelse (2 andalso 3)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse 1 (andalso 2 3))";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression8() throws Exception {
		// gunit test on line 26
		Object retval = execParser("andalsoExpression", 26, "raise Muh andalso true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (andalso (LOOKUP_NONFIX Muh) true))";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression9() throws Exception {
		// gunit test on line 27
		Object retval = execParser("andalsoExpression", 27, "(raise Muh) andalso true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso (raise (LOOKUP_NONFIX Muh)) true)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testOrelseExpression1() throws Exception {
		// gunit test on line 30
		Object retval = execParser("orelseExpression", 30, "true orelse false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse true false)";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testOrelseExpression2() throws Exception {
		// gunit test on line 31
		Object retval = execParser("orelseExpression", 31, "1 orelse 2 orelse 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse (orelse 1 2) 3)";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testOrelseExpression3() throws Exception {
		// gunit test on line 32
		Object retval = execParser("orelseExpression", 32, "1 orelse (2 orelse 3)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse 1 (orelse 2 3))";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testOrelseExpression4() throws Exception {
		// gunit test on line 33
		Object retval = execParser("orelseExpression", 33, "raise Muh orelse true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (orelse (LOOKUP_NONFIX Muh) true))";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testOrelseExpression5() throws Exception {
		// gunit test on line 34
		Object retval = execParser("orelseExpression", 34, "(raise Muh) orelse true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse (raise (LOOKUP_NONFIX Muh)) true)";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testPreInfixExpression1() throws Exception {
		// gunit test on line 37
		Object retval = execParser("preInfixExpression", 37, "12 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL 12 12)";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression2() throws Exception {
		// gunit test on line 38
		Object retval = execParser("preInfixExpression", 38, "raise Muh", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (LOOKUP_NONFIX Muh))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression3() throws Exception {
		// gunit test on line 39
		Object retval = execParser("preInfixExpression", 39, "if true then 12 else 25", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(if true 12 25)";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression4() throws Exception {
		// gunit test on line 40
		Object retval = execParser("preInfixExpression", 40, "fn _ => 26", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> _ 26))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression5() throws Exception {
		// gunit test on line 41
		Object retval = execParser("preInfixExpression", 41, "fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> 25 26) (=> _ 12))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression6() throws Exception {
		// gunit test on line 42
		Object retval = execParser("preInfixExpression", 42, "fn 12 => fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> 12 (fn (=> 25 26) (=> _ 12))))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression7() throws Exception {
		// gunit test on line 43
		Object retval = execParser("preInfixExpression", 43, "fn 12 => (fn 25 => 26) | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> 12 (fn (=> 25 26))) (=> _ 12))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testAppliedExpression1() throws Exception {
		// gunit test on line 46
		Object retval = execParser("appliedExpression", 46, "12 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL 12 12)";

		assertEquals("testing rule "+"appliedExpression", expecting, actual);
	}

	public void testAppliedExpression2() throws Exception {
		// gunit test on line 47
		Object retval = execParser("appliedExpression", 47, "a b", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP_NONFIX a) (LOOKUP_NONFIX b))";

		assertEquals("testing rule "+"appliedExpression", expecting, actual);
	}

	public void testAppliedExpression3() throws Exception {
		// gunit test on line 48
		Object retval = execParser("appliedExpression", 48, "a (12, 12)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP_NONFIX a) (TUPLE 12 12))";

		assertEquals("testing rule "+"appliedExpression", expecting, actual);
	}

	public void testAppliedExpression4() throws Exception {
		// gunit test on line 49
		Object retval = execParser("appliedExpression", 49, "a 12 12 12 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (CALL (CALL (CALL (LOOKUP_NONFIX a) 12) 12) 12) 12)";

		assertEquals("testing rule "+"appliedExpression", expecting, actual);
	}

	public void testAtomicExpression1() throws Exception {
		// gunit test on line 52
		Object retval = execParser("atomicExpression", 52, "op +", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP +)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression2() throws Exception {
		// gunit test on line 53
		Object retval = execParser("atomicExpression", 53, "op a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP a)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression3() throws Exception {
		// gunit test on line 54
		Object retval = execParser("atomicExpression", 54, "a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP_NONFIX a)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression4() throws Exception {
		// gunit test on line 55
		Object retval = execParser("atomicExpression", 55, "12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression5() throws Exception {
		// gunit test on line 56
		Object retval = execParser("atomicExpression", 56, "12.5", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12.5";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression6() throws Exception {
		// gunit test on line 57
		Object retval = execParser("atomicExpression", 57, "true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "true";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression7() throws Exception {
		// gunit test on line 58
		Object retval = execParser("atomicExpression", 58, "false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "false";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression8() throws Exception {
		// gunit test on line 59
		Object retval = execParser("atomicExpression", 59, "#\"a\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "#\"a\"";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression9() throws Exception {
		// gunit test on line 60
		Object retval = execParser("atomicExpression", 60, "\"abc\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "\"abc\"";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression10() throws Exception {
		// gunit test on line 61
		Object retval = execParser("atomicExpression", 61, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression11() throws Exception {
		// gunit test on line 62
		Object retval = execParser("atomicExpression", 62, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL (LOOKUP_NONFIX a))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression12() throws Exception {
		// gunit test on line 63
		Object retval = execParser("atomicExpression", 63, "[12]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression13() throws Exception {
		// gunit test on line 64
		Object retval = execParser("atomicExpression", 64, "[12.5]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12.5)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression14() throws Exception {
		// gunit test on line 65
		Object retval = execParser("atomicExpression", 65, "[true]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL true)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression15() throws Exception {
		// gunit test on line 66
		Object retval = execParser("atomicExpression", 66, "[false]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL false)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression16() throws Exception {
		// gunit test on line 67
		Object retval = execParser("atomicExpression", 67, "[#\"a\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL #\"a\")";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression17() throws Exception {
		// gunit test on line 68
		Object retval = execParser("atomicExpression", 68, "[\"abc\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL \"abc\")";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression18() throws Exception {
		// gunit test on line 69
		Object retval = execParser("atomicExpression", 69, "(ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP_NONFIX ert)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression19() throws Exception {
		// gunit test on line 70
		Object retval = execParser("atomicExpression", 70, "(1, ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(TUPLE 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression20() throws Exception {
		// gunit test on line 71
		Object retval = execParser("atomicExpression", 71, "(1; ert, 12)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression21() throws Exception {
		// gunit test on line 72
		Object retval = execParser("atomicExpression", 72, "(1; ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(EXPRESSIONS 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression22() throws Exception {
		// gunit test on line 73
		Object retval = execParser("atomicExpression", 73, "let in 12 end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let 12)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression23() throws Exception {
		// gunit test on line 74
		Object retval = execParser("atomicExpression", 74, "let exception Muh in raise Muh end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let (exception Muh) (raise (LOOKUP_NONFIX Muh)))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression24() throws Exception {
		// gunit test on line 75
		Object retval = execParser("atomicExpression", 75, "let exception Muh exception Gert in raise Muh end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let (exception Muh) (exception Gert) (raise (LOOKUP_NONFIX Muh)))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression25() throws Exception {
		// gunit test on line 76
		Object retval = execParser("atomicExpression", 76, "let exception Muh 12 in 12 end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testMatch1() throws Exception {
		// gunit test on line 79
		Object retval = execParser("match", 79, "_ => 26", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> _ 26)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch2() throws Exception {
		// gunit test on line 80
		Object retval = execParser("match", 80, "25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(=> 25 26) (=> _ 12)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch3() throws Exception {
		// gunit test on line 81
		Object retval = execParser("match", 81, "12 => fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> 12 (fn (=> 25 26) (=> _ 12)))";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch4() throws Exception {
		// gunit test on line 82
		Object retval = execParser("match", 82, "12 => (fn 25 => 26) | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> 12 (fn (=> 25 26))) (=> _ 12)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testType1() throws Exception {
		// gunit test on line 85
		Object retval = execParser("type", 85, "int", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "int";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType2() throws Exception {
		// gunit test on line 86
		Object retval = execParser("type", 86, "int list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list int)";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType3() throws Exception {
		// gunit test on line 87
		Object retval = execParser("type", 87, "int list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list (list int))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType4() throws Exception {
		// gunit test on line 88
		Object retval = execParser("type", 88, "list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType5() throws Exception {
		// gunit test on line 89
		Object retval = execParser("type", 89, "'12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "'12";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType6() throws Exception {
		// gunit test on line 90
		Object retval = execParser("type", 90, "('a -> 'b) -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> (-> 'a 'b) (-> (list 'a) (list 'b)))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType7() throws Exception {
		// gunit test on line 91
		Object retval = execParser("type", 91, "'a -> 'b -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> 'a (-> 'b (-> (list 'a) (list 'b))))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testTuple1() throws Exception {
		// gunit test on line 94
		Object retval = execParser("tuple", 94, "(ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP_NONFIX ert)";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple2() throws Exception {
		// gunit test on line 95
		Object retval = execParser("tuple", 95, "(1, ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(TUPLE 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple3() throws Exception {
		// gunit test on line 96
		Object retval = execParser("tuple", 96, "(1; ert, 12)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple4() throws Exception {
		// gunit test on line 97
		Object retval = execParser("tuple", 97, "(1; ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(EXPRESSIONS 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testList1() throws Exception {
		// gunit test on line 100
		Object retval = execParser("list", 100, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList2() throws Exception {
		// gunit test on line 101
		Object retval = execParser("list", 101, "[1]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 1)";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList3() throws Exception {
		// gunit test on line 102
		Object retval = execParser("list", 102, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL (LOOKUP_NONFIX a))";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList4() throws Exception {
		// gunit test on line 103
		Object retval = execParser("list", 103, "[1,2]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 1 2)";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testAtomicPattern1() throws Exception {
		// gunit test on line 106
		Object retval = execParser("atomicPattern", 106, "a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "a";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern2() throws Exception {
		// gunit test on line 107
		Object retval = execParser("atomicPattern", 107, "_", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "_";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern3() throws Exception {
		// gunit test on line 108
		Object retval = execParser("atomicPattern", 108, "12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern4() throws Exception {
		// gunit test on line 109
		Object retval = execParser("atomicPattern", 109, "12.5", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12.5";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern5() throws Exception {
		// gunit test on line 110
		Object retval = execParser("atomicPattern", 110, "true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "true";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern6() throws Exception {
		// gunit test on line 111
		Object retval = execParser("atomicPattern", 111, "false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "false";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern7() throws Exception {
		// gunit test on line 112
		Object retval = execParser("atomicPattern", 112, "#\"a\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "#\"a\"";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern8() throws Exception {
		// gunit test on line 113
		Object retval = execParser("atomicPattern", 113, "\"abc\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "\"abc\"";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern9() throws Exception {
		// gunit test on line 114
		Object retval = execParser("atomicPattern", 114, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern10() throws Exception {
		// gunit test on line 115
		Object retval = execParser("atomicPattern", 115, "[_]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL _)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern11() throws Exception {
		// gunit test on line 116
		Object retval = execParser("atomicPattern", 116, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL a)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern12() throws Exception {
		// gunit test on line 117
		Object retval = execParser("atomicPattern", 117, "[12]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern13() throws Exception {
		// gunit test on line 118
		Object retval = execParser("atomicPattern", 118, "[12.5]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12.5)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern14() throws Exception {
		// gunit test on line 119
		Object retval = execParser("atomicPattern", 119, "[true]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL true)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern15() throws Exception {
		// gunit test on line 120
		Object retval = execParser("atomicPattern", 120, "[false]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL false)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern16() throws Exception {
		// gunit test on line 121
		Object retval = execParser("atomicPattern", 121, "[#\"a\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL #\"a\")";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern17() throws Exception {
		// gunit test on line 122
		Object retval = execParser("atomicPattern", 122, "[\"abc\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL \"abc\")";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern18() throws Exception {
		// gunit test on line 123
		Object retval = execParser("atomicPattern", 123, "1::2::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: 1 (:: 2 LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern19() throws Exception {
		// gunit test on line 124
		Object retval = execParser("atomicPattern", 124, "(1::[])::[3]::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: (:: 1 LIST_LITERAL) (:: (LIST_LITERAL 3) LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern20() throws Exception {
		// gunit test on line 125
		Object retval = execParser("atomicPattern", 125, "_::xs", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: _ xs)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern21() throws Exception {
		// gunit test on line 126
		Object retval = execParser("atomicPattern", 126, "(1, \"abc\")::(3, \"\")::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: (TUPLE 1 \"abc\") (:: (TUPLE 3 \"\") LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}



}