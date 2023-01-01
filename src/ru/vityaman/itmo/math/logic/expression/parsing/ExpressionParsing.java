package ru.vityaman.itmo.math.logic.expression.parsing;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import ru.vityaman.itmo.math.logic.expression.model.Expression;
import ru.vityaman.itmo.math.logic.expression.parsing.antlr.generated.ExpressionLexer;
import ru.vityaman.itmo.math.logic.expression.parsing.antlr.generated.ExpressionParser;

public final class ExpressionParsing {
    @SuppressWarnings("deprecation")
    public static Expression readFrom(InputStream input) 
        throws IOException {
        FastReader reader = new FastReader(input);
        ANTLRInputStream antlrInput = 
            new ANTLRInputStream(reader.next());
        ExpressionLexer lexer = new ExpressionLexer(antlrInput);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        return parser.file().entireExpression;
    }

    private static class FastReader {
        BufferedReader reader;
        StringTokenizer tokenizer;
  
        public FastReader(InputStream input)
        {
            reader = 
                new BufferedReader(
                    new InputStreamReader(input)
                );
        }
  
        String next()
        {
            while (tokenizer == null || !tokenizer.hasMoreElements()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                }
                catch (IOException ignored) {
                    // Hope for the best
                }
            }
            return tokenizer.nextToken();
        }
    }
}
