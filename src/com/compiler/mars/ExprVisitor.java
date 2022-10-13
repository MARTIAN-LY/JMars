package com.compiler.mars;
/**
 *@Author Martian
 *
 **/
interface ExprVisitor<T> {
    T visit(Expr.Binary binary);

    T visit(Expr.Unary unary);

    T visit(Expr.Literal literal);

    T visit(Expr.Grouping grouping);

}
