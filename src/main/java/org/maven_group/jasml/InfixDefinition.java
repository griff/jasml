package org.maven_group.jasml;

/**
 * @author griff
 */
public class InfixDefinition
{
    private String name;
    private int precedence;
    private boolean leftAssociative;

    public InfixDefinition(String name, int precedence, boolean leftAssociative)
    {
        this.name = name;
        this.precedence = precedence;
        this.leftAssociative = leftAssociative;
    }

    public String getName()
    {
        return name;
    }

    public int getPrecedence()
    {
        return precedence;
    }

    public boolean isLeftAssociative()
    {
        return leftAssociative;
    }
}
