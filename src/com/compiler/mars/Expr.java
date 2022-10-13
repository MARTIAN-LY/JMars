package com.compiler.mars;

import com.compiler.mars.lexing.Token;

abstract class Expr {

	abstract <T> T accept(ExprVisitor<T> visitor);
	abstract <T> T accept(ExprVisitor2<T> visitor);

	static class Binary extends Expr{
		final Expr left;
		final Token operator;
		final Expr right;
		Binary(Expr left,Token operator,Expr right) {
			this.left = left;
			this.operator = operator;
			this.right = right;
		}

		@Override
		<T> T accept(ExprVisitor<T> visitor) {
			return visitor.visit(this);
		}

		@Override
		<T> T accept(ExprVisitor2<T> visitor) {
			return visitor.visit(this);
		}
	}
	static class Grouping extends Expr{
		final Expr expression;
		Grouping(Expr expression) {
			this.expression = expression;
		}

		@Override
		<T> T accept(ExprVisitor<T> visitor) {
			return visitor.visit(this);
		}

		@Override
		<T> T accept(ExprVisitor2<T> visitor) {
			return visitor.visit(this);
		}
	}
	static class Literal extends Expr{
		final Object value;
		Literal(Object value) {
			this.value = value;
		}

		@Override
		<T> T accept(ExprVisitor<T> visitor) {
			return visitor.visit(this);
		}

		@Override
		<T> T accept(ExprVisitor2<T> visitor) {
			return visitor.visit(this);
		}
	}
	static class Unary extends Expr{
		final Token operator;
		final Expr right;
		Unary(Token operator,Expr right) {
			this.operator = operator;
			this.right = right;
		}

		@Override
		<T> T accept(ExprVisitor<T> visitor) {
			return visitor.visit(this);
		}

		@Override
		<T> T accept(ExprVisitor2<T> visitor) {
			return visitor.visit(this);
		}
	}
}

