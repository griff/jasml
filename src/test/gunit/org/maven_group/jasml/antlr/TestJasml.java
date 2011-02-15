
import org.antlr.gunit.gUnitBaseTest;

public class TestJasml extends gUnitBaseTest {
	
	public void setUp() {
		this.packagePath = "./org/maven_group/jasml/antlr";
		this.lexerPath = "org.maven_group.jasml.antlr.JasmlLexer";
		this.parserPath = "org.maven_group.jasml.antlr.JasmlParser";
	}

	public void testType1() throws Exception {
		// gunit test on line 8
		Object retval = execParser("type", 8, "int", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.STRING, retval);
		Object expecting = "int";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType2() throws Exception {
		// gunit test on line 9
		Object retval = execParser("type", 9, "int list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list int)";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType3() throws Exception {
		// gunit test on line 10
		Object retval = execParser("type", 10, "int list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(list (list int))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType4() throws Exception {
		// gunit test on line 11
		Object retval = execParser("type", 11, "list list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.FAIL, retval);
		Object expecting = "FAIL";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType5() throws Exception {
		// gunit test on line 12
		Object retval = execParser("type", 12, "('a -> 'b) -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> (-> (-> ' a ' b) (list ' a)) (list ' b))";

		assertEquals("testing rule "+"type", expecting, actual);
	}

	public void testType6() throws Exception {
		// gunit test on line 13
		Object retval = execParser("type", 13, "'a -> 'b -> 'a list -> 'b list", false);
		Object actual = examineExecResult(org.antlr.gunit.gUnitParser.AST, retval);
		Object expecting = "(-> (-> (-> ' a ' b) (list ' a)) (list ' b))";

		assertEquals("testing rule "+"type", expecting, actual);
	}



}