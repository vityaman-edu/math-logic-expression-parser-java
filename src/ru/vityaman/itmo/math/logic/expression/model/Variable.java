package ru.vityaman.itmo.math.logic.expression.model;

public final class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public void appendAsString(StringBuilder builder) {
        builder.append(name);
    }
}
