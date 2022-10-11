package com.compiler.mars;

/**
 *@Author Martian
 *
 **/

public class Token {
    final TokenType type;
    final String lexeme;
    /** literal values : like numbers \ strings
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

    @Override
    public String toString() {
        return type + " " + lexeme + " " + literal;
    }


    public static void main(String[] args) {
        String substring = "asdfgh".substring(1, 1);
    }
}
