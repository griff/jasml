package org.maven_group.jasml;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.junit.Test;
import org.maven_group.jasml.antlr.JasmlLexer;
import org.maven_group.jasml.antlr.JasmlParser;

/**
 * @author griff
 */
public class SimpleTest
{
    protected JasmlParser getParser(String exp)
    {
        JasmlLexer lexer = new JasmlLexer(new ANTLRStringStream(exp) );
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new JasmlParser( tokens );
    }

    @Test
    public void expression() throws Exception
    {
        JasmlParser p = getParser("infix 2 +; infix 2 -; infix 3 *; infix 3 /; infixr 3 ^; 1 + 2 - 3");
        Object tree = p.startRule().getTree();
    }
}
