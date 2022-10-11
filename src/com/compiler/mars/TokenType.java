package com.compiler.mars;

/**
 *@Author Martian
 *
 **/

public enum TokenType {


    // Singe-character tokens.
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

    // One or two character tokens
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,
    AND, AND_LOGIC,
    OR, OR_LOGIC,

    // Literals
    IDENTIFIER, STRING, NUMBER,

    // Keywords.
    TRUE, FALSE, VAR, FUN, FOR, WHILE,
    IF, ELSE, NIL, CLASS,
    PRINT, RETURN, SUPER, THIS,

    EOF

}
