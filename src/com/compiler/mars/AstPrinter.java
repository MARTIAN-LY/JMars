package com.compiler.mars;

import com.compiler.mars.lexing.Token;
import com.compiler.mars.lexing.TokenType;

/**
 *@Author Martian
 *
 **/

public class AstPrinter implements ExprVisitor<String> {

    // Print our abstract syntax tree.
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visit(Expr.Binary binary) {
        return parenthesize(binary.operator.lexeme, binary.left, binary.right);
    }

    @Override
    public String visit(Expr.Unary unary) {
        return parenthesize(unary.operator.lexeme, unary.right);
    }

    @Override
    public String visit(Expr.Literal literal) {
        if (literal.value == null) {
            return "nil";
        }
        return literal.value.toString();
    }

    @Override
    public String visit(Expr.Grouping grouping) {
        return parenthesize("group", grouping.expression);
    }

    // Convert the syntax tree to groups with names( maybe Polish Notation? ).
    String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            // Recurse !!!
            builder.append(" ").append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }


    public static void main(String[] args) {
        Expr.Binary binary = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)
                ),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(45.67)
                )
        );
        AstPrinter printer = new AstPrinter();
        System.out.println(printer.print(binary));
    }
}
