package com.compiler.mars;

import com.compiler.mars.lexing.Token;
import com.compiler.mars.lexing.TokenType;

/**
 *@Author Martian
 *
 **/

public class AstPrinter2 implements ExprVisitor2<String> {


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
        new AstPrinter2().print(binary);
    }

    // Convert the syntax tree to Reverse Polish Notation.
    void print(Expr expr){
        System.out.println(expr.accept(this));
    }
    @Override
    public String visit(Expr.Binary binary) {
        return binary.left.accept(this) +
                " " +
                binary.right.accept(this) +
                " " +
                binary.operator.lexeme +
                " ";
    }

    @Override
    public String visit(Expr.Unary unary) {
        return unary.right.accept(this) +
                " " +
                unary.operator.lexeme +
                " ";
    }

    @Override
    public String visit(Expr.Literal literal) {
        if (literal.value == null) {
            return "nil ";
        }
        return literal.value.toString();
    }

    @Override
    public String visit(Expr.Grouping grouping) {
        return grouping.expression.accept(this);
    }
}
