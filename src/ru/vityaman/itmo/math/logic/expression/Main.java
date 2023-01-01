package ru.vityaman.itmo.math.logic.expression;

import java.io.IOException;
import ru.vityaman.itmo.math.logic.expression.model.Expression;
import ru.vityaman.itmo.math.logic.expression.parsing.ExpressionParsing;;

public class Main {
    
    public static void main(String[] args) throws IOException {
        Expression expression = 
            ExpressionParsing.readFrom(System.in);
        StringBuilder repr = new StringBuilder();
        expression.appendAsString(repr);
        System.out.println(repr);
    }
}
