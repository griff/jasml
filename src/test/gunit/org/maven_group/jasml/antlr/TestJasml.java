
import org.antlr.gunit.gUnitBaseTest;

public class TestJasml extends gUnitBaseTest {
	
	public void setUp() {
		this.packagePath = "./org/maven_group/jasml/antlr";
		this.lexerPath = "org.maven_group.jasml.antlr.JasmlLexer";
		this.parserPath = "org.maven_group.jasml.antlr.JasmlParser";
	}

	public void testMatch1() throws Exception {
		// gunit test on line 8
		Object retval = execParser("match", 8, "_ => 26", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> _ 26)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch2() throws Exception {
		// gunit test on line 9
		Object retval = execParser("match", 9, "25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(=> 25 26) (=> _ 12)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch3() throws Exception {
		// gunit test on line 10
		Object retval = execParser("match", 10, "12 => fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> 12 (fn (=> 25 26) (=> _ 12)))";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch4() throws Exception {
		// gunit test on line 11
		Object retval = execParser("match", 11, "12 => (fn 25 => 26) | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> 12 (fn (=> 25 26))) (=> _ 12)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testType1() throws Exception {
		// gunit test on line 14
		Object retval = execParser("type", 14, "int", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "int";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType2() throws Exception {
		// gunit test on line 15
		Object retval = execParser("type", 15, "int list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list int)";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType3() throws Exception {
		// gunit test on line 16
		Object retval = execParser("type", 16, "int list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list (list int))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType4() throws Exception {
		// gunit test on line 17
		Object retval = execParser("type", 17, "list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType5() throws Exception {
		// gunit test on line 18
		Object retval = execParser("type", 18, "('a -> 'b) -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> (-> (' a) (' b)) (-> (list (' a)) (list (' b))))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType6() throws Exception {
		// gunit test on line 19
		Object retval = execParser("type", 19, "'a -> 'b -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> (' a) (-> (' b) (-> (list (' a)) (list (' b)))))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testTuple1() throws Exception {
		// gunit test on line 22
		Object retval = execParser("tuple", 22, "(ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "ert";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple2() throws Exception {
		// gunit test on line 23
		Object retval = execParser("tuple", 23, "(1, ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(TUPLE_LITERAL 1 ert)";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testList1() throws Exception {
		// gunit test on line 26
		Object retval = execParser("list", 26, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList2() throws Exception {
		// gunit test on line 27
		Object retval = execParser("list", 27, "[1]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 1)";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList3() throws Exception {
		// gunit test on line 28
		Object retval = execParser("list", 28, "[1,2]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 1 2)";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testAtomicPattern1() throws Exception {
		// gunit test on line 31
		Object retval = execParser("atomicPattern", 31, "a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "a";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern2() throws Exception {
		// gunit test on line 32
		Object retval = execParser("atomicPattern", 32, "_", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "_";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern3() throws Exception {
		// gunit test on line 33
		Object retval = execParser("atomicPattern", 33, "12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern4() throws Exception {
		// gunit test on line 34
		Object retval = execParser("atomicPattern", 34, "12.5", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12.5";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern5() throws Exception {
		// gunit test on line 35
		Object retval = execParser("atomicPattern", 35, "true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "true";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern6() throws Exception {
		// gunit test on line 36
		Object retval = execParser("atomicPattern", 36, "false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "false";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern7() throws Exception {
		// gunit test on line 37
		Object retval = execParser("atomicPattern", 37, "#\"a\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "#\"a\"";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern8() throws Exception {
		// gunit test on line 38
		Object retval = execParser("atomicPattern", 38, "\"abc\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "\"abc\"";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern9() throws Exception {
		// gunit test on line 39
		Object retval = execParser("atomicPattern", 39, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern10() throws Exception {
		// gunit test on line 40
		Object retval = execParser("atomicPattern", 40, "[_]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL _)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern11() throws Exception {
		// gunit test on line 41
		Object retval = execParser("atomicPattern", 41, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL a)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern12() throws Exception {
		// gunit test on line 42
		Object retval = execParser("atomicPattern", 42, "[12]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern13() throws Exception {
		// gunit test on line 43
		Object retval = execParser("atomicPattern", 43, "[12.5]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12.5)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern14() throws Exception {
		// gunit test on line 44
		Object retval = execParser("atomicPattern", 44, "[true]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL true)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern15() throws Exception {
		// gunit test on line 45
		Object retval = execParser("atomicPattern", 45, "[false]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL false)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern16() throws Exception {
		// gunit test on line 46
		Object retval = execParser("atomicPattern", 46, "[#\"a\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL #\"a\")";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern17() throws Exception {
		// gunit test on line 47
		Object retval = execParser("atomicPattern", 47, "[\"abc\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL \"abc\")";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern18() throws Exception {
		// gunit test on line 48
		Object retval = execParser("atomicPattern", 48, "1::2::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: 1 (:: 2 LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern19() throws Exception {
		// gunit test on line 49
		Object retval = execParser("atomicPattern", 49, "(1::[])::[3]::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: (:: 1 LIST_LITERAL) (:: (LIST_LITERAL 3) LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern20() throws Exception {
		// gunit test on line 50
		Object retval = execParser("atomicPattern", 50, "_::xs", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: _ xs)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern21() throws Exception {
		// gunit test on line 51
		Object retval = execParser("atomicPattern", 51, "(1, \"abc\")::(3, \"\")::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: (TUPLE_LITERAL 1 \"abc\") (:: (TUPLE_LITERAL 3 \"\") LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}



}