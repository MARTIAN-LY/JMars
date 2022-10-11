package com.compiler.mars;

/**
 *@Author Martian
 *
 **/


abstract class Expr {
    final Expr left;
    final Token operator;
    final Expr right;


    Expr(Expr left, Token operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    static class Binary extends Expr{
        Binary(Expr left, Token operator, Expr right) {
            super(left, operator, right);
        }
    }

    static class Unary extends  Expr{
        Unary(Expr left, Token operator, Expr right) {
            super(left, operator, right);
        }
    }

    static class Literal extends Expr{
        Literal(Expr left, Token operator, Expr right) {
            super(left, operator, right);
        }
    }

    static class Parentheses extends Expr{
        Parentheses(Expr left, Token operator, Expr right) {
            super(left, operator, right);
        }
    }

}
