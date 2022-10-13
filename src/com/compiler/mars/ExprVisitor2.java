package com.compiler.mars;

/**
 *@Author Martian
 *
 **/

public interface ExprVisitor2<T> {
    T visit(Expr.Binary binary);

    T visit(Expr.Unary unary);

    T visit(Expr.Literal literal);

    T visit(Expr.Grouping grouping);
}
