
import org.antlr.gunit.gUnitBaseTest;

public class TestJasml extends gUnitBaseTest {
	
	public void setUp() {
		this.packagePath = "./org/maven_group/jasml/antlr";
		this.lexerPath = "org.maven_group.jasml.antlr.JasmlLexer";
		this.parserPath = "org.maven_group.jasml.antlr.JasmlParser";
	}

	public void testAndOrExpression1() throws Exception {
		// gunit test on line 8
		Object retval = execParser("andOrExpression", 8, "12 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL 12 12)";

		assertEquals("testing rule "+"andOrExpression", expecting, actual);
	}

	public void testAndOrExpression2() throws Exception {
		// gunit test on line 9
		Object retval = execParser("andOrExpression", 9, "raise Muh", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (LOOKUP_NONFIX Muh))";

		assertEquals("testing rule "+"andOrExpression", expecting, actual);
	}

	public void testAndOrExpression3() throws Exception {
		// gunit test on line 10
		Object retval = execParser("andOrExpression", 10, "if true then 12 else 25", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(if true 12 25)";

		assertEquals("testing rule "+"andOrExpression", expecting, actual);
	}

	public void testAndOrExpression4() throws Exception {
		// gunit test on line 11
		Object retval = execParser("andOrExpression", 11, "fn _ => 26", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> _ 26))";

		assertEquals("testing rule "+"andOrExpression", expecting, actual);
	}

	public void testAndOrExpression5() throws Exception {
		// gunit test on line 12
		Object retval = execParser("andOrExpression", 12, "fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> 25 26) (=> _ 12))";

		assertEquals("testing rule "+"andOrExpression", expecting, actual);
	}

	public void testAndOrExpression6() throws Exception {
		// gunit test on line 13
		Object retval = execParser("andOrExpression", 13, "fn 12 => fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> 12 (fn (=> 25 26) (=> _ 12))))";

		assertEquals("testing rule "+"andOrExpression", expecting, actual);
	}

	public void testAndOrExpression7() throws Exception {
		// gunit test on line 14
		Object retval = execParser("andOrExpression", 14, "fn 12 => (fn 25 => 26) | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> 12 (fn (=> 25 26))) (=> _ 12))";

		assertEquals("testing rule "+"andOrExpression", expecting, actual);
	}

	public void testAppliedExpression1() throws Exception {
		// gunit test on line 17
		Object retval = execParser("appliedExpression", 17, "12 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL 12 12)";

		assertEquals("testing rule "+"appliedExpression", expecting, actual);
	}

	public void testAppliedExpression2() throws Exception {
		// gunit test on line 18
		Object retval = execParser("appliedExpression", 18, "a b", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP_NONFIX a) (LOOKUP_NONFIX b))";

		assertEquals("testing rule "+"appliedExpression", expecting, actual);
	}

	public void testAppliedExpression3() throws Exception {
		// gunit test on line 19
		Object retval = execParser("appliedExpression", 19, "a (12, 12)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP_NONFIX a) (TUPLE 12 12))";

		assertEquals("testing rule "+"appliedExpression", expecting, actual);
	}

	public void testAppliedExpression4() throws Exception {
		// gunit test on line 20
		Object retval = execParser("appliedExpression", 20, "a 12 12 12 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (CALL (CALL (CALL (LOOKUP_NONFIX a) 12) 12) 12) 12)";

		assertEquals("testing rule "+"appliedExpression", expecting, actual);
	}

	public void testAtomicExpression1() throws Exception {
		// gunit test on line 23
		Object retval = execParser("atomicExpression", 23, "op +", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP +)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression2() throws Exception {
		// gunit test on line 24
		Object retval = execParser("atomicExpression", 24, "op a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP a)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression3() throws Exception {
		// gunit test on line 25
		Object retval = execParser("atomicExpression", 25, "a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP_NONFIX a)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression4() throws Exception {
		// gunit test on line 26
		Object retval = execParser("atomicExpression", 26, "12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression5() throws Exception {
		// gunit test on line 27
		Object retval = execParser("atomicExpression", 27, "12.5", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12.5";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression6() throws Exception {
		// gunit test on line 28
		Object retval = execParser("atomicExpression", 28, "true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "true";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression7() throws Exception {
		// gunit test on line 29
		Object retval = execParser("atomicExpression", 29, "false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "false";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression8() throws Exception {
		// gunit test on line 30
		Object retval = execParser("atomicExpression", 30, "#\"a\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "#\"a\"";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression9() throws Exception {
		// gunit test on line 31
		Object retval = execParser("atomicExpression", 31, "\"abc\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "\"abc\"";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression10() throws Exception {
		// gunit test on line 32
		Object retval = execParser("atomicExpression", 32, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression11() throws Exception {
		// gunit test on line 33
		Object retval = execParser("atomicExpression", 33, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL (LOOKUP_NONFIX a))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression12() throws Exception {
		// gunit test on line 34
		Object retval = execParser("atomicExpression", 34, "[12]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression13() throws Exception {
		// gunit test on line 35
		Object retval = execParser("atomicExpression", 35, "[12.5]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12.5)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression14() throws Exception {
		// gunit test on line 36
		Object retval = execParser("atomicExpression", 36, "[true]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL true)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression15() throws Exception {
		// gunit test on line 37
		Object retval = execParser("atomicExpression", 37, "[false]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL false)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression16() throws Exception {
		// gunit test on line 38
		Object retval = execParser("atomicExpression", 38, "[#\"a\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL #\"a\")";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression17() throws Exception {
		// gunit test on line 39
		Object retval = execParser("atomicExpression", 39, "[\"abc\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL \"abc\")";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression18() throws Exception {
		// gunit test on line 40
		Object retval = execParser("atomicExpression", 40, "(ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP_NONFIX ert)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression19() throws Exception {
		// gunit test on line 41
		Object retval = execParser("atomicExpression", 41, "(1, ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(TUPLE 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression20() throws Exception {
		// gunit test on line 42
		Object retval = execParser("atomicExpression", 42, "(1; ert, 12)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression21() throws Exception {
		// gunit test on line 43
		Object retval = execParser("atomicExpression", 43, "(1; ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(EXPRESSIONS 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression22() throws Exception {
		// gunit test on line 44
		Object retval = execParser("atomicExpression", 44, "let in 12 end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let 12)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression23() throws Exception {
		// gunit test on line 45
		Object retval = execParser("atomicExpression", 45, "let exception Muh in raise Muh end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let (exception Muh) (raise (LOOKUP_NONFIX Muh)))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression24() throws Exception {
		// gunit test on line 46
		Object retval = execParser("atomicExpression", 46, "let exception Muh exception Gert in raise Muh end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let (exception Muh) (exception Gert) (raise (LOOKUP_NONFIX Muh)))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression25() throws Exception {
		// gunit test on line 47
		Object retval = execParser("atomicExpression", 47, "let exception Muh 12 in 12 end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testMatch1() throws Exception {
		// gunit test on line 50
		Object retval = execParser("match", 50, "_ => 26", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> _ 26)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch2() throws Exception {
		// gunit test on line 51
		Object retval = execParser("match", 51, "25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(=> 25 26) (=> _ 12)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch3() throws Exception {
		// gunit test on line 52
		Object retval = execParser("match", 52, "12 => fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> 12 (fn (=> 25 26) (=> _ 12)))";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch4() throws Exception {
		// gunit test on line 53
		Object retval = execParser("match", 53, "12 => (fn 25 => 26) | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> 12 (fn (=> 25 26))) (=> _ 12)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testType1() throws Exception {
		// gunit test on line 56
		Object retval = execParser("type", 56, "int", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "int";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType2() throws Exception {
		// gunit test on line 57
		Object retval = execParser("type", 57, "int list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list int)";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType3() throws Exception {
		// gunit test on line 58
		Object retval = execParser("type", 58, "int list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list (list int))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType4() throws Exception {
		// gunit test on line 59
		Object retval = execParser("type", 59, "list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType5() throws Exception {
		// gunit test on line 60
		Object retval = execParser("type", 60, "('a -> 'b) -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> (-> (' a) (' b)) (-> (list (' a)) (list (' b))))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType6() throws Exception {
		// gunit test on line 61
		Object retval = execParser("type", 61, "'a -> 'b -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> (' a) (-> (' b) (-> (list (' a)) (list (' b)))))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testTuple1() throws Exception {
		// gunit test on line 64
		Object retval = execParser("tuple", 64, "(ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP_NONFIX ert)";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple2() throws Exception {
		// gunit test on line 65
		Object retval = execParser("tuple", 65, "(1, ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(TUPLE 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple3() throws Exception {
		// gunit test on line 66
		Object retval = execParser("tuple", 66, "(1; ert, 12)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple4() throws Exception {
		// gunit test on line 67
		Object retval = execParser("tuple", 67, "(1; ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(EXPRESSIONS 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testList1() throws Exception {
		// gunit test on line 70
		Object retval = execParser("list", 70, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList2() throws Exception {
		// gunit test on line 71
		Object retval = execParser("list", 71, "[1]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 1)";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList3() throws Exception {
		// gunit test on line 72
		Object retval = execParser("list", 72, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL (LOOKUP_NONFIX a))";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList4() throws Exception {
		// gunit test on line 73
		Object retval = execParser("list", 73, "[1,2]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 1 2)";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testAtomicPattern1() throws Exception {
		// gunit test on line 76
		Object retval = execParser("atomicPattern", 76, "a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "a";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern2() throws Exception {
		// gunit test on line 77
		Object retval = execParser("atomicPattern", 77, "_", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "_";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern3() throws Exception {
		// gunit test on line 78
		Object retval = execParser("atomicPattern", 78, "12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern4() throws Exception {
		// gunit test on line 79
		Object retval = execParser("atomicPattern", 79, "12.5", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12.5";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern5() throws Exception {
		// gunit test on line 80
		Object retval = execParser("atomicPattern", 80, "true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "true";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern6() throws Exception {
		// gunit test on line 81
		Object retval = execParser("atomicPattern", 81, "false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "false";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern7() throws Exception {
		// gunit test on line 82
		Object retval = execParser("atomicPattern", 82, "#\"a\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "#\"a\"";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern8() throws Exception {
		// gunit test on line 83
		Object retval = execParser("atomicPattern", 83, "\"abc\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "\"abc\"";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern9() throws Exception {
		// gunit test on line 84
		Object retval = execParser("atomicPattern", 84, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern10() throws Exception {
		// gunit test on line 85
		Object retval = execParser("atomicPattern", 85, "[_]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL _)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern11() throws Exception {
		// gunit test on line 86
		Object retval = execParser("atomicPattern", 86, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL a)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern12() throws Exception {
		// gunit test on line 87
		Object retval = execParser("atomicPattern", 87, "[12]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern13() throws Exception {
		// gunit test on line 88
		Object retval = execParser("atomicPattern", 88, "[12.5]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12.5)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern14() throws Exception {
		// gunit test on line 89
		Object retval = execParser("atomicPattern", 89, "[true]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL true)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern15() throws Exception {
		// gunit test on line 90
		Object retval = execParser("atomicPattern", 90, "[false]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL false)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern16() throws Exception {
		// gunit test on line 91
		Object retval = execParser("atomicPattern", 91, "[#\"a\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL #\"a\")";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern17() throws Exception {
		// gunit test on line 92
		Object retval = execParser("atomicPattern", 92, "[\"abc\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL \"abc\")";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern18() throws Exception {
		// gunit test on line 93
		Object retval = execParser("atomicPattern", 93, "1::2::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: 1 (:: 2 LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern19() throws Exception {
		// gunit test on line 94
		Object retval = execParser("atomicPattern", 94, "(1::[])::[3]::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: (:: 1 LIST_LITERAL) (:: (LIST_LITERAL 3) LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern20() throws Exception {
		// gunit test on line 95
		Object retval = execParser("atomicPattern", 95, "_::xs", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: _ xs)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern21() throws Exception {
		// gunit test on line 96
		Object retval = execParser("atomicPattern", 96, "(1, \"abc\")::(3, \"\")::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: (TUPLE 1 \"abc\") (:: (TUPLE 3 \"\") LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}



}