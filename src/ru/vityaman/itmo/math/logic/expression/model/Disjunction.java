package ru.vityaman.itmo.math.logic.expression.model;

public final class Disjunction implements Expression {
    private final Expression left;
    private final Expression right;

    public Disjunction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public void appendAsString(StringBuilder builder) {
        builder.append("(|,");
        left.appendAsString(builder);
        builder.append(',');
        right.appendAsString(builder);
        builder.append(')');
    }
}
