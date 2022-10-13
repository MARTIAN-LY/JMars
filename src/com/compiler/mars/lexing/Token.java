package com.compiler.mars.lexing;

/**
 *@Author Martian
 *
 **/

public class Token {
    final TokenType type;
    public final String lexeme;
    /** literal values : like numbers , strings
     *  convert them to objects so that we can use it.
     * */
    final Object literal;
    /** The lexeme's position. */
    final int line;

    public Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

//    public static Token create(int line, TokenType type) {
//
//        return new Token(
//                type,
//                tokenTypes.get(type),
//                null,
//                line
//        );
//    }
//
//    public static Token create(int line, TokenType type, Object literal) {
//        return new Token(
//                type,
//                tokenTypes.get(type),
//                literal,
//                line
//        );
//    }

    @Override
    public String toString() {
        return type + " " + lexeme + " " + literal;
    }

}
