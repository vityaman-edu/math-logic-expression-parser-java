package ru.vityaman.itmo.math.logic.expression.model;

public final class Negation implements Expression {
    private final Expression child;

    public Negation(Expression child) {
        this.child = child;
    }

    @Override
    public void appendAsString(StringBuilder builder) {
        builder.append("(!");
        child.appendAsString(builder);
        builder.append(')');
    }
}
