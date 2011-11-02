
import org.antlr.gunit.gUnitBaseTest;

public class TestJasml extends gUnitBaseTest {
	
	public void setUp() {
		this.packagePath = "./org/maven_group/jasml/antlr";
		this.lexerPath = "org.maven_group.jasml.antlr.JasmlLexer";
		this.parserPath = "org.maven_group.jasml.antlr.JasmlParser";
	}

	public void testDeclarations1() throws Exception {
		// gunit test on line 8
		Object retval = execParser("declarations", 8, "12 val test=23", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations2() throws Exception {
		// gunit test on line 9
		Object retval = execParser("declarations", 9, "12;val test=23", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12 (val test 23)";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations3() throws Exception {
		// gunit test on line 10
		Object retval = execParser("declarations", 10, "val test=23;12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(val test 23) 12";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations4() throws Exception {
		// gunit test on line 11
		Object retval = execParser("declarations", 11, "val test=23 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(val test (CALL 23 12))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations5() throws Exception {
		// gunit test on line 12
		Object retval = execParser("declarations", 12, "val test=23 val muh=55", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(val test 23) (val muh 55)";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations6() throws Exception {
		// gunit test on line 13
		Object retval = execParser("declarations", 13, "val test=23 val muh=55;12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(val test 23) (val muh 55) 12";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations7() throws Exception {
		// gunit test on line 14
		Object retval = execParser("declarations", 14, "val test=23 val muh=55;12 val test2=24", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations8() throws Exception {
		// gunit test on line 15
		Object retval = execParser("declarations", 15, "val test=23 val muh=55;12;val test2=24", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(val test 23) (val muh 55) 12 (val test2 24)";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations9() throws Exception {
		// gunit test on line 16
		Object retval = execParser("declarations", 16, "12;val test=23 val muh=55", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12 (val test 23) (val muh 55)";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations10() throws Exception {
		// gunit test on line 17
		Object retval = execParser("declarations", 17, "val test=23; val muh=55", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(val test 23) (val muh 55)";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations11() throws Exception {
		// gunit test on line 18
		Object retval = execParser("declarations", 18, "val d = b let in 55 end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(val d (CALL (LOOKUP_NONFIX b) (let 55)))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations12() throws Exception {
		// gunit test on line 21
		Object retval = execParser("declarations", 21, "infix 4 ::; infix +; infix 0 -; infix 3 *; infix 3 /; infixr 3 ^; 12 + 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP +) (TUPLE 12 12))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations13() throws Exception {
		// gunit test on line 22
		Object retval = execParser("declarations", 22, "infix 4 ::; infix +; infix 0 -; infix 3 *; infix 3 /; infixr 3 ^; 1 + 2 - 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP -) (TUPLE (CALL (LOOKUP +) (TUPLE 1 2)) 3))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations14() throws Exception {
		// gunit test on line 23
		Object retval = execParser("declarations", 23, "infix 4 ::; infix +; infix 0 -; infix 3 *; infix 3 /; infixr 3 ^; 1 + 2 + 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP +) (TUPLE (CALL (LOOKUP +) (TUPLE 1 2)) 3))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations15() throws Exception {
		// gunit test on line 24
		Object retval = execParser("declarations", 24, "infix 4 ::; infix +; infix 0 -; infix 3 *; infix 3 /; infixr 3 ^; 1 + 2 * 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP +) (TUPLE 1 (CALL (LOOKUP *) (TUPLE 2 3))))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations16() throws Exception {
		// gunit test on line 25
		Object retval = execParser("declarations", 25, "infix 4 ::; infix +; infix 0 -; infix 3 *; infix 3 /; infixr 3 ^; (1 + 2) * 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP *) (TUPLE (CALL (LOOKUP +) (TUPLE 1 2)) 3))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations17() throws Exception {
		// gunit test on line 26
		Object retval = execParser("declarations", 26, "infix 4 ::; infix +; infix 0 -; infix 3 *; infix 3 /; infixr 3 ^; 1 ^ 2 ^ 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP ^) (TUPLE 1 (CALL (LOOKUP ^) (TUPLE 2 3))))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations18() throws Exception {
		// gunit test on line 27
		Object retval = execParser("declarations", 27, "infix 4 ::; infix +; infix 0 -; infix 3 *; infix 3 /; infixr 3 ^; 1 * 2 * 3 ^ 4", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP *) (TUPLE (CALL (LOOKUP *) (TUPLE 1 2)) (CALL (LOOKUP ^) (TUPLE 3 4))))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations19() throws Exception {
		// gunit test on line 28
		Object retval = execParser("declarations", 28, "infix 4 ::; infix +; infix 0 -; infix 3 *; infix 3 /; infixr 3 ^; 1 * 2 ^ 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP *) (TUPLE 1 (CALL (LOOKUP ^) (TUPLE 2 3))))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations20() throws Exception {
		// gunit test on line 29
		Object retval = execParser("declarations", 29, "infix 4 ::; infix +; infix 0 -; infix 3 *; infix 3 /; infixr 3 ^; 1 + 2 :: []", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP +) (TUPLE 1 (CALL (LOOKUP ::) (TUPLE 2 LIST_LITERAL))))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations21() throws Exception {
		// gunit test on line 30
		Object retval = execParser("declarations", 30, "infix =; 12 = 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP =) (TUPLE 12 12))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations22() throws Exception {
		// gunit test on line 31
		Object retval = execParser("declarations", 31, "infix =; let nonfix = in = (12, 12) end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(let (CALL (LOOKUP_NONFIX =) (TUPLE 12 12)))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testDeclarations23() throws Exception {
		// gunit test on line 32
		Object retval = execParser("declarations", 32, "infix =; let nonfix = in = (12, 12) end; 12 = 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(let (CALL (LOOKUP_NONFIX =) (TUPLE 12 12))) (CALL (LOOKUP =) (TUPLE 12 12))";

		assertEquals("testing rule "+"declarations", expecting, actual);
	}

	public void testLetDeclaration1() throws Exception {
		// gunit test on line 35
		Object retval = execParser("letDeclaration", 35, "open klam", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(open klam)";

		assertEquals("testing rule "+"letDeclaration", expecting, actual);
	}

	public void testLetDeclaration2() throws Exception {
		// gunit test on line 36
		Object retval = execParser("letDeclaration", 36, "exception Muh", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(exception Muh)";

		assertEquals("testing rule "+"letDeclaration", expecting, actual);
	}

	public void testLetDeclaration3() throws Exception {
		// gunit test on line 37
		Object retval = execParser("letDeclaration", 37, "val x=12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(val x 12)";

		assertEquals("testing rule "+"letDeclaration", expecting, actual);
	}

	public void testLetDeclaration4() throws Exception {
		// gunit test on line 38
		Object retval = execParser("letDeclaration", 38, "val _::xs=[12]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(val (:: _ xs) (LIST_LITERAL 12))";

		assertEquals("testing rule "+"letDeclaration", expecting, actual);
	}

	public void testLetDeclaration5() throws Exception {
		// gunit test on line 39
		Object retval = execParser("letDeclaration", 39, "fun yaml _ = 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fun yaml (= _ 12))";

		assertEquals("testing rule "+"letDeclaration", expecting, actual);
	}

	public void testLetDeclaration6() throws Exception {
		// gunit test on line 40
		Object retval = execParser("letDeclaration", 40, "fun yaml _ x = x", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fun yaml (= _ x (LOOKUP_NONFIX x)))";

		assertEquals("testing rule "+"letDeclaration", expecting, actual);
	}

	public void testLetDeclaration7() throws Exception {
		// gunit test on line 41
		Object retval = execParser("letDeclaration", 41, "fun yaml _ x = x | yaml x = 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fun yaml (= _ x (LOOKUP_NONFIX x)) (= x 12))";

		assertEquals("testing rule "+"letDeclaration", expecting, actual);
	}

	public void testLetDeclaration8() throws Exception {
		// gunit test on line 42
		Object retval = execParser("letDeclaration", 42, "fun = (a, b) = 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(fun = (= (TUPLE a b) 12))";

		assertEquals("testing rule "+"letDeclaration", expecting, actual);
	}

	public void testLetDeclaration9() throws Exception {
		// gunit test on line 43
		Object retval = execParser("letDeclaration", 43, "fun m [] = 0 | m x::xs = x + m xs", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fun m (= LIST_LITERAL 0) (= (:: x xs) (CALL (CALL (CALL (LOOKUP_NONFIX x) (LOOKUP_NONFIX +)) (LOOKUP_NONFIX m)) (LOOKUP_NONFIX xs))))";

		assertEquals("testing rule "+"letDeclaration", expecting, actual);
	}

	public void testExpression1() throws Exception {
		// gunit test on line 46
		Object retval = execParser("expression", 46, "12 : int", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: 12 int)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression2() throws Exception {
		// gunit test on line 47
		Object retval = execParser("expression", 47, "12 : a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: 12 a)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression3() throws Exception {
		// gunit test on line 48
		Object retval = execParser("expression", 48, "12 : 'a -> 'b", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: 12 (-> 'a 'b))";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression4() throws Exception {
		// gunit test on line 49
		Object retval = execParser("expression", 49, "raise Muh : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (: (LOOKUP_NONFIX Muh) exn))";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression5() throws Exception {
		// gunit test on line 50
		Object retval = execParser("expression", 50, "raise Muh : exn : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (: (: (LOOKUP_NONFIX Muh) exn) exn))";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression6() throws Exception {
		// gunit test on line 51
		Object retval = execParser("expression", 51, "raise Muh : exn : exn : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (: (: (: (LOOKUP_NONFIX Muh) exn) exn) exn))";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression7() throws Exception {
		// gunit test on line 52
		Object retval = execParser("expression", 52, "(raise Muh) : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: (raise (LOOKUP_NONFIX Muh)) exn)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression8() throws Exception {
		// gunit test on line 53
		Object retval = execParser("expression", 53, "(raise Muh) : exn : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: (: (raise (LOOKUP_NONFIX Muh)) exn) exn)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testExpression9() throws Exception {
		// gunit test on line 54
		Object retval = execParser("expression", 54, "(raise Muh) : exn : exn : exn", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(: (: (: (raise (LOOKUP_NONFIX Muh)) exn) exn) exn)";

		assertEquals("testing rule "+"expression", expecting, actual);
	}

	public void testAndalsoExpression1() throws Exception {
		// gunit test on line 57
		Object retval = execParser("andalsoExpression", 57, "true andalso false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso true false)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression2() throws Exception {
		// gunit test on line 58
		Object retval = execParser("andalsoExpression", 58, "1 andalso 2 andalso 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso (andalso 1 2) 3)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression3() throws Exception {
		// gunit test on line 59
		Object retval = execParser("andalsoExpression", 59, "1 andalso (2 andalso 3)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso 1 (andalso 2 3))";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression4() throws Exception {
		// gunit test on line 60
		Object retval = execParser("andalsoExpression", 60, "1 andalso 2 orelse 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso 1 (orelse 2 3))";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression5() throws Exception {
		// gunit test on line 61
		Object retval = execParser("andalsoExpression", 61, "1 orelse 2 andalso 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso (orelse 1 2) 3)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression6() throws Exception {
		// gunit test on line 62
		Object retval = execParser("andalsoExpression", 62, "(1 andalso 2) orelse 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse (andalso 1 2) 3)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression7() throws Exception {
		// gunit test on line 63
		Object retval = execParser("andalsoExpression", 63, "1 orelse (2 andalso 3)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse 1 (andalso 2 3))";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression8() throws Exception {
		// gunit test on line 64
		Object retval = execParser("andalsoExpression", 64, "raise Muh andalso true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (andalso (LOOKUP_NONFIX Muh) true))";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testAndalsoExpression9() throws Exception {
		// gunit test on line 65
		Object retval = execParser("andalsoExpression", 65, "(raise Muh) andalso true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(andalso (raise (LOOKUP_NONFIX Muh)) true)";

		assertEquals("testing rule "+"andalsoExpression", expecting, actual);
	}

	public void testOrelseExpression1() throws Exception {
		// gunit test on line 68
		Object retval = execParser("orelseExpression", 68, "true orelse false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse true false)";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testOrelseExpression2() throws Exception {
		// gunit test on line 69
		Object retval = execParser("orelseExpression", 69, "1 orelse 2 orelse 3", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse (orelse 1 2) 3)";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testOrelseExpression3() throws Exception {
		// gunit test on line 70
		Object retval = execParser("orelseExpression", 70, "1 orelse (2 orelse 3)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse 1 (orelse 2 3))";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testOrelseExpression4() throws Exception {
		// gunit test on line 71
		Object retval = execParser("orelseExpression", 71, "raise Muh orelse true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (orelse (LOOKUP_NONFIX Muh) true))";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testOrelseExpression5() throws Exception {
		// gunit test on line 72
		Object retval = execParser("orelseExpression", 72, "(raise Muh) orelse true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(orelse (raise (LOOKUP_NONFIX Muh)) true)";

		assertEquals("testing rule "+"orelseExpression", expecting, actual);
	}

	public void testPreInfixExpression1() throws Exception {
		// gunit test on line 75
		Object retval = execParser("preInfixExpression", 75, "raise Muh", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(raise (LOOKUP_NONFIX Muh))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression2() throws Exception {
		// gunit test on line 76
		Object retval = execParser("preInfixExpression", 76, "if true then 12 else 25", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(if true 12 25)";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression3() throws Exception {
		// gunit test on line 77
		Object retval = execParser("preInfixExpression", 77, "fn _ => 26", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> _ 26))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression4() throws Exception {
		// gunit test on line 78
		Object retval = execParser("preInfixExpression", 78, "fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> 25 26) (=> _ 12))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression5() throws Exception {
		// gunit test on line 79
		Object retval = execParser("preInfixExpression", 79, "fn 12 => fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> 12 (fn (=> 25 26) (=> _ 12))))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression6() throws Exception {
		// gunit test on line 80
		Object retval = execParser("preInfixExpression", 80, "fn 12 => (fn 25 => 26) | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(fn (=> 12 (fn (=> 25 26))) (=> _ 12))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression7() throws Exception {
		// gunit test on line 83
		Object retval = execParser("preInfixExpression", 83, "12 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL 12 12)";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression8() throws Exception {
		// gunit test on line 84
		Object retval = execParser("preInfixExpression", 84, "a b", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP_NONFIX a) (LOOKUP_NONFIX b))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression9() throws Exception {
		// gunit test on line 85
		Object retval = execParser("preInfixExpression", 85, "a (12, 12)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (LOOKUP_NONFIX a) (TUPLE 12 12))";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testPreInfixExpression10() throws Exception {
		// gunit test on line 86
		Object retval = execParser("preInfixExpression", 86, "a 12 12 12 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(CALL (CALL (CALL (CALL (LOOKUP_NONFIX a) 12) 12) 12) 12)";

		assertEquals("testing rule "+"preInfixExpression", expecting, actual);
	}

	public void testAtomicExpression1() throws Exception {
		// gunit test on line 89
		Object retval = execParser("atomicExpression", 89, "op +", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP +)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression2() throws Exception {
		// gunit test on line 90
		Object retval = execParser("atomicExpression", 90, "op a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP a)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression3() throws Exception {
		// gunit test on line 91
		Object retval = execParser("atomicExpression", 91, "a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP_NONFIX a)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression4() throws Exception {
		// gunit test on line 92
		Object retval = execParser("atomicExpression", 92, "12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression5() throws Exception {
		// gunit test on line 93
		Object retval = execParser("atomicExpression", 93, "12.5", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12.5";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression6() throws Exception {
		// gunit test on line 94
		Object retval = execParser("atomicExpression", 94, "true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "true";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression7() throws Exception {
		// gunit test on line 95
		Object retval = execParser("atomicExpression", 95, "false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "false";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression8() throws Exception {
		// gunit test on line 96
		Object retval = execParser("atomicExpression", 96, "#\"a\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "#\"a\"";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression9() throws Exception {
		// gunit test on line 97
		Object retval = execParser("atomicExpression", 97, "\"abc\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "\"abc\"";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression10() throws Exception {
		// gunit test on line 98
		Object retval = execParser("atomicExpression", 98, "#1 (12, 44)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(#1 (TUPLE 12 44))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression11() throws Exception {
		// gunit test on line 99
		Object retval = execParser("atomicExpression", 99, "#1 #2 z", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(#1 (#2 (LOOKUP_NONFIX z)))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression12() throws Exception {
		// gunit test on line 100
		Object retval = execParser("atomicExpression", 100, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression13() throws Exception {
		// gunit test on line 101
		Object retval = execParser("atomicExpression", 101, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL (LOOKUP_NONFIX a))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression14() throws Exception {
		// gunit test on line 102
		Object retval = execParser("atomicExpression", 102, "[12]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression15() throws Exception {
		// gunit test on line 103
		Object retval = execParser("atomicExpression", 103, "[12.5]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12.5)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression16() throws Exception {
		// gunit test on line 104
		Object retval = execParser("atomicExpression", 104, "[true]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL true)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression17() throws Exception {
		// gunit test on line 105
		Object retval = execParser("atomicExpression", 105, "[false]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL false)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression18() throws Exception {
		// gunit test on line 106
		Object retval = execParser("atomicExpression", 106, "[#\"a\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL #\"a\")";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression19() throws Exception {
		// gunit test on line 107
		Object retval = execParser("atomicExpression", 107, "[\"abc\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL \"abc\")";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression20() throws Exception {
		// gunit test on line 108
		Object retval = execParser("atomicExpression", 108, "(ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP_NONFIX ert)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression21() throws Exception {
		// gunit test on line 109
		Object retval = execParser("atomicExpression", 109, "(1, ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(TUPLE 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression22() throws Exception {
		// gunit test on line 110
		Object retval = execParser("atomicExpression", 110, "(1; ert, 12)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression23() throws Exception {
		// gunit test on line 111
		Object retval = execParser("atomicExpression", 111, "(1; ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(EXPRESSIONS 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression24() throws Exception {
		// gunit test on line 112
		Object retval = execParser("atomicExpression", 112, "let in 12 end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let 12)";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression25() throws Exception {
		// gunit test on line 113
		Object retval = execParser("atomicExpression", 113, "let exception Muh in raise Muh end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let (exception Muh) (raise (LOOKUP_NONFIX Muh)))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression26() throws Exception {
		// gunit test on line 114
		Object retval = execParser("atomicExpression", 114, "let exception Muh exception Gert in raise Muh end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let (exception Muh) (exception Gert) (raise (LOOKUP_NONFIX Muh)))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression27() throws Exception {
		// gunit test on line 115
		Object retval = execParser("atomicExpression", 115, "let exception Muh 12 in 12 end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression28() throws Exception {
		// gunit test on line 116
		Object retval = execParser("atomicExpression", 116, "let 12 val test = 55 in end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testAtomicExpression29() throws Exception {
		// gunit test on line 117
		Object retval = execParser("atomicExpression", 117, "let fun m [] = 0 | m x::xs = x + m xs val tester = m [1, 2, 3, 4] in tester end", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(let (fun m (= LIST_LITERAL 0) (= (:: x xs) (CALL (CALL (CALL (LOOKUP_NONFIX x) (LOOKUP_NONFIX +)) (LOOKUP_NONFIX m)) (LOOKUP_NONFIX xs)))) (val tester (CALL (LOOKUP_NONFIX m) (LIST_LITERAL 1 2 3 4))) (LOOKUP_NONFIX tester))";

		assertEquals("testing rule "+"atomicExpression", expecting, actual);
	}

	public void testMatch1() throws Exception {
		// gunit test on line 120
		Object retval = execParser("match", 120, "_ => 26", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> _ 26)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch2() throws Exception {
		// gunit test on line 121
		Object retval = execParser("match", 121, "25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(=> 25 26) (=> _ 12)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch3() throws Exception {
		// gunit test on line 122
		Object retval = execParser("match", 122, "12 => fn 25 => 26 | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> 12 (fn (=> 25 26) (=> _ 12)))";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testMatch4() throws Exception {
		// gunit test on line 123
		Object retval = execParser("match", 123, "12 => (fn 25 => 26) | _ => 12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(=> 12 (fn (=> 25 26))) (=> _ 12)";

		assertEquals("testing rule "+"match", expecting, actual);
	}

	public void testType1() throws Exception {
		// gunit test on line 126
		Object retval = execParser("type", 126, "int", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "int";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType2() throws Exception {
		// gunit test on line 127
		Object retval = execParser("type", 127, "int list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list int)";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType3() throws Exception {
		// gunit test on line 128
		Object retval = execParser("type", 128, "int list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list (list int))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType4() throws Exception {
		// gunit test on line 129
		Object retval = execParser("type", 129, "list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType5() throws Exception {
		// gunit test on line 130
		Object retval = execParser("type", 130, "'12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "'12";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType6() throws Exception {
		// gunit test on line 131
		Object retval = execParser("type", 131, "('a -> 'b) -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> (-> 'a 'b) (-> (list 'a) (list 'b)))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType7() throws Exception {
		// gunit test on line 132
		Object retval = execParser("type", 132, "'a -> 'b -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> 'a (-> 'b (-> (list 'a) (list 'b))))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testTuple1() throws Exception {
		// gunit test on line 135
		Object retval = execParser("tuple", 135, "()", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "TUPLE";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple2() throws Exception {
		// gunit test on line 136
		Object retval = execParser("tuple", 136, "(ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LOOKUP_NONFIX ert)";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple3() throws Exception {
		// gunit test on line 137
		Object retval = execParser("tuple", 137, "(1, ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(TUPLE 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple4() throws Exception {
		// gunit test on line 138
		Object retval = execParser("tuple", 138, "(1; ert, 12)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testTuple5() throws Exception {
		// gunit test on line 139
		Object retval = execParser("tuple", 139, "(1; ert)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(EXPRESSIONS 1 (LOOKUP_NONFIX ert))";

		assertEquals("testing rule "+"tuple", expecting, actual);
	}

	public void testList1() throws Exception {
		// gunit test on line 142
		Object retval = execParser("list", 142, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList2() throws Exception {
		// gunit test on line 143
		Object retval = execParser("list", 143, "[1]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 1)";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList3() throws Exception {
		// gunit test on line 144
		Object retval = execParser("list", 144, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL (LOOKUP_NONFIX a))";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testList4() throws Exception {
		// gunit test on line 145
		Object retval = execParser("list", 145, "[1,2]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 1 2)";

		assertEquals("testing rule "+"list", expecting, actual);
	}

	public void testAtomicPattern1() throws Exception {
		// gunit test on line 148
		Object retval = execParser("atomicPattern", 148, "a", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "a";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern2() throws Exception {
		// gunit test on line 149
		Object retval = execParser("atomicPattern", 149, "_", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "_";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern3() throws Exception {
		// gunit test on line 150
		Object retval = execParser("atomicPattern", 150, "12", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern4() throws Exception {
		// gunit test on line 151
		Object retval = execParser("atomicPattern", 151, "12.5", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "12.5";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern5() throws Exception {
		// gunit test on line 152
		Object retval = execParser("atomicPattern", 152, "true", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "true";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern6() throws Exception {
		// gunit test on line 153
		Object retval = execParser("atomicPattern", 153, "false", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "false";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern7() throws Exception {
		// gunit test on line 154
		Object retval = execParser("atomicPattern", 154, "#\"a\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "#\"a\"";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern8() throws Exception {
		// gunit test on line 155
		Object retval = execParser("atomicPattern", 155, "\"abc\"", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "\"abc\"";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern9() throws Exception {
		// gunit test on line 156
		Object retval = execParser("atomicPattern", 156, "[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "LIST_LITERAL";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern10() throws Exception {
		// gunit test on line 157
		Object retval = execParser("atomicPattern", 157, "[::]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "(LIST_LITERAL ::)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern11() throws Exception {
		// gunit test on line 158
		Object retval = execParser("atomicPattern", 158, "[_]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL _)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern12() throws Exception {
		// gunit test on line 159
		Object retval = execParser("atomicPattern", 159, "[a]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL a)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern13() throws Exception {
		// gunit test on line 160
		Object retval = execParser("atomicPattern", 160, "[12]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern14() throws Exception {
		// gunit test on line 161
		Object retval = execParser("atomicPattern", 161, "[12.5]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL 12.5)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern15() throws Exception {
		// gunit test on line 162
		Object retval = execParser("atomicPattern", 162, "[true]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL true)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern16() throws Exception {
		// gunit test on line 163
		Object retval = execParser("atomicPattern", 163, "[false]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL false)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern17() throws Exception {
		// gunit test on line 164
		Object retval = execParser("atomicPattern", 164, "[#\"a\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL #\"a\")";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern18() throws Exception {
		// gunit test on line 165
		Object retval = execParser("atomicPattern", 165, "[\"abc\"]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(LIST_LITERAL \"abc\")";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern19() throws Exception {
		// gunit test on line 166
		Object retval = execParser("atomicPattern", 166, "()", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "TUPLE";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern20() throws Exception {
		// gunit test on line 167
		Object retval = execParser("atomicPattern", 167, "(a)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "a";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern21() throws Exception {
		// gunit test on line 168
		Object retval = execParser("atomicPattern", 168, "(x, y, _)", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(TUPLE x y _)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern22() throws Exception {
		// gunit test on line 169
		Object retval = execParser("atomicPattern", 169, "1::2::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: 1 (:: 2 LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern23() throws Exception {
		// gunit test on line 170
		Object retval = execParser("atomicPattern", 170, "(1::[])::[3]::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: (:: 1 LIST_LITERAL) (:: (LIST_LITERAL 3) LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern24() throws Exception {
		// gunit test on line 171
		Object retval = execParser("atomicPattern", 171, "_::xs", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: _ xs)";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}

	public void testAtomicPattern25() throws Exception {
		// gunit test on line 172
		Object retval = execParser("atomicPattern", 172, "(1, \"abc\")::(3, \"\")::[]", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(:: (TUPLE 1 \"abc\") (:: (TUPLE 3 \"\") LIST_LITERAL))";

		assertEquals("testing rule "+"atomicPattern", expecting, actual);
	}



}